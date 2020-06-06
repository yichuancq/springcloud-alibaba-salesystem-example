import com.example.keygen.core.common.Result;
import com.example.keygen.core.keyGenApplication;
import com.example.keygen.core.service.IDGen;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @calss name SpringIDGenServiceTest
 * @description:
 * @author: yichuan
 * @create time: 2020/06/06 16:23
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = keyGenApplication.class)
public class SpringIDGenServiceTest {
    @Autowired
    private IDGen idGen;

    @Test
    public void testGetId() {
        idGen.init();
        for (int i = 0; i < 100; i++) {
            Result r = idGen.get("pay");
            System.out.println(r);
        }
    }
}
