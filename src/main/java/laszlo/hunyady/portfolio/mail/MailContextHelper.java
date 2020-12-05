package laszlo.hunyady.portfolio.mail;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;

@Slf4j
@Component
public class MailContextHelper {

    @Value("${mail.thanks.name.pre}")
    public String NAME_PRE;
    @Value("${mail.thanks.name.post}")
    public String NAME_POST;
    @Value("${mail.thanks.body.email.pre}")
    public String BODY_EMAIL_PRE;
    @Value("${mail.thanks.body.email.post}")
    public String BODY_EMAIL_POST;
    @Value("${mail.thanks.farewell}")
    public String FAREWELL;
    @Value("${mail.thanks.sign}")
    public String SIGN;

    public Context buildContactMailContext(MailRequest mail) {
        Context context = new Context();
        context.setVariable("message", mail.getMessage());
        context.setVariable("name", mail.getName());
        context.setVariable("email", mail.getEmail());
        addFooterContext(context);
        return context;
    }

    public Context buildThanksMailContext(MailRequest mail) {
        Context context = new Context();
        context.setVariable("greetings", NAME_PRE + mail.getName() + NAME_POST);
        context.setVariable("mail_body", BODY_EMAIL_PRE + mail.getEmail() + BODY_EMAIL_POST);
        context.setVariable("farewell", FAREWELL);
        context.setVariable("sign", SIGN);
        addFooterContext(context);
        return context;
    }

    private void addFooterContext(Context context) {
        context.setVariable("position", "Backend fejlesztő");
        context.setVariable("copyright", "© HUNYADY LÁSZLÓ 2020-2020 minden jog fenntartva.");
    }
}
