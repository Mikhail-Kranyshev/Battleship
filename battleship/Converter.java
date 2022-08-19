package battleship;

import java.util.Arrays;

public class Converter {

    public static int convertingLetterToNumber(char ch) {
        return switch (ch) {
            case 'A' -> 0;
            case 'B' -> 1;
            case 'C' -> 2;
            case 'D' -> 3;
            case 'E' -> 4;
            case 'F' -> 5;
            case 'G' -> 6;
            case 'H' -> 7;
            case 'I' -> 8;
            case 'J' -> 9;
            default -> -1;
        };
    }

    public static int[] convertingCoordinate(char[] chars) {
        int[] coordinate = {-1, -1};
        if (chars.length == 2 || chars.length == 3) {
            coordinate[0] = convertingLetterToNumber(chars[0]);
            if (chars.length == 3) {
                coordinate[1] = Integer.parseInt(String.valueOf(new char[]{chars[1], chars[2]})) - 1;
            } else {
                coordinate[1] = Integer.parseInt(String.valueOf(chars[1])) - 1;
            }
            if (coordinate[0] == -1 && (coordinate[1] < 0 || coordinate[1] > 9)) {
                coordinate = new int[]{-1, -1};
            }
        }
        return coordinate;
    }
}
