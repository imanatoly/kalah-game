package net.kalah.game;


import net.kalah.game.board.Board;

import static net.kalah.game.board.Slot.*;

public class TestUtil {

    public static void initBoard(Board board, int[] slots) {
        board.setStoneCount(A_0, (byte) slots[0]);
        board.setStoneCount(A_1, (byte) slots[1]);
        board.setStoneCount(A_2, (byte) slots[2]);
        board.setStoneCount(A_3, (byte) slots[3]);
        board.setStoneCount(A_4, (byte) slots[4]);
        board.setStoneCount(A_5, (byte) slots[5]);
        board.setStoneCount(KALAH_A, (byte) slots[6]);
        board.setStoneCount(B_0, (byte) slots[7]);
        board.setStoneCount(B_1, (byte) slots[8]);
        board.setStoneCount(B_2, (byte) slots[9]);
        board.setStoneCount(B_3, (byte) slots[10]);
        board.setStoneCount(B_4, (byte) slots[11]);
        board.setStoneCount(B_5, (byte) slots[12]);
        board.setStoneCount(KALAH_B, (byte) slots[13]);
    }
}
