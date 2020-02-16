package Battleship;

import java.util.Arrays;
import java.util.List;

public class ShipPart {
    private Space space;
    private boolean hit;

    public ShipPart() {
        this.space = null;
        this.hit = false;
    }

    ShipPart(Space space) {
        this.space = space;
    }

    ShipPart(Space space, boolean state) {
        this.space = space;
        this.hit = state;
    }

    public Space getSpace() {
        return space;
    }

    public void setSpace(Space space) {
        this.space = space;
    }

    public boolean isHit() {
        return this.hit;
    }

    public static void setSpaces(ShipPart[] parts, Space[] spaces) {
        if (parts.length == spaces.length) {
            for (int part = 0; part < parts.length; part++) {
                parts[part].setSpace(spaces[part]);
                spaces[part].setShipPart(parts[part]);
            }
        }
    }

    public static ShipPart[] toArray(List<ShipPart> parts) {
        ShipPart[] shipParts = new ShipPart[parts.size()];
        for (int i = 0; i < parts.size(); i++) {
            shipParts[i] = parts.get(i);
        }

        return shipParts;
    }

    public static ShipPart[] generateParts(int length, boolean hit) {
        ShipPart[] parts = new ShipPart[length];
        for (int i = 0; i < length; i++) {
            parts[i] = new ShipPart();
        }

        return parts;
    }
}
