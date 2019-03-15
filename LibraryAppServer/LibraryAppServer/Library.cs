using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace LibraryAppServer
{
    public class Book
    {
        public string Title { get; set; }
        public string Description { get; set; }
        public double Rating { get; set; }
        public int Stock { get; set; }

        public Book(string title,string desctiption, double rating,int stock)
        {
            this.Title = title;
            this.Description = desctiption;
            this.Rating = rating;
            this.Stock = stock;
        }
    }
}
