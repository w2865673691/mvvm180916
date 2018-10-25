package com.bdstar.www.xmlbuild.frames;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.AbstractListModel;

public class DataModel extends AbstractListModel {
    public static final String umin = "umin";
    public static final String umout = "umout";
    ArrayList<File> files = new ArrayList<>();

    public DataModel(String rootPath) {
        String dirPath = rootPath + "\\drawableIn";
        iniList(dirPath,"umin");

        dirPath = rootPath + "\\drawableOut";
        iniList(dirPath,"umout");
    }

    private void iniList(String dirPath,String pre) {
        File file = new File(dirPath);
        if (file.exists() && file.isDirectory()) {
            File[] fileArr = file.listFiles();
            for (int i = 0; i < fileArr.length; i++) {
                File tmp = fileArr[i];
                String fileName = tmp.getName();
                String suffix = fileName.substring(fileName.lastIndexOf("."));
                File realFile = new File(dirPath + "\\"+pre + String.format("%03d", i) + suffix);
                tmp.renameTo(realFile);
            }

            files.addAll(Arrays.asList( file.listFiles()));
        }
    }


    @Override
    public int getSize() {
        return files.size();
    }

    public File getItemAt(int i) {
        return files.get(i);
    }

    @Override
    public Object getElementAt(int i) {
        return getItemAt(i).getName();
    }
}
