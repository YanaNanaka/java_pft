package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContactsfromXml() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")))) {
      String xml = "";
      String line = reader.readLine();
    while (line != null) {
        xml += line;
        line = reader.readLine();
      }
      XStream xstream = new XStream();
    xstream.processAnnotations(ContactData.class);
      List<ContactData> contacts = (List<ContactData>)xstream.fromXML(xml);
    return contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
    }
  }

  @DataProvider
  public Iterator<Object[]> validContactsFromJson() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>(){}.getType());
      return contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
    }
  }

  @Test (dataProvider = "validContactsFromJson")
  public void testContactCreation(ContactData contact) {
    Groups groups = app.db().groups();
    Contacts before = app.db().contacts();
    app.contact().initContactCreation();
    ContactData newContact = new ContactData()
    .withFirstname("Иван").withMiddlename("Иванович").withLastname("Иванов").
    withNickname("Ванька").withCompany("КиТ").withAddress("Москва, ул. Лесная, д. 7").
    withHome("552233").withMobile("89632541789").inGroup(groups.iterator().next());
    app.goTo().homePage();
    //File photo = new File("src/test/resources/avatar.png");
    //ContactData contact = new ContactData()
          //.withFirstname("Иван").withMiddlename("Иванович").withLastname("Иванов").
            //withNickname("Ванька").withCompany("КиТ").withAddress("Москва, ул. Лесная, д. 7").
                    //withHome("552233").withMobile("89632541789").withGroup("1").withPhoto(photo);
    app.contact().create(contact, true);
    app.contact().returnToHomePage();
    assertThat(app.group().сount(), equalTo(before.size() +1));
    Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.withAdded(contact.
            withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

    verifyContactListInUI();

  }
    //@Test
  //public void testCurrentDir() {
   // File currentDir = new File(".");
    //System.out.println(currentDir.getAbsolutePath());
  // File photo = new File("src/test/resources/avatar.png");
   // System.out.println(photo.getAbsolutePath());
   // System.out.println(photo.exists());
 // }
}
