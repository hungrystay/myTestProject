package vmloading;


/**
 * ����ʹ�����ֶ���ʾ����
 * �����ڱ���׶λ���������ĳ������У�������û��ֱ�����õ����峣�����࣬��˲��ᴥ�����峣������ĳ�ʼ����
 **/
public class ConstClass {

    static {
        System.out.println("ConstClass init!");
    }

    public static final String HELLOWORLD = "hello world";

    Class cl = ConstClass.class;


}


