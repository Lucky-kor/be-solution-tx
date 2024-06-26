package com.springboot.helper.event;

import com.springboot.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberRegistrationEvent {
    private Member member;
}
