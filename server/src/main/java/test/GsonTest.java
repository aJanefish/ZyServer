package test;

import transformer.GsonTransformer;

public class GsonTest {
    String name;
    int age;

    public static void main(String[] args) {
        GsonTransformer gsonTransformer = GsonTransformer.getDefault();

        try {
            String ss = gsonTransformer.render("22");
            System.out.println(ss);//"22"
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            GsonTest gsonTest = new GsonTest();
            gsonTest.age = 18;
            gsonTest.name = "zy";//{"name":"zy","age":18}
            String ss = gsonTransformer.render(gsonTest);
            System.out.println(ss);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
