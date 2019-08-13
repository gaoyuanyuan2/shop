package com.yan.shop.service.impl;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.metadata.Sheet;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yan.shop.common.utils.ShopUtils;
import com.yan.shop.dao.MemberMapper;
import com.yan.shop.dto.MemberListReq;
import com.yan.shop.entity.Member;
import com.yan.shop.model.xls.MemberXls;
import com.yan.shop.response.PageDataResult;
import com.yan.shop.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yan on  12/08/2019.
 */
@Service
public class MemberServiceImpl implements MemberService {


    @Autowired
    private MemberMapper memberMapper;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public PageDataResult getMemberList(MemberListReq req) {
        PageDataResult pageDataResult = new PageDataResult();
        Page<Object> page = PageHelper.startPage(req.getPage(), req.getLimit());
        List<Member> member = memberMapper.selectByMember(buildMemberCondition(req));
        if (!CollectionUtils.isEmpty(member)) {
            pageDataResult.setList(member);
            pageDataResult.setTotals(Integer.valueOf(page.getTotal() + ""));
        }
        return pageDataResult;
    }

    private Member buildMemberCondition(MemberListReq req) {
        Member member = new Member();
        member.setName(req.getName());
        member.setLabel(req.getLabel());
        member.setPhone(req.getPhone());
        member.setEmail(req.getEmail());
        return member;
    }

    @Override
    public Map<String, Object> addMember(Member member) {
        Map<String, Object> data = new HashMap();
        try {
            member.setCreateTime(new Timestamp(System.currentTimeMillis()));
            int result = memberMapper.insert(member);
            if (buildReturn(data, result)) return data;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("添加异常！", e);
        }
        return data;
    }

    @Override
    public Map<String, Object> updateMember(Member member) {
        Map<String, Object> data = new HashMap();
        try {
            member.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            int result = memberMapper.updateByPrimaryKey(member);
            if (buildReturn(data, result)) return data;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("添加异常！", e);
        }
        return data;
    }

    @Override
    public Map<String, Object> deleteMember(Long id) {
        Map<String, Object> data = new HashMap<>();
        try {
            if (ShopUtils.buildRes(data, memberMapper.deleteByPrimaryKey(id), logger, id)) return data;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("删除异常！", e);
        }
        return data;
    }

    @Override
    public Map<String, Object> fileImport(MultipartFile file) {
        Map<String, Object> data = new HashMap<>();
        try (InputStream inputStream = file.getInputStream()) {
            Sheet sheet = new Sheet(2, 1, MemberXls.class);
            List<Object> memberXls = EasyExcelFactory.read(inputStream, sheet);
            if (CollectionUtils.isEmpty(memberXls)) {
                buildReturn(data, 0);
                return data;
            }
            memberXls.stream().forEach(e -> {
                String[] array = e.toString().replace("[", "").replace("]", "").split(",");
                Member member = new Member();
                member.setName(array[0]);
                member.setLabel(array[1]);
                member.setPhone(array[2]);
                member.setEmail(array[3]);
                member.setRemark(array[4]);
                member.setCreateTime(new Timestamp(System.currentTimeMillis()));
                memberMapper.insert(member);
            });
        } catch (IOException e) {
            logger.error("IOException！", e);
        }
        buildReturn(data, 1);
        return data;
    }

    private boolean buildReturn(Map<String, Object> data, int result) {
        if (result == 0) {
            data.put("code", 0);
            data.put("msg", "失败");
            logger.error("失败");
            return true;
        }
        data.put("code", 1);
        data.put("msg", "成功!");
        return false;
    }

    public void importMember(String emails) {
        String[] emailArray = emails.split(",");
        Arrays.stream(emailArray).forEach(e -> {
            Member member = new Member();
            member.setEmail(e);
            member.setLabel("高圆圆");
            member.setCreateTime(new Timestamp(System.currentTimeMillis()));
            memberMapper.insert(member);
        });
    }
}
