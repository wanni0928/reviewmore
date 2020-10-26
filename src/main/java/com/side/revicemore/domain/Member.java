package com.side.revicemore.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class Member {
    private Long memberId;
    private String memberAccount;
    private String memberPassword;
    private LocalDateTime memberDate;

    public Member() {
    }

    public Member(Long id, String account, String password, LocalDateTime memberDate) {
        this.memberId = id;
        this.memberAccount = account;
        this.memberPassword = password;
        this.memberDate = memberDate;
    }
}
