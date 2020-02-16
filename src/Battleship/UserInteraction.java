package Battleship;

import java.util.Arrays;
import java.util.Scanner;

public class UserInteraction {
    BattleBoard boardA;
    BattleBoard boardB;
    Team currentTurn;

    UserInteraction(BattleBoard boardA, BattleBoard boardB) {
        this.boardA = boardA;
        this.boardB = boardB;
        currentTurn = boardA.team;
    }

    public void setShips(String[] commands) {
        initShips();
        System.out.println("Place a ship using R[row]C[column] [SHIP NAME lowercase] [H/V]");
        Team a = boardA.team;
        Team b = boardB.team;

        int teamCount = 0;
        for (Team team : new Team[]{a, b}) {
            team.getTeamBoard().display();
            System.out.println("Team " + team.getTeamName() + " set your ships!");

            while (!team.shipsArePlaced()) {
                Scanner input;
                if (commands.length == 0) {
                    input = new Scanner(System.in);
                } else {
                    input = new Scanner(commands[teamCount]);
                }
                for (Ship ship : team.getShips()) {
                    System.out.println("Please place your " + ship.getName());

                    String userInput = input.nextLine();
                    while (!isValid(userInput, team, ship)) {
                        System.out.println("Sorry that wasn't a valid input");
                        System.out.println("Please place your " + ship.getName());
                        userInput = input.nextLine();
                    }

                    String[] command = userInput.split(" ");
                    Space shipStart = parseSpace(command[0]);
                    Ship.direction dir = parseDirection(command[2]);
                    ship.setDir(dir);
                    team.getTeamBoard().setSpaces(shipStart, ship);

                    team.getTeamBoard().display();
                }
            }
            teamCount++;
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        }

    }

    public Team game() {
        System.out.println("\n\n\n\n\nGame is starting...\n\n");
        while (!(boardA.isOver() || boardB.isOver())) {
            getOtherTeam().getTeamBoard().displayGuessBoard();
            currentTurn.getTeamBoard().display();
            System.out.println("Current Team: " + currentTurn.getTeamName());
            System.out.println("Make your guess  ex: R[row]C[col]");
            Scanner input = new Scanner(System.in);
            String userInput = input.nextLine();

            while (!guessIsValid(userInput)) {
                System.out.println("Your guess was invalid, please try again!");
                userInput = input.nextLine();
            }

            Space guess = parseSpace(userInput);
            guess = currentTurn.opponentBoard.getSpace(guess);
            boolean hit = currentTurn.opponentBoard.markGuess(guess);


            System.out.println("\n\n\n\n\n\n");
            System.out.println("--------" + currentTurn.getTeamName() + " Stats-----------");

            if (hit) {
                Ship hitShip = getOtherTeam().shipByPart(guess.getShipPart());
                System.out.println("Hit!!!");
                if (hitShip.isSunk()) {
                    System.out.println("You sunk my " + hitShip.getName());
                }
            } else {
                System.out.println("Miss!!!");
            }

            nextTurn();
        }

        if (boardA.isOver()) {
            return boardB.team;
        } else {
            return boardA.team;
        }
    }

    public Team getOtherTeam() {
        if (currentTurn == boardA.team) {
            return boardB.team;
        } else {
            return boardA.team;
        }
    }

    public boolean guessIsValid(String input) {
        try {
            Space spaceGuess = parseSpace(input);
            if (spaceGuess == null) {
                return false;
            }
            if (currentTurn.opponentBoard.getSpace(spaceGuess).isGuessed()) {
                return false;
            }
        } catch (Exception a) {
            System.out.println(a.getMessage() + ". The input you entered is not parsable, please try again!");
            return false;
        }

        return true;
    }

    public void nextTurn() {
        Team[] teams = {boardA.team, boardB.team};
        if (teams[0].equals(currentTurn)) {
            currentTurn = teams[1];
        } else if (teams[1].equals(currentTurn)) {
            currentTurn = teams[0];
        }
    }

    public Team[] initShips() {
        Team[] teams = new Team[]{boardA.team, boardB.team};
        for (Team team : teams) {
            team.initShips();
        }
        return teams;
    }

    public int getShipLen(Team team, String name) {
        Ship ship = team.getShipByName(name);
        if (ship != null) {
            return ship.length();
        }
        return 0;
    }

    public boolean isValid(String input, Team team, Ship shipExpected) {
        String[] command = input.split(" ");

        Space shipStart;
        Ship shipType;
        Ship.direction dir;

        try {
            shipStart = parseSpace(command[0]);
            if (shipStart == null) {
                return false;
            }
        } catch (Exception a) {
            System.out.println(a.getMessage() + ". The input you entered is not parsable, please try again!");
            return false;
        }

        shipType = team.getShipByName(command[1]);
        dir = parseDirection(command[2]);

        if (!shipType.getName().equals(shipExpected.getName())) {
            return false;
        }

        // check that direction is not none
        if (dir == Ship.direction.NONE) {
            return false;
        }

        // check that ship type is not none
        if (shipType == null) {
            return false;
        }

        Space[] shipSpaces = new Space[shipType.length()];
        switch (dir) {
            case VERTICAL:
                shipSpaces = team.getTeamBoard().getColumnSpaces(shipStart.column, shipStart.row, shipStart.row + shipExpected.length() - 1);
                break;
            case HORIZONTAL:
                shipSpaces = team.getTeamBoard().getRowSpaces(shipStart.row, shipStart.column, shipStart.column + shipExpected.length() - 1);
                break;
            case NONE:
                return false;
        }

        // checks that the ship end and start is in bounds
        if (!boardA.inBounds(shipSpaces[0]) && !boardA.inBounds(shipSpaces[shipSpaces.length - 1])) {
            return false;
        }

        // Check if ship already in space
        for (Space space : shipSpaces) {
            if (team.getTeamBoard().isTaken(space)) {
                return false;
            }
        }



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
        Space space = new Space(space_row, space_col);
        if (boardA.inBounds(space)) {
            return space;
        } else {
            return null;
        }
    }


    public Ship.direction parseDirection(String input) {
        char c = input.charAt(input.length() - 1);
        if (c == 'h' || c == 'H') {
            return Ship.direction.HORIZONTAL;
        } else if (c == 'v' || c == 'V') {
            return Ship.direction.VERTICAL;
        }
        return Ship.direction.NONE;
    }
}
