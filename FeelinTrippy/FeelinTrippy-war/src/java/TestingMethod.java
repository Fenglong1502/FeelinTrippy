
import etc.RandomPassword;
import etc.SmsService;
import session.CustomerSession;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author fengl
 */
public class TestingMethod {

    public static void main(String[] args) {
//         SmsService.sendSms();
         System.out.println("Done");
    }

    public void sendEmail() {
        String recipientEmail = "zell1502@hotmail.com";
        String newPassword = new RandomPassword().generateRandomPassword();

        String msg = "Your password has been reset! Please login with the new password:\n\"" + newPassword + "\"";
        CustomerSession.sendEmail(recipientEmail, "Reset Password", msg);

        System.out.println("Done");
    }
}
