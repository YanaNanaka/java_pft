package ru.stqa.pft.mantis.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

import javax.mail.MessagingException;
import java.util.List;
import java.util.Optional;

import static org.testng.Assert.assertTrue;


public class ChangePasswordTests extends TestBase {
    private Optional<UserData> user;

    @BeforeMethod
    public void before() {
        app.mail().start();


    }

    @Test
    public void testLoginAfterChangedPassword() throws Exception {
        Users users = app.db().users();
        Optional<UserData> user = users
                .stream()
                .findAny();
        assertTrue(user.isPresent());
        app.dataHelper().loginUI();
        app.sessionHelper().changePassword(user);
        HttpSession session = app.newSession();
        session.logout();
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        String confirmationLink = app.registration()
                .findLinkFromMailWithText(mailMessages, user.get().getEmail(), "Your password has been reset");
        String passwordNew = String.format("password%s", user.get().getId());
        app.sessionHelper().updateUserPassword(confirmationLink, user, passwordNew);
        assertTrue(session.login(user.get().getUsername(), passwordNew));
        assertTrue(session.isLoggedInAs(user.get().getUsername()));
    }

    @AfterMethod
    public void after() throws MessagingException {
        app.mail().stop();
    }

}