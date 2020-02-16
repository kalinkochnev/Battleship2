package Battleship;


import java.util.ArrayList;
import java.util.List;

public class BattleBoard {
    Space[][] board;

    BattleBoard(Space[][] board) {
        this.board = board;
    }

    public static BattleBoard blankBoard() {
        Space[][] board = new Space[10][10];
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board.length; col++) {
                board[row][col] = new Space(row, col);
            }
        }

        Team red = new Team("A");
        Team black = new Team("B");

        // TODO get input from user for spaces

        return new BattleBoard(board);
    }

    public Space[][] getBoardSpaces() {
        return board;
    }

    public boolean inBounds(Ship ship) {
        boolean firstInBounds = this.inBounds(ship.getFirst().getSpace());
        boolean secondInBounds = this.inBounds(ship.getLast().getSpace());
        return firstInBounds && secondInBounds;
    }

    public boolean inBounds(Space space) {
        if (space.column >= 0 && space.column <= board.length - 1) {
            if (space.row >= 0 && space.row <= board.length - 1) {
                return true;
            }
        }
        return false;
    }

    public Space getSpace(int row, int column) {
        return this.board[row][column];
    }

    public Space getSpace(Space space) {
        return this.board[space.row][space.column];
    }

    public Space[] getRowSpaces(int rowNum, int start, int end) {
        Space[] newRow = new Space[end + 1 - start];

        for (int space = start; space <= end; space++) {
            newRow[space - start] = this.getSpace(rowNum, space);
        }

        return newRow;
    }

    public Space[] getRow(int rowNum) {
        return getRowSpaces(rowNum, 0, board.length - 1);
    }

    public Space[] getCol(int colNum) {
        return getColumnSpaces(colNum, 0, board.length - 1);
    }

    public Space[] getColumnSpaces(int colNum, int start, int end) {
        Space[] spaces = new Space[end + 1 - start];

        for (int row = start; row <= end; row++) {
            spaces[row - start] = this.getSpace(row, colNum);
        }

        return spaces;
    }

    public Space[] getColumn(int colNum) {
        return getColumnSpaces(colNum, 0, board.length);
    }

    public boolean isTaken(Space space) {
        return this.getSpace(space).getShipPart() == null;
    }

    // Test that the spaces are the same length as the ship
    public void setSpaces(Space start, Ship ship) {
        Space[] spaces = new Space[ship.length()];

        switch (ship.getDir()) {
            case VERTICAL:
                spaces = this.getColumnSpaces(start.column, start.row, start.row + ship.length() - 1);
            case HORIZONTAL:
                spaces = this.getRowSpaces(start.row, start.column, start.column + ship.length() - 1);
        }

        ShipPart[] parts = ShipPart.generateParts(ship.length(), false);

        ShipPart.setSpaces(parts, spaces);
        ship.setShipParts(parts);

    }
}
