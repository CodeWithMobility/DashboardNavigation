package com.mobiledev.dashboardnavigation;

/**
 * Created by manu on 1/7/2018.
 */

public class ItemModel {
    private int image;
    private String title;

    public int getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    private String amount;
    private String category;
    public ItemModel(int image, String title, String amount, String category) {
        this.image = image;
        this.title = title;
        this.amount = amount;
        this.category = category;
    }


}
