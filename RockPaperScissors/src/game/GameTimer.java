package game;

//Jednoducha trieda ktora rata cas v simulacii
public class GameTimer {

    private double totalTime;

    private float startTime;

    public GameTimer() {
        this.startTime = System.nanoTime();
    }

    public void stopTimer(int speed) {
        float finalTime = System.nanoTime();
        this.totalTime += ((double)(finalTime - this.startTime) / 1000000000) * speed;
    }

    public void update(int speed) {
        float stop = System.nanoTime();
        this.totalTime = ((double)(stop - this.startTime) / 1000000000) * speed;
        this.startTime = System.nanoTime();
    }

    public double getTotalTime() {
        return this.totalTime;
    }
}
