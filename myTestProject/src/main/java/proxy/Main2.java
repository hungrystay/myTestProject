package proxy;

public class Main2 {
    public static void main(String[] args) {
        UserService o = new CglibProxy(new UserServiceImpl()).createProxy();

        System.out.println("-----o------");
        System.out.println(o);

        Object name = o.printName(1);

        System.out.println("----name-----");
        System.out.println(name);
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

        Object age = o.printAge(2);
        System.out.println("----age------");
        System.out.println(age);
    }
}
