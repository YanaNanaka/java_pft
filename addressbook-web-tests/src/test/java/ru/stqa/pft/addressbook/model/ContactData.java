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
    private String group;

    public ContactData(String firstname, String middlename, String lastname, String nickname, String company, String address, String home, String mobile, String group) {
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.nickname = nickname;
        this.company = company;
        this.address = address;
        this.home = home;
        this.mobile = mobile;
        this.group = group;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
        if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        return mobile != null ? mobile.equals(that.mobile) : that.mobile == null;
    }

    @Override
    public int hashCode() {
        int result = firstname != null ? firstname.hashCode() : 0;
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (mobile != null ? mobile.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", address='" + address + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }

    public String getGroup() { return group; }
}
