package access.package2;

import access.package1.AccessBase;

public class MainDifferentPackage {

    public static void main(String args[]) {
        AccessBase accessBase = new AccessBase();

        AccessImp2 accessImp2 = new AccessImp2();
        //public�����ڰ��ⱻ����, protected��default������
        System.out.println(accessBase.value1);


        System.out.println(accessImp2.value1);
    }

}
