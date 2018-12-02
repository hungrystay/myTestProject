package vmloading;

import java.io.IOException;
import java.io.InputStream;

public class ClassLoaderTest {
    public static void main(String[] args) throws Exception {

        Object a = new Object();

        final ClassLoader myLoader1 = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
//                Class<?> c = findLoadedClass(name);
                try {
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    InputStream is = getClass().getResourceAsStream(fileName);
                    if (is == null) {
                        return super.loadClass(name);
                    }
                    byte[] b = new byte[is.available()];
                    is.read(b);
                    return defineClass(name, b, 0, b.length);
                } catch (IOException e) {
                    throw new ClassNotFoundException(name);
                }
            }
        };

        ClassLoader myLoader2 = new ClassLoader(myLoader1) {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                return myLoader1.loadClass(name);
            }
        };


        Object classObj1 = myLoader1.loadClass("vmloading.ClassLoaderTest");
        Object classObj2 = myLoader2.loadClass("vmloading.ClassLoaderTest");
        Object classObj3 = new HotSwapClassLoader().loadFile("vmloading.ClassLoaderTest");
        Object classObj4 = new HotSwapClassLoader().loadFile("vmloading.ClassLoaderTest");
        System.out.println(classObj1 == classObj2);

        System.out.println(((Class) classObj1).newInstance() instanceof vmloading.ClassLoaderTest);


        System.out.println(classObj3 == classObj4);
//        Object obj1 = myLoader1.loadClass("vmloading.ClassLoaderTest").newInstance();
//        System.out.println(obj.getClass());
        Object obj = ((Class) classObj3).newInstance();
        System.out.println(obj instanceof vmloading.ClassLoaderTest);
    }
}
