package net.kalah.game;

/**
 * @author ahmety on 12.09.2017.
 */
public enum Player {
    A,
    B;

    public Player opponent() {
        return this == A ? B : A;
    }
}
