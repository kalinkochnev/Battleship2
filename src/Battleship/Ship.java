package Battleship;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ship {
    private Team team;
    private List<ShipPart> shipParts;
    private direction dir;
    private String name;

    public Ship(String name, Team team, direction dir, ShipPart[] parts) {
        this.name = name;
        this.team = team;
        this.dir = dir;
        this.shipParts = new ArrayList<>(Arrays.asList(parts));
    }

    public enum direction {
        VERTICAL, HORIZONTAL, NONE
    }

    public direction getDir() {
        return this.dir;
    }

    public ShipPart[] getParts() {
        return ShipPart.toArray(shipParts);
    }

    public void setShipParts(ShipPart[] parts) {
        this.shipParts = new ArrayList<>(Arrays.asList(parts));
    }

    public ShipPart getFirst() {
        return shipParts.get(0);
    }

    public ShipPart getLast() {
        return shipParts.get(shipParts.size()-1);
    }

    public int length() {
        return shipParts.size();
    }

    public ShipPart getShipPart(int index) {
        return this.shipParts.get(index);
    }
    public ShipPart[] getShipParts() {
        ShipPart[] parts = new ShipPart[shipParts.size()];
        for (int s = 0; s < parts.length; s++) {
            parts[s] = shipParts.get(s);
        }
        return parts;
    }

    public void setDir(Ship.direction dir) {
        this.dir = dir;
    }

    public String getName() {
        return name;
    }

    public boolean isSunk() {
        int sunk = 0;
        for (ShipPart part : shipParts) {
            if (part.isHit()) {
                sunk++;
            }
        }

        return shipParts.size() == sunk;
    }
}
