package Battleship;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Team {
    private String teamName;
    private List<Ship> ships = new ArrayList<>();

    public Team(String name) {
        this.teamName = name;
    }

    public void initShips() {
        Ship carrier = new Ship(this, Ship.direction.NONE, ShipPart.generateParts(5, false));
        Ship battleship = new Ship(this, Ship.direction.NONE, ShipPart.generateParts(4, false));
        Ship cruiser = new Ship(this, Ship.direction.NONE, ShipPart.generateParts(3, false));
        Ship sub = new Ship(this, Ship.direction.NONE, ShipPart.generateParts(3, false));
        Ship destroyer = new Ship(this, Ship.direction.NONE, ShipPart.generateParts(2, false));
        Ship[] ships = new Ship[]{carrier, cruiser, sub, destroyer};
        this.addShips(ships);
    }

    public Ship getShipByName(String input) {
        String[] shipNames = new String[] {"carrier", "cruiser", "sub", "destroyer"};
        for (int ship = 0; ship < shipNames.length; ship++) {
            if (input.equals(shipNames[ship])) {
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
}
