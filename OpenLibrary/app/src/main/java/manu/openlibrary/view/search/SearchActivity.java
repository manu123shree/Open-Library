package manu.openlibrary.view.search;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import manu.openlibrary.R;
import manu.openlibrary.model.data.Book;
import manu.openlibrary.presenter.PresenterContract;
import manu.openlibrary.presenter.SearchPresenter;
import manu.openlibrary.view.results.SearchResultsActivity;

public class SearchActivity extends AppCompatActivity implements SearchViewContract {

    private PresenterContract presenter;

    private EditText etSearch;
    private Button btnSearch;
    private RelativeLayout rlProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        setTitle("Search Books");

        presenter = new SearchPresenter(this);

        etSearch = findViewById(R.id.etSearch);
        btnSearch = findViewById(R.id.btnSearch);
        rlProgress = findViewById(R.id.rlProgress);

        btnSearch.setTransformationMethod(null);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performSearch();
            }
        });

        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int keyCode, KeyEvent keyEvent) {
                if(keyCode == EditorInfo.IME_ACTION_SEARCH) {
                    performSearch();
                }
                return false;
            }
        });
    }

    private void performSearch() {
        String keyword = etSearch.getText().toString();
        if(keyword.length() > 0) {
            etSearch.setError(null);
            presenter.performSearch(keyword);
        } else {
            etSearch.setError("Please Enter a Keyword to Search");
        }
    }

    @Override
    public void onSearchStarted() {
        rlProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void onSearchResultsReceived(ArrayList<Book> books) {
        rlProgress.setVisibility(View.GONE);

        SearchResultsActivity.books = books;
        Intent intent = new Intent(getApplicationContext(), SearchResultsActivity.class);
        startActivity(intent);
    }

    @Override
    public void onSearchFailed() {
        rlProgress.setVisibility(View.GONE);
        Toast.makeText(SearchActivity.this, "Failed to Search", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        presenter.destroy();
        super.onDestroy();
    }
}

