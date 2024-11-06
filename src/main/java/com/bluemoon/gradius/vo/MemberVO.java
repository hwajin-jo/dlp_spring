package com.bluemoon.gradius.vo;

import lombok.Data;

@Data
public class MemberVO {
    int id;
    String loginID;
    String password;
    boolean enabled;
}
