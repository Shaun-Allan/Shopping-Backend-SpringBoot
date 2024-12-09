package com.shaun.shopping.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
public class Product {
    @Id
    private String pid;
    private String pname;
    private String pdesc;
    private int cost;
    private String image; // New field for storing image as a Base64 string or URL

    public Product(String pid, String pname, String pdesc, int cost, String image) {
        this.pid = pid;
        this.pname = pname;
        this.pdesc = pdesc;
        this.cost = cost;
        this.image = image;
    }

    public Product() {
        this.pid = null;
        this.pname = null;
        this.pdesc = null;
        this.cost = 0;
        this.image = null;
    }

    // Getters and Setters
    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPdesc() {
        return pdesc;
    }

    public void setPdesc(String pdesc) {
        this.pdesc = pdesc;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
