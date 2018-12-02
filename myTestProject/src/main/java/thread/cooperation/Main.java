package thread.cooperation;

public class Main {

    public static void main(String args[]) throws InterruptedException {
        AlarmAgent alarmAgent = AlarmAgent.getInstance();
        alarmAgent.init();

        alarmAgent.sendAlarm("this is a test, this is a test, the alarm info level is very high", 1000);

        new Thread().join();

    }
}
