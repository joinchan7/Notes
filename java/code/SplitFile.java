package com.chan.io3;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * 面向对象思想封装
 * <p>
 * split:将指定文件分成n块,并存入指定文件夹中
 * merge:将分开的文件进行合并
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

        this.destPaths = new ArrayList<>();
        init();
    }

    // 构造器可以相互调用和重载
    public SplitFile(String srcPath, String destDir) {
        this(srcPath, destDir, 1024);
    }

    // 初始化
    private void init() {
        // 总长度
        long len = src.length();
        // 分几块
        this.size = (int) Math.ceil(len * 1.0 / blockSize);
        for (int i = 0; i < size; i++) {
            this.destPaths.add(this.destDir + "/" + i + "-" + this.src.getName());
        }
    }

    public void split() throws IOException {
        // 总长度
        long len = src.length();
        int beginPos;
        // 当前块的实际大小
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

    /**
     * 文件合并
     */
    public void merge(String destPath) throws IOException {
        OutputStream os = new BufferedOutputStream(new FileOutputStream(destPath, true));
        Vector<InputStream> iv = new Vector<>();
        SequenceInputStream sis;
        for (String path : destPaths) {
            iv.add(new BufferedInputStream(new FileInputStream(path)));
        }
        sis = new SequenceInputStream(iv.elements());

        byte[] flush = new byte[1024];
        int len = sis.read(flush);
        while (len != -1) {
            os.write(flush, 0, len);
            len = sis.read(flush);
        }

        os.flush();
        os.close();
        sis.close();
    }

    public static void main(String[] args) throws IOException {
        SplitFile sf = new SplitFile("IO_test/src/com/chan/linux.png",
                "IO_test/src/com/chan/io3/dest",
                1026);
        SplitFile sf2 = new SplitFile("IO_test/src/com/chan/baiDu.html",
                "IO_test/src/com/chan/io3/dest");

        sf.split();
        sf2.split();

        sf.merge("IO_test/src/com/chan/merge.png");
    }

    /**
     * 指定第几块的起始位置和实际长度,进行分割
     *
     * @param i          第几块
     * @param beginPos   起始位置
     * @param actualSize 需要获取的实际大小
     */
    // 分块思想:起始,实际大小
    private void splitDetail(int i, int beginPos, int actualSize) throws IOException {
        RandomAccessFile raf = new RandomAccessFile(this.src, "r");
        RandomAccessFile raf2 = new RandomAccessFile(this.destPaths.get(i), "rw");

        raf.seek(beginPos);

        byte[] flush = new byte[1024];
        int len = raf.read(flush);
        while (len != -1) {
            // 如果当前块实际长度>缓存区长度,获取本次读取所有数据
            if (actualSize > len) {
                raf2.write(flush, 0, len);
                actualSize -= len;
                // 如果当前块实际长度>缓存区长度,获取实际长度
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
