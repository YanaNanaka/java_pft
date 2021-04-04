package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.MailMessage;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class RegistrationTests extends TestBase {

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testRegistration() throws Exception {
        long now = System.currentTimeMillis();
        String user = String.format("user%s", now);
        String password = "password";
        String email = String.format("user%s@localhost", now);
        String name = String.format("user%sname", now);
        //app.james().createUser(user, password);
        app.registration().start(user, email);
        List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
        //List<MailMessage> mailMessages = app.james().waitForMail(user, password, 100000);
        //String confirmationLink = findConfirmationLink(mailMessages, email);
        //String confirmationLink = app.registration().findConfirmationLink(mailMessages, email);
        String confirmationLink = app.registration().findLinkFromMailWithText(mailMessages, email, "Спасибо за регистрацию.");
        app.registration().finish(confirmationLink, password, name);
        assertTrue(app.newSession().login(user, password));

    }

    @AfterMethod (alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}
