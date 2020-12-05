package laszlo.hunyady.portfolio.mail;

import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

@Component
public class Localizator {

    private static final String RESOURCES = "resources";

    public String localize(String key, Object... params) {
        String pattern = ResourceBundle.getBundle(RESOURCES, Locale.getDefault()).getString(key);
        MessageFormat formatter = new MessageFormat(pattern);
        return formatter.format(params);
    }
}
