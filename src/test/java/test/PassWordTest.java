package test;
/**
* @Description 类说明: 
* @author 作者 E-mail: xiong
* @GitConfig name: xionggit  email: shao200815@163.com
* @date 创建时间：2017-10-30 15:53:50
*/

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PassWordTest {

    @Test
    public void testPassEncode() {
        BCryptPasswordEncoder util = new BCryptPasswordEncoder(4);  
        for(int i = 0 ; i < 10; i ++){  
              System. out.println(util.encode("123456" ));  
        } 
    }
    
}
