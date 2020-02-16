//package Tests;

import Battleship.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class BattleBoardTests {
    Team teamA = new Team("A");
    BattleBoard board = BattleBoard.blankBoard(teamA);

    @AfterEach
    void resetBoard() {
        board = BattleBoard.blankBoard(teamA);
    }

    @Test
    void testInBounds() {
        Space space = new Space(0, 0);
        assertTrue(board.inBounds(space));

        space = new Space(9, 9);
        assertTrue(board.inBounds(space));

        space = new Space(9, 0);
        assertTrue(board.inBounds(space));

        space = new Space(0, 9);
        assertTrue(board.inBounds(space));

        space = new Space(0, 4);
        assertTrue(board.inBounds(space));

        space = new Space(9, 4);
        assertTrue(board.inBounds(space));

        space = new Space(9, 4);
        assertTrue(board.inBounds(space));

        space = new Space(4, 0);
        assertTrue(board.inBounds(space));

        space = new Space(4, 9);
        assertTrue(board.inBounds(space));
    }

    @Test
    void testOutOfBounds() {
        Space space = new Space(-1, 0);
        assertFalse(board.inBounds(space));

        space = new Space(0, -1);
        assertFalse(board.inBounds(space));

        space = new Space(10, 0);
        assertFalse(board.inBounds(space));

        space = new Space(0, 10);
        assertFalse(board.inBounds(space));

        space = new Space(10, 0);
        assertFalse(board.inBounds(space));

        space = new Space(-1, -1);
        assertFalse(board.inBounds(space));

        space = new Space(10, 10);
        assertFalse(board.inBounds(space));
    }

    @Test
    void testGetRow() {
        Space[] row = board.getBoardSpaces()[0];
        assertArrayEquals(row, board.getRow(0));

        row = board.getBoardSpaces()[9];
        assertArrayEquals(row, board.getRow(9));
    }

    @Test
    void testGetCol() {
        Space[][] b = board.getBoardSpaces();
        Space[] col = new Space[]{
                b[0][0], b[1][0], b[2][0], b[3][0], b[4][0], b[5][0], b[6][0], b[7][0], b[8][0], b[9][0],
        };
        assertArrayEquals(col, board.getCol(0));


        col = new Space[]{
                b[0][9], b[1][9], b[2][9], b[3][9], b[4][9], b[5][9], b[6][9], b[7][9], b[8][9], b[9][9],
        };
        assertArrayEquals(col, board.getCol(9));
    }

    @Test
    void testGetRowPortion() {
        Space[][] b = board.getBoardSpaces();
        Space[] row = new Space[]{
                b[0][1], b[0][2], b[0][3], b[0][4], b[0][5], b[0][6], b[0][7], b[0][8],
        };
        assertArrayEquals(row, board.getRowSpaces(0, 1, 8));
    }

    @Test
    void testGetColPortion() {
        Space[][] b = board.getBoardSpaces();
        Space[] row = new Space[]{
                b[1][9], b[2][9], b[3][9], b[4][9], b[5][9], b[6][9], b[7][9], b[8][9],
        };
        assertArrayEquals(row, board.getColumnSpaces(9, 1, 8));
    }

    @Test
    void testSetSpaces() {
        Team black = new Team("black");
        ShipPart[] parts = ShipPart.generateParts(6, false);
        Ship ship = new Ship("test", black, Ship.direction.HORIZONTAL, parts);
        Space origin = new Space(0, 0);
        board.setSpaces(origin, ship);

        Space[] setSpaces = board.getRowSpaces(0, 0, 5);

    }
}

