package org.convert.encoding.big5toutf8;

import org.junit.jupiter.api.Test

import java.lang.reflect.Array;

import static org.junit.jupiter.api.Assertions.*;

class InternetExplorerTest {


    @Test
    void navigate() {

        navigateList.stream().
                filter({sourceI -> sourceI.split("navigate").size() > 1}).
                each {sourceI ->
                    println 18
                    String replaceVariable = (sourceI.split("navigate")[1])
                    String variable = replaceVariable.
                            substring(replaceVariable.indexOf("(")+1 ,replaceVariable.indexOf(")"))
                    println 22
                    String variableO = "encodeURI(${variable})"
                    println variableO
//                    println sourceI.replaceAll(variable,variableO)
                }
    }

    private List<String> navigateList = [
//        'frmHidden.navigate(sz);',
//        'frmHiddenL.navigate(sz); //IFRAME的jsp重跑',
//        'frmHidden2.navigate(url);--%>',
//'iFrameNavigate(i, encodeUrl);',
//            '//iFrameNavigate(i, sz);',
            'frmHidden.navigate("<%=tagf.getPrgPath(prgNo)%>SWJ<%=prgNo%>List.jsp?cmd=query1" + url_Q);'
    ]
}