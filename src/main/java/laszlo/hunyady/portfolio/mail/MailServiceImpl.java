package laszlo.hunyady.portfolio.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerErrorException;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;

@Service
public class MailServiceImpl implements MailService {
    public static final String CONTACT_THANK_YOU_MAIL = "contact_thank_you_mail";
    public static final String MAIL = "mail";

    @Value("${mail.contact.subject}")
    private String contact;
    @Value("${mail.bot.address}")
    private String mailBotAddress;

    @Autowired
    private MailContextHelper contextHelper;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private SpringTemplateEngine templateEngine;

    public void send(MailRequest mail) {
        try {
            mailSender.send(buildContactMail(mail));
            mailSender.send(buildThanksMail(mail));
        } catch (MessagingException e) {
            throw new ServerErrorException("Not proper mail data.", e);
        }
    }

    private MimeMessage buildContactMail(MailRequest mail) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
        helper.setFrom(mail.getEmail());
        helper.setTo(mailBotAddress);
        helper.setSubject(mail.getEmail() + " - " + mail.getName());
        helper.setText(templateEngine.process(MAIL, contextHelper.buildContactMailContext(mail)), true);
        return mimeMessage;
    }

    private MimeMessage buildThanksMail(MailRequest mail) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
        helper.setFrom(mailBotAddress);
        helper.setTo(mail.getEmail());
        helper.setSubject(contact);
        helper.setText(templateEngine.process(CONTACT_THANK_YOU_MAIL, contextHelper.buildThanksMailContext(mail)), true);
        return mimeMessage;
    }
}
