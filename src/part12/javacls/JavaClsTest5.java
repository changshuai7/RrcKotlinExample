package part12.javacls;


import kotlin.jvm.JvmClassMappingKt;
import kotlin.reflect.KClass;
import part12.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class JavaClsTest5 {

    //调用Kotlin的方法
    public static void main(String[] args) {


        ///////////// 实例变量 /////////////
        System.out.println();

        TestFieldCls cls = new TestFieldCls("hello");
        System.out.println(cls.name);

        ///////////// 类变量 /////////////
        System.out.println();

        System.out.println(TestFieldCls1.city);

        TestFieldCls1.province = "山西省";
        System.out.println(TestFieldCls1.province);

        System.out.println(TestFieldCls1.constTest1);
        System.out.println(RRCPart12.constTest2);
        System.out.println(ConstObj.constTest3);

        ///////////// 类方法 /////////////
        System.out.println();

        RRCPart12.clsFun1();//调用顶层的类方法
        TestObjFunCls/*.Companion*/.clsFun2(); //伴生对象方法
        ObjCls/*.INSTANCE*/.clsFun3();//对象声明（命名对象）方法

        /**
         对于命名对象中有 ＠JvmStatic 修饰的方法，既可通过命名对象的 INSTANCE （引用单例对象〉来调用该方法，也可通过命名对象对应的类来调用该方法；
         对于伴生对象中有 @JvmStatic 修饰的方法，既可通过伴生对象所在类的 Companion （引用伴生对象〉来调用该方法，也可通过伴生对象所在的类来调用该方法。
         */


        ///////////// KClass /////////////
        System.out.println();

        try {

            // 以下代码用于获取指定Class 对象的 KClass 对象。
            Class<?> aClass = Class.forName("java.util.ArrayList");
            System.out.println(aClass);
            KClass<?> kClass = JvmClassMappingKt.getKotlinClass(aClass);
            System.out.println(kClass);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ///////////// JvmName()解决扩展方法冲突 /////////////
        System.out.println();

        List<String> list1 = new ArrayList<String>();
        List<Integer> list2 = new ArrayList<Integer>();
        RRCPart12.listEx(list1);
        RRCPart12.listExInt(list2);

        ///////////// 方法重载 /////////////
        System.out.println();

        RRCPart12.testReLoad("abc", 12);
        RRCPart12.testReLoad("abc", 12, "bj");
        RRCPart12.testReLoad("abc", 12, "bj", 100000);


        ///////////// check异常 /////////////
        try {
            RRCPart12.testCheckException();
        } catch (IOException e) {
            System.out.println("出现异常: " + e.getMessage());
        }

    }

}


