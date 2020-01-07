package com.chan.io2;

import java.io.*;

/**
 * 使用文件输入流和输出流
 * 实现文件的拷贝(拓展:利用递归实现文件夹的拷贝)
 */
public class Copy {
    public static void main(String[] args) {
        copy("IO_test/src/com/chan/linux.png", "IO_test/src/com/chan/copy.png");
    }

    public static void copy(String srcPath, String destPath) {
        File src = new File(srcPath);
        File dest = new File(destPath);

        InputStream is = null;
        OutputStream os = null;

        try {
            is = new FileInputStream(src);
            os = new FileOutputStream(dest);
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
        } finally {
            // 释放资源:先打开的后关闭
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
