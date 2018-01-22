package io.iamkyu;

import org.junit.Test;
import org.springframework.util.Base64Utils;

import java.util.Base64;

/**
 * @author Kj Nam
 */
public class LearningTest {

    @Test
    public void name() {
        String clientName = "i-am-client";
        String clientSecret = "i-am-secret";


        Base64.Encoder encoder = Base64.getEncoder();
        byte[] encode = encoder.encode(String.format("%s:%s", clientName, clientSecret).getBytes());

        String s = encoder.encodeToString(encode);

        // Base64.Decoder decoder = Base64.getDecoder();
        // System.out.println(new String(s.getBytes()));


        String s1 = Base64Utils.encodeToString(String.format("%s:%s", clientName, clientSecret).getBytes());
        System.out.println(
        new String(Base64Utils.decodeFromString(s1))

        );

        // String encoding = new String(Base64Utils.encode(String.format("%s:%s", clientName, clientSecret).getBytes()));
        // System.out.println(encoding);
        //
        // System.out.println(Base64Utils.decodeFromString(encoding));
    }
}
