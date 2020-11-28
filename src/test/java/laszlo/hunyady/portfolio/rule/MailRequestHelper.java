package laszlo.hunyady.portfolio.rule;

import laszlo.hunyady.portfolio.mail.MailRequest;

public class MailRequestHelper {

    public static final String TEST_NAME = "TestName";
    public static final String TEST_EMAIL = "test@test.test";
    public static final String TEST_MESSAGE = "testMessage";

    private MailRequestHelper() {
    }

    public static MailRequest getValidEmail() {
        return MailRequest.builder().name(TEST_NAME).email(TEST_EMAIL).message(TEST_MESSAGE).build();
    }

    public static String buildTestSubject() {
        return TEST_EMAIL + " - " + TEST_NAME;
    }
}
