package com.libraryapp.libraryappclient;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private LibraryApi libraryApi;
    private RecyclerView bookRecyclerView;
    private BookAdapter bookAdapter;
    private RecyclerView.LayoutManager bookLayoutManager;
    private ArrayList<Book> bookList = new ArrayList<>();
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://libraryappserver2019.azurewebsites.net/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createBookList();

    }

    public void createBookList() {

        libraryApi = retrofit.create(LibraryApi.class);
        Call<List<Book>> call = libraryApi.getBooks();
        call.enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                if (!response.isSuccessful()) {
                    return;
                }

                List<Book> booksToCast = response.body();
                bookList = (ArrayList<Book>) booksToCast;
                buildRecyclerView();
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                return;
            }
        });
    }

    public void buildRecyclerView() {
        bookRecyclerView = findViewById(R.id.bookRecyclerView);
        bookRecyclerView.setHasFixedSize(true);
        bookLayoutManager = new LinearLayoutManager(this);
        bookAdapter = new BookAdapter(bookList);

        bookRecyclerView.setLayoutManager(bookLayoutManager);
        bookRecyclerView.setAdapter(bookAdapter);
        bookAdapter.setOnItemClickListener(new BookAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                openSingleBookView(position);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.book_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

//        for(int i=0; i < bookList.size(); i++) {
//            menu.add(R.id.FilterByGenre,(i+1),Menu.NONE,bookList.get(i).getGenre());
//        }

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                bookAdapter.getFilter().filter(s);
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {
            // Order book by
            case R.id.orderByTitle:
                getBooksOrderedBy("Title");
                return true;
            case R.id.orderByAuthor:
                getBooksOrderedBy("Author");
                return true;
            case R.id.orderByGenre:
                getBooksOrderedBy("Genre");
                return true;
            case R.id.orderByRating:
                getBooksOrderedBy("Rating");
                return true;
            // Filter books by Genre
            case R.id.filterByNovel:
                getBooksFilteredByGenre("Novel");
                return true;
            case R.id.filterByAdventure:
                getBooksFilteredByGenre("Adventure");
                return true;
            case R.id.filterByAutobiography:
                getBooksFilteredByGenre("AutoBiography");
                return true;
            case R.id.filterByBiography:
                getBooksFilteredByGenre("Biography");
                return true;
            case R.id.filterByDarkFantasy:
                getBooksFilteredByGenre("Dark Fantasy");
                return true;
            case R.id.filterByHumour:
                getBooksFilteredByGenre("Humour");
                return true;
            case R.id.filterByTriller:
                getBooksFilteredByGenre("Thriller");
                return true;
            case R.id.filterByFantasyFiction:
                getBooksFilteredByGenre("Fantasy Ficton");
                return true;
            // Filter books by Author
            case R.id.filterByAndrewHodges:
                getBooksFilteredByAuthor(" Andrew Hodges");
            case R.id.filterByAndyWeir:
                getBooksFilteredByAuthor("Andy Weir");
            case R.id.filterByCormacMcCarthy:
                getBooksFilteredByAuthor("Cormac McCarthy");
            case R.id.filterByEmerMcLysaghtandSarahBreen:
                getBooksFilteredByAuthor("Emer McLysaght and Sarah Breen");
            case R.id.filterByGillianFlynn:
                getBooksFilteredByAuthor("Gillian Flynn");
            case R.id.filterByJ_K_Rowling:
                getBooksFilteredByAuthor("J.K. Rowling");
            case R.id.filterByKathrynStockett:
                getBooksFilteredByAuthor("Kathryn Stockett");
            case R.id.filterByMichelleObama:
                getBooksFilteredByAuthor("Michelle Obama");
            case R.id.filterByPaulaHawkins:
                getBooksFilteredByAuthor("Paula Hawkins");
            case R.id.filterByStephenKing:
                getBooksFilteredByAuthor("Stephen King");
            case R.id.languageEnglish:
                Locale localeEnglish = new Locale("en");
                Locale.setDefault(localeEnglish);
                Configuration config = new Configuration();
                config.locale = localeEnglish;
                getBaseContext().getResources().updateConfiguration(config,getBaseContext().getResources().getDisplayMetrics());
            case R.id.languageIrish:
                Locale localeIrish = new Locale("ga_IE");
                Locale.setDefault(localeIrish);
                Configuration configuration = new Configuration();
                configuration.locale = localeIrish;
                getBaseContext().getResources().updateConfiguration(configuration,getBaseContext().getResources().getDisplayMetrics());
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void getBooksOrderedBy(String selectedOrder) {

        libraryApi = retrofit.create(LibraryApi.class);
        Call<List<Book>> call = libraryApi.getBooksOrderedByTitle();

        switch (selectedOrder) {
            case "Title":
                call = libraryApi.getBooksOrderedByTitle();
                break;
            case "Author":
                call = libraryApi.getBooksOrderedByAuthor();
                break;
            case "Genre":
                call = libraryApi.getBooksOrderedByGenre();
                break;
            case "Rating":
                call = libraryApi.getBooksOrderedByRating();
                break;
        }
        call.enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                if (!response.isSuccessful()) {
                    return;
                }

                List<Book> booksToCast = response.body();
                bookList = (ArrayList<Book>) booksToCast;
                buildRecyclerView();
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                return;
            }
        });
    }

    public void getBooksFilteredByGenre(String selectedOrder) {

        libraryApi = retrofit.create(LibraryApi.class);
        Call<List<Book>> call = libraryApi.getBooksByGenre(selectedOrder);
        call.enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                if (!response.isSuccessful()) {
                    return;
                }

                List<Book> booksToCast = response.body();
                bookList = (ArrayList<Book>) booksToCast;
                buildRecyclerView();
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                return;
            }
        });
    }

    public void getBooksFilteredByAuthor(String selectedOrder) {

        libraryApi = retrofit.create(LibraryApi.class);
        Call<List<Book>> call = libraryApi.getBooksByAuthor(selectedOrder);

        call.enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                if (!response.isSuccessful()) {
                    return;
                }

                List<Book> booksToCast = response.body();
                bookList = (ArrayList<Book>) booksToCast;
                buildRecyclerView();
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                return;
            }
        });
    }

    public void openSingleBookView(int position) {
        Book book = bookList.get(position);
        Intent intent = new Intent(this, SingleBookPage.class);
        intent.putExtra("title", book.getTitle());
        intent.putExtra("author", book.getAuthor());
        intent.putExtra("genre", book.getGenre());
        intent.putExtra("isbn", book.getIsbn());
        intent.putExtra("book_cover_url", book.getBookCover());
        intent.putExtra("rating", book.getRating());
        intent.putExtra("stock", book.getStock());
        intent.putExtra("description", book.getDescription());

        startActivity(intent);
    }
}

