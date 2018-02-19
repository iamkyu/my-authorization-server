package io.iamkyu.util;

public interface PasswordEncoder {

    String encode(CharSequence rawPassword);

    boolean match(CharSequence rawPassword, String encodedPassword);
}
