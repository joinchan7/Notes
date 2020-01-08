/**
 * 操作IO流分段读取(字节)
 */
public class ioMulOperate {
    public static void main(String[] args) {
        // 创建源
        File src = new File("IO_test/src/com/chan/abc.txt");
        InputStream is = null;
        try {
            // 创建流
            is = new FileInputStream(src);
            // 操作
            // 缓存容器
            byte[] flush = new byte[1024];
            // 接收长度
            int len = is.read(flush);
            while (len != -1) {
                String str = new String(flush, 0, len);
                System.out.println((str);
                len = is.read(flush);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            try {
                if (null != is) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
