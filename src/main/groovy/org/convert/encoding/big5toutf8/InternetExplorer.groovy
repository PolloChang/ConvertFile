package org.convert.encoding.big5toutf8

import java.util.regex.PatternSyntaxException

/**
 * 處理IE
 */
class InternetExplorer {

    /**
     * 解決iframe.navigate 傳遞中文亂碼
     * <p>
     * 解決前的request URL:
     * ```URL=
     * http://localhost:8880/test?wfName=ä½æ¶å¥æ¶
     * ```
     * 解決後後requset URL:
     * ```URL=
     * http://localhost:8880/test?wfName=wfName=%E4%BD%8E%E6%94%B6%E5%85%A5%E6%88%B6
     * ```
     * </p>
     * @param sourceLine 要處理的程式
     * @return 處好的程式
     */
    String navigate(String sourceLine) throws PatternSyntaxException{
        String returnVal
        if(sourceLine.split("navigate").size() > 1){
            String replaceVariable = (sourceLine.split("navigate")[1])
            String variable = replaceVariable.
                    substring(replaceVariable.indexOf("(")+1 ,replaceVariable.indexOf(")"))
            String variableO = "encodeURI(${variable})"
            returnVal = sourceLine.replaceAll(variable,variableO)
        }
        else{
            returnVal = sourceLine
        }
        return returnVal
    }
}
