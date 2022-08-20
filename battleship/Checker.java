package battleship;

import java.util.Arrays;

public abstract class Checker {

    public static char checkShot(int[] coordinate, Ship[] ships) {
        for (Ship ship : ships) {
            for (int i = 0; i < ship.getSize(); i++) {
                if (Arrays.equals(ship.getLocation().get(i), coordinate)) {
                    ship.setShot(i);
                    if (ship.isDead()) {
                        return 'D';
                    }
                    return 'X';
                }
            }
        }
        return 'M';
    }

    public static String checkCoordinate(int[] coordinate, char[][] matrix) {
        if (isEmpty(coordinate)) {
            return "wrong input";
        }
        if (isAlreadyShot(coordinate, matrix)) {
            return "already shot";
        }
        return "ok";
    }

    public static String checkCoordinates(int[] x, int[] y, int size, Ship[] ships) {
        String status = "ok";
        if (isEmpty(x) || isEmpty(y)) {
            return "wrong input";
        }
        if (isWrongLength(x, y, size)) {
            status = "wrong length";
        }
        if (isWrongLocation(x, y)) {
            status = "wrong location";
        }
        if (isTooClose(x, y, ships)) {
            status = "too close";
        }
        return status;
    }


    private static boolean isEmpty(int[] coordinate) {
        return coordinate[0] == -1 || coordinate[1] == -1;
    }

    private static boolean isWrongLength(int[] x, int[] y, int size) {
        return !(Math.abs(x[0] - y[0] + x[1] - y[1]) + 1 == size);
    }

    private static boolean isWrongLocation(int[] x, int[] y) {
        return !((x[0] == y[0]) || (x[1] == y[1]));
    }

    private static boolean isTooClose(int[] x, int[] y, Ship[] ships) {
        for (Ship ship : ships) {
            if (!(ship.getLocation() == null)) {
                for (int[] cell : ship.getLocation()) {
                    for (int i = cell[0] - 1; i <= cell[0] + 1; i++) {
                        for (int j = cell[1] - 1; j <= cell[1] + 1; j++) {
                            if ((x[0] == i && x[1] == j) || (y[0] == i && y[1] == j)) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    private static boolean isAlreadyShot(int[] coordinate, char[][] matrix) {
        int x = coordinate[0];
        int y = coordinate[1];
        return matrix[x][y] == 'M' || matrix[x][y] == 'X';
    }

    static boolean areAllDead(Ship[] ships) {
        for (Ship ship : ships) {
            if (!ship.isDead()) {
                return true;
            }
        }
        return false;
    }
}
