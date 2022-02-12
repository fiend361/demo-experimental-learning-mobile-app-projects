package com.example.blog.Model;

public class Donor {
    private String donorDescription, image, firstName, bloodType;

    public Donor() {
    }

    public Donor(String donorDescription, String image, String firstName, String bloodType) {
        this.donorDescription = donorDescription;
        this.image = image;
        this.firstName = firstName;
        this.bloodType = bloodType;
    }

    public String getDonorDescription() {
        return donorDescription;
    }

    public void setDonorDescription(String donorDescription) {
        this.donorDescription = donorDescription;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }
}
