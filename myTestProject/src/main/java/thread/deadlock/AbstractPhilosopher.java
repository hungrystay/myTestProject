package thread.deadlock;

public abstract class AbstractPhilosopher extends Thread{
    public AbstractPhilosopher(String id) {
        super("Philosopher-" + id);
    }

}
