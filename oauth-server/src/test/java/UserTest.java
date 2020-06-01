import com.example.oauth.OAuthServerApplication;
import com.example.oauth.domain.UserInfo;
import com.example.oauth.service.user.UserInfoService;
import com.example.oauth.util.DigestUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @calss name UserTest
 * @description: test
 * @author: yichuan
 * @create time: 2020/06/01 04:56
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OAuthServerApplication.class)
public class UserTest {
    @Autowired
    private UserInfoService userInfoService;


    /**
     * 修改用户状态
     */
    @Test
    public void updateUser() throws Exception {
        UserInfo userInfo = userInfoService.findUserByName("admin");
        // 进行加密
        String newPassWord = DigestUtil.encrypt("123456");
        userInfo.setPassword(newPassWord);
        assert (userInfo != null);
        Byte userState = 1;  //用户状态
        userInfo.setState(userState);
        userInfoService.saveUser(userInfo);
        System.out.println("" + userInfo.toString());
    }
}
