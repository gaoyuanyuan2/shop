package com.yan.shop.service.impl;

import com.yan.shop.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yan on  13/08/2019.
 */
public class MemberServiceImplTest extends BaseTest {

    @Autowired
    MemberServiceImpl memberService;

    @Test
    public void importMember() {
        String str = "";
        Pattern p = Pattern.compile("(<[^>]*>)");
        Matcher m = p.matcher(str);
        List<String> result=new ArrayList<>();
        while(m.find()){
            System.out.println(m.group().replace("<","").replace(">",""));
            result.add(m.group().replace("<","").replace(">",""));
        }
        memberService.importMember(result);
    }

}