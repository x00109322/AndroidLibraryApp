using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using LibraryAppServer;

namespace LibraryAppServer.Controllers
{
    [Produces("application/json")]
    [Route("api/books")]
    [ApiController]
    public class LibraryController : ControllerBase
    {
        private readonly BooksContext _context;

        public LibraryController(BooksContext context)
        {
            _context = context;
        }

        // GET api/books/all
        [HttpGet("all")]
        public IEnumerable<Book> GetAllBooks()
        {
            var entries = _context.Book.OrderBy(book => book.ISBN);
            return entries;
        }

        // Update stock of book when checked out
        [HttpPatch("checkoutBook/{isbn}")]
        public IActionResult UpdateStock(string isbn,[FromBody]Book bookIn)
        {
            Book book = _context.Book.FirstOrDefault(b => b.ISBN  == isbn);
            int oldStock = book.Stock;
            int newStock = oldStock - 1;
            book.Stock = newStock;
            _context.Update(book);
            return Ok(book);
        }

        // get all matching books with matching title - should only be one

        // GET api/books/title/
        [HttpGet("title/{title}")]
        [ProducesResponseType(404)]
        [ProducesResponseType(200)]
        public IActionResult GetBookByTitle(string title)
        {
            //Linq query to find books by title, put all to upper case
            var match = _context.Book.FirstOrDefault(p => (p.Title.ToUpper() == title.ToUpper()));
            if (match == null)
            {
                return NotFound();
            }
            return Ok(match);
        }

        //GET api/books/rating/
        [HttpGet("booksOrderedByRating")]
        public IEnumerable<Book> GetBooksInOrderOfRating()
        {
            return _context.Book.OrderByDescending(book => book.Rating);
        }


        //GET api/books/genre/
        [HttpGet("genre/{genre}")]
        public IActionResult GetBookByGenre(String genre)
        {
            // LINQ query, find matching entries for genre
            var entries = _context.Book.Where(r => r.Genre.ToUpper() == genre.ToUpper());
            if (entries == null)
            {
                return NotFound();
            }
            return Ok(entries);
        }

        [HttpGet("booksOrderedByTitle")]
        public IEnumerable<Book> GetBooksInOrderOfTitle()
        {
            return _context.Book.OrderBy(book => book.Title);
        }

        [HttpGet("booksOrderedByAuthor")]
        public IEnumerable<Book> GetBooksInOrderOfAuthor()
        {
            return _context.Book.OrderBy(book => book.Author);
        }

        //GET api/books/author/
        [HttpGet("author/{author}")]
        [ProducesResponseType(404)]
        [ProducesResponseType(200)]
        public ActionResult<IEnumerable<Book>> GetAuthor(string author)
        {
            // put all to upper case
            var matches = _context.Book.Where(p => (p.Author == author));
            if (matches == null)
            {
                return NotFound();
            }
            return Ok(matches.OrderBy(book => book.Title));

        }


        [HttpGet("rating/{rating:int}")]
        public IActionResult GetMovieByRating(int rating)
        {
            // LINQ query, find matching entries for num of stars
            var entries = _context.Book.Where(r => r.Rating == rating);

            if (entries == null)
            {
                return NotFound();
            }
            return Ok(entries);
        }


        //test
        private bool MovieExists(string id)
        {
            return _context.Book.Any(e => e.Title == id);
        }


    }
}