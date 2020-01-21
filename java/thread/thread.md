### 线程和进程

> 进程:资源分配的单位
> 线程:调度和执行的单位

### 多线程的优点

1. 高可用
2. 高性能
3. 高并发

- 每个线程在自己的工作内存交互,工作内存与主存交互

### 创建线程

1. 继承 Thread 类
2. 实现 Runnable 接口(推荐)
   > 由于 java 无法多继承,尽量少用继承,多用实现
3. 实现 Callable 接口(JUC 并发)

### 具体方法

1. start() 不是立即执行,cpu 调到才执行
2. run() 不是开辟多线程,而是直接调用

### 推荐

- 避免单继承的局限性,优先使用接口
  方便共享资源

### 了解 callable 方法

callable 方法执行步骤:

```java
// 创建执行服务
ExecutorService ser = Executors.newFixedThreadPool(3);
// 提交执行
Future<Boolean> result1 = ser.submit(cd1);
Future<Boolean> result2 = ser.submit(cd2);
Future<Boolean> result3 = ser.submit(cd3);
// 获取结果
boolean r1 = result1.get();
boolean r2 = result2.get();
boolean r3 = result3.get();
System.out.println(r3);
// 关闭服务
ser.shutdown();
```

### lambda 的使用

多 Thread 中 lambda 的使用:

```java
/**
 * 多Thread中lambda的使用
 */
public class MyLambda4 {
    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("lambda1");
            }
        }).start();

        new Thread(() -> System.out.println("lambda2")).start();
    }
}
```
