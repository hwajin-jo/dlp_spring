package com.bluemoon.gradius.service;

import com.bluemoon.gradius.dto.MemberDto;
import com.bluemoon.gradius.mapper.MemberMapper;
import com.bluemoon.gradius.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberMapper memberMapper;

    public MemberVO getMemberById(String loginId) {
        return memberMapper.getMemberById(loginId);
    }
}
