package homework03;

import java.util.Scanner;

public class Main {

    /*
    Написать программу, которая загадывает случайное число от 0 до 9 и пользователю дается
    3 попытки угадать это число. При каждой попытке компьютер должен сообщить, больше ли
    указанное пользователем число, чем загаданное, или меньше. После победы или проигрыша
    выводится запрос – «Повторить игру еще раз? 1 – да / 0 – нет»(1 – повторить, 0 – нет).
     */
    public static void guessTheNumber () {
        Scanner scanner = new Scanner(System.in);
        int newGame = 1; // Переменная новой игры
        while (newGame == 1) { // Если = 1, то играем
            System.out.println("Вам нужно угадать число от 0 до 9 (включительно). Дается 3 попытки.");
            int number = (int) (Math.random() * 10); // Загадываем число от 0 до 9
            int userNumber; // Переменная для хранения числа пользователя
            for (int i = 1; i <= 3; i++) { // Запус цикла для трёх попыток
                System.out.print("Попытка №" + i + ". Введите число: ");
                userNumber = scanner.nextInt(); // Считывание числа пользователя
                if (userNumber == number) { // Проверка совпадения
                    System.out.println("Угадали! Число " + number); // Совпало -> поздравления и выход из опроса вариантов
                    break;
                } else if (i == 3) { // Если не совпадает и последняя попытка -> сочувствие и вывод загаданного числа
                    System.out.println("Попытки закончились! Число " + number + " не угадано!");
                } else if (userNumber > number) { // Если не совпадает и больше - > вывод сообщения, что больше
                    System.out.println("Число больше заданного");
                } else { // Иначе -> вывод сообщения что меньше
                    System.out.println("Число меньше заданного");
                }
            }
            System.out.println("Повторить игру еще раз? 1 – да / 0 – нет");
            newGame = scanner.nextInt(); // Запрос повтора игры
        }
    }

    /*
    Создать массив из слов
    String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli",
    "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut",
    "olive", "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"}.
    При запуске программы компьютер загадывает слово, запрашивает ответ у пользователя,
    сравнивает его с загаданным словом и сообщает, правильно ли ответил пользователь.
    Если слово не угадано, компьютер показывает буквы, которые стоят на своих местах.
    apple – загаданное
    apricot - ответ игрока
    ap############# (15 символов, чтобы пользователь не мог узнать длину слова)
    Для сравнения двух слов посимвольно можно пользоваться:
    String str = "apple";
    char a = str.charAt(0); - метод, вернет char, который стоит в слове str на первой позиции
    Играем до тех пор, пока игрок не отгадает слово.
    Используем только маленькие буквы.
     */
    static String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli",
            "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut",
            "olive", "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};

    public static void guessTheWord () {
        Scanner scanner = new Scanner(System.in);
        int newGame = 1; // Переменная новой игры
        while (newGame == 1) { // Если = 1, то играем
            int wordNumber = (int) (Math.random() * 25);    // Выбор числа от 0 до 24
            String word = words[wordNumber];                // Выбор слова под эти номером
            System.out.println("Вам нужно угадать слово. Вводить слова нужно строчными буквами английского алфавита.");
            while (true){                                   // Запуск бесконечного цикла, пока слово не будет отгадано
                System.out.print("Введите слово: ");
                String userWord = scanner.nextLine();       // Запрос слова пользователя
                if (userWord.equals(word)) {                // Если совпадает -> поздравление и выход из опроса
                    System.out.println("Угадали! Слово " + word);
                    break;
                } else {                                    // Если не совпадает то...
                    boolean stopShowLetters = false;        // Переменная для вывода отгаданных букв
                    int index = 0;                          // Счетчик выведеных символов
                    while (index < 15) {                    // Запуск цикла вывода 15-ти символов
                        if (index >= userWord.length() || index >= word.length()) {   // Проверка если счетчик больше
                            stopShowLetters = true;         //     длины введённого или загаданного слова то перестаем
                        }                                   //     выводить буквы и выводим '#'
                        if (!stopShowLetters) {             // Если можно выводить буквы
                            char letter = userWord.charAt(index);   // Берём букву из слова пользователя на месте index
                            if (letter != word.charAt(index)) {     // Сверяем с буквой загаданного слова
                                System.out.print("#");              // Если не совпадают то не выводим больше буквы
                            } else {
                                System.out.print(letter);           // Иначе выводим совпавшую букву
                            }
                        } else {                            // Если нет вывода букв печатаем #
                            System.out.print("#");
                        }
                        index++;                            // Переход к следующему символу
                    }
                }
                System.out.println();                       // Печать конца строки
            }
            System.out.println("Повторить игру еще раз? 1 – да / 0 – нет");
            newGame = Integer.parseInt(scanner.nextLine()); // Запрос повтора игры
        }
    }

    public static void main(String[] args) {
        guessTheNumber();    // Запуск игры отгадай число
        guessTheWord();      // Запуск игры отгадай слово
    }
}
