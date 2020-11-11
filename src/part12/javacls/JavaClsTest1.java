package part12.javacls;

public class JavaClsTest1 {

    /////////////////////////////////

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /////////////////////////////////


    public boolean isOk() {
        return true;
    }

    public void setOk(boolean ok) {
        System.out.println(ok);
    }

    /////////////////////////////////

    public int getAge() {
        return 10086;
    }

    /////////////////////////////////
    public boolean isGood() {
        return true;
    }
}


