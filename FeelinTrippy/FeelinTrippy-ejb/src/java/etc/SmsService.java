/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etc;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
/**
 *
 * @author fengl
 */
public class SmsService {
    public static void sendSms(){
        String ACCOUNT_SID = "ACde9aa9442d975921d72f94e1520309a3";
        String AUTH_TOKEN = "3f929fff19041e32307c47e9942d8aab";

       
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(new PhoneNumber("+6598315536"),
                new PhoneNumber("+19375836726"),
                "Testing Message From Twilio").create();

        System.out.println(message.getSid());
        
    }
}
