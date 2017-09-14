package net.kalah.game.board;


import net.kalah.game.Player;

import java.util.Arrays;

import static net.kalah.game.Player.A;
import static net.kalah.game.Player.B;

public enum Slot {

    A_0(A, 0, 0), A_1(A, 1, 1), A_2(A, 2, 2), A_3(A, 3, 3), A_4(A, 4, 4), A_5(A, 5, 5), KALAH_A(A, 6, 6),
    B_0(B, 0, 7), B_1(B, 1, 8), B_2(B, 2, 9), B_3(B, 3, 10), B_4(B, 4, 11), B_5(B, 5, 12), KALAH_B(B, 6, 13);

    private Player player;
    private byte cell;
    private byte boardIndex;

    Slot(Player player, int cell, int boardIndex) {
        this.player = player;
        this.cell = (byte) cell;
        this.boardIndex = (byte) boardIndex;
    }

    public Player getPlayer() {
        return player;
    }

    public byte getCell() {
        return cell;
    }

    public byte getIndex() {
        return boardIndex;
    }

    public static Slot instance(Player player, int cell) {
        return Arrays.stream(values()).filter(slot -> slot.player == player && slot.cell == cell).findFirst().get();
    }

    public static Slot fromIndex(int index) {
        return Arrays.stream(values()).filter(slot -> slot.boardIndex == index).findFirst().get();
    }

}
