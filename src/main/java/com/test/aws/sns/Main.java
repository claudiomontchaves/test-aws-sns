package com.test.aws.sns;

import java.util.HashMap;
import java.util.Map;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;


public class Main {

    private static final String AWS_SNS_SMS_TYPE = "AWS.SNS.SMS.SMSType";
    private static final String AWS_SNS_SMS_TYPE_VALUE = "Transactional";
    private static final String AWS_SNS_DATA_TYPE = "String";
    private static final String MESSAGE = "Hello from SNS";
    private static final String MOBILE_PHONE = "+351 999 999 999";

    public static void main(String args[]) throws Exception {
       Main main = new Main();
       main.sendSMS();
    }

    public void sendSMS() {
        try {
            AmazonSNS snsClient = AmazonSNSClient.builder().build();
            int requestTimeout = 3000;
            Map<String, MessageAttributeValue> smsAttributes = new HashMap<>();
            smsAttributes.put(AWS_SNS_SMS_TYPE, new MessageAttributeValue()
                    .withStringValue(AWS_SNS_SMS_TYPE_VALUE)
                    .withDataType(AWS_SNS_DATA_TYPE));

            PublishResult request = snsClient.publish(new PublishRequest()
                    .withMessage(MESSAGE)
                    .withPhoneNumber(MOBILE_PHONE)
                    .withMessageAttributes(smsAttributes)
                    .withSdkRequestTimeout(requestTimeout));
                    
            System.out.println(request.toString());

        } catch (Exception ex) {
            System.out.println("The SMS was not sent. Error message: " + ex.getMessage());
        }
    }
}
