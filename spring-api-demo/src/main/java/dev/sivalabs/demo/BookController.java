package dev.sivalabs.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
class BookController {
    private final BookService bookService;

    BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    Books getAll() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{isbn}")
    ResponseEntity<Book> getByIsbn(@PathVariable String isbn) {
        return bookService.getBookByIsbn(isbn)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // This is the current search endpoint that only searches by title
    @GetMapping(value = "/search")
    Books searchBooks(@RequestParam("q") String query) {
        return bookService.searchByTitle(query);
    }

    // This is the new search endpoint that searches by isbn, title, and author
    // @GetMapping(value = "/search")
    Books searchBooksV2(@RequestParam("q") String query) {
        return bookService.searchBooks(query);
    }
}
