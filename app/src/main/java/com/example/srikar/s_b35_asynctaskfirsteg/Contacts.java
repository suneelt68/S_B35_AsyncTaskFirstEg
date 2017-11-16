package com.example.srikar.s_b35_asynctaskfirsteg;

/**
 * Created by Srikar on 27-03-2017.
 */
public class Contacts {
    //3. design bean class with constructor setter and getrrer
    private String sno;
    private String name;
    private String email;
    private String mobile;
    private String imageUrl;

    public Contacts(String sno, String name, String email, String mobile,String imageUrl) {

        this.sno = sno;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.imageUrl =imageUrl;
    }


    public String getSno() {
        return sno;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }


}
