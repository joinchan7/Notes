yield 生成器:

- 生成器是一个不断产生值的函数
- 包含 yield 语句的函数是一个生成器
- 生成器每次产生一个值(yield 语句),函数被冻结,被唤醒后再产生一个值

实例:

> 普通写法

```py
def square(n):
    # 不断生成i的平方
    ls = [i**2 for i in range(n)]
        return ls
# 能唤醒小于5的值
for i in square(5):
    print(i, " ", end=" ")
# 结果为:0 1 4 9 16
```

> 生成器 yield 写法

```py
def gen(n):
    for i in range(n):
        # 不断生成i的平方
        yield i**2
# 能唤醒小于5的值
for i in gen(5):
    print(i, " ", end=" ")
# 结果为:0 1 4 9 16
```

优点:

- 更节省内存
- 响应更迅速
- 使用更灵活
