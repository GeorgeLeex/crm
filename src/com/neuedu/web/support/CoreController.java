package com.neuedu.web.support;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neuedu.system.tools.DTOUtils;

@WebServlet("*.do")
public class CoreController extends HttpServlet {

    //class inner{}
	/**
	 * 视图前缀
	 */
    private static String prefix; 
    /**
     * 视图后缀
     */
    private static String suffix; 
    /**
     * 存储着正确登录的用户信息的集合的名字
     */
    private static String userInfoMapName;
    /**
     * 用户流水号所对应的列名
     */
    private static String sysUserId;  
    /**
     * 用户的中文名称所对应的列名
     */
    private static String sysUserName;  
    /**
     * 用户的登录名所对应的列名
     */
    private static String sysLoginName; 
    /**
     * 用户类型,即权限所对应的列名
     */
    private static String sysUserType;  

    static {
        /*DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        Document document = null;
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            InputStream resourceAsStream = inner.class.getResourceAsStream("/config.xml");
            document = documentBuilder.parse(resourceAsStream);
            NodeList elements = document.getElementsByTagName("Config");
            Node config = elements.item(0);
            prefix = config.getChildNodes().item(1).getChildNodes().item(0).getNodeValue();
            suffix = config.getChildNodes().item(3).getChildNodes().item(0).getNodeValue();
         */
         try{ 
    		ResourceBundle resourceBundle = ResourceBundle.getBundle("loginInfo");
            userInfoMapName=resourceBundle.getString("userInfoMapName");
            sysUserId=resourceBundle.getString("sysUserId");
            sysUserName=resourceBundle.getString("sysUserName");
            sysLoginName=resourceBundle.getString("sysLoginName");
            sysUserType=resourceBundle.getString("sysUserType");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	//转发的路径名
        String toURL = null;
        BaseController controller = null;
        try {
            //获取并解析URL,获取URL所对应的实际请求的Servlet名
            String requestURI = req.getRequestURI();
            int index = requestURI.lastIndexOf("/");
            String url = requestURI.substring(index + 1).replace(".do","");
            int index2 = url.indexOf("_");
            String packageName = url.substring(0, index2);
            String servletName = url.substring(index2 + 1);
            Object obj = null;
            //生成实际请求Servlet的全路径名
            String classURL = "com.neuedu.web.servlet.impl." + packageName +"." + servletName;
            System.out.println(classURL);
            //实例化对应的Servlet
            obj = Class.forName(classURL).newInstance();
            //强转为BaseController方便动态调度其实现类的方法
            controller = (BaseController)obj;
            //传入页面数据转换后的dto
            Map<String, Object> dto = this.createDTO(req);
            //打印dto
            printDto(dto);
            //传入dto
            controller.setDTO(dto);
            //动态调度execute方法, 获取不同Servlet处理完后所需转发的路径名
            toURL = controller.execute();
            //获取数据信息及其他信息集合
            Map<String, Object> attributes = controller.getAttributes();
            //遍历数据信息集合并将其保存在request域中,织入属性存储切片
            this.saveRequestAttributes(req, attributes);
            //获取将要存入session域中的数据集合
            Map<String, Object> sessionAttributes = controller.getSessionAttributes();
            //织入session切片
            saveSessionAttributes(req, sessionAttributes);
        } catch (Exception e) {
        	//向request域中添加信息
            req.setAttribute("msg", "网络故障!");
            e.printStackTrace();
        }
        //如果返回的路径名为空, 则转发至错误页面
        if (toURL == null || "".equals(toURL)) {
            toURL = "MessageBox";
        }
        //如果返回的路径中包含了 response: ,则表示向页面直接响应输出信息,通常用以响应ajax请求
        if (toURL.contains("response:")) {
        	resp.setContentType("application/json;charset=UTF-8");
        	//获取输出对象
        	PrintWriter out = resp.getWriter();
        	//获取存在响应信息集合中的要响应的信息
        	Map<String, Object> responseMessages = controller.getResponseMessages();
        	//通过Jackson 将响应信息集合转换成json数据格式的字符串
        	String json = new ObjectMapper().writeValueAsString(responseMessages);
        	//输出响应信息
        	out.write(json);
        	//清空缓存
        	out.flush();
        	out.close();
        	//如果返回的路径中包含了invalidate,则表示关闭session会话
        } else if (toURL.contains("invalidate")){
        	//关闭当前session的会话
        	req.getSession().invalidate();
        } else {
            //req.getRequestDispatcher(prefix + toURL + suffix).forward(req, resp);
        	//转发
        	req.getRequestDispatcher("/page/" + toURL + ".jsp").forward(req, resp);
        }
    }

    /**
     * 将要保存至request域中的数据添加至request域中
     * @param req
     * @param attributes
     */
    private void saveRequestAttributes(HttpServletRequest req, Map<String, Object> attributes) {
        for (Map.Entry<String, Object> entry : attributes.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            req.setAttribute(key, value);
        }
    }

    /**
     * 将要保存至session域中的数据添加至session域中
     * @param request
     * @param sessionMap
     */
    private void saveSessionAttributes(HttpServletRequest request, Map<String, Object> sessionMap) {
        HttpSession session = request.getSession();
        for (Map.Entry<String, Object> entry : sessionMap.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            session.setAttribute(key, value);
        }
    }

    /**
     * 打印dto, 验证对页面数据的封装
     * @param dto
     */
    private void printDto(Map<String, Object> dto) {
        int count = 0;
        for (Map.Entry<String, Object> entry : dto.entrySet()) {
            String key = entry.getKey();
            if ("sysUserId".equals(key) || "sysUserName".equals(key) || "sysLoginName".equals(key) || "sysUserType".equals(key)) {
                continue;
            }
            Object value = entry.getValue();
            System.out.print(key + " : " + value + "\t\t");
            if (++count % 5 == 0) {
                System.out.println();
            }
        }
    }

    /**
     * 将页面所传入的数据进行转换
     * @param request
     * @return
     */
    private Map<String, Object> createDTO(HttpServletRequest request) {
        Map<String, Object> dto = new HashMap<String, Object>();
        Map<String, String[]> paramMap = request.getParameterMap();
        if (paramMap != null) {
            for (Map.Entry<String, String[]> entry : paramMap.entrySet()) {
                String key = entry.getKey();
                String[] value = entry.getValue();
                //如果值数组长度为1, 则获取下标为0的值
                if (value.length == 1) {
                    dto.put(key, value[0]);
                } else {
                    //值数组长度不为1则将其转换为字符串
                    dto.put(key, DTOUtils.getStringFromArray(value));
                }
            }
        }
        //封装用户登录数据
        HttpSession session = request.getSession();
        //获取登录成功的用户的信息
        Map<String, Object> userinfo = (Map<String, Object>) session.getAttribute(userInfoMapName);
        if (userinfo != null) {
            //将用户登录信息封装至dto
            dto.put("sysUserId", userinfo.get(sysUserId));
            dto.put("sysUserName", userinfo.get(sysUserName));
            dto.put("sysLoginName", userinfo.get(sysLoginName));
            dto.put("sysUserType", userinfo.get(sysUserType));
        } else {
            //无登录信息,未登录状态,补充模拟信息
            dto.put("sysUserId", "-1");
            dto.put("sysUserName", "未登录");
            dto.put("sysLoginName", "notLogin");
            dto.put("sysUserType", "x");
        }
        return dto;
    }

}
