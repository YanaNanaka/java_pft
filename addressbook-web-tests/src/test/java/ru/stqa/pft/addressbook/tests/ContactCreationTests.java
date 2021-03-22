package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;


import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    List<ContactData> before = app.contact().list();
    app.contact().initContactCreation();
    ContactData contact = new ContactData().withFirstname("Иван").withMiddlename("Иванович").withLastname("Иванов").
            withNickname("Ванька").withCompany("КиТ").withAddress("Москва, ул. Лесная, д. 7").withHome("552233").withMobile("89632541789").withGroup("1");
    app.contact().create(contact, true);
    app.contact().returnToHomePage();
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() +1);

    before.add(contact);
    Comparator<? super ContactData> byId = (q1, q2) -> Integer.compare(q1.getId(), q2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
