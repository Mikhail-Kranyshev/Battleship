package battleship;

import java.util.Arrays;
import java.util.Scanner;

public class Area {
    char[][] matrix = new char[10][10];
    final static String[] ERRORS = {
            "\nError! You entered the wrong coordinates! Try again:\n",
            "Error! Wrong length of %s! Try again:\n",
            "Error! Wrong ship location! Try again:\n",
            "Error! You placed it too close to another one. Try again:\n",
            "Error! You already shot here! Try again:\n"
    };
    public Area() {
        for (char[] chars : matrix) {
            Arrays.fill(chars, '~');
        }
        print();
    }

    public void start() {
        System.out.println("The game starts!\n");
        print();
    }

    public void print() {
        System.out.print(" ");
        for (int i = 1; i < 11; i++) {
            System.out.print(" " + i);
        }
        System.out.println();
        char ch = 'A';
        for (char[] chars : matrix) {
            System.out.print(ch++);
            for (char aChar : chars) {
                System.out.print(" " + aChar);
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
        for (Ship ship : ships) {
            System.out.printf("Enter the coordinates of %s (%d cells):\n\n", ship.getName(), ship.getSize());
            String flag = "";
            while (!flag.equals("ok")) {
                String[] coordinates = scanner.nextLine().split("\\s");
                System.out.println("\n");
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

    public void shot() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Take a shot!\n");
        String flag = "";
        int[] coordinate;
        while (!flag.equals("ok")) {
            String string = scanner.next();
            char[] chars = string.toCharArray();
            coordinate = Converter.convertingCoordinate(chars);
            switch (flag = Checker.checkCoordinate(coordinate, matrix)) {
                case "wrong input" -> System.out.println(ERRORS[0]);
                case "already shot" -> System.out.println(ERRORS[4]);
                default -> checkShot(coordinate);
            }
        }
    }

    void checkShot(int[] coordinate) {
        int x = coordinate[0];
        int y = coordinate[1];
        if (matrix[x][y] == '~') {
            matrix[x][y] = 'M';
            print();
            System.out.println("You missed!\n");
        } else {
            matrix[x][y] = 'X';
            print();
            System.out.println("You hit a ship!\n");
        }
    }
}

