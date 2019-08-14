package com.yan.shop.config;

import com.yan.shop.common.utils.AesEncryptUtils;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

/**
 * Created by yan on  12/08/2019.
 */
@Configuration
@EnableConfigurationProperties(MailProperties.class)
public class MyJavaMailSenderImpl extends JavaMailSenderImpl {

    public static void main(String[] args) {
        System.out.println(UUID.randomUUID());
    }

    private ArrayList<String> usernameList;
    private ArrayList<String> passwordList;
    private ArrayList<String> hostList;
    private static final String KEY = "03b4c47092ac4e13899338d8b1412a14";
    private int currentMailId = 0;

    private final MailProperties properties;

    public MyJavaMailSenderImpl(MailProperties properties) {
        this.properties = properties;
        if (usernameList == null)
            usernameList = new ArrayList<>();
        String[] userNames = AesEncryptUtils.decrypt(this.properties.getUsername(), KEY).split(",");
        if (userNames != null) {
            for (String user : userNames) {
                usernameList.add(user);
            }
        }
        if (passwordList == null)
            passwordList = new ArrayList<>();
        String[] passwords = AesEncryptUtils.decrypt(this.properties.getPassword(), KEY).split(",");
        if (passwords != null) {
            for (String pw : passwords) {
                passwordList.add(pw);
            }
        }
        if (hostList == null)
            hostList = new ArrayList<>();
        String[] hosts = this.properties.getHost().split(",");
        if (hosts != null) {
            for (String host : hosts) {
                hostList.add(host);
            }
        }
    }

    @Override
    public void send(MimeMessage[] mimeMessage) throws MailException {
        super.setUsername(usernameList.get(currentMailId));
        super.setPassword(passwordList.get(currentMailId));
        super.setHost(hostList.get(currentMailId));
        super.setDefaultEncoding(this.properties.getDefaultEncoding().name());
        super.setJavaMailProperties(asProperties(this.properties.getProperties()));
        super.send(mimeMessage);
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
