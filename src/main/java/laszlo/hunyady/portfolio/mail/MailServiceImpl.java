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
import java.util.Locale;

@Service
public class MailServiceImpl implements MailService {
    public static final String CONTACT_THANK_YOU_MAIL = "contact_thank_you_mail";
    public static final String MAIL = "mail";

    @Autowired
    private Localizator loc;

    @Value("${spring.mail.username}")
    private String mailBotAddress;
    @Value("${mail.language.code}")
    private String mailBotLanguageCode;

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private MailContextHelper contextHelper;
    @Autowired
    private SpringTemplateEngine templateEngine;

    @Override
    public void processContactMail(MailRequest mail) {
        try {
            Locale.setDefault(Locale.forLanguageTag(mailBotLanguageCode));
            mailSender.send(buildContactMail(mail));
            Locale.setDefault(Locale.forLanguageTag(mail.getLanguageCode()));
            mailSender.send(buildThanksForContactingMail(mail));
        } catch (MessagingException e) {
            throw new ServerErrorException("Not proper mail data.", e);
        }
    }

    private MimeMessage buildContactMail(MailRequest mail) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
        helper.setFrom(mail.getEmail());
        helper.setTo(mailBotAddress);
        helper.setSubject(mail.getName() + " - " + mail.getEmail());
        helper.setText(templateEngine.process(MAIL, contextHelper.buildContactMailContext(mail)), true);
        return mimeMessage;
    }

    private MimeMessage buildThanksForContactingMail(MailRequest mail) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
        helper.setFrom(mailBotAddress);
        helper.setTo(mail.getEmail());
        helper.setSubject(loc.localize("mail.contact.subject"));
        helper.setText(templateEngine.process(CONTACT_THANK_YOU_MAIL, contextHelper.buildThanksMailContext(mail)), true);
        return mimeMessage;
    }
}
