package ru.stqa.pft.addressbook.model;

public class ContactData {
    private String firstname;
    private String middlename;
    private String lastname;
    private String nickname;
    private String company;
    private String address;
    private String home;
    private String mobile;
    private String work;
    private String fax;
    private String email;
    private String bday;
    private String bmonth;
    private String byear;
    private String new_group;
    private String address2;

    public ContactData(String firstname, String middlename, String lastname, String nickname, String company, String address, String home, String mobile, String work, String fax, String email, String bday, String bmonth, String byear, String new_group, String address2) {
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.nickname = nickname;
        this.company = company;
        this.address = address;
        this.home = home;
        this.mobile = mobile;
        this.work = work;
        this.fax = fax;
        this.email = email;
        this.bday = bday;
        this.bmonth = bmonth;
        this.byear = byear;
        this.new_group = new_group;
        this.address2 = address2;
    }
}
