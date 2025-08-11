package com.gati.hankki.member.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gati.hankki.member.model.LoginRequestDto;
import com.gati.hankki.member.model.Member;
import com.gati.hankki.member.service.MemberService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
@Tag(name = "Members", description = "Members API")
public class MemberController {
	
    private final MemberService memberService;
	
    @PostMapping("/login")
    @Operation(summary = "로그인 API", description = "ID와 비밀번호로 로그인")
    public ResponseEntity<Member> login(@RequestBody LoginRequestDto loginRequestDto) {
        Member result = memberService.login(loginRequestDto);

        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }



}
