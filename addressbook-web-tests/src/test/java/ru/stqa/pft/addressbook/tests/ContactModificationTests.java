package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        app.getContactHelper().goToHomePage();
        app.getContactHelper().selectContact();
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("Василий", "Васильевич", "Васькин", "Васька", "КоТ", "Москва, ул. Мясная, д. 77", "542563", "89636441754"));
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnToHomePage();
    }
}