package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void initContactCreation() {
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

        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
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
        wd.findElement(By.cssSelector("a[href='edit.php?id=" + id + "']")).click();
    }

    public void submitContactModification () {
            click(By.xpath("(//input[@name='update'])[2]"));}


            public void deleteSelectedContact () {
            click(By.xpath("//input[@value='Delete']"));
            wd.switchTo().alert().accept();
        }

        public void create (ContactData contact,boolean creation){
            initContactCreation();
            fillContactForm(contact, creation);
            saveContact();
            contactCashe = null;
            returnToHomePage();
        }

        public void modify (ContactData contact) {
            initContactModificationById(contact.getId());
            fillContactForm(contact, false);
            submitContactModification();
            contactCashe = null;
            returnToHomePage();
        }

        public void delete (ContactData contact){
            selectContactById(contact.getId());
            deleteSelectedContact();
            contactCashe = null;
            returnToHomePage();
        }

        public boolean isThereAContact () {
            return isElementPresent(By.name("selected[]"));
        }

        public int getContactCount () {
            return wd.findElements(By.xpath("//img[@alt='Edit']")).size();
        }

         private Contacts contactCashe = null;

        public Contacts all () {
            if (contactCashe != null) {
                return new Contacts(contactCashe);
            }
            contactCashe = new Contacts();
            List<WebElement> rows = wd.findElements(By.cssSelector("tr[name='entry']"));
            for (WebElement row : rows) {
                int id = Integer.parseInt(row.findElement(By.cssSelector("td:nth-child(1) input")).getAttribute("value"));
                String firstname = row.findElement(By.cssSelector("td:nth-child(3)")).getText();
                String lastname = row.findElement(By.cssSelector("td:nth-child(2)")).getText();
                contactCashe.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname));
            }
            return new Contacts(contactCashe);
        }
}



