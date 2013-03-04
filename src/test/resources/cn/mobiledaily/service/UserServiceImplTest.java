package cn.mobiledaily.service;

import cn.mobiledaily.domain.identity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

@ContextConfiguration(locations = "classpath*:/applicationContext-test.xml")
public class UserServiceImplTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private UserService userService;

    @Test
    public void testRegister() throws Exception {
        userService.register("username", "password", "test@email");
//        User user = userService.
//        assertThat("register user", user, notNullValue());
//        assertThat("password should be encrypted", user.getPassword(), not("password"));
    }
}
