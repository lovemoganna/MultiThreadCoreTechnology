package aier.com.locktest;

/**
 * @author: ligang
 * date: 2018/3/15
 * time: 11:22
 */
public class SynchronizedObject {
    private String password = "a";
    private String username = "aa";

    public String getPassword() {
        return password;
    }

    public SynchronizedObject setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public SynchronizedObject setUsername(String username) {
        this.username = username;
        return this;
    }

    synchronized public void printString(String username, String password) {
        try {
            this.username = username;
            Thread.sleep(100000);
            this.password= password;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
