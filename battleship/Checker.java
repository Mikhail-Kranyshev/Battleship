package battleship;

import java.util.ArrayList;

public class Checker {

    public static String checkCoordinates(int[] x, int[] y, int size, Ship[] ships) {
        String status = "ok";
        if (coordinateIsEmpty(x) || coordinateIsEmpty(y)) {
            status = "wrong input";
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


    private static boolean coordinateIsEmpty(int[] coordinate) {
        return coordinate[0] == -1 || coordinate[1] == -1;
    }

    private static boolean isWrongLength(int[] x, int[] y, int size) {
        return !(Math.abs(x[0] - y[0] + x[1] - y[1]) + 1 == size);
    }

    private static boolean isWrongLocation(int[] x, int[] y) {
        return !((x[0] == y[0]) || (x[1] == y[1]));
    }

    private static boolean isTooClose(int[] x, int[] y, Ship[] ships) {
        for (Ship ship: ships) {
            if (!(ship.getLocation() == null)) {
                for (int[] cell: ship.getLocation()) {
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
}
