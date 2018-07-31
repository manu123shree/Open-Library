package manu.openlibrary.model.source;

import java.util.ArrayList;

import manu.openlibrary.model.data.Book;

public interface DataSourceListener {

    void onBooksDownloaded(ArrayList<Book> books);
    void onDownloadFailed();

}
