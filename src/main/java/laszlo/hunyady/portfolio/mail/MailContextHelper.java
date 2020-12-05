package laszlo.hunyady.portfolio.mail;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;

import java.util.Calendar;

@Slf4j
@Component
public class MailContextHelper {

    @Autowired
    private Localizator loc;

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
        context.setVariable("greetings", loc.localize("mail.thanks.name.pre") + mail.getName() + loc.localize("mail.thanks.name.post"));
        context.setVariable("mail_body", loc.localize("mail.thanks.body.email.pre") + mail.getEmail() + loc.localize("mail.thanks.body.email.post"));
        context.setVariable("farewell", loc.localize("mail.thanks.farewell"));
        context.setVariable("sign", loc.localize("mail.thanks.sign"));
        addFooterContext(context);
        return context;
    }

    private void addFooterContext(Context context) {
        context.setVariable("position", loc.localize("mail.footer.position"));
        context.setVariable("copyright", loc.localize("mail.footer.copyright", String.valueOf(Calendar.getInstance().get(Calendar.YEAR))));
    }
}
