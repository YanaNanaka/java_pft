package ru.stqa.pft.addressbook;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

public class ContactCreationTests {
  private WebDriver wd;

  @BeforeMethod(alwaysRun = true)
  public void setUp() throws Exception {
    wd = new ChromeDriver();
    wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    wd.get("http://localhost/addressbook/edit.php");
    login("admin", "secret");
  }
  private void login(String username, String password) {
    wd.findElement(By.name("user")).click();
    wd.findElement(By.name("user")).clear();
    wd.findElement(By.name("user")).sendKeys("admin");
    wd.findElement(By.name("pass")).click();
    wd.findElement(By.name("pass")).clear();
    wd.findElement(By.name("pass")).sendKeys("secret");
    wd.findElement(By.xpath("//input[@value='Login']")).click();
  }

  @Test
  public void testContactCreation() throws Exception {
    addNewContact();
    fillContactForm(new ContactData("Иван", "Иванович", "Иванов", "Ванька", "КиТ", "Москва, ул. Лесная, д. 7", "552233", "89632541789", "546987", "123654789", "ivan@mail.ru", "17", "August", "1990", "1", "Москва, ул. Ямская, д. 12, кв. 234"));
    saveContact();
    returnToHomePage();
  }

    private void addNewContact() {
      wd.findElement(By.linkText("add new")).click();
    }

    private void fillContactForm(ContactData contactData) {
      wd.findElement(By.name("firstname")).click();
      wd.findElement(By.name("firstname")).clear();
      wd.findElement(By.name("firstname")).sendKeys("Иван");
      wd.findElement(By.name("middlename")).click();
      wd.findElement(By.name("middlename")).clear();
      wd.findElement(By.name("middlename")).sendKeys("Иванович");
      wd.findElement(By.name("lastname")).click();
      wd.findElement(By.name("lastname")).clear();
      wd.findElement(By.name("lastname")).sendKeys("Иванов");
      wd.findElement(By.name("nickname")).click();
      wd.findElement(By.name("nickname")).clear();
      wd.findElement(By.name("nickname")).sendKeys("Ванька");
      wd.findElement(By.name("company")).click();
      wd.findElement(By.name("company")).clear();
      wd.findElement(By.name("company")).sendKeys("КиТ");
      wd.findElement(By.name("address")).click();
      wd.findElement(By.name("address")).clear();
      wd.findElement(By.name("address")).sendKeys("Москва, ул. Лесная, д. 7");
      wd.findElement(By.name("home")).click();
      wd.findElement(By.name("home")).clear();
      wd.findElement(By.name("home")).sendKeys("552233");
      wd.findElement(By.name("mobile")).click();
      wd.findElement(By.name("mobile")).clear();
      wd.findElement(By.name("mobile")).sendKeys("89632541789");
      wd.findElement(By.name("work")).click();
      wd.findElement(By.name("work")).clear();
      wd.findElement(By.name("work")).sendKeys("546987");
      wd.findElement(By.name("fax")).click();
      wd.findElement(By.name("fax")).clear();
      wd.findElement(By.name("fax")).sendKeys("123654789");
      wd.findElement(By.name("email")).click();
      wd.findElement(By.name("email")).clear();
      wd.findElement(By.name("email")).sendKeys("ivan@mail.ru");
      wd.findElement(By.name("bday")).click();
      new Select(wd.findElement(By.name("bday"))).selectByVisibleText("17");
      wd.findElement(By.name("bday")).click();
      wd.findElement(By.name("bmonth")).click();
      new Select(wd.findElement(By.name("bmonth"))).selectByVisibleText("August");
      wd.findElement(By.name("bmonth")).click();
      wd.findElement(By.name("byear")).click();
      wd.findElement(By.name("byear")).clear();
      wd.findElement(By.name("byear")).sendKeys("1990");
      wd.findElement(By.name("new_group")).click();
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText("1");
      wd.findElement(By.name("new_group")).click();
      wd.findElement(By.name("address2")).click();
      wd.findElement(By.name("address2")).clear();
      wd.findElement(By.name("address2")).sendKeys("Москва, ул. Ямская, д. 12, кв. 234");
    }

    private void saveContact() {
      wd.findElement(By.xpath("(//input[@name='submit'])[2]")).click();
    }

    private void returnToHomePage() {
      wd.findElement(By.linkText("home")).click();
    }

  @AfterMethod(alwaysRun = true)
  public void tearDown() throws Exception {
    wd.quit();
  }

  private boolean isElementPresent(By by) {
    try {
      wd.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }
}
