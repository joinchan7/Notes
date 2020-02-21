- 一些名词

  1. pv:page view(并发量)
  2. uv:unique view(用户量)
  3. vv:visit view

- 将 List 转换为 String 数组

  ```java
  values.toArray(new String[0]);
  ```

- response 响应状态码
  1. 1xx:服务器接收客户端,但没有接收完成,等待一段时间后,发送 1xx 多状态码
  2. 2xx:成功,代表是 200 (请求成功)
  3. 3xx:重定向
     - 代表 302 (重定向)
     - 代表 304 (访问缓存)
  4. 4xx:客户端错误
     - 代表 404 (请求路径没有资源)
     - 代表 405 (请求方式没有对应的 do 方法)
  5. 5xx:服务端错误,代表是 500(服务器内部出现异常)
