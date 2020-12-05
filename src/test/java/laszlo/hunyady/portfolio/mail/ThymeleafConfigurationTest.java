package laszlo.hunyady.portfolio.mail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.opentest4j.AssertionFailedError;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templatemode.TemplateMode;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;


@RunWith(MockitoJUnitRunner.class)
public class ThymeleafConfigurationTest {

    @Spy
    ThymeleafConfiguration conf;

    @Test
    public void testTemplateEngineOk() {
        var mock = mock(SpringResourceTemplateResolver.class);
        doReturn(mock).when(conf).htmlTemplateResolver();

        var engine = conf.springTemplateEngine();

        assertEquals(1, engine.getTemplateResolvers().size());
        assertEquals(mock, engine.getTemplateResolvers().stream().findFirst().orElseThrow(AssertionFailedError::new));
    }

    @Test
    public void testTemplateResolverOk() {
        var resolver = conf.htmlTemplateResolver();

        assertEquals("/templates/", resolver.getPrefix());
        assertEquals(".html", resolver.getSuffix());
        assertEquals(TemplateMode.HTML, resolver.getTemplateMode());
        assertEquals(StandardCharsets.UTF_8.name(), resolver.getCharacterEncoding());
    }
}