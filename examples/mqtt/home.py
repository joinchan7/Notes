import context
from aliyun_iot_device.mqtt import Client as IOT
import time
import re
import RPi.GPIO as GPIO
import smbus


def on_connect(client, userdata, flags, rc):
    print('subscribe')
    client.subscribe(qos=1)


def on_message(client, userdata, msg):
    print('receive message')
    print(str(msg.payload))
    
    val=re.findall(r'\"Lock_control\":\d', str(msg.payload))
    if(val==[]):
        led_control(msg)
        
    else:
        lock_control(msg)


def lock_control(msg):
    # 门锁状态控制
    # val=json.loads(msg.payload)
    # lock_status=val['params']('Lock_control')
    val = re.findall(r'\"Lock_control\":\d', str(msg.payload))
    lock_status = int(str(val)[17])

    topic = '/sys/'+PRODUCE_KEY+'/' + DEVICE_NAME+'/thing/event/property/post'
    payload_json = {
        'id': int(time.time()),
        'params': {
            'Lock_control': lock_status
        },
        'method': "thing.event.property.post"
    }
    iot.publish(topic=topic, payload=str(payload_json), qos=1)

    GPIO.setwarnings(False)
    GPIO.setmode(GPIO.BCM)
    In_Pin = 21
    GPIO.setup(In_Pin, GPIO.OUT, initial=GPIO.LOW)
    p = GPIO.PWM(In_Pin, 50)
    p.start(0)

    if(lock_status == 0):
        r = 0
    else:
        r = 180
    
    p.ChangeDutyCycle(2.5+r/360*20)
    time.sleep(1)

def led_control(msg):
    
    val = re.findall(r'\"LightLuminance\":\d*}', str(msg.payload))
    print(val)
    if(int(str(val)[19])!=0):
        i = int(str(val)[19]+str(val)[20])
    else:
        i = int(str(val)[19])
    print(i)
    GPIO.setwarnings(False)
    GPIO.setmode(GPIO.BCM)
    GPIO.setup(14, GPIO.OUT)
    
    pwm = GPIO.PWM(14, 80)
    pwm.start(0)
    pwm.ChangeDutyCycle(i)
    time.sleep(0.02)
    
    

def ht_upload():
    i2c = smbus.SMBus(1)
    addr = 0x44
    i2c.write_byte_data(addr, 0x23, 0x34)
    time.sleep(0.5)
    i2c.write_byte_data(addr, 0xe0, 0x0)
    data = i2c.read_i2c_block_data(addr, 0x0, 6)
    rawT = ((data[0]) << 8) | (data[1])
    rawR = ((data[3]) << 8) | (data[4])
    temp = round(-45 + rawT * 175 / 65535, 2)  # float
    hum = round(100 * rawR / 65535)  # int
    time.sleep(3)

    topic = '/sys/'+PRODUCE_KEY+'/' + DEVICE_NAME+'/thing/event/property/post'
    payload_json = {
        'id': int(time.time()),
        'params': {
            'CurrentHumidity': hum,
            'CurrentTemperature': temp
        },
        'method': "thing.event.property.post"
    }
    return topic, payload_json


PRODUCE_KEY = "a1XNizxeXZf"
DEVICE_NAME = "my_home"
DEVICE_SECRET = "velAIa2mV8eZKn6T2fucvpRuJY5EUdVT"

iot = IOT((PRODUCE_KEY, DEVICE_NAME, DEVICE_SECRET))


iot.on_connect = on_connect
iot.on_message = on_message

iot.connect()

iot.loop_start()
while True:
    topic, payload_json = ht_upload()
    iot.publish(topic=topic, payload=str(payload_json), qos=1)
    time.sleep(5)
