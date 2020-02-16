package Battleship;

import java.util.ArrayList;
import java.util.List;

public class BattleBoard {
    Space[][] board;
    Team team;

    BattleBoard(Space[][] board, Team team) {
        this.board = board;
        this.team = team;
        team.board = this;
    }

    public static BattleBoard blankBoard(Team team) {
        Space[][] board = new Space[10][10];
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board.length; col++) {
                board[row][col] = new Space(row, col);
            }
        }
        return new BattleBoard(board, team);
    }

    public Space[][] getBoardSpaces() {
        return board;
    }

    public boolean isOver() {
        for (int ship = 0; ship < team.getNumShips(); ship++) {
            Ship ship1 = team.getShip(ship);
            for (ShipPart part : ship1.getParts()) {
                if (!part.isHit()) {
                    return false;
                }
            }
        }

        return true;
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
        return this.getSpace(space).getShipPart() != null;
    }

    public boolean markGuess(Space space) {
        space = this.getSpace(space);
        if (isTaken(space)) {
            space.setGuess();
            ShipPart part = space.getShipPart();
            part.setHit();
            return true;
        } else {
            space.setGuess();
            return false;
        }
    }

    // Test that the spaces are the same length as the ship
    public void setSpaces(Space start, Ship ship) {
        Space[] spaces = new Space[ship.length()];

        switch (ship.getDir()) {
            case VERTICAL:
                spaces = this.getColumnSpaces(start.column, start.row, start.row + ship.length() - 1);
                break;
            case HORIZONTAL:
                spaces = this.getRowSpaces(start.row, start.column, start.column + ship.length() - 1);
                break;
        }

        ShipPart[] parts = ShipPart.generateParts(ship.length(), false);

        ShipPart.setSpaces(parts, spaces);
        ship.setShipParts(parts);

    }

    public void display() {
        System.out.println("----- Your board -----");
        for (int row = 0; row < board.length; row++) {
            String row_str = "R" + String.valueOf(row + 1);
            if (row_str.length() != 3) {
                row_str += " ";
            }

            boolean prevSpaceFilled = false;
            for (int space = 0; space < board.length; space++) {
                Space s = getSpace(row, space);
                ShipPart curr_piece = s.getShipPart();


                if (curr_piece == null) {
                    if (s.guess) {
                        row_str += "|_-_";
                    } else {
                        row_str += "|___";
                    }
                } else {
                    if (prevSpaceFilled) {
                        row_str += "  ";
                    } else {
                        row_str += "| ";
                    }
                    String symbol = "O";
                    if (curr_piece.isHit()) {
                        symbol = "X";
                    }

                    row_str += symbol + " ";
                    prevSpaceFilled = true;
                }

                if (space == board.length - 1) {
                    row_str += "|";
                }

            }
            System.out.println(row_str);

            if (row == board.length - 1) {
                System.out.println("    C1  C2  C3  C4  C5  C6  C7  C8  C9  C10");
            }
        }
    }

    public void displayGuessBoard() {
        System.out.println("----- Guess board -----");
        for (int row = 0; row < board.length; row++) {
            String row_str = "R" + String.valueOf(row + 1);
            if (row_str.length() != 3) {
                row_str += " ";
            }

            for (int space = 0; space < board.length; space++) {
                Space s = getSpace(row, space);
                if (!s.guess) {
                    row_str += "|   ";
                } else {
                    row_str += "| ";
                    String symbol = "-";
                    if (isTaken(s)) {
                        if (s.guess) {
                            symbol = "X";
                        }
                    }

                    row_str += symbol + " ";
                }

                if (space == board.length - 1) {
                    row_str += "|";
                }

            }
            System.out.println(row_str);

            if (row == board.length - 1) {
                System.out.println("    C1  C2  C3  C4  C5  C6  C7  C8  C9  C10");
            }
        }
    }
}
