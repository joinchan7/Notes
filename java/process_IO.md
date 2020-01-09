## 处理流

> 采用设计模式中的**装饰器模式**:
>
> 1.  抽象组件:需要装饰的抽象对象(接口或抽象父类)
> 2.  具体组件:需要装饰的对象
> 3.  抽象装饰类:包含了对抽象组件的引用以及装饰着共有的方法
> 4.  具体装饰类:被装饰的对象

### 字节缓冲流

> BufferedInputStream&BufferedOutputStream
> (默认 8k)

1. 提升性能
2. 最底层一定是节点流
3. 释放资源时,只需释放最外层(内部会从里到外依次释放)

- 使用(直接套用即可):

```java
InputStream is = new BufferedInputStream(new FileInputStream(src));
```

### 字符缓冲流

> BufferedReade&BufferedWriter

1. 要使用新方法`readLine()`和`nextLine()`,不能发生多态
2. 读取**下一行:**`String line = br.readLine()`
3. **换行:**`bw.newLine()` **条件:**`while (line != null)`

```java
BufferedReader br = new BufferedReader(new FileReader(src));
BufferedWriter bw = new BufferedWriter(new FileWriter(dest));
```

### 转换流

> InputStreamReader&OutputStreamWriter
> 读取:字节-->字符
> 写出:字符-->字节

1. 字符流与字节流的桥梁,处理文本更方便
2. 可以指定字符集编码,解码
