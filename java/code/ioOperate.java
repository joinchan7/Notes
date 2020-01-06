/**
 * 操作IO流标准步骤
 */
public class IOTest2 {
    public static void main(String[] args) {
        // 创建源
        File src = new File("IO_test/src/com/chan/abc.txt");
        InputStream is = null;
        try {
            // 创建流
            is = new FileInputStream(src);
            // 操作
            int temp = is.read();
            while (temp != -1) {
                System.out.println((char) temp);
                temp = is.read();
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
