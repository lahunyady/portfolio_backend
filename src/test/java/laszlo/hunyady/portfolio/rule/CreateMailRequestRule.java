package laszlo.hunyady.portfolio.rule;

import laszlo.hunyady.portfolio.MailRequest;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class CreateMailRequestRule implements TestRule {
    private MailRequest mailRequest;

    @Override
    public Statement apply(Statement statement, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                statement.evaluate();
            }
        };
    }

    public CreateMailRequestRule withValidEmail() {
        mailRequest = MailRequest.builder().name("TestName").email("test@test.test").message("testMessage").build();
        return this;
    }

    public MailRequest getMailRequest() {
        return mailRequest;
    }
}
