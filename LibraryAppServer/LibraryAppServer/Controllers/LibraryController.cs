using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;

namespace LibraryAppServer.Controllers
{
    [Route("api/library")]
    [ApiController]
    public class LibraryController : ControllerBase
    {
        // in memory storage until swap to cloud storage
        private static List<Book> books = new List<Book>()
        {
                new Book("Book A","description aaaaaa",4.7,24),
                new Book("Book B","description bbbbbb",3.5,45),
                new Book("Book C","description cccccc",4.9,12),
                new Book("Book D","description dddddd",2.8,37),
        };

        
        [HttpGet("all")]
        public IEnumerable<Book> GetAllBooks()
        {
            return books;                  
        }

        [HttpGet("title/{title:aplha")]
        [ProducesResponseType(404)]
        [ProducesResponseType(200)]
        public ActionResult<Book> GetBookByTitle(string title)
        {
            var match = books.Where(b => (b.Title == title.ToUpper()));
            if (match.Count() != 0)
            {
                return Ok(match);
            }
            else
            {
                return NotFound();
            }
        }
    }
}
