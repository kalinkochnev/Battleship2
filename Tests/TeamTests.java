//package Tests;

import Battleship.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TeamTests {
    Team teamA = new Team("A");
    BattleBoard board = BattleBoard.blankBoard(teamA);

    @AfterEach
    void resetBoard() {
        board = BattleBoard.blankBoard(teamA);
    }

    @Test
    void testAddShips() {
        Team team = new Team("A");
        Ship carrier = new Ship("carrier", team, Ship.direction.NONE, ShipPart.generateParts(5, false));
        Ship battleship = new Ship("battleship", team, Ship.direction.NONE, ShipPart.generateParts(4, false));
        Ship cruiser = new Ship("cruiser", team, Ship.direction.NONE, ShipPart.generateParts(3, false));
        Ship sub = new Ship("submarine", team, Ship.direction.NONE, ShipPart.generateParts(3, false));
        Ship destroyer = new Ship("destroyer", team, Ship.direction.NONE, ShipPart.generateParts(2, false));
        Ship[] ships = new Ship[]{carrier, battleship, cruiser, sub, destroyer};
        team.addShips(ships);
        assertEquals(carrier, team.getShip(0));
        assertEquals(cruiser, team.getShip(1));
        assertEquals(sub, team.getShip(2));
        assertEquals(destroyer, team.getShip(3));
    }


}

