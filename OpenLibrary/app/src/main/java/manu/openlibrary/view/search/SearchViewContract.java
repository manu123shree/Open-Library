package manu.openlibrary.view.search;

import java.util.ArrayList;

import manu.openlibrary.model.data.Book;

public interface SearchViewContract {

    void onSearchStarted();
    void onSearchResultsReceived(ArrayList<Book> books);
    void onSearchFailed();

}
