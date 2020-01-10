import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

/**
 * 将指定文件分成n块,并存入指定文件夹中
 * 面向对象思想封装
 */
public class SplitFile {
    // 源头
    private File src;
    // 目的地
    private String destDir;
    // 所有分割后的文件存储路径
    private List<String> destPaths;
    // 每块大小
    private int blockSize;
    // 分几块
    private int size;

    public SplitFile(String srcPath, String destDir, int blockSize) {
        this.src = new File(srcPath);
        this.destDir = destDir;
        this.blockSize = blockSize;
        this.destPaths = new ArrayList<String>();

        init();
    }

    // 构造器可以相互调用和重载
    public SplitFile(String srcPath, String destDir) {
        this(srcPath, destDir, 1024);
    }

    // 初始化
    private void init() {
        // 总长度
        long len = this.src.length();
        // 分几块
        this.size = (int) Math.ceil(len * 1.0 / blockSize);
        for (int i = 0; i < size; i++) {
            this.destPaths.add(this.destDir + "/" + i + "-" + this.src.getName());
        }
    }

    public void split() throws IOException {
        // 不能再使用这个src啦!!!
        // File src = new File("IO_test/src/com/chan/io3/Copy.java");
        // 总长度
        long len = src.length();

        int beginPos;
        int actualSize;
        for (int i = 0; i < size; i++) {
            beginPos = i * blockSize;

            if (i == size - 1) {
                // 如果是最后一块,使用剩余量
                actualSize = (int) len;
            } else {
                // 如果不是最后一块,使用blockSize
                actualSize = blockSize;
                // 剩余量
                len -= actualSize;
            }
            System.out.println(i + "-->" + beginPos + "-->" + actualSize);
            splitDetail(i, beginPos, actualSize);
        }
    }

    public static void main(String[] args) throws IOException {
        SplitFile sf = new SplitFile("IO_test/src/com/chan/linux.png",
                "IO_test/src/com/chan/io3/dest",
                1026);
        SplitFile sf2 = new SplitFile("IO_test/src/com/chan/baiDu.html",
                "IO_test/src/com/chan/io3/dest");

        sf.split();
        sf2.split();
    }

    /**
     * 指定第几块的起始位置和实际长度
     *
     * @param i          第几块
     * @param beginPos   起始位置
     * @param actualSize 需要获取的实际大小
     */
    // 分块思想:起始,实际大小
    private void splitDetail(int i, int beginPos, int actualSize) throws IOException {
        RandomAccessFile raf = new RandomAccessFile(this.src, "r");
        RandomAccessFile raf2 = new RandomAccessFile(this.destPaths.get(i), "rw");
        // 起始位置
        // 需要获取的实际大小

        raf.seek(beginPos);

        byte[] flush = new byte[1024];
        int len = raf.read(flush);
        while (len != -1) {
            // 获取本次读取所有数据
            if (actualSize > len) {
                raf2.write(flush, 0, len);
                actualSize -= len;
                // 获取实际大小
            } else {
                raf2.write(flush, 0, actualSize);
                break;
            }
            len = raf.read(flush);
        }
        raf2.close();
        raf.close();
    }
}
