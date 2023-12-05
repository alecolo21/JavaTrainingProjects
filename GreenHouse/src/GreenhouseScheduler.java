import java.util.concurrent.*;
import java.time.LocalTime;
public class GreenhouseScheduler {
    private static class LightOn implements Runnable {
        public void run() {
            System.out.println("Turning on lights");
        }
    }

    private static class LightOff implements Runnable {
        public void run() {
            System.out.println("Turning off lights");
        }
    }

    private static class WaterOn implements Runnable {
        public void run() {
            System.out.println("Turning greenhouse water on");
        }
    }

    private static class WaterOff implements Runnable {
        public void run() {
            System.out.println("Turning greenhouse water off");
        }
    }

    private static class ThermostatNight implements Runnable {
        public void run() {
            System.out.println("Thermostat to night setting");
        }
    }

    private static class ThermostatDay implements Runnable {
        public void run() {
            System.out.println("Thermostat to day setting");
        }
    }

    private static class Bell implements Runnable {
        public void run() {
            System.out.println("Bing!");
        }
    }
    static class Terminate implements Runnable {
        ScheduledThreadPoolExecutor scheduler;
        public Terminate (ScheduledThreadPoolExecutor scheduler)
        {this.scheduler=scheduler;}
        public void run() {
            System.out.println("Terminating");
            scheduler.shutdownNow();
        }
    }
    public static void schedule(ScheduledThreadPoolExecutor scheduler,
                                Runnable event, long delay, TimeUnit tu) {
        scheduler.schedule(event,delay,tu);
    }

    public static void repeat(ScheduledThreadPoolExecutor scheduler,
                              Runnable event, long initialDelay, long period, TimeUnit tu) {
        scheduler.scheduleAtFixedRate( event, initialDelay, period, tu);
    }
    public static void main(String[] args) {
        GreenhouseScheduler gh = new GreenhouseScheduler();
        ScheduledThreadPoolExecutor scheduler = new ScheduledThreadPoolExecutor(10);
        schedule(scheduler, new Terminate(scheduler), 10000, TimeUnit.MILLISECONDS);
        repeat(scheduler, new Bell(), 0, 1000, TimeUnit.MILLISECONDS);
        repeat(scheduler, new LightOn(), 0, 200, TimeUnit.MILLISECONDS);
        repeat(scheduler, new LightOff(), 100, 200, TimeUnit.MILLISECONDS);
        repeat(scheduler, new WaterOn(), 0, 600, TimeUnit.MILLISECONDS);
        repeat(scheduler, new WaterOff(), 300, 600, TimeUnit.MILLISECONDS);

        LocalTime now = LocalTime.now();
        LocalTime morning = LocalTime.of(7, 0, 0);
        LocalTime night= LocalTime.of(19, 0, 0);
        boolean light = now.isAfter(morning) && now.isBefore(night);

        int interval = 86400;
        if (light) {
            int secondi = night.toSecondOfDay() - now.toSecondOfDay();
            repeat(scheduler, new ThermostatDay(), 0, interval, TimeUnit.SECONDS);
            repeat(scheduler, new ThermostatNight(), secondi, interval, TimeUnit.SECONDS);}
        else {
            int secondi = morning.toSecondOfDay() - now.toSecondOfDay();
            repeat(scheduler, new ThermostatNight(), 0, interval, TimeUnit.SECONDS);
            repeat(scheduler, new ThermostatDay(),secondi, interval, TimeUnit.SECONDS);
        }
    }
}