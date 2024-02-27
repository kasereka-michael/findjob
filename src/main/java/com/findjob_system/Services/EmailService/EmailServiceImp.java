package com.findjob_system.Services.EmailService;

import com.findjob_system.models.User;
import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.activation.FileDataSource;
import jakarta.mail.BodyPart;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.util.List;
import java.util.Map;



@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImp implements EmailService {
    public static final String UTF_8_ENCODING = "UTF-8";
    public static final String EMAIL_TEMPLATE = "confirmtemplate";
    public static final String TEXT_HTML_ENCODING = "text/html";
    private static final String NEW_USER_ACCOUNT_VERIFICATION = "Account verification";
    public static final String RENEW_PASSWORD_TEMPLATE = "renewPasswordTemplate";
    public static final String VERIFICATION_CODE = "verification code";
    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;
    @Value("${spring.properties.mail.mine.smtp.verify.host}")
    private String host;
    @Value("${spring.mail.username}")
    private String fromEmail;
    @Value("${spring.properties.mail.mine.smtp.redict.to.password}")
    private String hostToPasswordRenew;

    private MimeMessage getMimeMessage(){

        return mailSender.createMimeMessage();
    }

    private String getContentId(String filename){

        return "<"+ filename +">";
    }

    @Override
    @Async
    public Boolean confirmAccountEmail(String name, String to, String token) {
        try{

            MimeMessage message = getMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, UTF_8_ENCODING);
            helper.setPriority(1);
            helper.setSubject(NEW_USER_ACCOUNT_VERIFICATION);
            helper.setFrom(fromEmail);
            helper.setTo(to);

            Context context = new Context();
            context.setVariables(Map.of("name", name,"url", getVerificationUrl(host,token)));

//            add html email body
            String text = templateEngine.process(EMAIL_TEMPLATE, context);
            MimeMultipart mimeMultipart = new MimeMultipart( "related");
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(text,TEXT_HTML_ENCODING);
            mimeMultipart.addBodyPart(messageBodyPart);

            // TODO: i will work on adding the image or logo to the html being sent later
//            log.info("the file path in the email service "+ path);
////            add image to the email body
//            BodyPart imageBodyPart = new MimeBodyPart();
//            DataSource datasource = new FileDataSource(path);
//
//   BodyPart imageBodyPart = new MimeBodyPart();
//            DataSource datasource = new FileDataSource(System.getProperty("user.home") +"/Downloads/KING.jpg");
//
//
//            imageBodyPart.setDataHandler(new DataHandler(datasource));
//            imageBodyPart.setHeader("Content-ID","image");
//            mimeMultipart.addBodyPart(imageBodyPart);
//
//            mimeMultipart.addBodyPart(imageBodyPart);

////            adding content and file in to the message
            message.setContent(mimeMultipart);



            mailSender.send(message);

        }catch(Exception exception){
            System.out.print("the error is here "+exception.getMessage());
            throw new RuntimeException("the error "+exception.getMessage());
        }

        return null;
    }

    @Override
    public void notificationOfPostedJob(String name, List<User> toList, String token) {

    }

    @Override
    public Boolean sendOPtCode(String name, String to, int opt) {

        try{

            MimeMessage message = getMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, UTF_8_ENCODING);
            helper.setPriority(1);
            helper.setSubject(VERIFICATION_CODE);
            helper.setFrom(fromEmail);
            helper.setTo(to);
            Context context = new Context();
            context.setVariables(Map.of("name", name,"url", hostToPasswordRenew,"randomnumber",opt));

//            add html email body
            String text = templateEngine.process(RENEW_PASSWORD_TEMPLATE, context);
            MimeMultipart mimeMultipart = new MimeMultipart( "related");
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(text,TEXT_HTML_ENCODING);
            mimeMultipart.addBodyPart(messageBodyPart);

            // TODO: i will work on adding the image or logo to the html being sent later
//            log.info("the file path in the email service "+ path);
////            add image to the email body
//            BodyPart imageBodyPart = new MimeBodyPart();
//            DataSource datasource = new FileDataSource(path);
//
//   BodyPart imageBodyPart = new MimeBodyPart();
//            DataSource datasource = new FileDataSource(System.getProperty("user.home") +"/Downloads/KING.jpg");
//
//
//            imageBodyPart.setDataHandler(new DataHandler(datasource));
//            imageBodyPart.setHeader("Content-ID","image");
//            mimeMultipart.addBodyPart(imageBodyPart);
//
//            mimeMultipart.addBodyPart(imageBodyPart);

////            adding content and file in to the message
            message.setContent(mimeMultipart);



            mailSender.send(message);

        }catch(Exception exception){
            System.out.print("the error is here "+exception.getMessage());
            throw new RuntimeException("the error "+exception.getMessage());
        }

        return null;
    }

    private static String getVerificationUrl(String host, String token) {
        return host + "/api/users?token=" +token;
    }
}
