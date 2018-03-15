package aier.com.servlet;

/**
 * @author: ligang
 * date: 2018/3/1
 * time: 12:09
 */
public class LoginServlet {
    private static String usernameRef;
    private static String passwordRef;

   synchronized public static void doPost(String username, String password) {
        try {
            usernameRef = username;
            if (username.equals("a")) {
                Thread.sleep(5000);
            }
            passwordRef = password;
            System.out.println("username = "+usernameRef+"   " +"pasword ="+password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
