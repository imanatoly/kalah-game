package net.kalah.game.simulation;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Simulation {

    public void run() {
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(new Agent());
        executor.execute(new Agent());
    }

    public static void main(String[] args) {
        new Simulation().run();
    }


}
