package library.model;

public class User {

    private String username;
    private String password;
    private boolean blocked;
    private int borrowedBooks;

    public User(String username, String password, boolean blocked) {
        this.username = username;
        this.password = password;
        this.blocked = blocked;
        this.borrowedBooks = 0;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public int getBorrowedBooks() {
        return borrowedBooks;
    }

    public void borrowBook() {
        borrowedBooks++;
    }
}