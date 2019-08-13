package com.yan.shop.service;

import com.yan.shop.dto.MemberListReq;
import com.yan.shop.entity.Member;
import com.yan.shop.response.PageDataResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * Created by yan on  12/08/2019.
 */
public interface MemberService {

    PageDataResult getMemberList(MemberListReq req);

    Map<String, Object> addMember(Member member);

    Map<String, Object> updateMember(Member member);

    Map<String, Object> deleteMember(Long id);

    Map<String, Object> fileImport(MultipartFile file);
}
