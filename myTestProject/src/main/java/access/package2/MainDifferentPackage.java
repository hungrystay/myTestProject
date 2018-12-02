package access.package2;

import access.package1.AccessBase;

public class MainDifferentPackage {

    public static void main(String args[]) {
        AccessBase accessBase = new AccessBase();

        AccessImp2 accessImp2 = new AccessImp2();
        //public可以在包外被访问, protected和default都不能
        System.out.println(accessBase.value1);


        System.out.println(accessImp2.value1);
    }

}
