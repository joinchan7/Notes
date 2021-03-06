# 正则表达式

---

#### 正则表达式类型

1. raw string 类型(原生字符串类型): `r'text'`
   `r'[1-9]\d{5}'`
   不包含转义符的字符串
2. string 类型
   `'[1-9]\\d{5}'`
   使用时需要加上转义字符

#### 正则表达式主要功能函数

| 函数        | 说明                                                             |
| ----------- | ---------------------------------------------------------------- |
| re.search   | 在**一个**字符串中搜索匹配正则表达式的第一个位置,返回 match 对象 |
| re.match    | 从**第一个**字符开始匹配,返回 match 对象                         |
| re.findall  | 以列表返回所有符合的子串                                         |
| re.split    | 分割,返回列表类型                                                |
| re.finditer | 搜索,迭代获得,可对其结果单独处理,返回 match 对象                 |
| re.sub      | 替换                                                             |

#### match 对象

- ##### 属性

  | 属性    | 说明                                  |
  | ------- | ------------------------------------- |
  | .string | 待匹配的文本                          |
  | .re     | 匹配时使用的 pattern 对象(正则表达式) |
  | .pos    | 正则表达式**搜索**文本的**开始**位置  |
  | .re     | 正则表达式**搜索**文本的**结束**位置  |

- ##### 方法

  | 方法      | 说明                                       |
  | --------- | ------------------------------------------ |
  | .group(0) | 获得匹配后的**字符串**                     |
  | .start()  | **匹配**字符串在原始字符串中的**开始**位置 |
  | .end()    | **匹配**字符串在原始字符串中的**结束**位置 |
  | .span()   | 返回(.start(),.end())                      |

#### 两种使用方法

1. 函数式用法:**一次**性操作
   ```py
   rst = re.search(r'[1-9]\d{5}', 'bit 100081')
   ```
2. 面向对象用法:编译后**多次**操作
   ```py
   pat = re.compile(r'[1-9]\d{5}')
   rst = pat.search('bit 100081')
   ```

#### 贪婪模式

1. re 库**默认**采用贪婪模式匹配:匹配最长字符串
2. 两种模式区别
   - 贪婪模式
   ```py
   match = re.search(r'PY.*N', 'PYANBNCNDN')
   match.group(0)
   匹配结果:'PYANBNCNDN'
   ```
   - 非贪婪模式
   ```py
   match = re.search(r'PY.*?N', 'PYANBNCNDN')
   match.group(0)
   匹配结果:'PYAN'
   ```

#### 常用方法

1. 最小匹配操作符(后面加`?`)
   `*?` `+?` `??` `.?` `{m,n}?`
2. P 后的 Y 有一个或无穷多个 `PY+`
   PY 开头,后续不多于 10 个字符,后续字`PY[^PY]{0,10}` 符不能是 P 或 Y
3. 0 次或 1 次`?` 1 次或多次`+` 0 次或多次`*`
4. **常用**的正则表达式
   0-99: `[1-9]?\d` 100-199: `1\d{2}`
   200-249: `2[0-4]\d` 250-255: `25[0-5]`
   IP 地址:
   ```re
   (([1-9]?\d|1\d{2}|2[0-4]\d|25[0-5]).){3}([1-9]?\d|1\d{2}|2[0-4]\d|25[0-5])
   ```
