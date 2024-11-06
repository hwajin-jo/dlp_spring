package com.bluemoon.gradius.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
public class MemberController {

    @GetMapping("/login")
    public String login() {
        return "/member/login";
    }

    @GetMapping("/success")
    public String loginSuccess() {
        return "index";
    }
}
