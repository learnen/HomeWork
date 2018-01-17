package result;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Result;
import com.zhuang.util.VerifyCode;
import org.apache.struts2.ServletActionContext;

import java.io.IOException;

import static com.opensymphony.xwork2.Action.SUCCESS;

public class CodeImageResult implements Result {

    public static String code;

    @Override
    public void execute(ActionInvocation actionInvocation) throws Exception {
        String imageCode = VerifyCode.getInstance().output(
                ServletActionContext.getResponse().getOutputStream()
        );
        code = imageCode;
    }
}
