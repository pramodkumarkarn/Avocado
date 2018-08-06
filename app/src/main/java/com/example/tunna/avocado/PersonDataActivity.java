package com.example.tunna.avocado;

public class PersonDataActivity {

    private String name;

    private String address;

    private String gender;

    private String phone;

    private String dateOfBirth;

    private String race;

    private String interestedIn;

    private String height;

    public String getName(){

        return name;

    }

    //setters and getters

    public void setName(String name){

        this.name=name;

    }

    public String getAddress(){

        return address;

    }

    public void setAddress(String address){

        this.address=address;

    }

    public String getGender(){

        return gender;

    }

    public void setGender(String gender){

        this.gender=gender;

    }

    public String getHeight(){

        return height;

    }

    public void setHeight (String height){

        this.height = height;

    }

    public void setDateOfBirth(String dateOfBirth) {

        this.dateOfBirth = dateOfBirth;

    }

    public String getDateOfBirth() {

        return dateOfBirth;

    }

    public String getPhone(){

        return phone;

    }

    public void setPhone(String phone){

        this.phone=phone;

    }

    public String getRace(){

        return race;

    }

    public void setRace(String race){

        this.race=race;

    }
    public String getInterestedIn(){

        return interestedIn;

    }

    public void setInterestedIn(String interestedIn){

        this.interestedIn=interestedIn;

    }

}
