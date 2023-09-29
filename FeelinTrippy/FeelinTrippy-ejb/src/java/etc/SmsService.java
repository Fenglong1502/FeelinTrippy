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
    public static void sendSms(String messageTxt, String recipientNumber){
        String ACCOUNT_SID = "[Account_SID]";
        String AUTH_TOKEN = "[AuthToken]";

       
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(new PhoneNumber("+65"+ recipientNumber),
                new PhoneNumber("+19375836726"),
               messageTxt).create();

        System.out.println(message.getSid());
        
    }
}
