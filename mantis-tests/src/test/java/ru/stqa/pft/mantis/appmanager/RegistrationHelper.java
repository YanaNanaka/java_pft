package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

import java.util.List;
import java.util.Optional;


public class RegistrationHelper extends  HelperBase {

    public RegistrationHelper(ApplicationManager app) {
        super(app);
    }

    public void start(String username, String email) {
        wd.get(app.getProperty("web.baseUrl")+"/signup_page.php");
       type(By.name("username"), username);
        type(By.name("email"), email);
        click(By.cssSelector("input[type='submit']"));

    }


    public void finish(String confirmationLink, String password, String name) {
        wd.get(confirmationLink);
        type(By.name("realname"), name);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.cssSelector("button[type='submit']"));
    }

    public String findLinkFromMailWithText(List<MailMessage> mailMessages, String email, String text) {
        Optional<MailMessage> message = mailMessages.stream().filter((m) -> m.to.equals(email) && m.text.contains(text)).findFirst();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(message.get().text);
    }
}
