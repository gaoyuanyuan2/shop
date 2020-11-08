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
            mailService.sendCalendar("淘bao搜索高圆圆台历，gou买2020最新台历！", "823789010@qq.com");
    }
}