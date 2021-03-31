package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;


public class GroupDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() ==0) {
            app.goTo().GroupPage();
            app.group().create(new GroupData().withName("3"));
        }
    }

    @Test
    public void testGroupDeletion() {
        Groups before = app.db().groups();
        GroupData deletedGroup = before.iterator().next();
        app.goTo().GroupPage();
        app.group().delete(deletedGroup);
        assertThat(app.group().сount(), equalTo(before.size()-1));
        Groups after = app.db().groups();
        assertThat(after, equalTo(before.without(deletedGroup)));
        }
}
