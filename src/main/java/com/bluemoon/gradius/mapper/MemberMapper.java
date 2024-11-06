package com.bluemoon.gradius.mapper;

import com.bluemoon.gradius.vo.MemberVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    MemberVO getMemberById(String loginId);
}
