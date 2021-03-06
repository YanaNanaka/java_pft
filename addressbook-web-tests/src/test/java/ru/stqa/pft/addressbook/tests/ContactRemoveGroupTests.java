package ru.stqa.pft.addressbook.tests;

import org.testng.AssertJUnit;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class ContactRemoveGroupTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        Groups groups = app.db().groups();
        if (app.db().groups().size() == 0) {
            app.goTo().GroupPage();
            app.group().create(new GroupData().withName("3"));
        }
        app.goTo().homePage();
        if (app.db().contacts().size() == 0) {
            app.contact().create(new ContactData().withFirstname("Иван").withMiddlename("Иванович").
                    withLastname("Иванов").withNickname("Ванька").withCompany("КиТ").withAddress("Москва, ул. Лесная, д. 7").
                    withHome("552233").withMobile("89632541789").inGroup(groups.iterator().next()), true);
        }
        if (app.db().contactWithGroup().size() == 0) {
            ContactData before = app.db().contactWithoutGroup();
            GroupData group = groups.iterator().next();
            app.goTo().homePage();
            app.contact().selectContactWithoutGroup(before);
            app.contact().selectGroup(group);
            app.contact().addContactToGroup();
        }
    }

    @Test
    public void testRemoveGroup() {
            ContactData before = app.db().contactInGroup();
            GroupData group = before.getGroups().iterator().next();
            app.goTo().homePage();
            app.contact().getGroupData(group);
            app.contact().selectContact(before);
            app.contact().removeContactFromGroup();
            ContactData after = app.db().contactById(before.getId());
            AssertJUnit.assertTrue(after.getGroups().contains(group));
    }
}

