package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().createGroup(new GroupData("3", "1", "2"));
    }
    app.getNavigationHelper().goToHomePage();
    int before = app.getContactHelper().getContactCount();
    app.getContactHelper().createContact(new ContactData("Иван", "Иванович", "Иванов", "Ванька", "КиТ", "Москва, ул. Лесная, д. 7", "552233", "89632541789", "3"), true);
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before +1);
  }
}
