package com.sakshi.coaching.Model;

import java.sql.Date;

public class Teacher {
    int id;
    String firstName;
    String middleName;
    String lastName;
    String educationDetails;
    int contactNumber;
    int experience;
    Date dob;
    int age;
    String email;
    String address;
    String street;
    String city;
    String state;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEducationDetails() {
        return educationDetails;
    }

    public void setEducationDetails(String educationDetails) {
        this.educationDetails = educationDetails;
    }

    public int getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(int contactNumber) {
        this.contactNumber = contactNumber;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Teacher() {
    }

    public Teacher(int id, String firstName, String middleName, String lastName, String educationDetails,
            int contactNumber, int experience, Date dob, int age, String email, String address, String street,
            String city, String state) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.educationDetails = educationDetails;
        this.contactNumber = contactNumber;
        this.experience = experience;
        this.dob = dob;
        this.age = age;
        this.email = email;
        this.address = address;
        this.street = street;
        this.city = city;
        this.state = state;
    }

    @Override
    public String toString() {
        return "Teacher [address=" + address + ", age=" + age + ", city=" + city + ", contactNumber=" + contactNumber
                + ", dob=" + dob + ", educationDetails=" + educationDetails + ", email=" + email + ", experience="
                + experience + ", firstName=" + firstName + ", id=" + id + ", lastName=" + lastName + ", middleName="
                + middleName + ", state=" + state + ", street=" + street + "]";
    }

}
