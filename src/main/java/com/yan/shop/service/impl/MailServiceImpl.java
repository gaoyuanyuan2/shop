package com.yan.shop.service.impl;

import com.yan.shop.config.MyJavaMailSenderImpl;
import com.yan.shop.service.MaliService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.concurrent.TimeUnit;

/**
 * Created by yan on  12/08/2019.
 */
@Service
public class MailServiceImpl implements MaliService {

    @Autowired
    private MyJavaMailSenderImpl mailSender;

    @Override
    public void sendCalendar(String subject, String to) throws MessagingException, InterruptedException {
        MimeMessage mailMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, true,"UTF-8");
        messageHelper.setFrom(mailSender.getUsername());
        if (to.contains(",")) {
            messageHelper.setTo(to.split(","));
        } else {
            messageHelper.setTo(to);
        }
        messageHelper.setSubject(subject);
        StringBuilder content = new StringBuilder("<html><head></head><body><h3>");
        content.append("高圆圆2020台历");
        content.append("<li><img src='https://s2.ax1x.com/2019/11/22/MocE01.png'></li>");
        content.append("<li><img src='https://s2.ax1x.com/2019/11/22/Mocek6.png'></li>");
        content.append("<li><img src='https://s2.ax1x.com/2019/11/22/MocVTx.png'></li>");
        content.append("</h3></body></html>");
        messageHelper.setText(content.toString(), true);
        mailSender.send(mailMessage);
        TimeUnit.SECONDS.sleep((int)(1+Math.random()*10));
    }
}
