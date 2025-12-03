class Comparison {

    static int counter = 0;
    static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        int numberOfThreads = 10;

        NormalThread[] normalThreads = new NormalThread[numberOfThreads];
        long startNormal = System.currentTimeMillis();

        for (int i = 0; i < numberOfThreads; i++) {
            normalThreads[i] = new NormalThread();
            normalThreads[i].start();
        }

        for (int i = 0; i < numberOfThreads; i++) {
            normalThreads[i].join();
        }

        long endNormal = System.currentTimeMillis();
        double normalSeconds = (endNormal - startNormal) / 1000.0;
        SynchronizedThread[] syncThreads = new SynchronizedThread[numberOfThreads];


        long startSync = System.currentTimeMillis();

        for (int i = 0; i < numberOfThreads; i++) {
            syncThreads[i] = new SynchronizedThread();
            syncThreads[i].start();
        }
        for (int i = 0; i < numberOfThreads; i++) {
            syncThreads[i].join();
        }

        long endSync = System.currentTimeMillis();
        double syncSeconds = (endSync - startSync) / 1000.0;
        System.out.println("Normal thread = " + normalSeconds + " seconds");
        System.out.println("Synchronized thread = " + syncSeconds + " seconds");
    }
}

class NormalThread extends Thread {
    public void run() {
        for (int i = 0; i < 1000000; i++) {
            Comparison.counter++;
        }
    }
}

class SynchronizedThread extends Thread {
    public void run() {
        for (int i = 0; i < 1000000; i++) {
            synchronized (Comparison.lock) {
                Comparison.counter++;
            }
        }
    }

}
