package laszlo.hunyady.portfolio.mail;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@CrossOrigin
@RestController
public class MailController {

    @Autowired
    private MailService mailSender;

    @PostMapping("/send")
    public ResponseEntity<HttpStatus> email(@RequestBody @Valid MailRequest mailData) {
        log.info("Send mail endpoint called with body: {} ", mailData);

        mailSender.send(mailData);

        return ResponseEntity.ok(HttpStatus.OK);
    }
}
