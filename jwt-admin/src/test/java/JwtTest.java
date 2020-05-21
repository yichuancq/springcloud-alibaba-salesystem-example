import com.example.JwtApplication;
import com.example.exception.DataException;
import com.example.exception.ResultCode;
import com.example.response.ResponseResultData;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = JwtApplication.class)
@Slf4j
public class JwtTest {

    /**
     * 测试自定义异常信息
     */
    @Test
    public void testException() {
        try {
            throw new DataException(400, "我是错误信息");
        } catch (DataException ex) {
            log.error("error:{}", ex.getErrorMessage());
        }
    }

    /**
     * 测试自定义返回结果
     */
    @Test
    public void testResult() {
        ResponseResultData resultData = new ResponseResultData();
        resultData.setData("Hello");
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("intkey", 1);
        resultMap.put("strKey", "yichuan");
        resultData.setResultMap(resultMap);
        //自定义ResultCode
        ResultCode code = ResultCode.SUCCESS;
        resultData.setCode(code.code());
        resultData.setMessage(code.message());
        log.info("resultData:{}", resultData.toString());

    }

}
