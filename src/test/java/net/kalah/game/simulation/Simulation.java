package net.kalah.game.simulation;

public class Simulation {

    public void run() {
        Agent a = new Agent();
        Agent b = new Agent();
        new Thread(a).run();
        new Thread(b).run();
    }

    public static void main(String[] args) {
        new Simulation().run();
    }


}
