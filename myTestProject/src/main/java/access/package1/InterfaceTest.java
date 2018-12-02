package access.package1;

public interface InterfaceTest {
    void test1();
    void test2();
    default void test3(){
        System.out.println("hello, this is test3");
    }
}
