package homework08add;

enum ReaderNoisiness { SILENT, DEFAULT, NOISY }

public class Reader {
    private final ReaderNoisiness readerNoisiness;
    private boolean haveABook;
    private Book book;

    public Reader(ReaderNoisiness readerNoisiness) {
        this.readerNoisiness = readerNoisiness;
        this.haveABook = false;
        book = null;
    }

    public ReaderNoisiness getReaderNoisiness() {
        return readerNoisiness;
    }

    public void receiveABook(Book book) {
        if (book != null) {
            this.book = book;
            haveABook = true;
            System.out.println("Читатель получил книгу: " + book.getName());
        }
    }

    public boolean isHaveABook() {
        return haveABook;
    }
}
