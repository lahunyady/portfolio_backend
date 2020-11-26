package laszlo.hunyady.portfolio;


import laszlo.hunyady.portfolio.rule.MailRequestHelper;
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

    @Spy
    private MainController controller;

    @Test
    public void sanity() {
        ResponseEntity<HttpStatus> email = controller.email(MailRequestHelper.getValidEmail());

        assertNotNull(email);
        assertNotNull(email.getBody());
        assertTrue(email.getBody().is2xxSuccessful());
    }
}