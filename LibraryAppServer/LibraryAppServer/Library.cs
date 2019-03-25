using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace LibraryAppServer
{
    public class Book
    {
        [Key]
        public string ISBN { get; set; }            //Unique key for international standard book number
        [Required]
        public string Title { get; set; }
        [Required]
        public string Author { get; set; }
        [Required]
        public string Genre { get; set; }
        public string Description { get; set; }
        public double Rating { get; set; }
        public int Stock { get; set; }

        

        
    }
}
