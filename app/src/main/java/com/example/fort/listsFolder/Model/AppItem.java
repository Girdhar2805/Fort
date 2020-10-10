package com.example.fort.listsFolder.Model;

import android.graphics.drawable.Drawable;

public class AppItem {

    public Drawable icons113;
    private String name113;
    private String packagename113;

    public AppItem(Drawable icons, String name, String packagename) {
        this.icons113 = icons;
        this.name113 = name;
        this.packagename113 = packagename;
    }

    public Drawable getIcons() {
        return icons113;
    }

    public void setIcons(Drawable icons) {
        this.icons113 = icons;
    }

    public String getName() {
        return name113;
    }

    public void setName(String name) {
        this.name113 = name;
    }

    public String getPackagename() {
        return packagename113;
    }

    public void setPackagename(String packagename) {
        this.packagename113 = packagename;
    }
}
