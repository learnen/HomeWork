package interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import javax.servlet.http.HttpServletRequest;

public class MyInterceptor extends AbstractInterceptor {

    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        Object user = request.getSession().getAttribute("user");
        String requestURI = request.getRequestURI();
        System.out.println(requestURI);
        if (!requestURI.equals("/user/login") && !requestURI.equals("/user/register") && user == null){
            return "login";
        }else {
            actionInvocation.invoke();
        }
        return "success";
    }

}
