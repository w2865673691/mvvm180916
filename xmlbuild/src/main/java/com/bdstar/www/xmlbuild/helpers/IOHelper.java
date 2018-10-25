package com.bdstar.www.xmlbuild.helpers;

import com.bdstar.www.xmlbuild.beans.ManualBean;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

public class IOHelper {
    public static void saveXml(String xmlString, File file) {
        if (file.exists()) {
            file.delete();
        }
        try {
            file.createNewFile();
            IOHelper.saveString(file.getAbsolutePath(), xmlString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveXml(String xmlString, String xmlmanualPath) {
        File file = new File(xmlmanualPath);
        saveXml(xmlString,file);
    }

    public static void saveString(String fileName, String content) {
        BufferedWriter writer = null;
        OutputStreamWriter outputStreamWriter = null;
        try {
            outputStreamWriter = new OutputStreamWriter(new FileOutputStream(fileName),
                    "UTF-8");
            writer = new BufferedWriter(outputStreamWriter);
            writer.write(content);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStreamWriter != null) {
                try {
                    outputStreamWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static ArrayList<ManualBean> pathManualsByParentId(String xml) {
        XPathFactory pathFactory = XPathFactory.newInstance();
        XPath path = pathFactory.newXPath();
        InputStream is = new ByteArrayInputStream(xml.getBytes());
        InputSource source = new InputSource(is);

        String data = "/ManualBean/subManualBeans/ManualBean/subManualBeans/ManualBean[parentId=\"01\"]";
        ArrayList<ManualBean> manualBeans = new ArrayList<ManualBean>();
        try {
            NodeList nodeList = (NodeList) path.evaluate(data, source, XPathConstants.NODESET);
            JAXBContext jaxbContext = JAXBContext.newInstance(ManualBean.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                ManualBean manualBean = (ManualBean) unmarshaller.unmarshal(node);
                manualBeans.add(manualBean);
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return manualBeans;
    }
}
