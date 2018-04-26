import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NoSync {
    private static NotSync noSync = new NotSync();
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();

        for (int i = 0; i < 1000; i++) {
            executor.execute(new AddOneTask());
        }

        executor.shutdown();

        while (!executor.isTerminated()) {
            System.out.println("What is balance? " + noSync.getSum());
        }
    }
        private static class AddOneTask implements Runnable{
            public void run(){
                noSync.addOne(1);
            }
        }

        private static class NotSync{
        private int sum = 0;

        public int getSum(){
            return sum;
        }

        public void addOne(int amount){
            int newSum = sum + amount;

            try {
                Thread.sleep(5);
            }
            catch (InterruptedException ex){

            }
            sum = newSum;
        }
        }
    }

