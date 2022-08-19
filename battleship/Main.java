package battleship;

public class Main {

    public static void game() {
        Ship[] ships = createShips();
        Area area = new Area();
        area.inputStartCoordinates(ships);
    }

    static Ship[] createShips() {
        Ship aircraftCarrier = new Ship("the Aircraft Carrier", 5);
        Ship battleship = new Ship("the Battleship", 4);
        Ship submarine = new Ship("Submarine", 3);
        Ship cruiser = new Ship("the Cruiser", 3);
        Ship destroyer = new Ship("the Destroyer", 2);
        Ship[] ships = new Ship[]{aircraftCarrier, battleship, submarine, cruiser, destroyer};
        return ships;
    }

    public static void main(String[] args) {
        game();
    }
}
