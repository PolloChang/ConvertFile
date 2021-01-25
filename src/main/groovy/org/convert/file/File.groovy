package org.convert.file

import org.convert.encoding.big5toutf8.InternetExplorer
import org.mozilla.universalchardet.UniversalDetector

import java.util.stream.Collectors

/**
 * 處理檔案
 */
class File {

    /**
     * 轉檔與處理檔案內容
     * @param startPath 來源路竟
     * @param purposePath 目標路徑
     * @param trunEncode
     * @param replaceStringL
     * @return
     */
    void convertAndReplace(
            String startPath,
            String purposePath,
            String trunEncode,
            LinkedHashMap replaceStringL
    ){
        gFileList.stream()
                .filter({ fileSourceDirI -> fileSourceDirI != "" &&  checkEncoding(fileSourceDirI) != null})
                .filter({fileSourceDirI ->  checkEncoding(fileSourceDirI) != null})
                .forEach({ fileSourceDirI ->
                    String goalFileDir = fileSourceDirI.replace(startPath,purposePath);

                    creatFile(goalFileDir)

                    convertFileCode(
                            fileSourceDirI,
                            goalFileDir,
                            checkEncoding(fileSourceDirI),
                            trunEncode,
                            replaceStringL
                    )
                })

        if(errFileIEL.size() > 0){
            println "以下檔案需人工檢視：encodeURI "
            errFileIEL.each {
                println it
            }
            println "以上檔案需人工檢視；encodeURI"
        }
    }

    private int findCount = 0;

    void setFindCount(int i){
        findCount = findCount+i
    }

    int getFindCount(){
        return findCount
    }

    private List<String> findList = new ArrayList<String>()

    void setFindList(String fileI){
        if(findList.indexOf(fileI)==-1){
            findList.add(fileI)
        }
    }

    List<String> getFindList(){
        return findList
    }


    /** 檔案集合 */
    public List<String> gFileList = new ArrayList<String>()

    /**
     * IE 轉錯需人工處理
     * @return
     */
    List<String> getErrFileIEL() {
        return errFileIEL
    }

    void setErrFileIEL(String errFileI) {
        if(errFileIEL.indexOf(errFileI) == -1){
            errFileIEL.add(errFileI)
        }
    }
    private List<String> errFileIEL = new ArrayList<String>()

    /**
     * 取得要處理的檔案
     * @param startPath 起始路徑
     * @param fileFilterL 篩選附檔名
     * @param exPathL 排除路徑
     */
    void getPathDir(String startPath,String[] fileFilterL,String[] exPathL){
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
    void getPathDir(
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
    String checkEncoding(String filePath) {
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
    void creatFile(String path) throws IOException {
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
    void convertFileCode(
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
    void convertFileCode(
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


    /**
     * 轉碼並處理
     * @param fileSourceDir 來源檔案
     * @param goalFileDir 輸出目標檔案
     * @param code 原始檔案編碼
     * @param convertEncode 輸出檔案編碼
     * @param replaceStringL 要取代的字串
     */
    void convertFileCode(
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

        InternetExplorer ie = new InternetExplorer()

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
                    pw.write("<%--${comments}--%>\r\n")
                    break
            }

        }

        lineL.each { lineI ->

            String writerI

            /*針對IE在UTF8環境下傳送中文的亂碼*/
            if(convertEncode == "UTF-8" && fileTyle == ".jsp"){
                try{
                    writerI = ie.navigate(lineI)
                }
                catch (Exception ex){
                    writerI = lineI
                    setErrFileIEL(fileSourceDir)
                }
            }
            else{
                writerI = lineI
            }
            pw.write(writerI)
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
    List<String> searchFileContent(String filePath,String fileCode, String keyWord) throws Exception {

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
                setFindList(filePath)
                findLineL.add(lineI)
            }

        return findLineL
    }

    /**
     * 尋找資料，模糊查詢清單
     * @param filePath 目標檔案路徑
     * @param fileCode 檔案編碼
     * @param keyWordL 查詢關鍵字清單
     * @return 有找到
     * @throws IOException
     */
    List<String> searchFileContent(String filePath,String fileCode, List<String> keyWordL) throws Exception {
        List<String> findLineL = []

        keyWordL.each { keyWordI ->
            findLineL.addAll(searchFileContent(
                    filePath,
                    fileCode,
                    keyWordI
            ))

        }
        return findLineL
    }
}
