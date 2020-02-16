package Battleship;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Team {
    private String teamName;
    private List<Ship> ships = new ArrayList<>();
    BattleBoard opponentBoard;
    BattleBoard board;


    public Team(String name) {
        this.teamName = name;
    }

    public BattleBoard getTeamBoard() {
        return board;
    }

    public void setBoards(BattleBoard opponentBoard, BattleBoard board) {
        this.opponentBoard = opponentBoard;
        this.board = board;
    }

    public boolean shipsArePlaced() {
        for (Ship ship : ships) {
            if (ship.getShipPart(0).getSpace() == null) {
                return false;
            }
        }
        return true;
    }

    public void initShips() {
        Ship carrier = new Ship("carrier", this, Ship.direction.NONE, ShipPart.generateParts(5, false));
        Ship battleship = new Ship("battleship", this, Ship.direction.NONE, ShipPart.generateParts(4, false));
        Ship cruiser = new Ship("cruiser", this, Ship.direction.NONE, ShipPart.generateParts(3, false));
        Ship sub = new Ship("submarine", this, Ship.direction.NONE, ShipPart.generateParts(3, false));
        Ship destroyer = new Ship("destroyer", this, Ship.direction.NONE, ShipPart.generateParts(2, false));
        Ship[] ships = new Ship[]{carrier, battleship, cruiser, sub, destroyer};
        this.addShips(ships);
    }

    public Ship getShipByName(String input) {
        for (int ship = 0; ship < getNumShips(); ship++) {
            if (input.equals(getShip(ship).getName())) {
                return this.ships.get(ship);
            }
        }
        return null;
    }

    public void addShip(Ship ship) {
        this.ships.add(ship);
    }

    public void addShips(Ship[] shipArr) {
        for (Ship ship : shipArr) {
            this.ships.add(ship);
        }
    }

    public Ship getShip(int index) {
        return ships.get(index);
    }

    public Ship shipByPart(ShipPart p) {
        for (int ship = 0; ship < getNumShips(); ship++) {
            Ship s = getShip(ship);
            for (ShipPart part : s.getShipParts()) {
                if (p == part) {
                    return s;
                }
            }
        }
        return null;
    }

    public int getNumShips() {
        return ships.size();
    }

    public Ship[] getShips() {
        Ship[] ships = new Ship[this.ships.size()];
        for (int s = 0; s < ships.length; s++) {
            ships[s] = getShip(s);
        }
        return ships;
    }

    public String getTeamName() {
        return this.teamName;
    }
}
