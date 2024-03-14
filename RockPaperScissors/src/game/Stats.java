package game;

public class Stats {

    private int touches;
    private int distanceTravelled;
    private final int[] kills;


    public Stats() {
        this.kills = new int[]{0, 0, 0};
        this.distanceTravelled = 0;
        this.touches = 0;
    }

    public int getTouches() {
        return this.touches;
    }

    public void setTouches() {
        this.touches++;
    }

    public int getDistanceTravelled() {
        return this.distanceTravelled;
    }

    public void addDistance() {
        this.distanceTravelled++;
    }

    public int[] getKills() {
        return this.kills;
    }

    public void setKills(int rockKill, int paperKill, int scissorsKill) {
        this.kills[0] += rockKill;
        this.kills[1] += paperKill;
        this.kills[2] += scissorsKill;
    }
}
