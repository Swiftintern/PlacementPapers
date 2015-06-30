package com.example.chhavi.swiftintern;

/**
 * Created by Administrator on 06-Mar-15.
 */
public class drawerItem {

    String ItemName;
    int imgResID;

    public drawerItem(String itemName, int imgResID) {
        super();
        ItemName = itemName;
        this.imgResID = imgResID;
    }

    public String getItemName() {
        return ItemName;
    }
    public void setItemName(String itemName) {
        ItemName = itemName;
    }
    public int getImgResID() {
        return imgResID;
    }
    public void setImgResID(int imgResID) {
        this.imgResID = imgResID;
    }

}