package Battleship;

import java.util.Arrays;
import java.util.Scanner;

public class UserInteraction {
    BattleBoard board;

    UserInteraction(BattleBoard board) {
        this.board = board;
    }

    public Team[] initShips() {
        Team[] teams = new Team[]{new Team("A"), new Team("B")};
        for (Team team : teams) {
            team.initShips();
        }
        return teams;
    }

    public int getShipLen(Team team, String name) {
        String[] shipNames = new String[] {"carrier", "cruiser", "sub", "destroyer"};
        for (int ship = 0; ship < shipNames.length; ship++) {
            if (name.equals(shipNames[ship])) {
                return team.getShip(ship).length();
            }
        }
        return 0;
    }

    public boolean isValid(String input, Team team) {
        String[] command = input.split(" ");

        Space shipStart;
        Ship shipType;
        Ship.direction dir;

        try {
            shipStart = parseSpace(command[0]);
        } catch (Exception a) {
            System.out.println(a.getMessage() + ". The input you entered is not parsable, please try again!");
            return false;
        }

        shipType = team.getShipByName(command[1]);
        dir = parseDirection(command[1]);

        // check that direction is not none
        if (dir == Ship.direction.NONE) {
            return false;
        }

        // check that ship type is not none
        if (shipType == null) {
            return false;
        }

        ShipPart[] parts = ShipPart.generateParts(shipType.length(), false);
        Ship ship = new Ship(new Team("team"), dir, parts);
        Space[] shipSpaces = new Space[ship.length()];
        switch (ship.getDir()) {
            case VERTICAL:
                shipSpaces = board.getColumnSpaces(shipStart.column, shipStart.row, shipStart.row + ship.length() - 1);
            case HORIZONTAL:
                shipSpaces = board.getRowSpaces(shipStart.row, shipStart.column, shipStart.column + ship.length() - 1);
        }
        ShipPart.setSpaces(parts, shipSpaces);

        // checks that the ship end and start is in bounds
        if (!board.inBounds(ship)) {
            return false;
        }

        // Check if ship already in space
        for (Space space : shipSpaces) {
            if(board.isTaken(space)) {
                return false;
            }
        }
/*

        //Checks that the piece being moved is the same team as the current
        if (!game.getPieceTeam(unvalidated_pc_loc.pc).equals(game.current_turn)) {
            System.out.println("You can't move that silly! You're not on the same team!");
            return false;
        }

        //Generated test move to be used for validation
        Move test_move = new Move(game, unvalidated_pc_loc);


        //If the turn is greater than one, restrict the piece to move to the restricted piece move
        if (game.restricted_piece_move != null) {
            if (!unvalidated_pc_loc.pc.equals(game.restricted_piece_move)) {
                System.out.println("You have the opportunity to make a double jump with piece" + game.getLocation(game.restricted_piece_move).toString() +
                        "!\n You may choose to make another jump or skip your turn." + "Would you like to skip? Y/N");
                try {
                    Scanner scanner = new Scanner(System.in);
                    String user_input = scanner.next();
                    if (user_input.toLowerCase().equals("y")) {
                        game.abstain = true;
                        game.restricted_piece_move = null;
                    } else {
                        return false;
                    }
                } catch (InputMismatchException a) {
                    System.out.println("There was something wrong with your input, please try again!");
                    return false;
                }
            } else {
                if (!test_move.possible_jumps.containsValue(unvalidated_end_space)) {
                    System.out.println("You can't jump there, you must double jump.");
                    return false;
                }
            }
        }

        //Checks if the end location is a possible move
        if (!test_move.isPossible(unvalidated_end_space)) {
            System.out.println("The space you want to move to is not valid! Please try again!");
            return false;
        }

        game.validated_end_space = unvalidated_end_space;
        game.validated_pc_space = unvalidated_pc_loc;
*/

        //If it passes all the criteria
        return true;
    }

    // R1C1 SHIPNAME H/V
    public Space parseSpace(String input) {
        int r_index = input.indexOf("R");
        int c_index = input.indexOf("C");

        int space_col;
        int space_row;

        String first = "";
        if (c_index < r_index) {
            space_row = Integer.parseInt(input.substring(r_index + 1)) - 1;
            space_col = Integer.parseInt(input.substring(c_index + 1, r_index)) - 1;
        } else {
            space_row = Integer.parseInt(input.substring(r_index + 1, c_index)) - 1;
            space_col = Integer.parseInt(input.substring(c_index + 1)) - 1;
        }
        Space space = new Space(space_col, space_row);
        if (board.inBounds(space)) {
            return space;
        } else {
            return null;
        }
    }



    public Ship.direction parseDirection(String input) {
        char c = input.charAt(input.length()-1);
        if (c =='h' || c=='H') {
            return Ship.direction.HORIZONTAL;
        } else if  (c =='v' || c=='V') {
            return Ship.direction.VERTICAL;
        }
        return Ship.direction.NONE;
    }
}
