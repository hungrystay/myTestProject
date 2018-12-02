package vmloading;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxyTest {

    interface IHello {
        void sayHello();
    }

    static class Hello implements IHello {
        @Override
        public void sayHello() {
            System.out.println("hello world");
        }
    }

    static class DynamicProxy implements InvocationHandler {

        Object originalObj;

        Object bind(Object originalObj) {
            this.originalObj = originalObj;
            return Proxy.newProxyInstance(originalObj.getClass().getClassLoader(), originalObj.getClass().getInterfaces(), this);
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("welcome");
            return method.invoke(originalObj, args);
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

//        Class outer = Class.forName("vmloading.DynamicProxyTest");
//
//        Class arrClass[]=outer.getDeclaredClasses();
//
//        String clName = "default";
//        for(Class obj:arrClass){
//            if(obj.getName().endsWith("$Hello")){
//                clName = obj.getName();
//                break;
//            }
////            System.out.println("Nest Class="+obj.getName());
//        }

//        Class inner = Class.forName(clName);

//        IHello hello = (IHello) new DynamicProxy().bind(inner.newInstance());
        IHello hello = (IHello) new DynamicProxy().bind(new Hello());
        hello.sayHello();
    }
}


