package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.getContactHelper().addNewContact();
    app.getContactHelper().fillContactForm(new ContactData("Иван", "Иванович", "Иванов", "Ванька", "КиТ", "Москва, ул. Лесная, д. 7", "552233", "89632541789"));
    app.getContactHelper().saveContact();
    app.getContactHelper().returnToHomePage();
  }

}
