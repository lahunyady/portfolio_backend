package laszlo.hunyady.portfolio.mail;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static laszlo.hunyady.portfolio.rule.MailRequestHelper.buildTestSubject;
import static laszlo.hunyady.portfolio.rule.MailRequestHelper.getValidEmail;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MailControllerTest {

    @Mock
    private JavaMailSender mailSender;

    @Spy
    @InjectMocks
    private MailController controller;

    @Before
    public void setup() {
        doNothing().when(mailSender).send(any(SimpleMailMessage.class));
    }

    @Test
    public void emailResponseOk() {
        ResponseEntity<HttpStatus> email = controller.email(getValidEmail());

        assertNotNull(email);
        assertNotNull(email.getBody());
        assertTrue(email.getBody().is2xxSuccessful());
    }

    @Test
    public void emailArgumentOk() {
        ArgumentCaptor<SimpleMailMessage> captor = ArgumentCaptor.forClass(SimpleMailMessage.class);

        controller.email(getValidEmail());

        verify(mailSender, times(1)).send(captor.capture());
        assertEquals(getValidEmail().getEmail(), captor.getValue().getFrom());
        assertEquals(getValidEmail().getMessage(), captor.getValue().getText());
        assertEquals(buildTestSubject(), captor.getValue().getSubject());
    }
}