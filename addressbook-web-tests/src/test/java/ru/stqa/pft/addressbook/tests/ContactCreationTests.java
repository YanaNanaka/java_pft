package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    Set<ContactData> before = app.contact().all();
    app.contact().initContactCreation();
    ContactData contact = new ContactData().withFirstname("Иван").withMiddlename("Иванович").withLastname("Иванов").
            withNickname("Ванька").withCompany("КиТ").withAddress("Москва, ул. Лесная, д. 7").withHome("552233").withMobile("89632541789").withGroup("1");
    app.contact().create(contact, true);
    app.contact().returnToHomePage();
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() +1);

    contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    before.add(contact);
    Assert.assertEquals(before, after);
  }
}
