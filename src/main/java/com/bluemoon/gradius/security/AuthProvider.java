package com.bluemoon.gradius.security;

import com.bluemoon.gradius.exception.isLockedException;
import com.bluemoon.gradius.service.MemberService;
import com.bluemoon.gradius.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AuthProvider implements AuthenticationProvider {
    private final MemberService memberService;
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String loginId = authentication.getName();
        String password = (String) authentication.getCredentials();

        MemberVO memberVO = memberService.getMemberById(loginId);
        if (memberVO == null) {
            throw new UsernameNotFoundException("User not found.");
        }

        // 비활성 계정일 경우
        if (!memberVO.isEnabled()) {
            throw new isLockedException("this account is locked");
        }

        // 비밀번호 불일치
        if (!passwordEncoder().matches(password, memberVO.getPassword())) {
            throw new BadCredentialsException("Password not Correct.");
        }

        // 로그인 시도 5회 이상일 경우 비활성


        // 최종 통과
        return new UsernamePasswordAuthenticationToken(loginId, password, null);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
