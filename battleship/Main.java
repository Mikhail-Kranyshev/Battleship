package battleship;

public class Main {

    public static void game() {
        Player player1 = new Player();
        Player player2 = new Player();
        player1.start();
        player2.start();
        boolean playerWin;
        while (true) {
            playerWin = player1.turn(player2);
            if (playerWin) {
                break;
            }
            playerWin = player2.turn(player1);
            if (playerWin) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        game();
    }
}
