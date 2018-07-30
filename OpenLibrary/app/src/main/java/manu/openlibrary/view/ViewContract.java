package manu.openlibrary.view;

import java.util.List;

public interface ViewContract {

    void onSearchStarted();
    void onSearchResultsReceived(List<String> results);
    void onSearchFailed();

}
