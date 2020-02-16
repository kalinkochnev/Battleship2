//package Tests;

import Battleship.BattleBoard;
import Battleship.Ship;
import Battleship.ShipPart;
import Battleship.Team;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ShipTests {
    ShipPart part1;
    ShipPart part2;

    @BeforeEach
    void reset() {
        part1 = new ShipPart();
        part2 = new ShipPart();
    }

    @Test
    void testShipPartCreation() {
        ShipPart[] parts = new ShipPart[] {part1, part2};

        Team team_a = new Team("A");
        Ship big_ship = new Ship("test", team_a, Ship.direction.HORIZONTAL, parts);
        ShipPart[] ship_parts = big_ship.getParts();
        assertArrayEquals(parts, ship_parts);
    }

    @Test
    void testGetDir() {
        ShipPart[] parts = new ShipPart[] {part2};

        Team team_a = new Team("A");
        Ship big_ship = new Ship("test", team_a, Ship.direction.HORIZONTAL, parts);
        assertEquals(big_ship.getDir(), Ship.direction.HORIZONTAL);
    }

    @Test
    void testGetFirst() {
        ShipPart[] parts = new ShipPart[] {part1, part2};

        Team team_a = new Team("A");
        Ship big_ship = new Ship("test", team_a, Ship.direction.HORIZONTAL, parts);
        assertEquals(part1, big_ship.getFirst());
    }

    @Test
    void testGetLast() {
        ShipPart[] parts = new ShipPart[] {part1, part2};

        Team team_a = new Team("A");
        Ship big_ship = new Ship("test", team_a, Ship.direction.HORIZONTAL, parts);
        assertEquals(part2, big_ship.getLast());
    }

    @Test
    void testGetShipPart() {
        ShipPart[] parts = new ShipPart[] {part1, part2};

        Team team_a = new Team("A");
        Ship big_ship = new Ship("test", team_a, Ship.direction.HORIZONTAL, parts);
        assertEquals(part2, big_ship.getShipPart(1));
    }

}