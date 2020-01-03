## java 中 this 的用法

- this:官方的说法 --> this 首先是一个对象,它**代表调用这个函数的对象**。
- 根据面向对象的基本语法,每当调用变量或者函数的时候,都要按照类名.变量(函数)的格式来调用,意即每个**变量或函数**都必须**属于**某一个实际的**对象**而不是一个类(static 的除外).

### 一般情况下,代表调用这个函数的对象

- 在**不会产生混淆**的地方(**如下例**), this 是**可以省略**的,一般都会加上,但我觉得**应该加上 this**,(但 Think in Java 里面说最好不要加,因为大家都不加)

```java
public class Test {
    public static void main(String args[]){
        Person p1 = new Person();
        p1.name = "zhang san";
        Person p2 = new Person();
        p2.name = "li si";

        p1.talk();
        p2.talk();
    }
}

class Person {
    String name;

    void talk() {
    System.out.println("My name is " + this.name);
    }
}
// 这里talk()函数中的this可省略,不管是"this.name"还是"name",运行的结果都是:
// My name is zhang san
// My name is li si
```

- 当函数里面**有参数**时,如果函数的参数和成员变量一样,这时不加 this 的话,程序就会根据"**就近原则**",自动调用最近的值(**如下例**)

```java
public class Test {
    public static void main(String args[]){
        Person p1 = new Person();
        p1.name = "zhang san";
        Person p2 = new Person();
        p2.name = "li si";

        p1.talk("zhang");
        p2.talk("li");
    }
}

class Person {
    String name;

    void talk() {
        /* 输出:
                My name is zhang
                My name is li */
        System.out.println("My name is " + name);
        /* 输出:
                My name is zhang san
                My name is li si*/
        System.out.println("My name is " + this.name);

    }
}
// 这里talk()函数中的this不能省略,采取"就近原则"
```

### 在类内部,代表自身对象

- 在一个类的内部，也可以使用 this 代表自身类的对象，或者换句话说，每个类内部都有一个隐含的成员变量，该成员变量的类型是该类的类型，该成员变量的名称是 this，实际使用 this 代表自身类的对象的示例代码如下：

```java
// 使用this代表自身类的对象
public class ReferenceObject {
    ReferenceObject instance;

    public ReferenceObject() {
        instance = this;
    }

    public void test() {
        System.out.println(this);
    }
}
// 在构造方法内部，将对象this的值赋值给instance，在test方法内部，输出对象this的内容，这里的this都代表自身类型的对象。
```
