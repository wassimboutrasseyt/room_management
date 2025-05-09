package com.example.room_management.controller.restController;

import com.example.room_management.services.implementations.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@RestController("memberRoomManage")
public class MemberController {

    private final MemberService memberService;
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }



}
