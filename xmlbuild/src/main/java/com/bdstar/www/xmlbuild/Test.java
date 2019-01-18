package com.bdstar.www.xmlbuild;

public class Test {


    public static void main(String[] args) {
        testFinally();
    }

    private static void testFinally() {
        try {
            try {
                int a = 1;
                int b = 1;

                int c = b / a;
            } catch (Exception e) {
                System.out.println("A:" + e.getMessage());

                return;
            } finally {
                System.out.println("A:finally");
            }

            System.out.println("B:do");
        } catch (Exception e) {
            System.out.println("B:" + e.getMessage());

            return;
        } finally {
            System.out.println("B:finally");
        }
    }

    private static void testMaxInt() {
        int a = Integer.MAX_VALUE;
        System.out.println("a-->" + a);
        int num = 10;
        for (int i = 0; i < num; i++) {
            a = a * 2;
            System.out.println("a-->" + a);
        }
    }


    //    public void setDoc(){
//        String info = readWord("娄言龙-导航.docx");
//        setText(info);
//    }
//
//    public String readWord(String fileName) {
//        InputStream some = null;
//        String text = null;
//        try {
////            String url = "file:///android_asset/" + fileName;
////            some = new FileInputStream(url);
//            some = getResources().getAssets().open(fileName);
//            WordExtractor extractor  = new WordExtractor();
//            text = extractor.extractText(some);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }finally {
//            if(some!=null){
//                try {
//                    some.close();
//                } catch (IOException e) {
//                }
//            }
//        }
//        return text;
//    }
}
