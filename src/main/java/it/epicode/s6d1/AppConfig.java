package it.epicode.s6d1;


import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@PropertySource("application.properties")
public class AppConfig {

    @Bean
    public Cloudinary cloudinary(@Value("${cloudinary.cloud_name}")String name,
                                 @Value("${cloudinary.api_key}") String key,
                                 @Value("${cloudinary.api_secret}") String secret){
     return new Cloudinary(ObjectUtils.asMap(
             "cloud_name", name,
                     "api_key", key,
                     "api_secret",secret));
    }


    @Bean
    public JavaMailSenderImpl getJavaMailSender(@Value("${gmail.transportProtocol}" )String protocol,
                                                @Value("${gmail.smtpAuth}" ) String auth,
                                                @Value("${gmail.smtpStarttlsEnable}" )String starttls,
                                                @Value("${gmail.debug}" )String debug,
                                                @Value("${gmail.email}" )String from,
                                                @Value("${gmail.password}" )String password,
                                                @Value("${gmail.smtSslEnable}" )String ssl,
                                                @Value("${gmail.host}" )String host,
                                                @Value("${gmail.port}" )String port){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(Integer.parseInt(port));

        mailSender.setUsername(from);
        mailSender.setPassword(password);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", protocol);
        props.put("mail.smtp.auth", auth);
        props.put("mail.smtp.starttls.enable", starttls);
        props.put("mail.debug", debug);
        props.put("smtp.ssl.enable",ssl);

        return mailSender;

    }
}



