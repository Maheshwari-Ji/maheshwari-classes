package com.sakshi.coaching.Model;

import java.sql.Date;

public class Student {
    int id;
    String firstName;
    String middleName;
    String lastName;
    String fatherName;
    String motherName;
    int contactNumber;
    Date dob;
    int age;
    String address;
    String street;
    String city;
    String state;

    public Student() {
    }

    public Student(int id, String firstName, String middleName, String lastName, String fatherName, String motherName,
            int contactNumber, Date dob, int age, String address, String street, String city, String state) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.fatherName = fatherName;
        this.motherName = motherName;
        this.contactNumber = contactNumber;
        this.dob = dob;
        this.age = age;
        this.address = address;
        this.street = street;
        this.city = city;
        this.state = state;
    }

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

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public int getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(int contactNumber) {
        this.contactNumber = contactNumber;
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

    @Override
    public String toString() {
        return "Student [address=" + address + ", age=" + age + ", city=" + city + ", contactNumber=" + contactNumber
                + ", dob=" + dob + ", fatherName=" + fatherName + ", firstName=" + firstName + ", id=" + id
                + ", lastName=" + lastName + ", middleName=" + middleName + ", motherName=" + motherName + ", state="
                + state + ", street=" + street + "]";
    }

}
