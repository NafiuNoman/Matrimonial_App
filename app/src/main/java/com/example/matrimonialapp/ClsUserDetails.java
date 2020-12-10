package com.example.matrimonialapp;

public class ClsUserDetails {


    String Name,Age,Gender,Religion;

    public ClsUserDetails(String name, String age, String gender, String religion) {
        Name = name;
        Age = age;
        Gender = gender;
        Religion = religion;
    }



    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getReligion() {
        return Religion;
    }

    public void setReligion(String religion) {
        Religion = religion;
    }


}
