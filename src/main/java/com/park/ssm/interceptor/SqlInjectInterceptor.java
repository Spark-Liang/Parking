package com.park.ssm.interceptor;

import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class SqlInjectInterceptor implements HandlerInterceptor{//sql注入攻击拦截，拦截非法攻击字符

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String requestType = request.getHeader("X-Requested-With");//获取http请求头类型
		if("XMLHttpRequest".equals(requestType)){//比较请求头是否为XMLHttpRequest
			Enumeration<String> names = request.getParameterNames();//从request中获取参数元素  
            while(names.hasMoreElements()){  //是否存在下一个参数元素
                String name = names.nextElement();  //获取下一个元素
                String[] values = request.getParameterValues(name);  
                for(String value: values){  
                    if(judgeXSS(value.toLowerCase())){  
                    	 sendMessage(response);
                    	 return false;
                    }  
                }  
            }    
		}  
	        return true; 
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	   /** 
     * 判断参数是否含有攻击串 
     * @param value 
     * @return 
     */  
    public boolean judgeXSS(String value){  
        if(value == null || "".equals(value)){  
            return false;  
        }  
        String xssStr = "and|or|select|update|delete|drop|truncate|%20|=|-|--|;|'|%|#|+|,|//|/| |\\|!=|(|)";  
        String[] xssArr = xssStr.split("\\|");  
        for(int i=0;i<xssArr.length;i++){  
            if(value.indexOf(xssArr[i])>-1){  
                return true;  
            }  
        }  
        return false;  
          
    }  
    
    /**
     * 返回json格式的错误信息 
     * @param response
     * @throws Exception
     */
    public static void sendMessage(HttpServletResponse response) throws Exception {
    	response.setCharacterEncoding("UTF-8");    
   	    response.setContentType("application/json; charset=utf-8");  
   	    PrintWriter out = null ;  
   	    JSONObject res = new JSONObject();  
   	    res.put("error","参数含有非法攻击字符,已禁止继续访问！");  
        out = response.getWriter();  
   	    out.append(res.toString());  
    }
}
