package com.bdstar.www.xmlbuild.build;


import java.io.File;
import java.io.UnsupportedEncodingException;

public class MainBuild {

    //Main-Class: com.bdstar.www.xmlbuild.build.MainBuild
    public static void main(String[] args) {
        String jarWholePath = MainBuild.class.getProtectionDomain().getCodeSource().getLocation().getFile();
        try {
            jarWholePath = java.net.URLDecoder.decode(jarWholePath, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            System.out.println(e.toString());
        }
        String jarPath = new File(jarWholePath).getParentFile().getAbsolutePath();

        System.out.println("jarPath--:"+jarPath);

        String path =jarPath+"\\";// "E:\\CarManual\\";
        ListManual.build(path);
        PictureManual.build(path);

//
//        String path ="E:\\CarManual\\";
//        PictureManual.build(path);

        System.out.println("MainBuild-->finish");
    }
}
