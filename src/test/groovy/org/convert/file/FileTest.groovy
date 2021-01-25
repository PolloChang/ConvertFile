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

    @Test
    void stringTest(){
        String t = """




<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=Utf8">
        <title>JSP Page</title>
        <style type="text/css">
            * {
                font-size:18px;
            }

            table {
                border-width: 0 0 1px 1px;
                border-spacing: 0;
                border-collapse: collapse;
                border-style: solid;
                border-color:black;
            }

            td, th {
                width: 25%;
                margin: 0;
                padding: 4px;
                border-width: 1px 1px 0 0;
                border-style: solid;
                border-color:black;
            }
        </style>
    </head>
    <body>
        <form id="UserInputForm" name = "UserInputForm" method="post">
            每日排程清單，如有異常時間請至系統檢視。<BR/>
            備註：此信函是透過系統自動傳送給您，請勿直接回覆，謝謝！<BR/>
            <BR/>
            <table style="width:900px;">
                <tbody>
                    <tr>
                        <th style="width:200px;">
                            <b>批次名稱</b>
                        </th>
                        <th style="width:200px;">
                            <b>排程類型</b>
                        </th>
                        <th style="width:200px;">
                            <b>註記</b>
                        </th>
                        <th style="width:100px;">
                            <b>開始</b>
                        </th>
                        <th style="width:100px;">
                            <b>結束</b>
                        </th>
                        <th style="width:100px;">
                            <b>花費</b>
                        </th>
                    </tr>
                    
                    <tr style="text-align:center;">
                        <td style="width:200px;">
                            swj2-AX簡訊歷程記錄
                        </td>
                        <td style="width:200px;">
                            202101201003
                        </td>
                        <td style="width:200px;">
                            AX簡訊歷程記錄0 筆.
                        </td>
                        <td style="width:100px;">
                            110/01/20<BR/>10:03:24
                        </td>
                        <td style="width:100px;">
                            110/01/20<BR/>10:03:32
                        </td>
                        <td style="width:100px;">
                            0 分
                        </td>
                    </tr>
                    
                    <tr style="text-align:center;">
                        <td style="width:200px;">
                            Z000Mail
                        </td>
                        <td style="width:200px;">
                            20210120
                        </td>
                        <td style="width:200px;">
                            172.18.17.144
                        </td>
                        <td style="width:100px;">
                            110/01/20<BR/>08:10:03
                        </td>
                        <td style="width:100px;">
                            110/01/20<BR/>08:10:07
                        </td>
                        <td style="width:100px;">
                            0 分
                        </td>
                    </tr>
                    
                    <tr style="text-align:center;">
                        <td style="width:200px;">
                            BOOK2SFAA_BATCH
                        </td>
                        <td style="width:200px;">
                            202101200715
                        </td>
                        <td style="width:200px;">
                            SPOON排程_身障手冊每日異動上傳社家署。
                        </td>
                        <td style="width:100px;">
                            110/01/20<BR/>07:15:09
                        </td>
                        <td style="width:100px;">
                            
                        </td>
                        <td style="width:100px;">
                            <font color="red">未完成</font>
                        </td>
                    </tr>
                    
                    <tr style="text-align:center;">
                        <td style="width:200px;">
                            ICF2SWJDB_BATCH
                        </td>
                        <td style="width:200px;">
                            202101200700
                        </td>
                        <td style="width:200px;">
                            SPOON排程_ICF同步資料。
                        </td>
                        <td style="width:100px;">
                            110/01/20<BR/>07:00:32
                        </td>
                        <td style="width:100px;">
                            110/01/20<BR/>07:14:39
                        </td>
                        <td style="width:100px;">
                            14 分
                        </td>
                    </tr>
                    
                    <tr style="text-align:center;">
                        <td style="width:200px;">
                            P121_TIMEOUT
                        </td>
                        <td style="width:200px;">
                            202101200601
                        </td>
                        <td style="width:200px;">
                            SPOON排程_已逾期案件及將到期案件每日寄信通知45 筆.
                        </td>
                        <td style="width:100px;">
                            110/01/20<BR/>06:02:01
                        </td>
                        <td style="width:100px;">
                            110/01/20<BR/>06:02:47
                        </td>
                        <td style="width:100px;">
                            0 分
                        </td>
                    </tr>
                    
                    <tr style="text-align:center;">
                        <td style="width:200px;">
                            NC101_W00_CASEDATA
                        </td>
                        <td style="width:200px;">
                            202101200443
                        </td>
                        <td style="width:200px;">
                            SPOON排程_雲端證件包API增加好孕專車福利身分93358 筆.
                        </td>
                        <td style="width:100px;">
                            110/01/20<BR/>04:43:42
                        </td>
                        <td style="width:100px;">
                            110/01/20<BR/>06:34:36
                        </td>
                        <td style="width:100px;">
                            110 分
                        </td>
                    </tr>
                    
                    <tr style="text-align:center;">
                        <td style="width:200px;">
                            3ICFI
                        </td>
                        <td style="width:200px;">
                            2021012004
                        </td>
                        <td style="width:200px;">
                            172.18.17.223, 共完成 0 筆。
                        </td>
                        <td style="width:100px;">
                            110/01/20<BR/>04:15:04
                        </td>
                        <td style="width:100px;">
                            110/01/20<BR/>04:16:35
                        </td>
                        <td style="width:100px;">
                            1 分
                        </td>
                    </tr>
                    
                    <tr style="text-align:center;">
                        <td style="width:200px;">
                            W2800
                        </td>
                        <td style="width:200px;">
                            20210120
                        </td>
                        <td style="width:200px;">
                            172.18.17.139 市民卡交換
                        </td>
                        <td style="width:100px;">
                            110/01/20<BR/>03:33:26
                        </td>
                        <td style="width:100px;">
                            110/01/20<BR/>04:00:08
                        </td>
                        <td style="width:100px;">
                            26 分
                        </td>
                    </tr>
                    
                    <tr style="text-align:center;">
                        <td style="width:200px;">
                            2K00
                        </td>
                        <td style="width:200px;">
                            20210120
                        </td>
                        <td style="width:200px;">
                            172.18.17.138
                        </td>
                        <td style="width:100px;">
                            110/01/20<BR/>02:32:05
                        </td>
                        <td style="width:100px;">
                            
                        </td>
                        <td style="width:100px;">
                            <font color="red">未完成</font>
                        </td>
                    </tr>
                    
                    <tr style="text-align:center;">
                        <td style="width:200px;">
                            Actmps
                        </td>
                        <td style="width:200px;">
                            20210120
                        </td>
                        <td style="width:200px;">
                            172.18.17.138
                        </td>
                        <td style="width:100px;">
                            110/01/20<BR/>02:30:09
                        </td>
                        <td style="width:100px;">
                            110/01/20<BR/>02:32:05
                        </td>
                        <td style="width:100px;">
                            1 分
                        </td>
                    </tr>
                    
                    <tr style="text-align:center;">
                        <td style="width:200px;">
                            GM300MAKE
                        </td>
                        <td style="width:200px;">
                            20210120
                        </td>
                        <td style="width:200px;">
                            172.18.17.221
                        </td>
                        <td style="width:100px;">
                            110/01/20<BR/>02:30:07
                        </td>
                        <td style="width:100px;">
                            110/01/20<BR/>02:30:15
                        </td>
                        <td style="width:100px;">
                            0 分
                        </td>
                    </tr>
                    
                    <tr style="text-align:center;">
                        <td style="width:200px;">
                            7100-脆弱家庭介接
                        </td>
                        <td style="width:200px;">
                            20210120021357
                        </td>
                        <td style="width:200px;">
                            排程開始
                        </td>
                        <td style="width:100px;">
                            110/01/20<BR/>02:13:57
                        </td>
                        <td style="width:100px;">
                            
                        </td>
                        <td style="width:100px;">
                            <font color="red">未完成</font>
                        </td>
                    </tr>
                    
                    <tr style="text-align:center;">
                        <td style="width:200px;">
                            7100-脆弱家庭介接
                        </td>
                        <td style="width:200px;">
                            20210120021247
                        </td>
                        <td style="width:200px;">
                            排程開始
                        </td>
                        <td style="width:100px;">
                            110/01/20<BR/>02:12:47
                        </td>
                        <td style="width:100px;">
                            
                        </td>
                        <td style="width:100px;">
                            <font color="red">未完成</font>
                        </td>
                    </tr>
                    
                    <tr style="text-align:center;">
                        <td style="width:200px;">
                            7100-脆弱家庭介接
                        </td>
                        <td style="width:200px;">
                            20210120020505
                        </td>
                        <td style="width:200px;">
                            排程開始
                        </td>
                        <td style="width:100px;">
                            110/01/20<BR/>02:05:05
                        </td>
                        <td style="width:100px;">
                            
                        </td>
                        <td style="width:100px;">
                            <font color="red">未完成</font>
                        </td>
                    </tr>
                    
                    <tr style="text-align:center;">
                        <td style="width:200px;">
                            PS101-校安系統介接
                        </td>
                        <td style="width:200px;">
                            20210120020400
                        </td>
                        <td style="width:200px;">
                            共 -1 筆，已完成 -1 筆。
                        </td>
                        <td style="width:100px;">
                            110/01/20<BR/>02:04:00
                        </td>
                        <td style="width:100px;">
                            110/01/20<BR/>02:04:56
                        </td>
                        <td style="width:100px;">
                            0 分
                        </td>
                    </tr>
                    
                    <tr style="text-align:center;">
                        <td style="width:200px;">
                            W28_BATCH
                        </td>
                        <td style="width:200px;">
                            202101200200
                        </td>
                        <td style="width:200px;">
                            排程_悠遊卡資料介接。新增393筆。
                        </td>
                        <td style="width:100px;">
                            110/01/20<BR/>02:00:42
                        </td>
                        <td style="width:100px;">
                            110/01/20<BR/>02:01:38
                        </td>
                        <td style="width:100px;">
                            0 分
                        </td>
                    </tr>
                    
                    <tr style="text-align:center;">
                        <td style="width:200px;">
                            P000-脆弱家庭介接
                        </td>
                        <td style="width:200px;">
                            20210120020038
                        </td>
                        <td style="width:200px;">
                            共 -1 筆，已完成 -1 筆。
                        </td>
                        <td style="width:100px;">
                            110/01/20<BR/>02:00:38
                        </td>
                        <td style="width:100px;">
                            110/01/20<BR/>02:13:15
                        </td>
                        <td style="width:100px;">
                            12 分
                        </td>
                    </tr>
                    
                    <tr style="text-align:center;">
                        <td style="width:200px;">
                            swj2-AR200二手輔具-回收輔具資料同步
                        </td>
                        <td style="width:200px;">
                            202101200100
                        </td>
                        <td style="width:200px;">
                            回收輔具資料同步1138 筆.
                        </td>
                        <td style="width:100px;">
                            110/01/20<BR/>01:00:33
                        </td>
                        <td style="width:100px;">
                            110/01/20<BR/>01:00:52
                        </td>
                        <td style="width:100px;">
                            0 分
                        </td>
                    </tr>
                    
                    <tr style="text-align:center;">
                        <td style="width:200px;">
                            7100-脆弱家庭介接
                        </td>
                        <td style="width:200px;">
                            20210120004630
                        </td>
                        <td style="width:200px;">
                            排程開始
                        </td>
                        <td style="width:100px;">
                            110/01/20<BR/>00:46:30
                        </td>
                        <td style="width:100px;">
                            
                        </td>
                        <td style="width:100px;">
                            <font color="red">未完成</font>
                        </td>
                    </tr>
                    
                    <tr style="text-align:center;">
                        <td style="width:200px;">
                            9500Batch
                        </td>
                        <td style="width:200px;">
                            20210120
                        </td>
                        <td style="width:200px;">
                            172.18.17.138
                        </td>
                        <td style="width:100px;">
                            110/01/20<BR/>00:15:03
                        </td>
                        <td style="width:100px;">
                            110/01/20<BR/>01:04:08
                        </td>
                        <td style="width:100px;">
                            49 分
                        </td>
                    </tr>
                    
                    <tr style="text-align:center;">
                        <td style="width:200px;">
                            7100-脆弱家庭介接
                        </td>
                        <td style="width:200px;">
                            20210119233549
                        </td>
                        <td style="width:200px;">
                            排程開始
                        </td>
                        <td style="width:100px;">
                            110/01/19<BR/>23:35:49
                        </td>
                        <td style="width:100px;">
                            
                        </td>
                        <td style="width:100px;">
                            <font color="red">未完成</font>
                        </td>
                    </tr>
                    
                    <tr style="text-align:center;">
                        <td style="width:200px;">
                            P000-高風險_大數據介接_每日
                        </td>
                        <td style="width:200px;">
                            20210119230027
                        </td>
                        <td style="width:200px;">
                            共 0 筆，已完成 0 筆。
                        </td>
                        <td style="width:100px;">
                            110/01/19<BR/>23:00:27
                        </td>
                        <td style="width:100px;">
                            110/01/19<BR/>23:17:26
                        </td>
                        <td style="width:100px;">
                            16 分
                        </td>
                    </tr>
                    
                    <tr style="text-align:center;">
                        <td style="width:200px;">
                            swj2_衛福部介接ICF手冊資料
                        </td>
                        <td style="width:200px;">
                            20210119
                        </td>
                        <td style="width:200px;">
                            swj2_衛福部介接ICF手冊資料
                        </td>
                        <td style="width:100px;">
                            110/01/19<BR/>23:00:13
                        </td>
                        <td style="width:100px;">
                            110/01/20<BR/>00:03:49
                        </td>
                        <td style="width:100px;">
                            63 分
                        </td>
                    </tr>
                    
                    <tr style="text-align:center;">
                        <td style="width:200px;">
                            PD100_TIMEOUT
                        </td>
                        <td style="width:200px;">
                            202101192030
                        </td>
                        <td style="width:200px;">
                            SPOON排程_PD100已逾期案件及將到期案件每日寄信通知${SUCCESS_COUNT} 筆.
                        </td>
                        <td style="width:100px;">
                            110/01/19<BR/>20:30:30
                        </td>
                        <td style="width:100px;">
                            110/01/19<BR/>20:30:37
                        </td>
                        <td style="width:100px;">
                            0 分
                        </td>
                    </tr>
                    
                    <tr style="text-align:center;">
                        <td style="width:200px;">
                            4C93A
                        </td>
                        <td style="width:200px;">
                            20210119
                        </td>
                        <td style="width:200px;">
                            172.18.17.144
                        </td>
                        <td style="width:100px;">
                            110/01/19<BR/>17:00:12
                        </td>
                        <td style="width:100px;">
                            110/01/19<BR/>17:00:12
                        </td>
                        <td style="width:100px;">
                            0 分
                        </td>
                    </tr>
                    
                    <tr style="text-align:center;">
                        <td style="width:200px;">
                            W4CMail
                        </td>
                        <td style="width:200px;">
                            20210119
                        </td>
                        <td style="width:200px;">
                            172.18.17.220
                        </td>
                        <td style="width:100px;">
                            110/01/19<BR/>17:00:03
                        </td>
                        <td style="width:100px;">
                            110/01/19<BR/>17:04:33
                        </td>
                        <td style="width:100px;">
                            4 分
                        </td>
                    </tr>
                    
                    <tr style="text-align:center;">
                        <td style="width:200px;">
                            3G00
                        </td>
                        <td style="width:200px;">
                            20210119
                        </td>
                        <td style="width:200px;">
                            172.18.17.156
                        </td>
                        <td style="width:100px;">
                            110/01/19<BR/>15:29:18
                        </td>
                        <td style="width:100px;">
                            110/01/19<BR/>15:30:25
                        </td>
                        <td style="width:100px;">
                            1 分
                        </td>
                    </tr>
                    
                    <tr style="text-align:center;">
                        <td style="width:200px;">
                            3ICFI
                        </td>
                        <td style="width:200px;">
                            2021011915
                        </td>
                        <td style="width:200px;">
                            172.18.17.156, 共完成 244249 筆。
                        </td>
                        <td style="width:100px;">
                            110/01/19<BR/>15:29:18
                        </td>
                        <td style="width:100px;">
                            110/01/19<BR/>23:10:20
                        </td>
                        <td style="width:100px;">
                            461 分
                        </td>
                    </tr>
                    
                    <tr style="text-align:center;">
                        <td style="width:200px;">
                            CN300_BATCH
                        </td>
                        <td style="width:200px;">
                            202101191515
                        </td>
                        <td style="width:200px;">
                            SPOON排程_新北卡會員推播。
                        </td>
                        <td style="width:100px;">
                            110/01/19<BR/>15:15:31
                        </td>
                        <td style="width:100px;">
                            
                        </td>
                        <td style="width:100px;">
                            <font color="red">未完成</font>
                        </td>
                    </tr>
                    
                    <tr style="text-align:center;">
                        <td style="width:200px;">
                            CN200_BATCH
                        </td>
                        <td style="width:200px;">
                            202101191500
                        </td>
                        <td style="width:200px;">
                            SPOON排程_新北卡會員註冊API。
                        </td>
                        <td style="width:100px;">
                            110/01/19<BR/>15:00:32
                        </td>
                        <td style="width:100px;">
                            110/01/19<BR/>15:05:23
                        </td>
                        <td style="width:100px;">
                            4 分
                        </td>
                    </tr>
                    
                    <tr style="text-align:center;">
                        <td style="width:200px;">
                            P000-脆弱家庭介接
                        </td>
                        <td style="width:200px;">
                            20210119130026
                        </td>
                        <td style="width:200px;">
                            共 -1 筆，已完成 -1 筆。
                        </td>
                        <td style="width:100px;">
                            110/01/19<BR/>13:00:26
                        </td>
                        <td style="width:100px;">
                            110/01/19<BR/>13:08:22
                        </td>
                        <td style="width:100px;">
                            7 分
                        </td>
                    </tr>
                    
                    <tr style="text-align:center;">
                        <td style="width:200px;">
                            swj2-AX簡訊歷程記錄
                        </td>
                        <td style="width:200px;">
                            202101191001
                        </td>
                        <td style="width:200px;">
                            AX簡訊歷程記錄3 筆.
                        </td>
                        <td style="width:100px;">
                            110/01/19<BR/>10:01:50
                        </td>
                        <td style="width:100px;">
                            110/01/19<BR/>10:01:54
                        </td>
                        <td style="width:100px;">
                            0 分
                        </td>
                    </tr>
                    
                    <tr style="text-align:center;">
                        <td style="width:200px;">
                            Z000Mail
                        </td>
                        <td style="width:200px;">
                            20210119
                        </td>
                        <td style="width:200px;">
                            172.18.17.221
                        </td>
                        <td style="width:100px;">
                            110/01/19<BR/>08:10:04
                        </td>
                        <td style="width:100px;">
                            110/01/19<BR/>08:10:05
                        </td>
                        <td style="width:100px;">
                            0 分
                        </td>
                    </tr>
                    
                </tbody>
            </table>
        </form>
    </body>
</html>"""

        println t
    }
}