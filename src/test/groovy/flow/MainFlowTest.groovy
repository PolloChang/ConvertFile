package flow;

import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.*;

class MainFlowTest {

    //已處理的檔案數
    private static int trunFiles;

    //參數設定:設定轉碼目標
    private final static String trunEncode = "UTF-8"
    //參數設定:起始目錄
    private final static String startPath = "D:\\Wezoomtek\\swjweb"
    //參數設定:篩選檔案
    private final static String[] pFilterL = [
            ".jsp",
            ".java",
            ".js",".css",".txt",".properties"
            ]
    //參數設定:目的路徑
    private final static String purposePath = "C:\\Wezoomtek\\swjweb"
    //排除路徑
    private final static String[] exPathL = [
            "build","dist","nbproject","PA-DOC",".git"
            ]
    private final static LinkedHashMap replaceStringL = [
            'pageEncoding="big5"':'pageEncoding="UTF-8"',
            'pageEncoding="Big5"':'pageEncoding="UTF-8"',
            'pageEncoding="BIG5"':'pageEncoding="UTF-8"',
            'pageEncoding = "big5"':'pageEncoding="UTF-8"',
            'pageEncoding = "Big5"':'pageEncoding="UTF-8"',
            'pageEncoding = "BIG5"':'pageEncoding="UTF-8"',
            'pageEncoding="ms950"':'pageEncoding="UTF-8"',
            'pageEncoding="Ms950"':'pageEncoding="UTF-8"',
            'pageEncoding="MS950"':'pageEncoding="UTF-8"',
            'charset="big5">':'charset="UTF-8">',
            'charset="big5">;':'charset="UTF-8">;',
            'request.setCharacterEncoding("big5");':'request.setCharacterEncoding("UTF-8");',
            'request.setCharacterEncoding("BIG5");':'request.setCharacterEncoding("UTF-8");',
            '"text/html;charset=ms950"'         :'"text/html; charset=UTF-8"',
            '"text/html;charset=Ms950"'         :'"text/html; charset=UTF-8"',
            '"text/html;charset=MS950"'         :'"text/html; charset=UTF-8"',
            '"text/html; charset=ms950"'        :'"text/html; charset=UTF-8"',
            '"text/html; charset=Ms950"'        :'"text/html; charset=UTF-8"',
            '"text/html; charset=MS950"'        :'"text/html; charset=UTF-8"',
            '"text/html; charset=big5"'         :'"text/html; charset=UTF-8"',
            '"text/html; charset=Big5"'         :'"text/html; charset=UTF-8"',
            '"text/html; charset=BIG5"'         :'"text/html; charset=UTF-8"',
            '"text/html;charset=big5"'          :'"text/html; charset=UTF-8"',
            '"text/html;charset=Big5"'          :'"text/html; charset=UTF-8"',
            '"text/html;charset=BIG5"'          :'"text/html; charset=UTF-8"',
            '"text/html; charset = big5"':'contentType="text/html; charset=UTF-8"',
            'response.setContentType("text/html; charset=big5");':
            'response.setContentType("text/html; charset=UTF-8");',
            'request.getContextPath()%>/resources/js/function.min.js" charset="big5"':
            'request.getContextPath()%>/resources/js/function.min.js" charset="UTF-8"',
            'src="<%=request.getContextPath()%>/resources/ajax/LoadingPage.js" charset="big5"':
            'src="<%=request.getContextPath()%>/resources/ajax/LoadingPage.js" charset="UTF-8"',
            '<%=request.getContextPath()%>/resources/js/map/map.layer.js" charset="BIG5"':
            '<%=request.getContextPath()%>/resources/js/map/map.layer.js" charset="UTF-8"',
            '<%=request.getContextPath()%>/resources/js/map/map.ui.js" charset="BIG5"':
            '<%=request.getContextPath()%>/resources/js/map/map.ui.js" charset="UTF-8"',
            '<%=request.getContextPath()%>/resources/js/map/map.func.js" charset="BIG5"':
            '<%=request.getContextPath()%>/resources/js/map/map.func.js" charset="UTF-8""',
            '<%=request.getContextPath()%>/resources/js/map/map.func.searchAGY.js" charset="BIG5"':
            '<%=request.getContextPath()%>/resources/js/map/map.func.searchAGY.js" charset="UTF-8""',
            '<%=request.getContextPath()%>/resources/js/map/map.func.buffer.js" charset="BIG5"':
            '<%=request.getContextPath()%>/resources/js/map/map.func.buffer.js" charset="UTF-8""',
            '<%=contextPath + srcPath + "/js/function.min.js?version=20150608"%>" charset="big5"':
            '<%=contextPath + srcPath + "/js/function.min.js?version=20150608"%>" charset="UTF-8""',
            'src="<%=request.getContextPath()%>/resources/js/Chart.min.js" charset="big5"':
            'src="<%=request.getContextPath()%>/resources/js/Chart.min.js" charset="UTF-8""',
            'out.println("<script language=\\"JavaScript\\" src=\\"" + contextPath + srcPath + "/js/CheckData.js"+version+"\\" charset=\\"big5\\"></script>");':
            'out.println("<script language=\\"JavaScript\\" src=\\"" + contextPath + srcPath + "/js/CheckData.js"+version+"\\" charset=\\"UTF-8\\"></script>");',
            'out.println("<script language=\\"JavaScript\\" src=\\"" + contextPath + srcPath + "/js/DateUtil.js"+version+"\\" charset=\\"big5\\"></script>");':
            'out.println("<script language=\\"JavaScript\\" src=\\"" + contextPath + srcPath + "/js/DateUtil.js"+version+"\\" charset=\\"UTF-8\\"></script>");',
            'out.println("<script language=\\"JavaScript\\" src=\\"" + contextPath + srcPath + "/js/jquery.validationEngine-zh.js"+version+"\\" charset=\\"big5\\"></script>");':
            'out.println("<script language=\\"JavaScript\\" src=\\"" + contextPath + srcPath + "/js/jquery.validationEngine-zh.js"+version+"\\" charset=\\"UTF-8\\"></script>");',
            'out.println("<script language=\\"JavaScript\\" src=\\"" + contextPath + srcPath + "/js/function.min.js?version=20150608\\" charset=\\"big5\\"></script>");':
            'out.println("<script language=\\"JavaScript\\" src=\\"" + contextPath + srcPath + "/js/function.min.js?version=20150608\\" charset=\\"UTF-8\\"></script>");',
            'out.println("<script language=\\"JavaScript\\" src=\\"" + contextPath + srcPath + "/ajax/LoadingPage.js"+version+"\\" charset=\\"big5\\"></script>");':
            'out.println("<script language=\\"JavaScript\\" src=\\"" + contextPath + srcPath + "/ajax/LoadingPage.js"+version+"\\" charset=\\"UTF-8\\"></script>");',
            'out.println("<script language=\\"JavaScript\\" src=\\"" + contextPath + srcPath + "/js/jquery.validationEngine.3.0.0-zh_TW.js"+version+"\\" charset=\\"big5\\"></script>");':
            'out.println("<script language=\\"JavaScript\\" src=\\"" + contextPath + srcPath + "/js/jquery.validationEngine.3.0.0-zh_TW.js"+version+"\\" charset=\\"UTF-8\\"></script>");',
            ]

    /**
     * IE轉碼
     */
    @Test
    void convertBig5ToUTF8InIE() {

    }
}