package manu.openlibrary.view.results;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import manu.openlibrary.R;
import manu.openlibrary.model.data.Book;

public class SearchResultsActivity extends AppCompatActivity {

    public static ArrayList<Book> books = new ArrayList<>();

    private ListView list;

    private BooksListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        setTitle("Search Results");

        list = findViewById(R.id.list);

        adapter = new BooksListAdapter(this, books);
        list.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        books.clear();
        super.onBackPressed();
    }
}
