package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        app.getNavigationHelper().gotoGroupPage();
        if (! app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().createGroup(new GroupData("3", "1", "2"));
        }
        app.getNavigationHelper().goToHomePage();
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Иван", "Иванович", "Иванов",
                    "Ванька", "КиТ", "Москва, ул. Лесная, д. 7", "552233", "89632541789",
                    "3"), true);
        }
        int before = app.getContactHelper().getContactCount();
        app.getContactHelper().selectContact(before -1);
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("Василий", "Васильевич", "Васькин",
                "Васька", "КоТ", "Москва, ул. Мясная, д. 77", "542563", "89636441754",
                null), false);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnToHomePage();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before);
    }
}
