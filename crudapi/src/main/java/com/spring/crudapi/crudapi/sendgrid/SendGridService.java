package com.spring.crudapi.crudapi.sendgrid;


import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SendGridService {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(SendGridService.class);

    @Value("${email.props.sender}")
    private String emailSender;

    public void sendEmailUsingSendGrid(String to, String subject, String content){
        try {
            SendGrid sg = new SendGrid("SENDGRID_KEY_GOES_HERE");
            Request request = new Request();
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");

            //String mailTo = "anilkumar.jupalli@ontario.ca";
            //String mailSubject = "Sending with Twilio SendGrid is Fun!";
            //String mailFrom = "test@example.com";
            //String mailContent = "and easy to do anywhere, even with Java!";

            request.setBody("{\"personalizations\":[{\"to\":[" + getRecipientsString(to) +
                    " ],\"subject\":\" " + subject +
                    " \"}],\"from\":{\"email\":\" " + emailSender +
                    " \"},\"content\":[{\"type\":\"text/plain\",\"value\": \" "+content+" \"}]}");
            Response response = sg.api(request);

            if (response.getStatusCode() >= 200 && response.getStatusCode() < 300) {
                logger.info("SendGrid Email sent successfully!");
            } else {
                logger.info("SendGrid Failed to send Email");
            }

            logger.info("SendGrid Email Response Status Code: " + response.getStatusCode());
            logger.info("SendGrid Email Response Body: " + response.getBody());
            logger.info("SendGrid Email Response Headers: " + response.getHeaders());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getRecipientsString(String commaSeparatedEmails) {
        String[] emails = commaSeparatedEmails.split(";");
        StringBuilder recipientsBuilder = new StringBuilder();
        for (String email : emails) {
            recipientsBuilder.append("{\"email\":\"").append(email.trim()).append("\"},");
        }
        //Removing the trailing comma/semicolon
        recipientsBuilder.setLength(recipientsBuilder.length() - 1);
        logger.info("Recipients: "+ recipientsBuilder.toString());
        return recipientsBuilder.toString();
    }

}
