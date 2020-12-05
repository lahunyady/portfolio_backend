package laszlo.hunyady.portfolio.mail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.thymeleaf.context.Context;

import static laszlo.hunyady.portfolio.rule.MailRequestHelper.getValidEmail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class MailContextHelperTest {

    @Spy
    private MailContextHelper helper;

    @Test
    public void contactMailContextOk() {
        MailRequest email = getValidEmail();

        Context context = helper.buildContactMailContext(email);

        assertContactMailVariables(email, context);
        assertFooterVariables(context);
    }

    @Test
    public void thanksMailContextOk() {
        Context context = helper.buildThanksMailContext(getValidEmail());

        assertThanksMailVariables(context);
        assertFooterVariables(context);
    }

    private void assertThanksMailVariables(Context context) {
        assertTrue(context.containsVariable("greetings"));
        assertTrue(context.containsVariable("mail_body"));
        assertTrue(context.containsVariable("farewell"));
        assertTrue(context.containsVariable("sign"));
    }

    private void assertContactMailVariables(MailRequest email, Context context) {
        assertAVariable(context, "message", email.getMessage());
        assertAVariable(context, "name", email.getName());
        assertAVariable(context, "email", email.getEmail());
    }

    private void assertAVariable(Context context, String variableKey, String expectedValue) {
        assertTrue(context.containsVariable(variableKey));
        assertEquals(expectedValue, context.getVariable(variableKey));
    }

    private void assertFooterVariables(Context context) {
        assertTrue(context.containsVariable("position"));
        assertTrue(context.containsVariable("copyright"));
    }
}