import context
from aliyun_iot_device.mqtt import Client as IOT
import json
import multiprocessing
import time
import smbus

options = {
    'productKey': 'a1XNizxeXZf',
    'deviceName': 'sht30',
    'deviceSecret': 'DHiiAOERvkmoS29rQU2kIDW6WMiOr9OU',
    'host': 'iot-as-mqtt.cn-shanghai.aliyuncs.com'
}


def GetSTH():
    i2c = smbus.SMBus(1)
    addr = 0x44
    i2c.write_byte_data(addr, 0x23, 0x34)
    time.sleep(0.5)
    i2c.write_byte_data(addr, 0xe0, 0x0)
    data = i2c.read_i2c_block_data(addr, 0x0, 6)
    rawT = ((data[0]) << 8) | (data[1])
    rawR = ((data[3]) << 8) | (data[4])
    temp = -45 + rawT * 175 / 65535

    print("Temperature in Celsius is : %.2f ℃" % temp)
    RH = 100 * rawR / 65535
    print("Relative Humidity is : %.2f %%RH" % RH)
    time.sleep(3)
    mytemp = '%.2f' % temp
    myhum = '%d' % RH
    return mytemp, myhum


host = options['productKey'] + '.' + options['host']

# The callback for when a PUBLISH message is received from the server.


def on_message(client, userdata, msg):
    #topic = '/' + productKey + '/' + deviceName + '/update'
    # {"method":"thing.service.property.set","id":"169885527","params":{"LED":1},"version":"1.0.0"}
    print(msg.payload)
    #led = setjson['params']['LED']
    #GPIO.output(led_pin,(GPIO.HIGH if led==1 else GPIO.LOW ))


def on_connect(client, userdata, flags_dict, rc):
    print("Connected with result code " + str(rc))


def on_disconnect(client, userdata, flags_dict, rc):
    print("Disconnected.")


def worker(client):
    topic = '/sys/'+options['productKey']+'/' + \
        options['deviceName']+'/thing/event/property/post'
    while True:
        time.sleep(5)
        T, H = GetSTH()
        print('T=', T, 'H=', H)

        if T != 0 or H != 0:
            payload_json = {
                'id': int(time.time()),
                'params': {
                    'CurrentHumidity': int(H),
                    'CurrentTemperature': float(T)
                },
                'method': "thing.event.property.post"
            }

            print('send data to iot server: ' + str(payload_json))

            client.publish(topic=topic, payload=str(payload_json))


if __name__ == '__main__':
    client = IOT(
        (options['productKey'], options['deviceName'], options['deviceSecret']))
    client.on_connect = on_connect
    client.on_disconnect = on_disconnect
    client.on_message = on_message
    client.connect()

    p = multiprocessing.Process(target=worker, args=(client,))
    p.start()
    client.loop_forever()
