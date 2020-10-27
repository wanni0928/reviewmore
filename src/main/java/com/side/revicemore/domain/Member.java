package com.side.revicemore.domain;

import com.side.revicemore.domain.form.MemberForm;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class Member {
    private Long memberId;
    private String memberAccount;
    private String memberPassword;
    private LocalDateTime memberDate;
    private String memberStatus;

    public Member() {
    }

    public Member(Long id, String account, String password, LocalDateTime memberDate) {
        this.memberId = id;
        this.memberAccount = account;
        this.memberPassword = password;
        this.memberDate = memberDate;
    }

    public void setMember(MemberForm memberForm){
        this.memberAccount = memberForm.getAccount();
        this.memberPassword = memberForm.getPassword();
        
    @Override
    public String toString() {
        return "Member{" +
                "memberId=" + memberId +
                ", memberAccount='" + memberAccount + '\'' +
                ", memberPassword='" + memberPassword + '\'' +
                ", memberDate=" + memberDate +
                ", memberStatus=" + memberStatus +
                '}';
    }
}
