package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void initContactCreation() {
        if (isElementPresent(By.name("submit"))) {
            return;
        }
        click(By.linkText("add new"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("middlename"), contactData.getMiddlename());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("nickname"), contactData.getNickname());
        type(By.name("company"), contactData.getCompany());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getHome());
        type(By.name("mobile"), contactData.getMobile());
        type(By.name("email"), contactData.getEmail());
        //attach(By.name("photo"), contactData.getPhoto());

        if (creation) {
            if (contactData.getGroups().size() > 0) {
                Assert.assertTrue(contactData.getGroups().size() == 1);
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
            }
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }


    public void saveContact() {
        click(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void returnToHomePage() {
        if (isElementPresent(By.id("maintable"))) {
            return;
        }
        click(By.linkText("home page"));
    }

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }


    public void initContactModificationById(int id) {
        wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
    }

    public void submitContactModification() {
        click(By.xpath("(//input[@name='update'])[2]"));
    }


    public void deleteSelectedContact() {
        click(By.xpath("//input[@value='Delete']"));
        wd.switchTo().alert().accept();
    }

    public void create(ContactData contact, boolean creation) {
        initContactCreation();
        fillContactForm(contact, creation);
        saveContact();
        contactCashe = null;
        returnToHomePage();
    }

    public void modify(ContactData contact) {
        initContactModificationById(contact.getId());
        fillContactForm(contact, false);
        submitContactModification();
        contactCashe = null;
        returnToHomePage();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectedContact();
        contactCashe = null;
        returnToHomePage();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int сount() {
        return wd.findElements(By.xpath("//img[@alt='Edit']")).size();
    }

    private Contacts contactCashe = null;

    public Contacts all() {
        if (contactCashe != null) {
            return new Contacts(contactCashe);
        }
        contactCashe = new Contacts();
        List<WebElement> rows = wd.findElements(By.cssSelector("tr[name='entry']"));
        for (WebElement row : rows) {
            int id = Integer.parseInt(row.findElement(By.cssSelector("td:nth-child(1) input")).getAttribute("value"));
            String firstname = row.findElement(By.cssSelector("td:nth-child(3)")).getText();
            String lastname = row.findElement(By.cssSelector("td:nth-child(2)")).getText();
            String allPhones = row.findElement(By.cssSelector("td:nth-child(6)")).getText();
            String address = row.findElement(By.cssSelector("td:nth-child(4)")).getText();
            String allMails = row.findElement(By.cssSelector("td:nth-child(5)")).getText();
            contactCashe.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
                    .withAllPhones(allPhones).withAddress(address).withAllMails(allMails));
        }
        return new Contacts(contactCashe);
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
                .withHome(home).withMobile(mobile).withWork(work).withEmail(email)
                .withEmail2(email2)
                .withEmail3(email3)
                .withAddress(address);
    }

    public void selectGroup(GroupData group) {
        wd.findElement(By.xpath(String.format("//select[@name='to_group']/option[@value='%s']", group.getId()))).click();
    }

    public void selectContactInGroup(ContactData contact) {
        click(By.xpath(String.format("//input[@type='checkbox']", contact.getId())));
    }

    public void removeContactFromGroup() {
        click(By.name("remove"));
        contactCashe = null;
    }

    public void getGroupData(GroupData groupData) {
        click(By.xpath(String.format("//select[@name='group']/option[text() = '%s']", groupData.getName())));
    }

    public void selectContactNotGroup(ContactData contact) {
        click(By.xpath(String.format("//input[@type='checkbox']", contact.getId())));
    }

    public void addContactToGroup() {
        wd.findElement(By.name("add")).click();
        contactCashe = null;
    }

    public void selectNone() {
        wd.findElement(By.name("group")).click();
        new Select(wd.findElement(By.name("group"))).selectByVisibleText("[none]");
        wd.findElement(By.name("group")).click();
    }

}



