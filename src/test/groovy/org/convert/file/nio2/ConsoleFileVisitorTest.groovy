package org.convert.file.nio2

import java.nio.file.FileVisitResult
import java.nio.file.Path
import java.nio.file.SimpleFileVisitor
import java.nio.file.attribute.BasicFileAttributes

/**
 * 實作FileVisitorTest範例
 * 目的：列出資料夾裏面所有檔案內容
 */
class ConsoleFileVisitorTest extends SimpleFileVisitor<Path>{

    /**
     * 列出資料夾
     * @param path
     * @param atters
     * @return
     * @throws IOException
     */
    @Override
    FileVisitResult preVisitDirectory(
            Path path,
            BasicFileAttributes atters
    )
    throws IOException
    {
        println "show Directory before : "+path.getFileName()
        return FileVisitResult.CONTINUE
    }

    /**
     * 列出檔案
     * @param path
     * @param atters
     * @return
     * @throws IOException
     */
    @Override
    FileVisitResult visitFile(
            Path path,
            BasicFileAttributes atters
    )
            throws IOException
    {
        println "is File : "+path.getFileName()
        return FileVisitResult.CONTINUE
    }

    /**
     * 讀取失敗
     * @param path
     * @param ex
     * @return
     */
    @Override
    FileVisitResult visitFileFailed(
            Path path,
            IOException ex
    )
    {
        println "has error in File : "+path.getFileName()
        ex.printStackTrace()
        return FileVisitResult.CONTINUE
    }

    FileVisitResult postVisitDirectory(
            Path path, IOException ex
    )
    throws IOException
    {
        println "show Directory after : "+path.getFileName()
        return FileVisitResult.CONTINUE
    }
}
