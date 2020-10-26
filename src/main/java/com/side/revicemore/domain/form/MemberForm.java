package com.side.revicemore.domain.form;

import lombok.Getter;

@Getter
public class MemberForm {
    private String account;
    private String password;

    public MemberForm(String account, String password) {
        this.account = account;
        this.password = password;
    }
}
