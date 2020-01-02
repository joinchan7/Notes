import java.io.File;
import java.util.Objects;

/**
 * 面向对象
 * 统计给定目录下所有文件的大小(Byte)
 * 完善:实现不同File大小的统计
 */
public class DirCount {
    // 大小
    private long len;
    // 文件夹
    private String path;
    // 源
    private File src;
    private int fileSize;
    private int dirSize;

    public DirCount(String path) {
        this.path = path;
        this.src = new File(path);
        count(this.src);
    }

    private void count(File src) {
        if (null != src && src.exists()) {
            if (src.isFile()) {
                len += src.length();
                this.fileSize++;
            } else {
                for (File f : Objects.requireNonNull(src.listFiles())) {
                    count(f);
                }
                this.dirSize++;
            }
        }
    }

    public long getLen() {
        return len;
    }

    public int getFileSize() {
        return fileSize;
    }

    public int getDirSize() {
        return dirSize;
    }

    public static void main(String[] args) {
        System.out.println("dirLen->dirFileSize->dirDirSize");
        DirCount dir = new DirCount("IO_test/src");
        System.out.println(dir.getLen() + "\t-->" + dir.getFileSize() + "\t-->" + dir.getDirSize());

        DirCount dir2 = new DirCount("IO_test/src/com/chan/io");
        System.out.println(dir2.getLen() + "\t-->" + dir2.getFileSize() + "\t-->" + dir2.getDirSize());
    }
}
