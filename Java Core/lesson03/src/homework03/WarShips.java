package homework03;

import java.util.Arrays;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class WarShips {

    public static final int COUNT_AVAILABLE_SHIPS = 4;
    public static final int SIZE_WAR_FIELD = 5;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        do {
            System.out.println("\n============ МОРСКОЙ БОЙ ============\n");
            int[][] userWarField = createAndFillUserWarField();
            int[][] computerWarField = createAndFillComputerWarField();
            int remainingUserShips = COUNT_AVAILABLE_SHIPS;
            int remainingComputerShips = COUNT_AVAILABLE_SHIPS;
            int[][] computerDoesShoot = new int[SIZE_WAR_FIELD][SIZE_WAR_FIELD];
            boolean isUserShoot = random.nextBoolean();
            System.out.println("\n============ ИГРА НАЧАЛАСЬ ===========\n");
            while (remainingComputerShips > 0 && remainingUserShips > 0) {
                if (isUserShoot) {
                    System.out.print("Ход пользователя. Укажите ячейку для стрельбы (X Y): ");
                    int indexCeilX = scanner.nextInt();
                    int indexCeilY = scanner.nextInt();
                    if (computerWarField[indexCeilY][indexCeilX] == 1) {
                        System.out.println("Убил!");
                        computerWarField[indexCeilY][indexCeilX] = 4;
                        remainingComputerShips--;
                    } else {
                        System.out.println("Промах!");
                        computerWarField[indexCeilY][indexCeilX] = 3;
                        isUserShoot = false;
                    }
                } else {
                    System.out.print("Ход компьютера: ");
                    int indexCeilX = random.nextInt(SIZE_WAR_FIELD);
                    int indexCeilY = random.nextInt(SIZE_WAR_FIELD);
                    while (computerDoesShoot[indexCeilX][indexCeilY] == 1) {
                        indexCeilX = random.nextInt(SIZE_WAR_FIELD);
                        indexCeilY = random.nextInt(SIZE_WAR_FIELD);
                    }
                    computerDoesShoot[indexCeilY][indexCeilX] = 1;
                    if (userWarField[indexCeilY][indexCeilX] == 1) {
                        System.out.println("Компьютер убил!");
                        userWarField[indexCeilY][indexCeilX] = 4;
                        remainingUserShips--;
                    } else {
                        System.out.println("Компьютер промахнулся!");
                        isUserShoot = true;
                    }
                }
                printFields(userWarField, computerWarField);
            }
            if (isUserShoot) {
                System.out.println("Победа!");
            } else {
                System.out.println("Вы проиграли!");
            }
            System.out.print("Хотите сыграть заново? (Y/N) -> ");
        } while (scanner.next().toUpperCase(Locale.ROOT).equals("Y"));
        scanner.close();
    }

    private static int[][] createAndFillComputerWarField() {
        Random random = new Random();
        int[][] warField = new int[SIZE_WAR_FIELD][SIZE_WAR_FIELD];
        int countAvailableShips = COUNT_AVAILABLE_SHIPS;
        while (countAvailableShips > 0) {
            int indexCeilX = random.nextInt(SIZE_WAR_FIELD);
            int indexCeilY = random.nextInt(SIZE_WAR_FIELD);
            if (warField[indexCeilY][indexCeilX] == 0) {
                warField[indexCeilY][indexCeilX] = 1;
                countAvailableShips--;
            }
        }
        return warField;
    }

    private static int[][] createAndFillUserWarField() {
        Scanner scanner = new Scanner(System.in);
        int[][] warField = new int[SIZE_WAR_FIELD][SIZE_WAR_FIELD];
        int countAvailableShips = COUNT_AVAILABLE_SHIPS;
        System.out.println("Ваше поле для расстановки кораблей: ");
        printUserField(warField);
        while (countAvailableShips > 0) {
            System.out.print("Куда поставить корабль? Вводите номер поля\nцифрами через пробел или ENTER (X Y): ");
            int indexCeilX = scanner.nextInt();
            int indexCeilY = scanner.nextInt();
            if (indexCeilX < 0 || indexCeilX >= SIZE_WAR_FIELD || indexCeilY < 0 || indexCeilY >= SIZE_WAR_FIELD) {
                System.err.println("Недопустимый индекс. Укажите число от 0 до " + (SIZE_WAR_FIELD - 1));
            } else if (warField[indexCeilY][indexCeilX] != 0) {
                System.err.println("Ячейка уже занята!");
            } else {
                warField[indexCeilY][indexCeilX] = 1;
                countAvailableShips--;
                printUserField(warField);
            }
        }
        return warField;
    }

    private static void printUserField(int[][] warField) {
        System.out.print("  ");
        for (int i = 0; i < SIZE_WAR_FIELD; i++) {
            System.out.print("  " + i);
        }
        System.out.println();
        for (int i = 0; i < SIZE_WAR_FIELD; i++) {
            System.out.println(" " + i + " " + Arrays.toString(warField[i]));
        }
    }


    private static void printFields(int[][] warField1, int[][] warField2) {
        System.out.print("    Ваше поле  ");
        for (int i = 0; i < SIZE_WAR_FIELD-3; i++) {
            System.out.print("   ");
        }
        System.out.println("   Поле компьютера");
        System.out.print("  ");
        for (int i = 0; i < SIZE_WAR_FIELD; i++) {
            System.out.print("  " + i);
        }
        System.out.print("     ");
        for (int i = 0; i < SIZE_WAR_FIELD; i++) {
            System.out.print("  " + i);
        }
        System.out.println("  ");
        for (int i = 0; i < SIZE_WAR_FIELD; i++) {
            System.out.print(" " + i + " " + Arrays.toString(warField1[i]) + "   " + i + " [");

            for (int j = 0; j < SIZE_WAR_FIELD; j++) {
                if (j == SIZE_WAR_FIELD-1) {
                    if (warField2[i][j] == 0 || warField2[i][j] == 1) {
                        System.out.print("0");
                    } else if (warField2[i][j] == 3) {
                        System.out.print("*");
                    } else {
                        System.out.print(warField2[i][j]);
                    }
                } else {
                    if (warField2[i][j] == 0 || warField2[i][j] == 1) {
                        System.out.print("0, ");
                    } else if (warField2[i][j] == 3) {
                        System.out.print("*, ");
                    } else {
                        System.out.print(warField2[i][j] + ", ");
                    }
                }
            }
            System.out.println("]");
        }
    }
}