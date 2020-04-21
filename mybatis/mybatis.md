### 自定义 mybatis 分析

1. 两件事:

   - 创建代理对象

   - 在代理对象中调用 selectList

2. 自定义的相关类:

   - class Resources
   - class SqlSessionFactoryBuilder

   - interface SqlSessionFactory
   - interface SqlSession

### 延时加载

1. 延时加载:

   真正使用数据时才发起查询,不用的时候不查询,按需加载

2. 立即加载:

   不管用不用,只要调用方法,马上发起查询

3. 四种对应关系加载方式:

   一对多,多对多:延时加载

   一对一,多对一:立即加载

### 缓存

1. 定义:

   存在内存中的临时数据

2. 为什么使用缓存

   减少和数据库交互的次数,提高执行效率

3. 适用于使用缓存的条件

   - 经常查询并且不经常改变
   - 数据的正确去否与最终结果影响不大

4. 一级缓存和二级缓存

   - 一级缓存

     mybatis 中 SqlSession 对象的缓存,,缓存内容为对象

     当执行查询后,查询的结果会同时存入 SqlSession 为我们提供的一块区域中

     该区域结构为 Map,当再次查询同样的数据,mybatis 会先去 SqlSession 中查询是否有该数据,若有则直接取出

     当 SqlSession 对象消失,mybatis 一级缓存对应消失

     调用 SqlSession 的添加,修改,删除,commit(),close(),clearCache()后会清空一级缓存

   - 二级缓存

     mybatis 中 SqlSessionFactory 对象中的缓存,由同一个 SqlSessionFactory 对象创建的 SqlSession 共享其缓存,缓存内容为数据

     使用步骤:

     1. 让 mybatis 支持二级缓存,SqlMapConfig.xml
     2. 让当前映射文件支持二级缓存,UserDao.xml
     3. 让当前操作支持二级缓存,`<select>`
