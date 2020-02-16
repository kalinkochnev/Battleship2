package Battleship;

public class Main {
    public static Team A = new Team("Red");
    public static Team B = new Team("Blue");

    public static void main(String[] args) {
        BattleBoard boardA = BattleBoard.blankBoard(A);
        BattleBoard boardB = BattleBoard.blankBoard(B);

        A.setBoards(boardB, boardA);
        B.setBoards(boardA, boardB);

        UserInteraction interact = new UserInteraction(boardA, boardB);
        interact.setShips(new String[] {preset1(), preset2()});
        Team winner = interact.game();

        System.out.println("\n\n\n" + winner.getTeamName() + " won!!!!");
    }

    public static String preset1() {
        String[] input = new String[]{
                "C2R3 carrier V",
                "C5R5 battleship H",
                "R10C5 cruiser H",
                "R1C10 submarine V",
                "C5R8 destroyer H"
        };
        String output = "";
        for (String i : input) {
            output += i + "\n";
        }
        return output;
    }

    public static String preset2() {
        String[] input = new String[]{
                "R1C10 carrier v",
                "R10C1 battleship h",
                "R1C1 cruiser h",
                "R6C7 submarine v",
                "R4C3 destroyer h"
        };
        String output = "";
        for (String i : input) {
            output += i + "\n";
        }
        return output;
    }

}
