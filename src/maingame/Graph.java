package maingame;

import java.util.Random;

public class Graph {
    private int type;
    private int score;
    private double trangle;

    public Graph() {
        Random r = new Random();
        this.trangle = 0;
        this.score = r.nextInt( 5) + 1;
        this.type = r.nextInt(2);
    }

    public int subScore() {
        return --score;
    }

    public int getScore() {
        return score;
    }

    public boolean isZero() {
        return score == 0;
    }

    public int getType() {
        return type;
    }
}
