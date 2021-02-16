package homework08add;

public class Book {
    private final String name;
    private boolean availability;

    public Book(String name, boolean availability) {
        this.name = name;
        this.availability = availability;
    }

    public String getName() {
        return name;
    }

    public boolean isAvailable() {
        return availability;
    }

    public void setBookIssued() {
        availability = false;
    }
}
