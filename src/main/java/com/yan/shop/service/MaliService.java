package com.yan.shop.service;

import javax.mail.MessagingException;

/**
 * Created by yan on  12/08/2019.
 */
public interface MaliService {
    void sendCalendar(String subject, String to) throws MessagingException, InterruptedException;
}
