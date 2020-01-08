/**
 * 封装拷贝,
 * 封装释放资源
 * try( ; )...with...resource的使用
 */
public class FileUtils {
    public static void main(String[] args) {
        //  文件-->文件
        try {
            InputStream is = new FileInputStream("IO_test/src/com/chan/abc.txt");
            OutputStream os = new FileOutputStream("IO_test/src/com/chan/copy.txt");
            copy(is, os);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 文件-->字节数组
        byte[] data = null;
        try {
            InputStream is = new FileInputStream("IO_test/src/com/chan/linux.png");
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            copy(is, bos);

            data = bos.toByteArray();
            System.out.println(data.length);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 字节数组-->文件
        try {
            assert data != null;
            InputStream is = new ByteArrayInputStream(data);
            OutputStream os = new FileOutputStream("IO_test/src/com/chan/copy3.png");

            copy(is, os);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * 对接输入输出流
     *
     * @param is 输入流
     * @param os 输出流
     */
    public static void copy(InputStream is, OutputStream os) {
        try (is; os) {
            // 分段读取
            byte[] flush = new byte[1024];
            int len = is.read(flush);
            while (len != -1) {
                os.write(flush, 0, len);    // 分段写入
                len = is.read(flush);
            }
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}