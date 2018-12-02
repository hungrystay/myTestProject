package thread;


class MyThread extends Thread{

    private int ticketsCount=5; //一共有5张火车票
    private String name; //窗口, 也即是线程的名字
    public MyThread(String name){
        this.name=name;
    }
    @Override
    public void run(){
        while(ticketsCount>0){
            ticketsCount--; //如果还有票，就卖掉一张票
            System.out.println(name+"卖掉了1张票，剩余票数为:"+ticketsCount);
        }
    }
}