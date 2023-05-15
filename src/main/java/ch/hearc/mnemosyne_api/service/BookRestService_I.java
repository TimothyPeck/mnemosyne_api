package ch.hearc.mnemosyne_api.service;

import java.util.List;

import ch.hearc.mnemosyne_api.model.BookRest;

public interface BookRestService_I {

	public BookRest getBookById(Long id);

	public List<BookRest> getAllBooks();

	public BookRest deleteBookById(Long id);

	public BookRest updateBookById(Long id, BookRest book);

	public void addBookToLibrary(BookRest book);

    public List<BookRest> findBook(String searchTerm);
}
