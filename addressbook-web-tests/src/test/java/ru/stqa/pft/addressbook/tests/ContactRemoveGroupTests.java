package ru.stqa.pft.addressbook.tests;

import org.testng.AssertJUnit;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

public class ContactRemoveGroupTests extends TestBase {

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
        Contacts contacts = app.db().contacts();
        if(contacts.stream().iterator().next().getGroups().size() == 0) {
            app.contact().selectContactById(contacts.iterator().next().getId());
            app.contact().selectGroup(contacts);
            app.contact().addContactToGroup();
            app.goTo().homePage();
        }
    }


    @Test
    public void testRemoveGroup() {
        ContactData contactData = app.db().contactInGroup();
        GroupData groupData = contactData.getGroups().iterator().next();
        app.contact().getGroupData(groupData);
        app.contact().selectContactInGroup(contactData);
        app.contact().removeContactFromGroup();
        app.goTo().homePage();
        ContactData contactAfter = app.db().contactById(contactData.getId());
        AssertJUnit.assertTrue(contactAfter.getGroups().contains(groupData));
    }
}

