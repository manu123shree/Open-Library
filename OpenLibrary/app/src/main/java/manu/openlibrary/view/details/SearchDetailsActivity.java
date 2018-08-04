package manu.openlibrary.view.details;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import manu.openlibrary.R;
import manu.openlibrary.model.data.Book;

public class SearchDetailsActivity extends AppCompatActivity {

    public static Book book = null;

    private TextView textView;
    private TextView tvBook;
    private TextView tvAuthor;
    private TextView tvIsbn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_details_activity);

        setTitle("Search Results");
        textView = findViewById(R.id.tvSearchDetails);
        tvBook = findViewById(R.id.tvBookName);
        tvAuthor = findViewById(R.id.tvSearchAuthor);
        tvIsbn = findViewById(R.id.tvSearchIsbn);

       tvBook.setText(book.title);
       tvAuthor.setText(book.getAuthoNamesString());
       tvIsbn.setText(book.getIsbnString());

    }
}
