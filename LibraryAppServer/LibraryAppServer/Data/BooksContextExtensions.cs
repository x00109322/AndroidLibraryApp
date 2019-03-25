using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace LibraryAppServer.Data
{
    public static class BooksContextExtensions
    {
        public static void EnsureDatabaseSeeded(this BooksContext context)
        {
            if (!context.Books.Any())
            {
                context.AddRange(new Book[] {
                    new Book()
                    {
                        ISBN = "000123456",
                        Title = "Harry Potter and The Deathly Hallows",
                        Author = "J.K. Rowling",
                        Genre = "Fantasy Ficton",
                        Description = "Without the guidance and protection of their professors, Harry (Daniel Radcliffe), Ron (Rupert Grint) and Hermione (Emma Watson) begin a mission to destroy the Horcruxes, the sources of Voldemort's immortality. Though they must rely on one another more than ever, dark forces threaten to tear them apart. Voldemort's Death Eaters have seized control of the Ministry of Magic and Hogwarts, and they are searching for Harry -- even as he and his friends prepare for the ultimate showdown.",
                        Rating = 4.7,
                        Stock = 22
                    },
                    new Book()
                    {
                        ISBN = "000123457",
                        Title = "The Help",
                        Author = "Kathryn Stockett",
                        Genre = "Novel",
                        Description = "In Jackson, Mississippi, in the 60's, the aspirant writer Skeeter Phelan has just graduated and returns home after finding a job writing in a futile newspaper column in the local newspaper. When she arrives home, she finds that her nanny and family's maid Constantine Jefferson is gone. Skeeter sees the chance of writing a book about the relationship of the black maids with the Southern society for an editor from New York. First she convinces Aibileen Clark to open her heart to her; then Minny Jackson is unfairly fired by the arrogant Hilly Holbrook, who is a leader in the racist high society, and Minny decides to tell her stories after finding a job with the outcast Celia Foote. Soon eleven other maids accept to be interviewed by Skeeter that also tells the truth about Constantine. When the book 'The Help' is released, Jackson's high society will never be the same.",
                        Rating = 4.4,
                        Stock = 41

                    },
                    new Book()
                    {
                        ISBN = "000123458",
                        Title = "The Green Mile",
                        Author = "Stephen King",
                        Genre = "Dark Fantasy",
                        Description = "The protagonist and narrator of the book and the death-row supervisor at Cold Mountain Penitentiary. He is 40 years old when the main bulk of the story takes place, in 1932. He is a caring man and takes excellent care of the men on his block, avoiding conflict and keeping the peace whenever possible.",
                        Rating = 4.2,
                        Stock = 35
                    },
                    new Book()
                    {
                        ISBN = "000123459",
                        Title = "The Martian",
                        Author = "Andy Weir",
                        Genre = "Novel",
                        Description = " An astronaut becomes stranded on Mars after his team assume him dead, and must rely on his ingenuity to find a way to signal to Earth that he is alive. During a manned mission to Mars, Astronaut Mark Watney is presumed dead after a fierce storm and left behind by his crew.",
                        Rating = 3.9,
                        Stock = 45
                    },
                    new Book()
                    {
                        ISBN = "000123460",
                        Title = "No Country for Old Men",
                        Author = "Cormac McCarthy",
                        Genre = "Adventure",
                        Description = "The plot follows the interweaving paths of the three central characters (Llewelyn Moss, Anton Chigurh, and Ed Tom Bell) set in motion by events related to a drug deal gone bad near the Mexican–American border in remote Terrell County in southwest Texas.",
                        Rating = 4.1,
                        Stock = 27
                    },
                    new Book()
                    {
                        ISBN = "000123461",
                        Title = "Oh My God, What a Complete Aisling",
                        Author = "Emer McLysaght and Sarah Breen",
                        Genre = "Humour",
                        Description = "What a complete Aisling can be considered a late coming of age novel. 28 years old and in love with her boyfriend John, Aisling is looking for the wedding ring. When John fails to deliver after 7 years of dating, Aisling breaks up with him and tries to move her life on. But naturally moving on is not so simple, what with her dad being ill and all. New friendships, new relationships, new adventures all combine together in a humorous and engaging novel.",
                        Rating = 4.3,
                        Stock = 18
                    },
                    new Book()
                    {
                        ISBN = "000123462",
                        Title = "Becoming",
                        Author = "Michelle Obama",
                        Genre = "Autobiography",
                        Description = "The book's 24 chapters (plus a preface and epilogue) are divided into three sections: Becoming Me, Becoming Us, and Becoming More. Become Me traces Obama's early life growing up on the South Side of Chicago, through her education at Princeton University and Harvard Law School, to her early career as a lawyer at the law firm Sidley Austin, where she met Barack Obama. Becoming Us departs from the beginning of their romantic relationship and follows their marriage, the beginning of his political career in the Illinois State Senate. The section ends with election night in 2008 when Barack Obama was elected President of the United States and Becoming More describes their life as First Family.",
                        Rating = 3.9,
                        Stock = 22
                    },
                    new Book()
                    {
                        ISBN = "000123463",
                        Title = "The Girl on The Train",
                        Author = "Paula Hawkins",
                        Genre = "Thriller",
                        Description = "Commuter Rachel Watson catches daily glimpses of a seemingly perfect couple, Scott and Megan, from the window of her train. One day, Watson witnesses something shocking unfold in the backyard of the strangers' home. Rachel tells the authorities what she thinks she saw after learning that Megan is now missing and feared dead. Unable to trust her own memory, the troubled woman begins her own investigation, while police suspect that Rachel may have crossed a dangerous line.",
                        Rating = 3.7,
                        Stock = 29
                    },
                    new Book()
                    {
                        ISBN = "000123464",
                        Title = "Alan Turing: The Enigma",
                        Author = " Andrew Hodges",
                        Genre = "Biography",
                        Description = "Alan Turing: The Enigma is a biography of the British mathematician, codebreaker, and early computer scientist, Alan Turing by Andrew Hodges. The book covers Alan Turing's life and work.",
                        Rating = 3.5,
                        Stock = 12
                    },
                    new Book()
                    {
                        ISBN = "000123465",
                        Title = "Gone Girl",
                        Author = "Gillian Flynn",
                        Genre = "Thriller",
                        Description = "With his wife's disappearance having become the focus of an intense media circus, a man sees the spotlight turned on him when it's suspected that he may not be innocent. On the occasion of his fifth wedding anniversary, Nick Dunne reports that his wife, Amy, has gone missing.",
                        Rating = 4.4,
                        Stock = 20
                    }
                });
                context.SaveChanges();
            }
        }

    }
}
