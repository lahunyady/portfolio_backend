package laszlo.hunyady.portfolio;


import laszlo.hunyady.portfolio.rule.CreateMailRequestRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class MainControllerTest {

    private CreateMailRequestRule mail = new CreateMailRequestRule().withValidEmail();

    @Spy
    private MainController controller;

    @Test
    public void sanity() {
        ResponseEntity<HttpStatus> email = controller.email(mail.getMailRequest());

        assertNotNull(email);
        assertNotNull(email.getBody());
        assertTrue(email.getBody().is2xxSuccessful());
    }
}