package net.kalah.game.board;

import lombok.Getter;
import net.kalah.game.Player;

import java.util.Arrays;

import static java.text.MessageFormat.format;
import static net.kalah.Constants.MAX_INITIAL_STONE_COUNT;
import static net.kalah.Constants.MIN_INITIAL_STONE_COUNT;
import static net.kalah.game.Player.A;
import static net.kalah.game.Player.B;
import static net.kalah.game.board.Slot.KALAH_A;
import static net.kalah.game.board.Slot.KALAH_B;

/**
 * Board is actually circular, so board data can be kept in array
 */
@Getter
public class Board {


    private final static byte CELL_COUNT = 14;

    private byte[] cells = new byte[CELL_COUNT];

    public Board(byte initialStoneCount) {

        if (initialStoneCount < MIN_INITIAL_STONE_COUNT || initialStoneCount > MAX_INITIAL_STONE_COUNT) {
            throw new IllegalArgumentException(format("Initial stone count must be in between [{0}, {1}]", MIN_INITIAL_STONE_COUNT, MAX_INITIAL_STONE_COUNT));
        }

        for (int i = 0; i < KALAH_A.getIndex(); i++) {
            cells[i] = initialStoneCount;
        }
        for (int i = KALAH_A.getIndex() + 1; i < KALAH_B.getIndex(); i++) {
            cells[i] = initialStoneCount;
        }
    }

    public void addStone(Slot slot) {
        addStone(slot, (byte) 1);
    }

    public void addStone(Slot slot, byte stoneCount) {
        cells[slot.getIndex()] += stoneCount;
    }

    public void emptySlot(Slot slot) {
        cells[slot.getIndex()] = 0;
    }

    public void emptySlots(Slot... slot) {
        Arrays.stream(slot).forEach(this::emptySlot);
    }

    public byte getStoneCount(Slot slot) {
        return cells[slot.getIndex()];
    }

    public Slot opposite(Slot slot) {
        return Slot.instance(slot.getPlayer().opponent(), 5 - slot.getCell());
    }

    public Slot calculateLast(Slot start, byte stepCount) {
        Player player = start.getPlayer();
        Slot current = start;
        while (stepCount > 0) {
            current = nextSlot(player, current);
            stepCount--;
        }
        return current;
    }

    public Slot nextSlot(Player player, Slot current) {
        byte nextSlotIndex = (byte) ((current.getIndex() + 1) % CELL_COUNT);
        Slot next = Slot.fromIndex(nextSlotIndex);
        if (isMovable(player, next))
            return next;
        return nextSlot(player, next);
    }

    private boolean isMovable(Player player, Slot slot) {
        if (player == A && slot == KALAH_B)
            return false;
        if (player == B && slot == KALAH_A)
            return false;
        return true;
    }


}
