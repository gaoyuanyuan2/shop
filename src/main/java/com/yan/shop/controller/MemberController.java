package com.yan.shop.controller;

import com.yan.shop.dto.MemberListReq;
import com.yan.shop.entity.Member;
import com.yan.shop.response.PageDataResult;
import com.yan.shop.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by yan on  19/06/2019.
 */
@Controller
@RequestMapping("member")
public class MemberController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MemberService memberService;

    @RequestMapping("/memberManage")
    public String toPage() {
        logger.info("进入member管理");
        return "member/memberManage";
    }

    @RequestMapping(value = "/getMemberList", method = RequestMethod.GET)
    @ResponseBody
    public PageDataResult getMemberList(HttpServletRequest request, MemberListReq req) {

        PageDataResult pdr = new PageDataResult();
        try {
            if (null == req.getPage()) {
                req.setPage(1);
            }
            if (null == req.getLimit()) {
                req.setLimit(10);
            }
            pdr = memberService.getMemberList(req);
            logger.info("列表查询=pdr:" + pdr);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("列表查询异常！", e);
        }
        return pdr;
    }


    @PostMapping("/setMember")
    @ResponseBody
    public Map<String, Object> setRole(Member member) {
        logger.info("设置Member[新增或更新]！member:" + member);
        Map<String, Object> data;
        if (member.getId() == null) {
            data = memberService.addMember(member);
        } else {
            data = memberService.updateMember(member);
        }
        return data;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteMember(@RequestParam("id") Long id) {
        return memberService.deleteMember(id);
    }

}
