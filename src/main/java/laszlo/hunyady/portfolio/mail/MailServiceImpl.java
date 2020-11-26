package laszlo.hunyady.portfolio.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerErrorException;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;

@Service
public class MailServiceImpl implements MailService {
    @Value("${mail.to.address}")
    private String mailTarget;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    public void send(MailRequest mail) {
        try {
            mailSender.send(buildMailForm(mail));
        } catch (MessagingException e) {
            throw new ServerErrorException("Not proper mail data.", e);
        }
    }

    private MimeMessage buildMailForm(MailRequest mail) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper message = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
        message.setFrom(mail.getEmail());
        message.setTo(mailTarget);
        message.setSubject(mail.getEmail() + " - " + mail.getName());
        message.setText(templateEngine.process("mail", buildThymeleafContext(mail)), true);

        return mimeMessage;
    }

    private Context buildThymeleafContext(MailRequest mail) {
        Context context = new Context();
        context.setVariable("message", mail.getMessage());
        return context;
    }
}
