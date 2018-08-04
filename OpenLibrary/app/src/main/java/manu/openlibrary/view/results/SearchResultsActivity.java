package manu.openlibrary.view.results;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import manu.openlibrary.R;
import manu.openlibrary.model.data.Book;
import manu.openlibrary.view.details.SearchDetailsActivity;

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

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SearchDetailsActivity.book = books.get(i);
                Intent intent = new Intent(getApplicationContext(), SearchDetailsActivity.class);
                startActivity(intent);

            }
        } );
    }

    @Override
    public void onBackPressed() {
        books.clear();
        super.onBackPressed();
    }
}
