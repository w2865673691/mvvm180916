package com.bdstar.www.xmlbuild.frames;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LinkComponent {
    boolean isEdit = true;
    JTextField picLink;
    JTextField picX;
    JTextField picY;
    JButton btnEdit;
    ArrayList<Component> components = new ArrayList<>();

    public LinkComponent(JTextField picLink, JTextField picX, JTextField picY) {
        this.picLink = picLink;
        this.picX = picX;
        this.picY = picY;
        addComponent(picLink);
        addComponent(picX);
        addComponent(picY);
    }

    public JTextField getPicLink() {
        return picLink;
    }

    public void setPicLink(JTextField picLink) {
        this.picLink = picLink;
    }

    public JTextField getPicX() {
        return picX;
    }

    public void setPicX(JTextField picX) {
        this.picX = picX;
    }

    public JTextField getPicY() {
        return picY;
    }

    public void setPicY(JTextField picY) {
        this.picY = picY;
    }

    public JButton getBtnEdit() {
        return btnEdit;
    }

    public void setBtnEdit(JButton btnEdit) {
        this.btnEdit = btnEdit;
    }

    public boolean isEdit() {
        return isEdit;
    }

    public void setEdit(boolean edit) {
        isEdit = edit;
        if (btnEdit != null) {
            if (isEdit) {
                btnEdit.setText("编辑中");

            } else {
                btnEdit.setText("编辑");
            }
        }
    }

    public ArrayList<Component> getComponents() {
        return components;
    }

    public void setComponents(ArrayList<Component> components) {
        this.components = components;
    }

    public void addComponent(Component component) {
        if (component != null && !components.contains(component)) {
            components.add(component);
        }
    }

    public void clearComponent(JPanel panel) {
        if (panel != null) {
            for (Component component : components) {
                panel.remove(component);
            }
            components.clear();
        }
    }
}
