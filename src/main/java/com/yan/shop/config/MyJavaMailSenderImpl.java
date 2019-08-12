package com.yan.shop.config;

import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;

/**
 * Created by yan on  12/08/2019.
 */
@Configuration
@EnableConfigurationProperties(MailProperties.class)
public class MyJavaMailSenderImpl  extends JavaMailSenderImpl {
    private ArrayList<String> usernameList;
    private ArrayList<String> passwordList;
    private int currentMailId = 0;

    private final MailProperties properties;

    public MyJavaMailSenderImpl(MailProperties properties) {
        this.properties = properties;
        if (usernameList == null)
            usernameList = new ArrayList<>();
        String[] userNames = this.properties.getUsername().split(",");
        if (userNames != null) {
            for (String user : userNames) {
                usernameList.add(user);
            }
        }
        if (passwordList == null)
            passwordList = new ArrayList<>();
        String[] passwords = this.properties.getPassword().split(",");
        if (passwords != null) {
            for (String pw : passwords) {
                passwordList.add(pw);
            }
        }
    }

    @Override
    public void send(MimeMessage[] mimeMessage ) throws MailException {

        super.setUsername(usernameList.get(currentMailId));
        super.setPassword(passwordList.get(currentMailId));

        super.setHost(this.properties.getHost());
        super.setDefaultEncoding(this.properties.getDefaultEncoding().name());
        super.setJavaMailProperties(asProperties(this.properties.getProperties()));
        super.send(mimeMessage);

        // 轮询
        currentMailId = (currentMailId + 1) % usernameList.size();
    }

    private Properties asProperties(Map<String, String> source) {
        Properties properties = new Properties();
        properties.putAll(source);
        return properties;
    }

    @Override
    public String getUsername() {
        return usernameList.get(currentMailId);
    }

}
