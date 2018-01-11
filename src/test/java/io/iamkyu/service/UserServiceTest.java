package io.iamkyu.service;

import io.iamkyu.domain.User;
import io.iamkyu.dto.UserCredentials;
import io.iamkyu.exception.BadCredentialsException;
import io.iamkyu.repository.UserJdbcRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.mockito.Mockito.when;

/**
 * @author Kj Nam
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest {

    @Mock
    private UserJdbcRepository userRepository;

    private UserService userService;

    private String userName = "foo";
    private String hashOfPassword = "fcde2b2edba56bf408601fb721fe9b5c338d10ee429ea04fae5511b68fbf8fb9";

    @Before
    public void setUp() {
        userService = new UserService(userRepository);
    }

    @Test
    public void 사용자이름과_비밀번호가_맞으면_인증에_성공한다() throws Exception {
        //given
        UserCredentials userCredentials = new UserCredentials(userName, hashOfPassword);
        User user = new User(1L, userName, hashOfPassword);
        when(userRepository.findByUserName(userName))
                .thenReturn(Optional.of(user));

        //when
        User authenticatedUser = userService.userAuthentication(userCredentials);

        //then
        Assert.assertThat(authenticatedUser, instanceOf(User.class));
        Assert.assertNotNull(authenticatedUser.getNo());
        Assert.assertEquals(userCredentials.getName(), authenticatedUser.getName());
        Assert.assertEquals(userCredentials.getPassword(), authenticatedUser.getPassword());
    }

    @Test(expected = BadCredentialsException.class)
    public void 아이디가_존재하지_예외가_발생한다() throws Exception {
        //given
        UserCredentials userCredentials = new UserCredentials(null, null);

        //when
        userService.userAuthentication(userCredentials);

        //then
        //exception
    }

    @Test(expected = BadCredentialsException.class)
    public void 비밀번호가_맞지_않으면_예외가_발생한다() throws Exception {
        when(userRepository.findByUserName(userName))
                .thenReturn(Optional.of(new User(1L, userName, hashOfPassword)));

        UserCredentials badCredentials = new UserCredentials(userName, "hunter2");

        //when
        User authenticatedUser = userService.userAuthentication(badCredentials);

        //then
        //exception
    }
}