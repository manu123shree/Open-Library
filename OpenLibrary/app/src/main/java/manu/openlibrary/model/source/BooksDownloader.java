package manu.openlibrary.model.source;

import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import manu.openlibrary.model.data.Book;

public class BooksDownloader extends AsyncTask<String, Void, ArrayList<Book>> {

    private OnBooksDownloadedListener listener;

    public BooksDownloader(OnBooksDownloadedListener listener) {
        this.listener = listener;
    }

    @Override
    protected ArrayList<Book> doInBackground(String... strings) {
        try {
            String keyword = strings[0];
            keyword = URLEncoder.encode(keyword, "UTF-8");
            URL url = new URL("http://openlibrary.org/search.json?q="+keyword);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            if(connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader inputStream = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                byte[] buffer = new byte[1024];
                String line;
                StringBuilder sb = new StringBuilder();
                while((line = inputStream.readLine()) != null) {
                    sb.append(line);
                }
                String json = sb.toString();
                return BooksParser.parseFromJSON(json);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<Book> books){
        listener.onBooksDownloaded(books);
    }

}
