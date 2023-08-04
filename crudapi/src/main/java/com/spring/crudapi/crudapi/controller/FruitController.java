package com.spring.crudapi.crudapi.controller;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.spring.crudapi.crudapi.model.ApiResponse;
import com.spring.crudapi.crudapi.service.FruitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RestController
public class FruitController {

    @Autowired
    FruitService fruitService;

    @GetMapping(value = "/hello")
    public String hello() {
        log.info("--> FruitController.hello() reached");
        return "Hello Fruit Lover";
    }

    @GetMapping(value = "/sendmail")
    public String sendmail() {
        log.info("--> Sending email from sendgrid (1st method)");
        sendMailViaSendgrid();
        return "end1";
    }

    private void sendMailViaSendgrid() {

        String SENDGRID_API_KEY = "SEND_GRID_KEY_GOES_HERE";

        Email from = new Email("test@example.com");
        String subject = "Sending with Twilio SendGrid is Fun";
        Email to = new Email("samplemail@email.com");
        Content content = new Content("text/plain", "and easy to do anywhere, even with Java");
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(System.getenv(SENDGRID_API_KEY));
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping(value = "/sendmail2")
    public String sendmail2() {
        log.info("--> Sending email from sendgrid (2nd method)");
        sendMailViaSendgrid2();
        return "end2";
    }

    public void sendMailViaSendgrid2() {
        try {
            String SENDGRID_API_KEY = "SENDGRID_KEY_GOES_HERE"; //69 alphanumeric with dots
            SendGrid sg = new SendGrid(SENDGRID_API_KEY);
            Request request = new Request();
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            //request.setBody("{\"personalizations\":[{\"to\":[{\"email\":\"anilkumar.jupalli@ontario.ca\"}],\"subject\":\"Sending with Twilio SendGrid is Fun\"}],\"from\":{\"email\":\"test@example.com\"},\"content\":[{\"type\":\"text/plain\",\"value\": \"and easy to do anywhere, even with Java\"}]}");
            String mailTo = "samplemail@email.com";
            String mailSubject = "Sending with Twilio SendGrid is Fun!";
            String mailFrom = "test@example.com";
            String mailContent = "and easy to do anywhere, even with Java aug 03, 2023 test1234!";
            request.setBody("{\"personalizations\":[{\"to\":[{\"email\":\" " + mailTo +
                    " \"}],\"subject\":\" " + mailSubject +
                    " \"}],\"from\":{\"email\":\" " + mailFrom +
                    " \"},\"content\":[{\"type\":\"text/plain\",\"value\": \" "+mailContent+" \"}]}");
            Response response = sg.api(request);
            System.out.println("status: "+response.getStatusCode());
            System.out.println("body: "+response.getBody());
            System.out.println("headers: "+response.getHeaders());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @GetMapping(value = "/fruit/{id}")
    public ApiResponse getFruitById(@PathVariable("id") int id){
        log.info("--> FruitController.getFruitById() reached");
        return fruitService.getFruitById(id);
    }

    @GetMapping(value = "/fruits")
    public ApiResponse getFruits() {
        log.info("--> FruitController.getFruits() reached");
        return fruitService.getFruits();
    }



}
