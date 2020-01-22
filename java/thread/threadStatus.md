## 线程的状态

### 五种状态

1. 新生状态 new Thread()
   - new
2. 就绪状态 start(), 阻塞解除, yield(), jvm 内部
   - runnable
3. 运行状态 cpu 调度
   - runnable
4. 阻塞状态 sleep(占用资源), wait(不占用资源), join(插队), read & write
   - sleep --> timed_waiting waiting
   - join --> timed_waiting waiting
   - wait & read & write --> blocked
5. 死亡状态 正常结束,强制中断 stop():不推荐使用
   - terminate

#### 注意:

1. 运行时,线程阻塞后进入就绪状态
2. 线程死亡后不能再次开启

### 选择

1. 线程之间存在**改**的行为: **保证线程安全**
2. 线程之间只是读的行为: 不用保证线程安全
