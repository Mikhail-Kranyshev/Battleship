package battleship;

import java.io.IOException;

public class Player {
    private Ship[] ships;
    private Area myArea;
    private Area enemyArea;
    private int idPlayer;
    private static int id = 0;

    public Player() {
        this.ships = createShips();
        this.myArea = new Area(ships);
        this.enemyArea = new Area();
        idPlayer = ++id;
    }

    private Ship[] getShips() {
        return ships;
    }

    public char[][] getArea() {
        return myArea.getMatrix();
    }

    private static Ship[] createShips() {
        Ship aircraftCarrier = new Ship("the Aircraft Carrier", 5);
        Ship battleship = new Ship("the Battleship", 4);
        Ship submarine = new Ship("Submarine", 3);
        Ship cruiser = new Ship("the Cruiser", 3);
        Ship destroyer = new Ship("the Destroyer", 2);
        return new Ship[]{aircraftCarrier, battleship, submarine, cruiser, destroyer};
    }

    public void start() {
        System.out.printf("Player %d, place your ships on the game field\n\n", idPlayer);
        myArea.print();
        myArea.inputStartCoordinates();
        promptEnterKey();
    }

    public static void promptEnterKey() {
        System.out.println("Press Enter and pass the move to another player");
        try {
            System.in.read();
            System.out.println("\n\n\n\n\n\n\n\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printScreen() {
        myArea.print(enemyArea);
    }

    public boolean turn(Player enemyPlayer) {
        printScreen();
        System.out.printf("Player %d, it's your turn:\n\n", idPlayer);
        if (enemyArea.shot(enemyPlayer.getShips(), enemyPlayer.getArea())) {
            printScreen();
            return true;
        }
        return false;
    }
}
