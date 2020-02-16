//package Tests;

import Battleship.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TeamTests {
    BattleBoard board = BattleBoard.blankBoard();

    @AfterEach
    void resetBoard() {
        board = BattleBoard.blankBoard();
    }

    @Test
    void testAddShips() {
        Team team = new Team("A");
        Ship carrier = new Ship(team, Ship.direction.NONE, ShipPart.generateParts(5, false));
        Ship cruiser = new Ship(team, Ship.direction.NONE, ShipPart.generateParts(4, false));
        Ship sub = new Ship(team, Ship.direction.NONE, ShipPart.generateParts(3, false));
        Ship destroyer = new Ship(team, Ship.direction.NONE, ShipPart.generateParts(2, false));

        Ship[] ships = new Ship[] {carrier, cruiser, sub, destroyer};
        team.addShips(ships);
        assertEquals(carrier, team.getShip(0));
        assertEquals(cruiser, team.getShip(1));
        assertEquals(sub, team.getShip(2));
        assertEquals(destroyer, team.getShip(3));
    }


}

