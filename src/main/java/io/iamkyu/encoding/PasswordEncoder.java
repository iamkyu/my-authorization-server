package io.iamkyu.encoding;

public interface PasswordEncoder {

    String encode(CharSequence rawPassword);

    boolean match(CharSequence rawPassword, String encodedPassword);
}
