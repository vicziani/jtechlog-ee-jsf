package employees;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Locale;

@Named
@SessionScoped
public class LocaleController implements Serializable {

    private Locale locale = new Locale("hu");

    public Locale getLocale() {
        return locale;
    }

    public void changeLocale(String lang) {
        locale = new Locale(lang);
    }
}
