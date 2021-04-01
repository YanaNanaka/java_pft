package ru.stqa.pft.addressbook.tests;

import org.testng.AssertJUnit;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;


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
                    withHome("552233").withMobile("89632541789").inGroup(groups.iterator().next()), true);
        }
    }

    @Test
    public void testAddToGroup() {
        ContactData contactData = app.db().contactNotInGroup();
        Groups groups = app.db().groups();
        GroupData group = groups.iterator().next();
        app.contact().selectNone();
        app.contact().selectContactNotGroup(contactData);
        app.contact().selectGroup(group);
        app.contact().addContactToGroup();
        ContactData contactAfter = app.db().contactById(contactData.getId());
        AssertJUnit.assertTrue(contactAfter.getGroups().contains(group));
    }
}


