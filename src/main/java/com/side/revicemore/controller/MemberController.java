package com.side.revicemore.controller;

import com.side.revicemore.controller.form.JoinForm;
import com.side.revicemore.domain.Member;
import com.side.revicemore.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping(value = "/members/new")
    public String joinForm(Model model) {
        model.addAttribute("form", new JoinForm());
        return "member/joinForm";
    }

    @PostMapping(value = "/members/new")
    public String join(JoinForm form) {
        Member member = new Member();
        member.setMemberAccount(form.getAccount());
        member.setMemberPassword(form.getPassword());

        memberService.join(member);
        return "redirect:/members";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "member/memberList";
    }
}
