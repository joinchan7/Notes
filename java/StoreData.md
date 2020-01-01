## 使用容器存储表格数据

### 示例

写入并读取下列表格的内容:
| ID | 姓名 | 薪水 | 入职日期 |
| ---- | ---- | ----- | -------- |
| 1001 | 张三 | 20000 | 2018.5.5 |
| 1002 | 李四 | 30000 | 2005.4.4 |
| 1003 | 王五 | 3000 | 2019.5.4 |

### 思路(ORM 思想)

> ORM 思想:关系对象映射

- **思路一**

  1. 每行数据使用一个 Map
  2. 整个表格使用一个 List

  ```java
    // 放入数据
    Map<String, Object> row1 = new HashMap<>();
    row1.put("id", 1001);
    row1.put("name", "张三");
    row1.put("salary", 20000);
    row1.put("date", "2018-5-5");

    Map<String, Object> row2 = new HashMap<>();
    row2.put("id", 1002);
    row2.put("name", "李四");
    row2.put("salary", 30000);
    row2.put("date", "2005-4-4");

    Map<String, Object> row3 = new HashMap<>();
    row3.put("id", 1003);
    row3.put("name", "王五");
    row3.put("salary", 2000);
    row3.put("date", "2019-5-4");

    List<Map<String, Object>> table1 = new ArrayList<>();
    table1.add(row1);
    table1.add(row2);
    table1.add(row3);

    // 取出数据
    for(Map<String, Object> row:table1) {
        Set<String> keySet = row.keySet();
        for (String key:keySet) {
            System.out.print(key+":"+row.get(key)+"\t");
        }
        System.out.println();
    }
    // 打印结果:
    // date:2018-5-5	name:张三	id:1001	salary:20000
    // date:2005-4-4	name:李四	id:1002	salary:30000
    // date:2019-5-4	name:王五	id:1003	salary:2000
  ```

- **思路二**

  1. 每行使用一个 JavaBean 对象
  2. 整个表格使用 Map/List

  ```java
    // 放入数据(需要提前定义User类,用来创建JavaBean对象)
    User user1=new User(1001,"张三",20000,"2018-5-5");
    User user2=new User(1002,"李四",30000,"2005-4-4");
    User user3=new User(1003,"王五",2000,"2019-5-4");

    List<User> list = new ArrayList<>();
    list.add(user1);
    list.add(user2);
    list.add(user3);

    for (User user:list) {
        System.out.println(user);
    }

    Map<Integer,User> map = new HashMap<>();
    map.put(1001,user1);
    map.put(1002,user2);
    map.put(1003,user3);
    // 取出数据
    Set<Map.Entry<Integer,User>> entrySet=map.entrySet();
    for(Map.Entry<Integer,User> temp:entrySet) {
        System.out.println(temp);
        System.out.println(temp.getKey()+"--------"+temp.getValue());
    }
  ```
