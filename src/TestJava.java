import java.lang.reflect.Method;

public class TestJava {

    public static void main(String[] args) {


        try {
            Class<String> cls = String.class;
            Method length = cls.getMethod("length");
            int a = (int) length.invoke("abc");
            System.out.println(a);
        }catch (Exception e){

        }


        String s = new String("abc");


    }

}
