package access.package1;

public class MainSamePackage {

    public static void main(String args[]) {
        AccessBase accessBase = new AccessBase();
        AccessImp1 AccessImp = new AccessImp1();


        //同一个包中可以直接访问protected, public ,default三种access的属性
        System.out.println(accessBase.value1);
        System.out.println(accessBase.value2);
        System.out.println(accessBase.value3);

        //protected, public, default三种access可以在本包通过子类直接访问
        System.out.println(AccessImp.value1);
        System.out.println(AccessImp.value2);
        System.out.println(AccessImp.value3);
    }

}
