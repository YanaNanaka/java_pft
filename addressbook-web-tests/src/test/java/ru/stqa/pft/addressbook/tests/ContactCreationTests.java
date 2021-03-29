package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    Contacts before = app.contact().all();
    app.contact().initContactCreation();
    File photo = new File("src/test/resources/avatar.png");
    ContactData contact = new ContactData()
            .withFirstname("Иван").withMiddlename("Иванович").withLastname("Иванов").
            withNickname("Ванька").withCompany("КиТ").withAddress("Москва, ул. Лесная, д. 7").
                    withHome("552233").withMobile("89632541789").withGroup("1").withPhoto(photo);
    app.contact().create(contact, true);
    app.contact().returnToHomePage();
    assertThat(app.contact().сount(), equalTo(before.size() +1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.withAdded(contact.
            withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }
    //@Test
  //public void testCurrentDir() {
    //File currentDir = new File(".");
    //System.out.println(currentDir.getAbsolutePath());
   // File photo = new File("src/test/resources/avatar.png");
    //System.out.println(photo.getAbsolutePath());
   // System.out.println(photo.exists());
  //}
}
