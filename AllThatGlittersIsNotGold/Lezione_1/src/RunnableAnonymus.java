public class RunnableAnonymus {
        public static void main (String[] args) {
            Runnable runnable = new Runnable () {
                public void run() {
                    System.out.println("Runnable running");
                    System.out.println("Runnable finished");
                }
            };
            Thread thread = new Thread (runnable);
            thread.start();
        }
}

