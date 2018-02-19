package io.iamkyu.util;

import java.security.MessageDigest;

public class ShaPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(rawPassword.toString().getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();

        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

    /**
     * @link <a href="https://codahale.com/a-lesson-in-timing-attacks/">A Lesson In Timing Attacks</a>
     */
    @Override 
    public boolean match(CharSequence rawPassword, String encodedPassword) { 
        final byte[] rawPasswordBytes = encode(rawPassword).getBytes();
        final byte[] encodedPasswordBytes = encodedPassword.getBytes();
        
        return match(rawPasswordBytes, encodedPasswordBytes); 
    }

    private boolean match(byte[] expect, byte[] actual) { 
        if (actual.length != expect.length) {
            return false;
        }

        int result = 0;

        // constant time algorithm
        for(int i = 0 ; i < actual.length ; i++) {
            result |= actual[i] ^ expect[i];
        }
        
        return result == 0;
    }
}
