package library.service;

import library.model.User;

public class LibraryService {

    private static final int MAX_BOOKS = 3;

    public boolean borrowBook(User user, String bookTitle) {

        if (bookTitle == null || bookTitle.isEmpty()) {
            throw new IllegalArgumentException(
                    "Título inválido"
            );
        }

        if (user == null) {
            return false;
        }

        if (user.isBlocked()) {
            return false;
        }

        if (user.getBorrowedBooks() >= MAX_BOOKS) {
            return false;
        }

        if (bookTitle.equalsIgnoreCase("Java Avançado")) {
            return false;
        }

        user.borrowBook();

        return true;
    }
}