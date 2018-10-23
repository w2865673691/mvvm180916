package com.bdstar.www.xmlbuild.beans;

import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ManualBean")
public class ManualBean extends BaseBean {
    public static final String alias = "ManualBean";

    public interface Type {
        final String empty = "empty";
        final String next = "next";
    }

    private String name;
    private String type;
    @XStreamAsAttribute
    private String id;

    @XmlElement(name = "reg_parentId")
    private String parentId;

    private ArrayList<ManualBean> subManualBeans;
    private String contenttext;

    private String relativeId;

    public String getRelativeId() {
        return relativeId;
    }

    public void setRelativeId(String relativeId) {
        this.relativeId = relativeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public ArrayList<ManualBean> getSubManualBeans() {
        return subManualBeans;
    }

    public void setSubManualBeans(ArrayList<ManualBean> subManualBeans) {
        this.subManualBeans = subManualBeans;
    }

    public String getContenttext() {
        return contenttext;
    }

    public void setContenttext(String contenttext) {
        this.contenttext = contenttext;
    }
}
