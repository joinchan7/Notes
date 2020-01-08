/**
 * 使用文件输入流和输出流
 * 实现文件的拷贝(拓展:利用递归实现文件夹的拷贝)
 * 使用try( ; )自动释放资源
 */
public class Copy2 {
    public static void main(String[] args) {
        copy("IO_test/src/com/chan/linux.png", "IO_test/src/com/chan/copy.png");
    }

    public static void copy(String srcPath, String destPath) {
        File src = new File(srcPath);
        File dest = new File(destPath);

        try (InputStream is = new FileInputStream(src); OutputStream os = new FileOutputStream(dest)) {
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