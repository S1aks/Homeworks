package homework08add;

import java.util.ArrayList;
import java.util.Random;

public class ReadingRoom {
    private final int noiseLimit;

    private int noise;
    private final Librarian librarian;
    private final ArrayList<Reader> readers;
    private final ArrayList<Book> books;

    private final Random random = new Random();

    public ReadingRoom(int noiseLimit, int bookQuantity) {
        this.noiseLimit = noiseLimit;
        noise = 0;
        librarian = new Librarian(LibrarianCharacter.values()[random.nextInt(2)]);
        readers = new ArrayList<>();
        System.out.println("Читальный зал открыт. Максимальный уровень шума - " + noiseLimit);
        switch (librarian.getLibrarianCharacter()) {
            case GOOD:
                System.out.println("Сейчас в нём работает добрый библиотекарь.");
                break;
            case EVIL:
                System.out.println("Сейчас в нём работает злой библиотекарь.");
        }
        books = new ArrayList<>();
        for (int i = 0; i < bookQuantity; i++) {
            books.add(new Book("Книга " + i, random.nextBoolean()));
        }
    }

    public void addReader() {
        Reader reader = new Reader(ReaderNoisiness.values()[random.nextInt(3)]);
        readers.add(reader);
        switch (reader.getReaderNoisiness()) {
            case SILENT:
                readerService(reader, 0, "тихий");
                break;
            case DEFAULT:
                readerService(reader, 1, "обычный");
                break;
            case NOISY:
                readerService(reader, 2, "шумный");
                break;
        }
    }

    private void readerService(Reader reader, int deltaNoise, String printString) {
        noise += deltaNoise;
        System.out.println("Добавлен " + printString + " читатель в читальный зал.   Уровень шума - " + noise);
        reader.receiveABook(librarian.giveABook(reader, books));
    }

    public boolean checkLimitNoise() {
        return noise > noiseLimit;
    }

    public void librarianActivity() {
        switch (librarian.getLibrarianCharacter()) {
            case GOOD:
                System.out.println("Библиотекарь спокойно делает замечение расшумевшимся читателям.");
                break;
            case EVIL:
                System.out.println("Библиотекарь кричит на шумных читателей и бьёт линейкой по столу.");
        }
    }
}
