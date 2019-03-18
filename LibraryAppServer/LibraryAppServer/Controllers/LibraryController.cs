using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;

namespace LibraryAppServer.Controllers
{
    [Produces("application/json")]
    [Route("api/library")]
    [ApiController]
    public class LibraryController : ControllerBase
    {
        // in memory storage until swap to cloud storage
        private static List<Book> books = new List<Book>()
        {
            new Book("Book A","Joe","description aaaaaa",4.7,24),
            new Book("Book B","Mary","description bbbbbb",3.5,45),
            new Book("Book C","Bob","description cccccc",4.9,12),
            new Book("Book D","Anne","description dddddd",2.8,37),
            new Book("Book AA","Anne","description double",3.1,9)
        };

        
        [HttpGet("all")]
        public IEnumerable<Book> GetAllBooks()
        {
            return books;                  
        }

        // get all matching books with matching title - should only be one
        // alpha not working
        [HttpGet("title/{title}")]
        [ProducesResponseType(404)]
        [ProducesResponseType(200)]
        public ActionResult<IEnumerable<Book>> GetBookByTitle(string title)
        {
            // put all to upper case
            var match = books.Where(p => (p.Title == title));
            if (match.Count() != 0)
            {
                return Ok(match);
            }
            else
            {
                return NotFound();
            }
        }

        [HttpGet("booksOrderedByRating")]
        public IEnumerable<Book> GetBooksInOrderOfRating()
        {
            return books.OrderByDescending(book => book.Rating);   
        }

        [HttpGet("booksOrderedByTitle")]
        public IEnumerable<Book> GetBooksInOrderOfTitle()
        {
            return books.OrderBy(book => book.Title);
        }

        [HttpGet("booksOrderedByAuthor")]
        public IEnumerable<Book> GetBooksInOrderOfAuthor()
        {
            return books.OrderBy(book => book.Author);
        }

        [HttpGet("author/{author}")]
        [ProducesResponseType(404)]
        [ProducesResponseType(200)]
        public ActionResult<IEnumerable<Book>> GetAuthor(string author)
        {
            // put all to upper case
            var matches = books.Where(p => (p.Author == author));
            if (matches.Count() != 0)
            {
                return Ok(matches.OrderBy(book => book.Title));
            }
            else
            {
                return NotFound();
            }
        }
    }
}
