## 迭代器 iterator 的使用

---

### iterator 遍历

- 遍历 List
  ```java
    List<String> list = new ArrayList<>();
    list.add("aa");
    list.add("bb");
    list.add("cc");
    for (Iterator<String> iter = list.iterator(); iter.hasNext(); ) {
        // iter.next()返回当前对象,并指向下一个对象
        String temp = iter.next();
        System.out.println(temp);
    }
    //输出:aa  bb  cc
  ```
- 遍历 Set
  ```java
    Set<String> set = new HashSet<>();
    set.add("dd");
    set.add("ee");
    set.add("ff");
    for (Iterator<String> iter = set.iterator(); iter.hasNext(); ) {
        String temp = iter.next();
        System.out.println(temp);
    }
    //输出:dd  ee  ff
  ```
- 遍历 Map

  - 方法一:转换为 entrySet 的遍历
    entrySet**本质**是将 Map 中的`Key`和`Value`以`Key=value`的形式放入 Set 的`key`中

    ```java
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "aa");
        map.put(2, "bb");
        map.put(7, "cc");

        // 思路:将Map转换为entrySet,遍历entrySet即可
        Set<Map.Entry<Integer, String>> set = map.entrySet();
        for (Iterator<Map.Entry<Integer, String>> iter = set.iterator(); iter.hasNext(); ) {
            Map.Entry<Integer, String> temp = iter.next();
            System.out.println(temp.getKey()+"---"+temp.getValue());
        }
        //输出:1---23  2---44  7---vv
    ```

  - 方法二:转换为 key 的遍历

    ```java
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "aa");
        map.put(2, "bb");
        map.put(7, "cc");

        // 思路:将Map转换为key,遍历key即可
        Set<Integer> key = map.keySet();
        for (Iterator<Integer> iter = key.iterator(); iter.hasNext(); ) {
            Integer temp = iter.next();
            System.out.println(temp + "---" + map.get(temp));
        }
        //输出:1---23  2---44  7---vv
    ```

### 遍历方式总结

1. **普通** for 循环
2. **增强** for 循环
3. **迭代器** iterator 循环(遍历时,需要**删除**集合中的元素时使用)
