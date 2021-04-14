package cn.katoumegumi.java.experiment.common;

import cn.katoumegumi.java.sql.entity.SqlEquation.Symbol;

import java.lang.ref.WeakReference;

public class Test {


    public static void main(String[] args) throws Throwable {
        //ThreadLocal threadLocal = new ThreadLocal();
        //Object t;
        WeakReference weakReference = new WeakReference<>(2);


        System.gc();
        Thread.sleep(10000);
        System.gc();
        Thread.sleep(10000);
        System.gc();
        System.out.println(weakReference.get());

    }

}
