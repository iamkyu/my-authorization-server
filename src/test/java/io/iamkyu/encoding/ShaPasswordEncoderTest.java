package io.iamkyu.encoding;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ShaPasswordEncoderTest {

    private PasswordEncoder shaPasswordEncoder;

    @Before
    public void setUp() {
        shaPasswordEncoder = new ShaPasswordEncoder();
    }

    @Test
    public void 비밀번호가_올바르면_true를_반환() {
        //given
        String string = "client-secret";
        String hash = "fdce8e4a65b70d186bd77cba2e0c580dcf1c6497da9f1b70eed849497e1f8ba2";

        //when then
        Assert.assertTrue(shaPasswordEncoder.match(string, hash));
    }


    @Test
    public void 비밀번호가_틀리면_false를_반환() {
        //given
        String string = "client-secret";
        String hash = "fdce8e4a65b70d186bd77cba2e0c580dcf1c6497da9f1b70eed849497e1f8bA2";

        //when then
        Assert.assertFalse(shaPasswordEncoder.match(string, hash));
    }
}

