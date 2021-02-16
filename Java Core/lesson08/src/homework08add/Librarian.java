package homework08add;

import java.util.ArrayList;

enum LibrarianCharacter { GOOD , EVIL }

public class Librarian {
    private final LibrarianCharacter librarianCharacter;

    public Librarian(LibrarianCharacter librarianCharacter) {
        this.librarianCharacter = librarianCharacter;
    }

    public LibrarianCharacter getLibrarianCharacter() {
        return librarianCharacter;
    }

    public Book giveABook(Reader reader, ArrayList<Book> books) {
        if (!reader.isHaveABook()) {
            for (int i = 0; i < books.size(); i++) {
                if (books.get(i).isAvailable()) {
                    books.get(i).setBookIssued();
                    return books.get(i);
                }
            }
        }
        return null;
    }
}
