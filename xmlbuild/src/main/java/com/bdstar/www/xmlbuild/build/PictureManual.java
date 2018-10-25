package com.bdstar.www.xmlbuild.build;

import com.bdstar.www.xmlbuild.beans.PictureBean;
import com.bdstar.www.xmlbuild.beans.PressBean;
import com.bdstar.www.xmlbuild.helpers.IOHelper;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class PictureManual {
    public static final String pathLocal = "xmlPicture";
    public static final String car_in = "car_in";
    public static final String car_out = "car_out";
    private static String xmlPath;

    public static void build(String path) {
        xmlPath = path + "\\raw";
        File file = new File(xmlPath);
        if (!file.exists()) {
            file.mkdirs();
        }

        path = path + pathLocal;
        getFileList(path + "\\"+car_in);
        getFileList(path + "\\"+car_out);
    }

    public static void getFileList(String strPath) {
        ArrayList<PictureBean> pictureBeans = new ArrayList<>();
        File dir = new File(strPath);
        if (!dir.exists()) {
            System.out.println(strPath + " file no exist");
            return;
        }

        File[] files = dir.listFiles();
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                String fileName = files[i].getName();
                System.out.println(fileName);
                if (!files[i].isDirectory()) {
                    if (fileName.endsWith(".txt")) {
                        File textFile = files[i];
                        PictureBean bean = readFile(textFile);
                        if (bean != null) {
                            ArrayList<PressBean> presses = bean.getPressBeans();
                            if(presses!=null){
                                for (PressBean press : presses) {
                                    String value = press.getLink();
                                    StringBuilder sb = new StringBuilder();
                                    if (value != null && value.contains(ListManual.pathLocal + "\\")) {
                                        String[] sp1 = value.split(ListManual.pathLocal + "\\\\");
                                        if (sp1.length == 2) {
                                            String[] sp2 = sp1[1].split("\\\\");

                                            sb.append("0");
                                            for (String nm : sp2) {
                                                System.out.println("nm:" + nm);
                                                if (nm.contains(".")) {
                                                    sb.append(nm.substring(0, nm.indexOf(".")));
                                                }
                                            }
                                        }
                                    }
                                    value = sb.toString();
                                    press.setLink(value);
                                }
                            }
                            pictureBeans.add(bean);
                        }
                    }
                }
            }
        }

        XStream xstream2 = new XStream();
        xstream2.alias(PictureBean.alias, PictureBean.class);
        xstream2.alias(PressBean.alias, PressBean.class);
        xstream2.processAnnotations(PictureBean.class);

        String xmlString = xstream2.toXML(pictureBeans);
        System.out.println("xml-->\n" + xmlString);

        String name = strPath.substring(strPath.lastIndexOf("\\") + 1, strPath.length());
        String dirPath = strPath.substring(0, strPath.lastIndexOf("\\"));
        String xmlPath = PictureManual.xmlPath + "\\xml" + name + ".txt";
        System.out.println("xmlPath-->" + xmlPath);

        IOHelper.saveXml(xmlString, xmlPath);
    }

    public static void saveFile(File file, PictureBean pictureBean) {
        XStream xStream = new XStream();
        xStream.alias(PictureBean.alias, PictureBean.class);
        xStream.alias(PressBean.alias, PressBean.class);
        xStream.processAnnotations(PictureBean.class);
        String xmlString = xStream.toXML(pictureBean);
        IOHelper.saveXml(xmlString, file);
    }

    public static PictureBean readFile(File file) {
        PictureBean pictureBean = new PictureBean();
        InputStreamReader inputStreamReader = null;
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");//"UTF-8");
            XStream xStream = new XStream(new DomDriver());
            xStream.alias(PictureBean.alias, PictureBean.class);
            xStream.alias(PressBean.alias, PressBean.class);
            xStream.processAnnotations(PictureBean.class);
            pictureBean = (PictureBean) xStream.fromXML(inputStreamReader);
        } catch (Exception e) {
//            e.printStackTrace();
        } finally {
            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (IOException e1) {
                }
            }
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e1) {
                }
            }
        }
        return pictureBean;
    }
}
