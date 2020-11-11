package part12.javacls;

import part12.RRCPart12;

public class JavaClsTest4 {

    //调用Kotlin的方法
    public static void main(String[] args) {

        RRCPart12.ktCallJava3();
        //注意：运行之前需要build -rebuild 重新编译项目
        RRCPart12.testEx();
    }

}


