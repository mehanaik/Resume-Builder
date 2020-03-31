package com.depstar.resumebuilder;

public class product {
    String productname,desc,time;

    public String getProductname() {
        return productname;
    }

    public product(String productname) {
        this.productname = productname;

    }

    public String getDesc() {
        return desc;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public product(String productname, String desc, String time) {
        this.productname = productname;
        this.desc = desc;
        this.time = time;
    }



}
