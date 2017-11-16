package com.example.srikar.s_b35_asynctaskfirsteg;

/**
 * Created by sunilkumar on 18-07-2017.
 */

public class Images {

    public String imageUrl,name;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Images(String imageUrl, String name) {

        this.imageUrl = imageUrl;
        this.name = name;
    }
    /*   public String imageUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String name;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Images(String imageUrl) {

        this.imageUrl = imageUrl;
    }*/
}
