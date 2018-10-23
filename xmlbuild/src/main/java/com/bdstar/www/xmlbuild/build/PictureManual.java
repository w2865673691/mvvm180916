package com.bdstar.www.xmlbuild.build;

import com.bdstar.www.xmlbuild.beans.ManualBean;
import com.bdstar.www.xmlbuild.beans.PictureBean;
import com.bdstar.www.xmlbuild.beans.PressBean;
import com.bdstar.www.xmlbuild.helpers.IOHelper;
import com.thoughtworks.xstream.XStream;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class PictureManual {
    public static final String pathLocal = "xmlPicture";
    private static String xmlPath;

    public static void build(String path) {
        xmlPath = path + "\\raw";
        File file = new File(xmlPath);
        if (!file.exists()) {
            file.mkdirs();
        }

        path = path + pathLocal;
        getFileList(path + "\\car_in");
        getFileList(path + "\\car_out");
    }

    public static void getFileList(String strPath) {
        ArrayList<PictureBean> pictureBeans = new ArrayList<>();
        File dir = new File(strPath);
        if (!dir.exists()) {
            System.out.println(strPath+" file no exist");
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
                        PictureBean bean = readFileByLines(textFile);
                        if (bean != null) {
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

    public static PictureBean readFileByLines(File file) {
        PictureBean pictureBean = null;
        BufferedReader reader = null;
        InputStreamReader inputStreamReader = null;
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            inputStreamReader = new InputStreamReader(fileInputStream, "GBK");//"UTF-8");
            reader = new BufferedReader(inputStreamReader);
            String tempString = null;
            PressBean pressBean = null;
            ArrayList<PressBean> pressBeans = null;
            int line = 1;
            while ((tempString = reader.readLine()) != null) {
//                System.out.println(String.format("%03d", line) + ":" + tempString);
                if (tempString.contains("=")) {
                    String[] properties = tempString.split("=");
                    if (properties.length == 2) {
                        String key = properties[0].trim();
                        String value = properties[1].trim();
//                        System.out.println("key=" + key + "; value=" + value + ";");
                        if (line == 1) {
                            if ("src".equals(key) && value != null && value.length() > 0) {
                                pictureBean = new PictureBean();
                                pictureBean.setSrc(value);
                                pressBeans = new ArrayList<>();
                                pictureBean.setPressBeans(pressBeans);
                            } else {
                                return null;
                            }
                        } else {
                            if (pictureBean == null) {
                                return null;
                            }
                            if ("width".equals(key)) {
                                pictureBean.setWidth(value);
                            }
                            if ("hight".equals(key)) {
                                pictureBean.setHight(value);
                            }
                            if ("r".equals(key)) {
                                pictureBean.setR(value);
                            }


                            if ("link".equals(key)) {
                                pressBean = new PressBean();
                                pressBeans.add(pressBean);

                                StringBuilder sb = new StringBuilder();
                                if (value != null && value.contains(ListManual.pathLocal + "\\")) {
                                    String[] sp1 = value.split(ListManual.pathLocal + "\\\\");
//                                    System.out.println("sp1-->"+sp1);
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
                                pressBean.setLink(value);
                            }

                            if ("x".equals(key)) {
                                pressBean.setX(value);
                            }

                            if ("y".equals(key)) {
                                pressBean.setY(value);
                                pressBean = null;
                            }
                        }
                        line++;
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e1) {
                }
            }
            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (IOException e1) {
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }

        return pictureBean;
    }
}
