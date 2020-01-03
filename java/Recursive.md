## 递归

- 本质:方法自己调用自己
- 递归头:何时结束递归
- 递归体:重复调用
- (一般可用循环代替,优先使用循环,效率高)

示例:

- 打印一到十

```java
public class DirDemo3 {
    public static void main(String[] args) {
        printTen(1);
    }

    public static void printTen(int n) {
        // 递归头:结束递归条件
        if (n > 10) {
            return;
        }
        System.out.println(n);
        // 递归体:自己调用自己
        printTen(n + 1);
    }
}
```
