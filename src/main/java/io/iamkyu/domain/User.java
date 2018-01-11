package io.iamkyu.domain;

/**
 * @author Kj Nam
 */
public class User {

    private long no;

    private String name;

    private String password;

    public User(long no, String name, String password) {
        this.no = no;
        this.name = name;
        this.password = password;
    }

    public long getNo() {
        return no;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
