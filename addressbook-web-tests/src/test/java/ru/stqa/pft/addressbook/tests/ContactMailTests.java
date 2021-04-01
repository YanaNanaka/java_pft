package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactMailTests extends  TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        Groups groups = app.db().groups();
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData().withFirstname("Иван").withMiddlename("Иванович").
                    withLastname("Иванов").withNickname("Ванька").withCompany("КиТ").withAddress("Москва, ул. Лесная, д. 7").
                    withHome("552233").withMobile("89632541789").withWork("124596").withEmail("sd@.ru").withEmail2("dfhd@.com").withEmail3("dffed@.com").inGroup(groups.iterator().next()), true);
        }
    }
    @Test
    public void testContactMails() {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        assertThat(contact.getAllMails(), equalTo(mergeMails(contactInfoFromEditForm)));

    }

    private String mergeMails(ContactData contact) {
        return Arrays.asList(
                contact.getEmail(),
                contact.getEmail2(),
                contact.getEmail3())
                .stream().filter(s -> !s.equals(""))
                .map(ContactMailTests::cleaned)
                .collect(Collectors.joining("\n"));
    }

    public static String cleaned(String mail) {
        return  mail.replaceAll("\\s","").replaceAll("[-()]", "");
    }
}
