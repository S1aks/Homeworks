package homework04;
/*
   Я думал, что это займет намного больше времени и кода. Но, как говорится глаза боятся, а руки делают.
   Каюсь, в интернет заглядывал в поисках подсказки. Но мне не нравились варианты со сравнением
   вариантов с таблицей и перебором всех возможных значений.
   На хабре наткнулся на комментарий к статье https://habr.com/ru/post/329300/#comment_10231706
   Внутри что-то щелкнуло, типа "Вот оно!!" )))
   Как оказалось в итоге не так всё сложно, главное продумать правильно логику и алгоритмы.
   Было очень приятно размять свой мозг на низком уровне)))
   Спасибо за интересные задачи!
 */
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static char[][] map;
    public static final int SIZE = 3;           // Размер поля SIZE x SIZE
    public static final int DOTS_TO_WIN = 3;    // Сколько в линии для победы
    public static final boolean AI_BLOCKER = true;  // Включение или отключение блокиратора ходов (true - вкл.)
    public static final char DOT_EMPTY = '*';
    public static final char DOT_X = 'X';
    public static final char DOT_O = 'O';
    public static int x, y;  // Координаты последнего хода (решил сделать глобальными, т.к. многие методы используют)
    private static Random random = new Random();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initMap();
        printMap();
        boolean humanTurn = true;   // Переключатель действующего игрока (человек или нет сейчас ходит?)
        while (true) {              //     чтобы не гонять дважды в одном цикле одни и те же процедуры
            doTurn(humanTurn);
            printMap();
            if (checkWin(humanTurn)) {
                System.out.println(humanTurn ? "Вы выиграли!!" : "Победил компьютер!!"); // Этого на лекциях пока не было))
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья!");
                break;
            }
            humanTurn = !humanTurn;
        }
        scanner.close();
    }

    private static void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    private static void printMap() {
        for (int i = 0; i < SIZE; i++) {
            System.out.print("\t" + (i+1));
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print(i+1);
            for (int j = 0; j < SIZE; j++) {
                System.out.print("\t" + map[i][j]);
            }
            System.out.println();
        }
    }

    private static void doTurn(boolean humanTurn) {
        if (humanTurn) {
            do {
                System.out.println("Введите координаты хода (X Y) ");
                x = scanner.nextInt() - 1;
                y = scanner.nextInt() - 1;
            } while (notCorrectCoords(x, y));
            map[y][x] = DOT_X;
        } else {
            if (AI_BLOCKER) {
                aiTurnWithCheckHumanLastTurnPotential();
            } else {
                aiTurn();
            }
            System.out.println("Компьютер сделал ход в (" + (x + 1) + " " + (y + 1) + ") ");
        }
    }

    private static boolean notCorrectCoords(int x, int y) {
        return (x < 0 || x >= SIZE || y < 0 || y >= SIZE || map[y][x] != DOT_EMPTY);
    }

    private static void aiTurn() {
        do {
            x = random.nextInt(SIZE);
            y = random.nextInt(SIZE);
        } while (notCorrectCoords(x, y));
        map[y][x] = DOT_O;
    }

    private static void aiTurnWithCheckHumanLastTurnPotential() {  // Имя по всем канонам))
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) {
                    x = j;
                    y = i;
                    map[i][j] = DOT_O;
                    if (checkWin(false)) {
                        return;     // <----------- Выход, если найдена комбинация, в которой компьютер выиграет с
                    } else {        //              установкой в неё хода компьютера
                        map[i][j] = DOT_EMPTY;
                    }
                }
            }
        }
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) {
                    x = j;
                    y = i;
                    map[i][j] = DOT_X;
                    if (checkWin(true)) {
                        map[i][j] = DOT_O;
                        return;     // <----------- Выход, если найдена комбинация, в которой человек выиграет с
                    } else {        //              установкой в неё хода компьютера
                        map[i][j] = DOT_EMPTY;
                    }
                }
            }
        }
        aiTurn(); // Если нет угрозы, то выбор координат через Random
    }

    private static boolean checkWin(boolean humanTurn) {
        if (humanTurn) {
            return (checkHorizontal(DOT_X) || checkVertical(DOT_X) || checkDiagonal(DOT_X));
        }
        return (checkHorizontal(DOT_O) || checkVertical(DOT_O) || checkDiagonal(DOT_O));
    }

    private static boolean checkHorizontal(char checkChar) {
        int solidLineDotsCounter = 0;
        for (int i = 0; i < SIZE; i++) {
            if (map[y][i] == checkChar) {
                solidLineDotsCounter++;
            } else if (solidLineDotsCounter < DOTS_TO_WIN) {
                solidLineDotsCounter = 0;
            }
        }
        return (solidLineDotsCounter >= DOTS_TO_WIN);
    }

    private static boolean checkVertical(char checkChar) {
        int solidLineDotsCounter = 0;
        for (int i = 0; i < SIZE; i++) {
            if (map[i][x] == checkChar) {
                solidLineDotsCounter++;
            } else if (solidLineDotsCounter < DOTS_TO_WIN) {
                solidLineDotsCounter = 0;
            }
        }
        return (solidLineDotsCounter >= DOTS_TO_WIN);
    }

    private static boolean checkDiagonal(char checkChar) {
        return (checkSlashDiagonal(checkChar) || checkBackSlashDiagonal(checkChar));
    }

    private static boolean checkSlashDiagonal(char checkChar) {
        int solidLineDotsCounter = 0;
        int checkX, checkY;
        if ((x + y) < SIZE) {  // Проверка координат хода и установка цикла проверки в верхнюю точку диагонали
            checkX = x + y;
            checkY = 0;
        } else {
            checkX = SIZE - 1 ;
            checkY = (x + y) - SIZE + 1;
        }
        while (checkX >= 0 && checkY < SIZE) {
            if (map[checkY][checkX] == checkChar) {
                solidLineDotsCounter++;
            } else if (solidLineDotsCounter < DOTS_TO_WIN) {
                solidLineDotsCounter = 0;
            }
            checkX--;
            checkY++;
        }
        return (solidLineDotsCounter >= DOTS_TO_WIN);
    }

    private static boolean checkBackSlashDiagonal(char checkChar) {
        int solidLineDotsCounter = 0;
        int checkX, checkY;
        if (x > y) {  // Проверка координат хода и установка цикла проверки в верхнюю точку диагонали
            checkX = x - y;
            checkY = 0;
        } else {
            checkX = 0;
            checkY = y - x;
        }
        while (checkX < SIZE && checkY < SIZE) {
            if (map[checkY][checkX] == checkChar) {
                solidLineDotsCounter++;
            } else if (solidLineDotsCounter < DOTS_TO_WIN) {
                solidLineDotsCounter = 0;
            }
            checkX++;
            checkY++;
        }
        return (solidLineDotsCounter >= DOTS_TO_WIN);
    }

    private static boolean isMapFull() {
        for (char[] line : map) {
            for (char dot : line) {
                if (dot == DOT_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }
}
