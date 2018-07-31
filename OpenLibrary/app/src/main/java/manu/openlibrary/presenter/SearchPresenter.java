package manu.openlibrary.presenter;

import java.util.ArrayList;

import manu.openlibrary.model.data.Book;
import manu.openlibrary.model.source.DataSourceListener;
import manu.openlibrary.model.source.RemoteDataSource;
import manu.openlibrary.view.search.SearchViewContract;

public class SearchPresenter implements PresenterContract, DataSourceListener {

    private SearchViewContract searchView;
    private RemoteDataSource remoteDataSource;

    public SearchPresenter(SearchViewContract searchView) {
        this.searchView = searchView;
        this.remoteDataSource = new RemoteDataSource(this);
    }

    @Override
    public void performSearch(String keyword) {
        if(remoteDataSource!= null && searchView != null) {
            remoteDataSource.searchBooks(keyword);
            searchView.onSearchStarted();
        }
    }

    @Override
    public void onBooksDownloaded(ArrayList<Book> books){
        if(searchView != null) {
            searchView.onSearchResultsReceived(books);
        }
    }

    @Override
    public void onDownloadFailed() {
        if(searchView != null) {
            searchView.onSearchFailed();
        }
    }

    @Override
    public void destroy() {
        remoteDataSource = null;
        searchView = null;
    }
}
