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

    public ContactData(String firstname, String middlename, String lastname, String nickname, String company, String address, String home, String mobile) {
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.nickname = nickname;
        this.company = company;
        this.address = address;
        this.home = home;
        this.mobile = mobile;
    }
    public String getFirstname () {
        return firstname;
    }
    public String getMiddlename () {
        return middlename;
    }
    public String getLastname () {
        return lastname;
    }
    public String getNickname () {
        return nickname;
    }
    public String getCompany () {
        return company;
    }
    public String getAddress () {
        return address;
    }
    public String getHome () {
        return home;
    }
    public String getMobile () {
        return mobile;
    }
}
