package org.convert.file

import org.convert.file.nio2.ConsoleFileVisitorTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class FileTest {
    String pathSource = "/home/jameschang/test/"

    @Test
    void DirAll(){
        Files.walkFileTree(Paths.get(pathSource),new ConsoleFileVisitorTest())
    }

    @Test
    void nio2() {
        String HOME = System.getProperty("user.home")

        Path p  = Paths.get(pathSource)


        try(
            def directoryStream = Files.newDirectoryStream(Paths.get(pathSource))
        ){
            def files = new ArrayList<String>()
            for(def path: directoryStream){
                if(Files.isDirectory(path)){
                    println path.getFileName()
                }
                else {
                    files.add(path.getFileName().toString())
                }
            }
            println "===========is Files=========="
            files.each {
                println it
            }
        }
        catch (IOError ex){
            ex.printStackTrace()
        }
    }
}