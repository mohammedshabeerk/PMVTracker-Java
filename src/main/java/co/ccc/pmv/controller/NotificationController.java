package co.ccc.pmv.controller;

import co.ccc.pmv.common.NotificationRequest;
import co.ccc.pmv.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    private final EmailService emailService;

    @Autowired
    public NotificationController(EmailService emailService){
        this.emailService = emailService;
    }

    @PostMapping("/send")
    public String sendNotification(@RequestBody NotificationRequest request) {
        String to = request.getTo();
        String subject = request.getSubject();
        String body = request.getBody();
        emailService.sendNotification(to, subject, body);
        return "Notification sent successfully";
    }
}
