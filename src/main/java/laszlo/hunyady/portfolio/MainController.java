package laszlo.hunyady.portfolio;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@CrossOrigin
@RestController
public class MainController {

    @Value("${mail.to.address}")
    private String mailTarget;

    @Autowired
    private JavaMailSender mailSender;

    @PostMapping("/send")
    public ResponseEntity<HttpStatus> email(@RequestBody @Valid MailRequest mailData) {
        log.info("Send mail endpoint called with body: {} ", mailData);
        mailSender.send(buildMailForm(mailData));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private SimpleMailMessage buildMailForm(@RequestBody @Valid MailRequest mail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mail.getEmail());
        message.setTo(mailTarget);
        message.setSubject(mail.getEmail() + " - " + mail.getName());
        message.setText(mail.getMessage());
        return message;
    }
}
