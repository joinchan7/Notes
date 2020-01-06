## java.IO

### 相关概念

1. 视角说明,以程序为中心
   > 由文件进入程序为输入流,由程序到文件为输出流
2. 核心类(5 个类 3 个接口)

   | 类           | 说明                                            |
   | ------------ | ----------------------------------------------- |
   | File         | 文件类(抽象表示形式,不一定存在)                 |
   | InputStream  | **字节**输入流                                  |
   | OutputStream | 字节输出流                                      |
   | Reader       | **字符**输入流                                  |
   | Writer       | 字符输出流                                      |
   | Closeable    | 关闭流接口(**通知操作系统**关闭流,释放系统资源) |
   | Flushable    | 刷新流接口(避免数据驻留)                        |
   | Serializable | 序列化接口(存储对象)                            |

3. 流分类
   - 输入流,输出流
   - **节点流**(直接操作 Byte,File),**处理流**(为了提高效率,简化操作)
   - 字节流,字符流(底层还是操作字节)
     > 字符集:GBK,UTF-8,Unicode

### File 类的使用

- pathSeparator
- separator

1. 字符串拼接使用"/"或"separator"
2. 基本操作:

```java
if (null==src||!src.exists()) {
            System.out.println("文件不存在");
        } else {
            if (src.isFile()) {
                System.out.println("文件操作");
            } else {
                System.out.println("文件夹操作");
            }
        }
```

3. createNewFile 创建文件
   con,com,com3 等操作系统关键字不能创建

```java
File src = new File("IO_test/src/con");  //false
File src = new File("IO_test/src/con");  //false
```

4. 创建文件夹
   - mkdir:父目录不存在,则创建失败
   - mkdirs:父目录不存在,则一同创建(推荐)
5. 递归打印给定目录下**所有**子孙目录和文件名称
   思路:递归和循环

```java
public static void printName(File src, int deep) {
    // 控制层次
    for (int i = 0; i < deep; i++) {
        System.out.print("-");
    }
    // 打印名称
    System.out.println(src.getName());
    // 递归头:结束递归条件
    if (null == src || !src.exists()) {
        return;
    } else if (src.isDirectory()) {
        for (File f : Objects.requireNonNull(src.listFiles())) {
            // 递归体:自己调用自己
            printName(f, deep + 1);
        }
    }
}
```

### 查文档的一些说明

> **API>default**
> 查文档时关注**常量,构造器,方法(名字 static?,形参,源码,返回值)**

1. 继承体系
2. 有构造器
3. 无构造器
   - 工具类:如 Math
   - 静态方法:如 Runtime
4. 方法

### 字节流字符流的选择

1. Excel,Word,PPT,音频,视频-->字节流
2. 中英文-->字符流

### IO 流操作步骤

1. 创建源
2. 选择流
3. 操作(读,写)
4. 释放系统资源
