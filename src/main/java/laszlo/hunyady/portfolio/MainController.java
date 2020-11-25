package laszlo.hunyady.portfolio;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@CrossOrigin
@RestController
public class MainController {

    @PostMapping("/send")
    public ResponseEntity<HttpStatus> email(MailRequest mail) {
        log.info("debug");
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
