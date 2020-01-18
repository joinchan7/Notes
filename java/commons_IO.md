## commons_IO

> FileUtils

### 统计大小

```java
sizeOf(file);
sizeOf(dictionary);
```

### 列出子孙级

```java
// 列出文件夹下一层(非空)
Collection<File> files = FileUtils.listFiles(new File("path"), EmptyFileFilter.NOT_EMPTY, null);
// 列出文件夹所有层(非空)
files = FileUtils.listFiles(new File("path"), EmptyFileFilter.NOT_EMPTY, DirectoryFileFilter.INSTANCE);
// 列出文件夹所有层(后缀名为java)
files = FileUtils.listFiles(new File("commons_IO"), new SuffixFileFilter("java"), DirectoryFileFilter.INSTANCE);
// 列出文件夹所有层(非空,且后缀名为java)
files = FileUtils.listFiles(new File("commons_IO"), FileFilterUtils.or(new SuffixFileFilter("java"), EmptyFileFilter.EMPTY),DirectoryFileFilter.INSTANCE);
```

### 读取

```java
// 读取文件
String msg = FileUtils.readFileToString(new File("commons_IO/empty.txt"), "UTF-8");
// 文件读取到字符
byte[] data = FileUtils.readFileToByteArray(new File("commons_IO/empty.txt"));
// 逐行读取
List<String> msg2 = FileUtils.readLines(new File("commons_IO/empty.txt"), "UTF-8");

// 增强for循环迭代
for (String s : msg2) {
    System.out.println(s);
}
// 迭代器迭代
LineIterator it = FileUtils.lineIterator(new File("commons_IO/empty.txt"));
while (it.hasNext()) {
    System.out.println(it.nextLine());
}
```

### 写出

```java
// 写出文件
FileUtils.write(new File("commons_IO/happy.txt"), "i am happy\r\n", "UTF-8");
FileUtils.writeStringToFile(new File("commons_IO/happy.txt"), "i am happy,too\r\n", "UTF-8", true);
FileUtils.writeByteArrayToFile(new File("commons_IO/happy.txt"),"i am happy,too,too\r\n".getBytes(StandardCharsets.UTF_8), true);
// 写出列表
List<String> data = new ArrayList<>();
data.add("ni");
data.add("wo");
data.add("ta");
FileUtils.writeLines(new File("commons_IO/happy.txt"), data, "---", true);
```

### 拷贝

```java
// 复制文件
FileUtils.copyFile(new File("commons_IO/linux.png"),new File("commons_IO/copy.png"));

// 拷贝文件到目录
FileUtils.copyToDirectory(new File("commons_IO/linux.png"),new File("commons_IO/lib"));

// 拷贝整个目录到目录(原目录放置在新目录里)
FileUtils.copyDirectoryToDirectory(new File("commons_IO/lib"),new File("commons_IO/lib2"));

// 拷贝目录到新目录(只拷贝原目录中的内容)
FileUtils.copyDirectory(new File("commons_IO/lib"), new File("commons_IO/lib2"));

// 拷贝URL内容
String url = "https://cn.bing.com/th?id=OIP.3k-eaDtZxcPRFwU3nKfG4AHaEp&pid=Api&rs=1";
FileUtils.copyURLToFile(new URL(url),new File("commons_IO/bing.jpg"));

String msg = IOUtils.toString(new URL("https://www.163.com"), "gbk");
System.out.println(msg);
```
