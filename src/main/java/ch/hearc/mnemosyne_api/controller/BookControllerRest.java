package ch.hearc.mnemosyne_api.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.hearc.mnemosyne_api.model.BookRest;
import ch.hearc.mnemosyne_api.service.BookRestService;


@CrossOrigin /*(origins = "http://127.0.0.1:5173") */
@RestController
@RequestMapping("/api")
public class BookControllerRest {

	@Autowired
	BookRestService bookService;

	@GetMapping(value = { "/books" })
	public List<BookRest> getAllBooks() {
		return bookService.getAllBooks();
	}

	// GET maintenant du coup
	@GetMapping(value = { "/books/{id}" })
	public BookRest getBook(@PathVariable("id") Long id) {
		return bookService.getBookById(id);
	}

	@PostMapping(value = { "/books/new" })
	public String newBook(@RequestBody BookRest book) {
		bookService.addBookToLibrary(book);
		return "Book " + book.getName() + " inserted";
	}

	@PostMapping(value = { "/books/delete/{id}" })
	public String deleteBook(@PathVariable("id") Long id) {
		BookRest book = bookService.getBookById(id);
		bookService.deleteBookById(id);
		return "Book " + book.getName() + " deleted";
	}

	@PostMapping(value = "/books/update/{id}")
	public String updateBook(@PathVariable("id") Long id, @RequestBody BookRest newBook) {
		BookRest oldBook = bookService.getBookById(id);
		String oldName = oldBook.getName();
		bookService.updateBookById(id, newBook);
		return "Book " + oldName + " updated to " + newBook.getName();
	}

	@PostMapping(value="/books/find/{searchTerm}")
	public List<BookRest> findBook(@PathVariable("searchTerm") String searchTerm) {
		return bookService.findBook(searchTerm);
	}
}