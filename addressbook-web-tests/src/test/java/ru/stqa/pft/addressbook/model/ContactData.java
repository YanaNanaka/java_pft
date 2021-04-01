package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;
import org.hibernate.sql.Select;
import org.openqa.selenium.By;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "addressbook")
@XStreamAlias("contact")
public class ContactData {
    @Id
    @XStreamOmitField
    private int id = Integer.MAX_VALUE;
    @Expose
    private String firstname;
    @Transient
    private String middlename;
    @Expose
    private String lastname;
    @Transient
    private String nickname;
    @Transient
    private String company;
    @Transient
    @Type(type = "text")
    private String address;
    @Expose
    @Type(type = "text")
    private String home;
    @Expose
    @Type(type = "text")
    private String mobile;
    @Transient
    @Type(type = "text")
    private String work;

    //@Transient
    @Expose
    @Type(type = "text")
    private String email;
    //@Transient
    @Expose
    @Type(type = "text")
    private String email2;
    @Transient
    @Type(type = "text")
    private String email3;
    @Transient
    private String allMails;
    @Transient
    //@Column(name = "photo")
    // @Type(type = "text")
    private String photo;
    @Transient
    private String allPhones;

    @ManyToMany (fetch = FetchType.EAGER)
    @JoinTable(name = "address_in_groups",
            joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<GroupData> groups = new HashSet<GroupData>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id && Objects.equals(firstname, that.firstname) && Objects.equals(lastname, that.lastname) && Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname, address);
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

    public File getPhoto() {
        return new File(photo);
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }

    public String getAllMails() { return allMails; }

    public String getAllPhones() {
        return allPhones;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public int getId () {
        return id;
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
    public String getWork () {
        return work;
    }
    public String getEmail() { return email; }
    public String getEmail2() { return email2; }
    public String getEmail3() { return email3; }

    public ContactData withAllMails(String allMails) {
        this.allMails = allMails;
        return this;
    }

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public ContactData withMiddlename(String middlename) {
        this.middlename = middlename;
        return this;
    }

    public ContactData withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public ContactData withNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public ContactData withCompany(String company) {
        this.company = company;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactData withHome(String home) {
        this.home = home;
        return this;
    }

    public ContactData withMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public ContactData withWork(String work) {
        this.work = work;
        return this;
    }

    public Groups getGroups() {
        return new Groups(groups);
    }

    public void setGroups(Set<GroupData> groups) {
        this.groups = groups;
    }

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
}
    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }
    public ContactData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public ContactData inGroup(GroupData group) {
        groups.add(group);
        return this;
    }
}