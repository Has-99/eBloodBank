package com.example.e_bloodbank;

enum BloodType {
    A_N("A-"), A_P("A+"), AB_N("AB-"), AB_P("AB+"), B_N("B-"), B_P("B+"), O_N("O-"), O_P("O+");

    private String value;

    BloodType(String value) {
        this.value = value;
    }

    String value() {
        return this.value;
    }
}

enum UserType {
    DONOR, RECIPIENT
}

class User {
    private String name;
    private String gender;
    private String bloodType;
    private String province;
    private String district;
    private String contactNumber;
    private UserType type;

    public User() {

    }

    public User(String name, String gender, String bloodType, String province, String district, String contactNumber, UserType type) {
        this.name = name;
        this.gender = gender;
        this.bloodType = bloodType;
        this.province = province;
        this.district = district;
        this.contactNumber = contactNumber;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getBloodType() {
        return bloodType;
    }

    public String getProvince() {
        return province;
    }

    public String getDistrict() {
        return district;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public UserType getType() {
        return type;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setType(UserType type) {
        this.type = type;
    }
}

class Hospital {
    private String cluster;
    private String district;
    private String province;
    private String tp;
    private String name;

    Hospital() {

    }

    public String getCluster() {
        return cluster;
    }

    public String getDistrict() {
        return district;
    }

    public String getProvince() {
        return province;
    }

    public String getTp() {
        return tp;
    }

    public String getName() {
        return name;
    }
}

