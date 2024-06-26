package com.springboot.helper.event;

import com.springboot.exception.BusinessLogicException;
import com.springboot.exception.ExceptionCode;
import com.springboot.helper.EmailSender;
import com.springboot.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.mail.MailSendException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

@EnableAsync
@Slf4j
@Component
public class MemberRegistrationEventListener {
    private final EmailSender emailSender;
    private final MemberService memberService;

    public MemberRegistrationEventListener(EmailSender emailSender, MemberService memberService) {
        this.emailSender = emailSender;
        this.memberService = memberService;
    }

    @Async
    @EventListener
    public void listen(MemberRegistrationEvent event) throws Exception {
        try {
            String message = "회원 가입을 축하합니다.";
            emailSender.sendEmail(message);
            // 문자 전송 관련 코드 작성
        } catch (MailSendException e) {
            e.printStackTrace();
            log.error("이메일이 발송되지 않아서, 등록한 Member정보를 롤백합니다.");
            memberService.deleteMember(event.getMember().getMemberId());
            throw new BusinessLogicException(ExceptionCode.MEMBER_EXISTS);
        }
    }
}
