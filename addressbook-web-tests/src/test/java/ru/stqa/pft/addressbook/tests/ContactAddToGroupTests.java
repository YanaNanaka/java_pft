package ru.stqa.pft.addressbook.tests;

import org.testng.AssertJUnit;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.testng.Assert.assertTrue;


public class ContactAddToGroupTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        Groups groups = app.db().groups();
        if (app.db().groups().size() ==0) {
            app.goTo().GroupPage();
            app.group().create(new GroupData().withName("3"));
        }
        app.goTo().homePage();
        if (app.db().contacts().size() == 0) {
            app.contact().create(new ContactData().withFirstname("Иван").withMiddlename("Иванович").
                    withLastname("Иванов").withNickname("Ванька").withCompany("КиТ").withAddress("Москва, ул. Лесная, д. 7").
                    withHome("552233").withMobile("89632541789"), true);
        }
            app.goTo().homePage();
            if (app.db().contactWithoutGroups().size() == 0) {
                app.goTo().homePage();
                app.contact().create(new ContactData().withFirstname("Иван").withMiddlename("Иванович").
                        withLastname("Иванов").withNickname("Ванька").withCompany("КиТ").withAddress("Москва, ул. Лесная, д. 7").
                        withHome("552233").withMobile("89632541789"), true);
            }
        }


    @Test
    public void testAddToGroup() {
        ContactData before = app.db().contactNotInGroup();
        Groups groups = app.db().groups();
        GroupData group = groups.iterator().next();
        app.contact().selectNone();
        app.contact().selectContactNotGroup(before);
        app.contact().selectGroup(group);
        app.contact().addContactToGroup();
        ContactData after = app.db().contactById(before.getId());
        assertTrue(after.getGroups().contains(group));
        verifyContactListInUI();
    }
}


