package proxy;

public class UserServiceImpl implements UserService {

    public String printName(int id) {
        System.out.println("------getName------");
        System.out.println("hahahahhahaha");
        System.out.println("hehehehhehheheheh");
        System.out.println("hohohohohohohohohoho");
        return "Tom";
    }

    public Integer printAge(int id) {
        System.out.println("------getAge------");
        return 10;
    }

}
