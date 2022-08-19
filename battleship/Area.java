package battleship;

import java.util.Arrays;
import java.util.Scanner;

public class Area {
    char[][] matrix = new char[10][10];
    final static String[] ERRORS = {
            "Error! Wrong input! Try again:\n",
            "Error! Wrong length of %s! Try again:\n",
            "Error! Wrong ship location! Try again:\n",
            "Error! You placed it too close to another one. Try again:\n"
    };
    public Area() {
        for (char[] chars : matrix) {
            Arrays.fill(chars, '~');
        }
        print();
    }

    public void print() {
        System.out.print(" ");
        for (int i = 1; i < 11; i++) {
            System.out.print(" " + i);
        }
        System.out.println();
        char ch = 'A';
        for (int i = 0; i < matrix.length; i++) {
            System.out.print(ch++);
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(" " + matrix[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    void addShipToArea(Ship ship) {
        for (int[] cell: ship.getLocation()) {
            matrix[cell[0]][cell[1]] = 'O';
        }
    }


    public void inputStartCoordinates(Ship[] ships) {
        Scanner scanner = new Scanner(System.in);
        for (Ship ship: ships) {
            System.out.printf("Enter the coordinates of %s (%d cells):\n\n", ship.getName(), ship.getSize());
            String flag = "";
            while (!flag.equals("ok")) {
                String[] coordinates = scanner.nextLine().split("\\s");
                System.out.println();
                int[] x = Converter.convertingCoordinate(coordinates[0].toCharArray());
                int[] y = Converter.convertingCoordinate(coordinates[1].toCharArray());
                switch (flag = Checker.checkCoordinates(x, y, ship.getSize(), ships)) {
                    case "wrong input" -> System.out.println(ERRORS[0]);
                    case "wrong length" -> System.out.println(ERRORS[1].formatted(ship.getName()));
                    case "wrong location" -> System.out.println(ERRORS[2]);
                    case "too close" -> System.out.println(ERRORS[3]);
                    default -> ship.setLocation(x, y);
                }
            }
            addShipToArea(ship);
            print();
        }
    }
}

