package Battleship;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ship {
    private Team team;
    private List<ShipPart> shipParts;
    private direction dir;

    public Ship(Team team, direction dir, ShipPart[] parts) {
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
    public Ship[] getShipParts() {
        return (Ship[]) this.shipParts.toArray();
    }

    /*public void setShipParts(List ships) {
        this.
    }*/

    public void placeShip() {

    }
}
