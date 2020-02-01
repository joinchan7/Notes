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

1. **提升性能(一般建议都加上)**
2. 最底层一定是节点流
3. 释放资源时,只需释放最外层(内部会从里到外依次释放)

- 使用(直接套用即可):

```java
InputStream is = new BufferedInputStream(new FileInputStream(src));
```

### 字符缓冲流

> BufferedReader&BufferedWriter

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

### 数据流

> DataOutputStream&DataInputStream

1. 先写出后读取
2. 读取顺序必须和写出顺序保持一致

### 对象流

> ObjectInputStream&ObjectOutputStream
> input: (反序列化 deserialization)
> output: (序列化 serialization)
> 序列化又叫持久化

1. 先写出后读取
2. 读取顺序必须和写出顺序保持一致
3. 不是所有对象**都能**序列化 (通行证 实现 **Serializable** 接口才能序列化)
   不是所有对象**都要**序列化(transient:透明)
   `java // transient ==> 指:该数据不需要序列化`
   `private transient String name;`
   **ps**:类型转换: `if(obj instanceof Class)`

```java
// 使用if(obj instanceof Class)防止发生错误
if (employee instanceof Emp) {
    Emp empObj = (Emp) employee;
    System.out.println(empObj.getName() + "==>" + empObj.getSalary());
}
```

### 打印流

> PrintStream&PrintWriter

1. System.out 也是 PrintStream 流的对象
2. 重定向 PrintStream 输出端

```java
// 重定向输出端,参数为打印流 PrintStream 的对象, true:自动刷新
System.setOut(new PrintStream(new BufferedOutputStream(new FileOutputStream("IO_test/src/com/chan/ps.txt")), true);
);
System.out.println(10);
// 重定向回控制台 FileDescriptor.out
System.setOut(new PrintStream(new BufferedOutputStream(new FileOutputStream(FileDescriptor.out)), true));
System.out.println("back");
```

### 随机读取和写入流

> RandomAccessFile( ,"r")&RandomAccessFile( ,"rw")

```java
// raf是 RandomAccessFile 的一个实例对象
raf.seek(2);
// 指定起始位置,读取剩余所有内容(r模式)
```

### 序列流

> SequenceInputStream&SequenceOutputStream

- 用于批量处理流
- 通常与 Vevtor 一起使用

```java
Vector<InputStream> iv = new Vector<>();
SequenceInputStream sis;
for (String path : destPaths) {
    iv.add(new BufferedInputStream(new FileInputStream(path)));
}
sis = new SequenceInputStream(iv.elements());
```
