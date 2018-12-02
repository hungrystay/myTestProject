package proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxy implements MethodInterceptor {

    UserService userServiceObject;

    CglibProxy(UserService userServiceObject) {
        this.userServiceObject = userServiceObject;
    }

    UserService createProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserServiceImpl.class);;
        enhancer.setCallback(this);
        return (UserService) enhancer.create();
    }

    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        Object o1 = null;
        if(method.getName() == "printName") {
            System.out.println("++++++before " + methodProxy.getSuperName() + "++++++");

            o1 = methodProxy.invokeSuper(o, args);

            System.out.println("++++++after " + methodProxy.getSuperName() + "++++++");
        }
        else {
            o1 = methodProxy.invokeSuper(o, args);
        }

        return o1;
    }

}
