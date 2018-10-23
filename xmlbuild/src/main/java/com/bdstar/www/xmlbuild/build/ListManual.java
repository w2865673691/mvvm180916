package com.bdstar.www.xmlbuild.build;

import com.bdstar.www.xmlbuild.beans.ManualBean;
import com.bdstar.www.xmlbuild.helpers.IOHelper;
import com.thoughtworks.xstream.XStream;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.SequenceInputStream;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

public class ListManual {
    public static final String pathLocal="xmlList";
    public static void build(String path) {
        String xmlPath = path + "\\raw";
        File file = new File(xmlPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        xmlPath = xmlPath + "\\xmlmanual.txt";

        path =path+ pathLocal;
        File file1=new File(path);
        if (!file1.exists()) {
            System.out.println(path+" file no exist");
            return;
        }

        ManualBean parentBean = new ManualBean();
        parentBean.setId("0");
        getFileList(path, parentBean);

        XStream xstream2 = new XStream();
        xstream2.alias(ManualBean.alias, ManualBean.class);
        xstream2.processAnnotations(ManualBean.class);
        String xmlString = xstream2.toXML(parentBean);
        System.out.println("xml-->\n" + xmlString);
        IOHelper.saveXml(xmlString, xmlPath);
    }


    public static void getFileList(String strPath, ManualBean parentBean) {
        String parentId = parentBean.getId();
        File dir = new File(strPath);
        File[] files = dir.listFiles();
        String relativeId = null;
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                String fileName = files[i].getName();
                System.out.println(fileName);
                if (fileName == null || fileName.length() <= 0 || !fileName.contains(".")) {
                    continue;
                }
                if (files[i].isDirectory()) {
                    String[] namePart = fileName.split("\\.");
                    if (namePart == null || namePart.length < 2) {
                        continue;
                    }
                    String mid = parentId + namePart[0];
                    String type = ManualBean.Type.next;
                    if (namePart.length >= 3 && "empty".equals(namePart[namePart.length - 1])) {
                        type = ManualBean.Type.empty;
                        relativeId = mid;
                    }
                    String name = namePart[1];
                    if (name.contains("l")) {
                        name = name.replace("l", "/");
                    }
                    if (name.endsWith("x")) {
                        System.out.println("xx--" + name);
                        name = name.substring(0, name.lastIndexOf("x") - 1);
                        name = name + "*";
                    }
                    ManualBean manualBean = new ManualBean();
                    manualBean.setName(name);
                    manualBean.setId(mid);
                    manualBean.setParentId(parentId);
                    manualBean.setRelativeId(relativeId);
                    manualBean.setType(type);

                    ArrayList<ManualBean> manualBeans = parentBean.getSubManualBeans();
                    if (manualBeans == null) {
                        manualBeans = new ArrayList<>();
                        parentBean.setSubManualBeans(manualBeans);
                    }
                    manualBeans.add(manualBean);

                    if (ManualBean.Type.next.equals(manualBean.getType())) {
                        getFileList(files[i].getAbsolutePath(), manualBean);
                    }
                } else { // file
                    if (fileName.equals("contenttext.txt")) {
                        File textFile = files[i];
                        String contenttext = readFileByLines(textFile);
                        parentBean.setContenttext(contenttext);
                    }
                }
            }

        }
    }



    public static String readFileByLines(File file) {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader reader = null;
        InputStreamReader inputStreamReader = null;
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            inputStreamReader = new InputStreamReader(fileInputStream, "GBK");//"UTF-8");
            reader = new BufferedReader(inputStreamReader);
            String tempString = null;
            int line = 1;
            while ((tempString = reader.readLine()) != null) {
                if (line == 1) {
                    stringBuilder.append(tempString);
                } else {
                    stringBuilder.append("\n" + tempString);
                }
                line++;
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

        return stringBuilder.toString();
    }


}
