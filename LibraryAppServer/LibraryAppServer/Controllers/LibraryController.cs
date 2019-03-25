using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using LibraryAppServer.Data;

namespace LibraryAppServer.Controllers
{
    [Produces("application/json")]
    [Route("api/book")]
    [ApiController]
    public class LibraryController : ControllerBase
    {
        private readonly BooksContext _context;

        public LibraryController(BooksContext context)
        {
            _context = context;
        }

        // GET api/movies/all
        [HttpGet("all")]
        public IEnumerable<Book> GetAllBooks()
        {
            var entries = _context.Books.OrderBy(book => book.ISBN);
            return entries;
        }
        

        // get all matching books with matching title - should only be one

        // GET api/books/title/
        [HttpGet("title/{title}")]
        [ProducesResponseType(404)]
        [ProducesResponseType(200)]
        public IActionResult GetBookByTitle(string title)
        {
            //Linq query to find books by title, put all to upper case
            var match = _context.Books.FirstOrDefault(p => (p.Title.ToUpper() == title.ToUpper()));
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
            return _context.Books.OrderByDescending(book => book.Rating);   
        }


        //GET api/books/genre/
        [HttpGet("genre/{genre}")]
        public IActionResult GetBookByGenre(String genre)
        {
            // LINQ query, find matching entries for genre
            var entries = _context.Books.Where(r => r.Genre.ToUpper() == genre.ToUpper());
            if (entries == null)
            {
                return NotFound();
            }
            return Ok(entries);
        }

        [HttpGet("booksOrderedByTitle")]
        public IEnumerable<Book> GetBooksInOrderOfTitle()
        {
            return _context.Books.OrderBy(book => book.Title);
        }

        [HttpGet("booksOrderedByAuthor")]
        public IEnumerable<Book> GetBooksInOrderOfAuthor()
        {
            return _context.Books.OrderBy(book => book.Author);
        }

        //GET api/books/author/
        [HttpGet("author/{author}")]
        [ProducesResponseType(404)]
        [ProducesResponseType(200)]
        public ActionResult<IEnumerable<Book>> GetAuthor(string author)
        {
            // put all to upper case
            var matches = _context.Books.Where(p => (p.Author == author));
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
            var entries = _context.Books.Where(r => r.Rating == rating);

            if (entries == null)
            {
                return NotFound();
            }
            return Ok(entries);
        }
        

        //test
        private bool MovieExists(string id)
        {
            return _context.Books.Any(e => e.Title == id);
        }
    }
}
