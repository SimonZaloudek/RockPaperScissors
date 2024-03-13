package game;

//Jednoducha trieda ktora rata cas v simulacii
public class GameTimer {

    private double totalTime;

    private final float startTime;

    public GameTimer() {
        this.startTime = System.nanoTime();
    }

    public void stopTimer() {
        float finalTime = System.nanoTime();
        this.totalTime = ((double)(finalTime - this.startTime) / 1000000000);
    }

    public double getTotalTime() {
        return this.totalTime;
    }
}
