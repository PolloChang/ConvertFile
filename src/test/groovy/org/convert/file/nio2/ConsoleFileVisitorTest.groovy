package org.convert.file.nio2

import java.nio.file.FileVisitResult
import java.nio.file.Path
import java.nio.file.SimpleFileVisitor
import java.nio.file.attribute.BasicFileAttributes

class ConsoleFileVisitorTest extends SimpleFileVisitor<Path>{

    @Override
    FileVisitResult preVisitDirectory(
            Path path,
            BasicFileAttributes atters
    )
    throws IOException
    {
        println path.getFileName()
        return FileVisitResult.CONTINUE
    }

    @Override
    FileVisitResult visitFile(
            Path path,
            BasicFileAttributes atters
    )
            throws IOException
    {
        println path.getFileName()
        return FileVisitResult.CONTINUE
    }

    @Override
    FileVisitResult visitFileFailed(
            Path path,
            IOException ex
    )
    {
        ex.printStackTrace()
        return FileVisitResult.CONTINUE
    }
}
