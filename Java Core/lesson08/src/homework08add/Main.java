package homework08add;

public class Main {
    public static void main(String[] args) {
        ReadingRoom readingRoom = new ReadingRoom(10, 20);
        while (!readingRoom.checkLimitNoise()) {
            readingRoom.addReader();
        }
        readingRoom.librarianActivity();
    }
}
