/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Entite;

import com.twilio.Twilio;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;



/**
 *
 * @author Karim
 */
public class TwilioSms {

    public static final String ACCOUNT_SID = "AC5fe6b66c42cabe963bfb7fb50f93463d";
    public static final String AUTH_TOKEN = "b4b7947071993e38a5fe9dfc5d7fbf24";

    public void sendSms(String body) {
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message.creator(new PhoneNumber("+21624273476"),
        new PhoneNumber("+16615230324"), 
        body).create();
    }
}
