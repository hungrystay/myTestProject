package access.package1;

public class MainSamePackage {

    public static void main(String args[]) {
        AccessBase accessBase = new AccessBase();
        AccessImp1 AccessImp = new AccessImp1();


        //ͬһ�����п���ֱ�ӷ���protected, public ,default����access������
        System.out.println(accessBase.value1);
        System.out.println(accessBase.value2);
        System.out.println(accessBase.value3);

        //protected, public, default����access�����ڱ���ͨ������ֱ�ӷ���
        System.out.println(AccessImp.value1);
        System.out.println(AccessImp.value2);
        System.out.println(AccessImp.value3);
    }

}
