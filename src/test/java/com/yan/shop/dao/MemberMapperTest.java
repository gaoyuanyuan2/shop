package com.yan.shop.dao;

import com.yan.shop.BaseTest;
import com.yan.shop.entity.Member;
import com.yan.shop.service.impl.MailServiceImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.mail.MessagingException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MemberMapperTest extends BaseTest {

    @Autowired
    MemberMapper memberMapper;

    @Autowired
    MailServiceImpl mailService;

    @Test
    public void selectAll() throws MessagingException, InterruptedException {
        System.err.println("star");
        List<Member> members = memberMapper.selectAll();
        StringBuffer stringBuffer = new StringBuffer();
        members.stream().map(Member::getEmail).distinct().limit(50).forEach(e -> stringBuffer.append(e).append(","));
        mailService.sendCalendar("淘bao搜索高圆圆台历，gou买2020最新台历！", stringBuffer.toString());
        Arrays.stream(stringBuffer.toString().split(",")).forEach(e->  memberMapper.updateByEmail(e));
        System.err.println( stringBuffer.toString());
        System.err.println("end");
    }
}