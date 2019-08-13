package com.yan.shop.service.impl;

import com.yan.shop.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by yan on  13/08/2019.
 */
public class MemberServiceImplTest extends BaseTest {

    @Autowired
    MemberServiceImpl memberService;

    @Test
    public void importMember() {
        memberService.importMember("1,2");
    }
}