package com.example.blog.Model;

public class Request {
    private String hospitalName;
    private String hospitalAddress;
    private String requestDescription;
    private String requestPoints;
    private String hospitalImage;
    private String requestQuantity;

    public Request() {

    }

    public Request(String hosName, String hosAddress, String reqDescription, String reqPoints, String hosImage, String reqQuantity) {
        this.hospitalName = hosName;
        this.hospitalAddress = hosAddress;
        this.requestDescription = reqDescription;
        this.requestPoints = reqPoints;
        this.hospitalImage = hosImage;
        this.requestQuantity = reqQuantity;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getHospitalAddress() {
        return hospitalAddress;
    }

    public void setHospitalAddress(String hospitalAddress) {
        this.hospitalAddress = hospitalAddress;
    }

    public String getRequestDescription() {
        return requestDescription;
    }

    public void setRequestDescription(String requestDescription) {
        this.requestDescription = requestDescription;
    }

    public String getRequestPoints() {
        return requestPoints;
    }

    public void setRequestPoints(String requestPoints) {
        this.requestPoints = requestPoints;
    }

    public String getHospitalImage() {
        return hospitalImage;
    }

    public void setHospitalImage(String hospitalImage) {
        this.hospitalImage = hospitalImage;
    }

    public String getRequestQuantity() {
        return requestQuantity;
    }

    public void setRequestQuantity(String requestQuantity) {
        this.requestQuantity = requestQuantity;
    }

}
