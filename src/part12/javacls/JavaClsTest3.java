package part12.javacls;


public class JavaClsTest3 {


    public static final String name = "hello world";
    public static int age = 100;


    public int sum(int[] arr) {
        if (arr == null) {
            return -1;
        }
        int result = 0;
        for (int i = 0; i < arr.length; i++) {
            result += arr[i];
        }
        return result;
    }

    public void test1(int... ints) {
        for (int i = 0; i < ints.length; i++) {
            System.out.println(ints[i]);
        }
    }

}


