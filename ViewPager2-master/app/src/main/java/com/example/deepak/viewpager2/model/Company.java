package com.example.deepak.viewpager2.model;

/**
 * Created by Chandan Suri on 4/8/2017.
 */

public class Company {

    private String companyName;
    private int thumbnail;

    public Company(String companyName,int thumbnail)
    {
        this.companyName = companyName;
        this.thumbnail = thumbnail;
    }

    public String getCompanyName()
    {
        return companyName;
    }

    public int getThumbnail()
    {
        return thumbnail;
    }

    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }

    public void setThumbnail(int thumbnail)
    {
        this.thumbnail = thumbnail;
    }
}
