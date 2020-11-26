package laszlo.hunyady.portfolio.rule;

import laszlo.hunyady.portfolio.MailRequest;

public class MailRequestHelper {
    private MailRequestHelper() {
    }

    public static MailRequest getValidEmail() {
        return MailRequest.builder().name("TestName").email("test@test.test").message("testMessage").build();
    }
}
