## bs4 的使用

#### 1. 基本使用

```
import bs4 from BeautifulSoup
//demo是html标签
soup=BeautifulSoup(demo,"html.parser")
```

#### 2. 五种不同的基本元素

- Tag
- Name
- Attributes
- NavigableString
- Comment  
  注意:.string 会提取 comment 类型字符,如不需要则要判断

#### 3.标签树的遍历

- 标签树的下行遍历
  - contents
  - children
  - descendants
- 标签树的上行遍历
  - parent
  - parents
- 标签树的平行遍历
  - next_sibling
  - previous_sibling
  - next_siblings
  - previous_siblings
