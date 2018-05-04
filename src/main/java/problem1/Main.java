package problem1;

public class Main extends Thread{
    private static int currentMax = 50;
    private int id;
    private final Object objWait;

    private Main(int id, Object objWait){
        this.id = id;
        this.objWait = objWait;
    }

    public static void main(String[] args)  {
        Object objWait = new Object();
        for (int i = 1; i < 51; ++i){
            Thread thread = new Main(i, objWait);
            thread.start();
        }
    }

    public void run() {
        try {
            synchronized (objWait){
                while (id < currentMax){
                    objWait.wait();
                }
                --currentMax;
                System.out.println("Hello from Thread-" + id);
                objWait.notifyAll();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}