package com.example.mowolfvillageon.fridgev1.FoodFiles;

import com.example.mowolfvillageon.fridgev1.Food;

/**
 * Created by mowolfvillageon on 2/24/16.
 */
public class Drinks  extends Food {
    private String name;
    private String catagory;
    private String expiration;

    public Drinks(String owner, String name, String expiration) {
        super(owner, "Drink", expiration, name);
        /* super(owner);
        this.name = name;
        this.catagory = "Drink";
        this.expiration = expiration; */
    }

    /* public String getName() {
        return name;
    }

    public String getCatagory() { return catagory; }

    public void setName(String name) {
        this.name = name;
    }

    public String getExpiration () { return expiration; } */
}
