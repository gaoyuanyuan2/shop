package com.yan.shop.service.impl;

import com.yan.shop.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.mail.MessagingException;

/**
 * Created by yan on  12/08/2019.
 */
public class MailServiceImplTest extends BaseTest {

    @Autowired
    MailServiceImpl mailService;

    @Test
    public void sendCalendar() throws MessagingException, InterruptedException {
        for (int i = 0; i < 5; i++) {
            mailService.sendCalendar("开会2019.8.12", "648433358@qq.com");
        }
    }
}