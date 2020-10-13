package com.side.revicemore.domain;

import lombok.Getter;

@Getter
public class Member {
    private Long member_id;
    private String memberAccount;
    private String memberPassword;

    protected Member() {
    }

    public Member(Long id, String account, String password) {
        this.member_id = id;
        this.memberAccount = account;
        this.memberPassword = password;
    }
}
