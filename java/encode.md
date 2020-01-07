## 编码和解码

- GBK Unicode (UTF-8:变长 UTF-16:定长)
- 编码:字符-->字节

```java
String msg = "this is msg";
byte[] data = msg.getBytes();
```

- 解码:字节-->字符

```java
byte[] data = new byte[10];
String msg = new byte(data, 0, data.length);
```
