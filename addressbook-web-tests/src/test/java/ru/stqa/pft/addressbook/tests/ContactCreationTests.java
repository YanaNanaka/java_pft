package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    Contacts before = app.contact().all();
    app.contact().initContactCreation();
    ContactData contact = new ContactData().withFirstname("Иван").withMiddlename("Иванович").withLastname("Иванов").
            withNickname("Ванька").withCompany("КиТ").withAddress("Москва, ул. Лесная, д. 7").withHome("552233").withMobile("89632541789").withGroup("1");
    app.contact().create(contact, true);
    app.contact().returnToHomePage();
    assertThat(app.contact().сount(), equalTo(before.size() +1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }
}
