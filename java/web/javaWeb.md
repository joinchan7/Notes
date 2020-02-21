## java 网络编程

- 定位
  1. IP
  2. 端口
  3. URL
- TCP/UDP
  - TCP:面向连接,安全可靠
    1. ServerSocket accept
    2. Socket IO
  - UDP:非面向连接,效率高
    1. DatagramSocket send receive
    2. DatagramPacket

### cookie

- 域名共享问题
  1. 同一个服务器下,不同项目, cookie 默认不能共享
     若要实现共享,则使用 `c.setPath("/")`
  2. 不同服务器下,实现 cookie 共享
     `setDomain(String path)`:如果设置一级域名相同,那么多个服务器之间可以实现 cookie 共享
     例:`setDomain(".baidu.com")`,则 tieba.baidu.com 和 news.baidu.com 可以实现域名共享
  3. 一级域名与二级域名 (tieba.baidu.com)
     一级域名:.baidu.com
     二级域名:tieba
- cookie 存活时间
  设置方法: `setMaxAge(int expiry)`
  1. 正数:长久存储,存储时间为数值大小
  2. 负数:默认值,关闭浏览器自动删除
  3. 0:直接删除该 cookie
