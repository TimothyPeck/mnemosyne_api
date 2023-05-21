package ch.hearc.mnemosyne_api.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

import ch.hearc.mnemosyne_api.model.BookRest;
import ch.hearc.mnemosyne_api.model.log.LogType;
import ch.hearc.mnemosyne_api.model.log.SimpleLog;
import ch.hearc.mnemosyne_api.service.BookRestService;
import ch.hearc.mnemosyne_api.service.ProducerService;

@CrossOrigin /* (origins = "http://127.0.0.1:5173") */
@RestController
@RequestMapping("/api")
public class BookControllerRest {

	@Autowired
	BookRestService bookService;

	@Autowired
	private ProducerService producerService;

	@GetMapping(value = { "/books" })
	public List<BookRest> getAllBooks() {
		SimpleLog log = new SimpleLog(LogType.INFO, "GET /books", "Gets all the books");
		producerService.send(log);
		return bookService.getAllBooks();
	}

	// GET maintenant du coup
	@GetMapping(value = { "/books/{id}" })
	public BookRest getBook(@PathVariable("id") Long id) {
		try {
			BookRest book = bookService.getBookById(id);
			SimpleLog log = new SimpleLog(LogType.INFO, "GET /books/" + id, "Gets the book with id " + id);
			producerService.send(log);
			return book;
		} catch (Exception e) {
			SimpleLog log = new SimpleLog(LogType.ERROR, "GET /books/" + id, "Book with id " + id + " not found");
			producerService.send(log);
			return null;
		}
	}

	@PostMapping(value = { "/books/new" })
	public String newBook(@RequestBody BookRest book) {
		try {

			bookService.addBookToLibrary(book);
			SimpleLog log = new SimpleLog(LogType.INFO, "POST /books/new", "Book " + book.getName() + " inserted");
			producerService.send(log);
			return "Book " + book.getName() + " inserted";
		} catch (Exception e) {
			SimpleLog log = new SimpleLog(LogType.ERROR, "POST /books/new", "Book " + book.getName() + " not inserted");
			producerService.send(log);
			return "Book " + book.getName() + " not inserted";
		}
	}

	@DeleteMapping(value = { "/books/delete/{id}" })
	public String deleteBook(@PathVariable("id") Long id) {
		try {

			BookRest book = bookService.getBookById(id);
			bookService.deleteBookById(id);
			SimpleLog log = new SimpleLog(LogType.INFO, "POST /books/delete/" + id,
					"Book " + book.getName() + " deleted");
			producerService.send(log);
			return "Book " + book.getName() + " deleted";
		} catch (Exception e) {
			SimpleLog log = new SimpleLog(LogType.ERROR, "POST /books/delete/" + id,
					"Book with id " + id + " not found");
			producerService.send(log);
			return "Book with id " + id + " not found";
		}
	}

	@PutMapping(value = "/books/{id}")
	public String updateBook(@PathVariable("id") Long id, @RequestBody String newBookSpring) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);

			BookRest oldBook = bookService.getBookById(id);
			String oldName = oldBook.getName();
			BookRest newBook = mapper.readValue(newBookSpring, BookRest.class);
			bookService.updateBookById(id, newBook);
			SimpleLog log = new SimpleLog(LogType.INFO, "PUT /books/update/" + id,
					"Book " + oldName + " updated to " + newBook.getName());
			producerService.send(log);
			System.out.println("Book " + oldName + " updated to " + newBook.getName());
			return "Book " + oldName + " updated to " + newBook.getName();
		} catch (Exception e) {
			SimpleLog log = new SimpleLog(LogType.ERROR, "PUT /books/update/" + id,
					"Book with id " + id + " not found");
			producerService.send(log);
			return "Book with id " + id + " not found";
		}
	}

	@PostMapping(value = "/books/find/{searchTerm}")
	public List<BookRest> findBook(@PathVariable("searchTerm") String searchTerm) {
		try {
			List<BookRest> books = bookService.findBook(searchTerm);
			SimpleLog log = new SimpleLog(LogType.INFO, "POST /books/find/" + searchTerm,
					"Books found with search term " + searchTerm);
			producerService.send(log);
			return books;
		} catch (Exception e) {
			SimpleLog log = new SimpleLog(LogType.ERROR, "POST /books/find/" + searchTerm,
					"Books not found with search term " + searchTerm);
			producerService.send(log);
			return null;
		}
	}
}