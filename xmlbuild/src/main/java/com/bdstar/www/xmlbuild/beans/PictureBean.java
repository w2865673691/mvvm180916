package com.bdstar.www.xmlbuild.beans;

import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import java.util.ArrayList;

public class PictureBean extends BaseBean {
    public static final String alias = "PictureBean";
    private String id;
    @XStreamAsAttribute
    private String src;
    private String width;
    private String hight;
    private String r;
    private ArrayList<PressBean> pressBeans;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHight() {
        return hight;
    }

    public void setHight(String hight) {
        this.hight = hight;
    }

    public String getR() {
        return r;
    }

    public void setR(String r) {
        this.r = r;
    }

    public ArrayList<PressBean> getPressBeans() {
        return pressBeans;
    }

    public void setPressBeans(ArrayList<PressBean> pressBeans) {
        this.pressBeans = pressBeans;
    }

}
