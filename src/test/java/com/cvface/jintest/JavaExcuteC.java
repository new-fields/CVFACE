package com.cvface.jintest;

/**
 * 测试通过java调用C代码
 * 参考：https://blog.csdn.net/u010411264/article/details/73732500
 * 参考：https://www.cnblogs.com/kissazi2/p/3298884.html
 * 参考：https://blog.csdn.net/u013153983/article/details/59484458/
 */
public class JavaExcuteC {
    static{
        System.loadLibrary("PROJECT_01");
    }

    /**
     * 调用native方法display，该方法在C代码中实现，输出 Hello World! I'm From The C Programmer'
     */
    public native void display();

    public static void main(String [] args){
        new JavaExcuteC().display();
    }
}
