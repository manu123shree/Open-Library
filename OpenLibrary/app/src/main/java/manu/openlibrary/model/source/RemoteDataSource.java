package manu.openlibrary.model.source;

import java.util.ArrayList;

import manu.openlibrary.model.data.Book;

public class RemoteDataSource implements OnBooksDownloadedListener {

    private DataSourceListener listener;
    private BooksDownloader downloader;

    public RemoteDataSource(DataSourceListener listener) {
        this.listener = listener;
        this.downloader = new BooksDownloader(this);
    }

    public void searchBooks(String keyword) {
        downloader.execute(keyword);
    }

    @Override
    public void onBooksDownloaded(ArrayList<Book> books) {
        if(books != null)
            listener.onBooksDownloaded(books);
        else
            listener.onDownloadFailed();
    }
}
