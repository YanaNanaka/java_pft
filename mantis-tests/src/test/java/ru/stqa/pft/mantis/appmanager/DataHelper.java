package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import static org.testng.Assert.assertTrue;


public class DataHelper extends HelperBase{

    public DataHelper(ApplicationManager app) {
        super(app);
    }

    public Optional<UserData> createUser() throws IOException, MessagingException {
        long now = System.currentTimeMillis();
        String email = String.format("user%s@localchost.localdomain", now);
        String user = String.format("user%s", now);
        String password = System.getProperty("userPassword");
        String name = String.format("user%sname", now);
        app.registration().start(user, email);
        List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
        String confirmationLink = app.registration().findLinkFromMailWithText(mailMessages, email, "Спасибо за регистрацию.");
        app.registration().finish(confirmationLink, password, name);
        Users users = app.db().users();
        Optional<UserData> createdUser = users
                .stream()
                .filter((u) -> u.getEmail().equals(email))
                .findFirst();
        assertTrue(createdUser.isPresent());
        return createdUser;
    }

    public void loginUI() throws ClassNotFoundException {
        wd.get(app.getProperty("web.baseUrl") + "login_page.php");
        wd.findElement(By.id("username")).click();
        wd.findElement(By.id("username")).clear();
        wd.findElement(By.id("username")).sendKeys("administrator");
        wd.findElement(By.cssSelector("input[type='submit']")).click();
        wd.findElement(By.id("password")).click();
        wd.findElement(By.id("password")).clear();
        wd.findElement(By.id("password")).sendKeys("root");
        wd.findElement(By.cssSelector("input[type='submit']")).click();
    }

}