package org.convert.file

import java.io.File;

class LineSeparatorHelper {
    enum LINE_SEPARATOR {
        WINDOWS, LINUX, MAC, UNKNOWN
    }
    private LineSeparatorHelper() {
    }
    static LINE_SEPARATOR getLineSeparator(File f) throws IllegalArgumentException {
        if (f == null || !f.isFile() || !f.exists()) {
            throw new IllegalArgumentException("file must exists!");
        }
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(f, "r");
            String line = raf.readLine();
            if (line == null) {
                return LINE_SEPARATOR.UNKNOWN;
            }
// 必須執行這一步，因為 RandomAccessFile 的 readLine() 會自動忽略並跳過換行符，所以需要先回退文件指針位置
// "ISO-8859-1" 為 RandomAccessFile 使用的字符集，此處必須指定，否則中文 length 獲取不對
            raf.seek(line.getBytes("ISO-8859-1").length);
            byte nextByte = raf.readByte();
            if (nextByte == 0x0A) {
                return LINE_SEPARATOR.LINUX;
            }
            if (nextByte != 0x0D) {
                return LINE_SEPARATOR.UNKNOWN;
            }
            try {
                nextByte = raf.readByte();
                if (nextByte == 0x0A) {
                    return LINE_SEPARATOR.WINDOWS;
                }
                return LINE_SEPARATOR.MAC;
            } catch (EOFException e) {
                return LINE_SEPARATOR.MAC;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (raf != null) {
                try {
                    raf.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return LINE_SEPARATOR.UNKNOWN;
    }
}
