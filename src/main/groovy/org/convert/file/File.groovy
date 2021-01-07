package org.convert.file

import org.mozilla.universalchardet.UniversalDetector

import java.util.stream.Collectors

/**
 * 處理檔案
 */
class File {

    /** 檔案集合 */
    public static List<String> gFileList = new ArrayList<String>();

    /**
     * 取得要處理的檔案
     * @param startPath 起始路徑
     * @param fileFilterL 篩選附檔名
     * @param exPathL 排除路徑
     */
    static void getPathDir(String startPath,String[] fileFilterL,String[] exPathL){
        fileFilterL.each { fileFilterI ->
            getPathDir(startPath,fileFilterI,exPathL)
        }

    }

    /**
     * 取得要處理的檔案
     * @param startPath 起始路徑
     * @param fileFilter 篩選附檔名
     * @param exPathL 排除路徑
     */
    static void getPathDir(
            String startPath,
            String fileFilter,
            String[] exPathL){
        java.io.File topPathFile = new java.io.File( startPath );
        String[] filePathArray = topPathFile.list()
        if(filePathArray != null){
            for(int i=0 ; i< filePathArray.length ; i++){
                String filePath = ""
                //排除路徑
                if(!(filePathArray[i] in exPathL)){
                    filePath = startPath+"/"+ filePathArray[i]
                }

                java.io.File file = new java.io.File( filePath)
                //判斷是否為檔案
                if(file.isFile()){
                    if( fileFilter.equals("*") || file.getPath().indexOf(fileFilter) >= 0 ){
                        if(gFileList.indexOf(startPath + "/" + filePathArray[i]) == -1){
                            gFileList.add( startPath + "/" + filePathArray[i] )
                        }
                    }
                }
                else {
                    getPathDir(filePath,fileFilter,exPathL)
                }
            }
        }
        else {
            if(gFileList.indexOf(startPath) == -1){
                gFileList.add( startPath )
            }
        }

    }

    /**
     * 確認檔案編碼
     * @param filePath 檔案路徑
     * @return
     */
    static String checkEncoding(String filePath) {
        /* 讀取檔案 */
        FileInputStream fis = new FileInputStream(filePath);
        /* 建立分析器 */
        UniversalDetector detector = new UniversalDetector(null);

        int nread;
        byte[] buf = new byte[4]
        while ((nread = fis.read(buf)) > 0 && !detector.isDone()) {
            /* 分析資料 */
            detector.handleData(buf, 0, nread)
        }
        fis.close()
        detector.dataEnd()
        /* 取得編碼格式 */
        String charset = detector.getDetectedCharset()

        /*預設ANSI*/
        if(charset == null){
            charset = "Cp1252"
        }

        return charset
    }

    /**
     * 建立檔案
     * @param path
     * @throws IOException
     */
    static void creatFile(String path) throws IOException {
        String checkPath = path.substring(0,path.lastIndexOf("/"));
        java.io.File newPath = new java.io.File( checkPath );
        //判斷目錄是否存在，不存在則建立
        if(!newPath.exists()){
            newPath.mkdirs();
        }
        //判斷檔案是否存在，不存在則建立
        java.io.File newFile = new java.io.File( path );
        if(!newFile.exists()){
            newFile.createNewFile();
        }
    }

    /**
     * 轉碼程式
     * @param fileSourceDir 檔案來源
     * @param goalFileDir 存放檔案目標路徑
     * @param code 原檔編碼
     * @param convertEncode 目標編碼
     * @throws IOException
     */
    static void convertFileCode(
            String fileSourceDir,
            String goalFileDir,
            String code,
            String convertEncode
    ) throws IOException {

        BufferedReader br = new BufferedReader( new InputStreamReader(new FileInputStream(fileSourceDir), code))

        PrintWriter pw = new PrintWriter( new OutputStreamWriter(new FileOutputStream(goalFileDir), convertEncode))

        String line

        while ((line = br.readLine()) != null) {
            pw.write(line+"\r\n")
        }

        pw.flush()
        pw.close()

    }

    /**
     * 轉碼程式
     * @param fileSourceDir 檔案來源
     * @param goalFileDir 存放檔案目標路徑
     * @param code 原檔編碼
     * @param convertEncode 目標編碼
     * @throws IOException
     */
    static void convertFileCode(
            String fileSourceDir,
            String goalFileDir,
            String code,
            String convertEncode,
            String replaceSource,
            String replaceGoal
    ) throws IOException {
        BufferedReader br = new BufferedReader( new InputStreamReader(new FileInputStream(fileSourceDir), code))

        PrintWriter pw = new PrintWriter( new OutputStreamWriter(new FileOutputStream(goalFileDir), convertEncode))

        String line

        while ((line = br.readLine()) != null) {
            if(line.indexOf(replaceSource)>-1){
                pw.write(line.replace(replaceSource,replaceGoal))
            }
            else{
                pw.write(line)
            }
            pw.write("\r\n")
        }

        pw.flush()
        pw.close()
    }


    static void convertFileCode(
            String fileSourceDir,
            String goalFileDir,
            String code,
            String convertEncode,
            LinkedHashMap replaceStringL
    ) {

        BufferedReader br = new BufferedReader( new InputStreamReader(new FileInputStream(fileSourceDir), code))

        PrintWriter pw = new PrintWriter( new OutputStreamWriter(new FileOutputStream(goalFileDir), convertEncode))

        List<String> lines = br.lines().collect(Collectors.toList())

        String fileTyle = fileSourceDir.substring(fileSourceDir.lastIndexOf("."),fileSourceDir.length());

        String comments = "新北市社政14期DB2升級轉碼批次處理"

        boolean hadReplace = false

        List<String> lineL = new ArrayList<String>()
        lines.stream()
            .filter({lineI -> lineI != null})
            .each { lineI ->
                replaceStringL.each {key,val ->
                    if(lineI.indexOf(key as String) > -1){
                        lineI = lineI.replace(key as String, val as String)
                        hadReplace = true
                    }
                }
                lineL.add(lineI)
            }

        if(hadReplace){
            switch (fileTyle){
                case ".java":
                    pw.write("//${comments}\r\n")
                    break
                case ".jsp":
                    pw.write("<%--${comments}-->\r\n")
                    break
            }

        }

        lineL.each { lineI ->
            pw.write(lineI)
            pw.write("\r\n")
        }

        pw.flush()
        pw.close()

    }


    /**
     * 尋找資料，模糊查詢
     * @param filePath 目標檔案路徑
     * @param fileCode 檔案編碼
     * @param keyWord 查詢關鍵字
     * @return 有找到
     * @throws IOException
     */
    static List<String> searchFileContent(String filePath,String fileCode, String keyWord) throws Exception {

        if(filePath == null){throw new Exception("The function searchFileContent param [filePath] can't null ");}
        if(fileCode == null){throw new Exception("The function searchFileContent param [fileCode] can't null ");}
        if(keyWord == null){throw new Exception("The function searchFileContent param [keyWord] can't null ");}

        keyWord = keyWord.toUpperCase();
        /*去除空格*/
        keyWord = keyWord.replaceAll("\\s*", "")


        List<String> findLineL = []

        BufferedReader br = new BufferedReader( new InputStreamReader(new FileInputStream(filePath), fileCode));

        List<String> lines = br.lines().collect(Collectors.toList())

        lines.stream()
            .filter({lineI -> lineI.toUpperCase().replaceAll("\\s*", "").indexOf(keyWord) != -1})
            .filter({lineI->findLineL.indexOf(lineI) == -1})
            .each {lineI ->
                findLineL.add(lineI)
            }

        return findLineL
    }
}
