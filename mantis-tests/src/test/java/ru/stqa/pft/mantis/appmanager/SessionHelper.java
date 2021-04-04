package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import ru.stqa.pft.mantis.model.UserData;

import java.util.Optional;


public class SessionHelper extends HelperBase {

    public SessionHelper(ApplicationManager app) {
        super(app);
    }

    public void changePassword(Optional<UserData> user) throws InterruptedException {
        selectUser(user);
        click(By.xpath("//form[@id = 'manage-user-reset-form']//input[@type='submit']"));
    }

    private void selectUser(Optional<UserData> user) {
        wd.get(app.getProperty("web.baseUrl") + String.format("/manage_user_edit_page.php?user_id=%s", user.get().getId()));
    }

    public void updateUserPassword(String confirmationLink, Optional<UserData> user, String password) {
        wd.get(confirmationLink);
        type(By.name("realname"), user.get().getUsername());
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.cssSelector("button[type='submit']"));
    }

}