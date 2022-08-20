package battleship;

import java.util.*;

public class Ship {
    String name;
    int size;
    ArrayList<int[]> location;
    char[] ship;

    public Ship (String name, int size) {
        this.name = name;
        this.size = size;
        location = new ArrayList<>(size);
        this.ship = new char[size];
    }



    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public ArrayList<int[]> getLocation() {
        return location;
    }

    public char[] getShip() {
        return ship;
    }

    void setShot() {
        Arrays.fill(ship, 'O');
    }

    void setShot(int index) {
        ship[index] = 'X';
    }

    public void setLocation(int[] x, int[] y) {
        int start, end;
        if (x[0] == y[0]) {
            if (x[1] > y[1]) {
                start = y[1];
                end = x[1];
            } else {
                start = x[1];
                end = y[1];
            }
            for (int i = start; i <= end; i++) {
                location.add(new int[]{x[0], i});
            }
        } else {
            if (x[0] > y[0]) {
                start = y[0];
                end = x[0];
            } else {
                start = x[0];
                end = y[0];
            }
            for (int i = start; i <= end; i++) {
                location.add(new int[]{i, x[1]});
            }
        }
        setShot();
    }
}
