package manu.openlibrary.model.source;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import manu.openlibrary.model.data.Author;
import manu.openlibrary.model.data.Book;

public class BooksParser {

    public static ArrayList<Book> parseFromJSON(String json) {
        ArrayList<Book> books = new ArrayList<>();
        try{
            JSONObject jsonObject = new JSONObject(json);
            JSONArray docsArray = jsonObject.getJSONArray("docs");
            int count = docsArray.length();
            for(int i=0; i<count; i++) {
                JSONObject obj = docsArray.getJSONObject(i);
                String title = obj.optString("title", "");
                String cover_image_id = obj.optString("cover_i", "");

                ArrayList<String> isbns = new ArrayList<>();
                if(obj.has("isbn")) {
                    JSONArray isbnArray = obj.getJSONArray("isbn");
                    int isbn_count = isbnArray.length();
                    for (int x = 0; x < isbn_count; x++) {
                        isbns.add(isbnArray.getString(x));
                    }
                }

                ArrayList<Author> authors = new ArrayList<>();
                if(obj.has("author_name") && obj.has("author_key")) {
                    JSONArray authorNames = obj.getJSONArray("author_name");
                    JSONArray authorKeys = obj.getJSONArray("author_key");
                    if(authorNames.length() == authorKeys.length()) {
                        int authorsCount = authorKeys.length();
                        for (int x = 0; x < authorsCount; x++) {
                            String name = authorNames.getString(x);
                            String image_id = authorKeys.getString(x);
                            authors.add(new Author(name, image_id));
                        }
                    }
                }

                books.add(new Book(title, cover_image_id, isbns, authors));
            }
        }catch (Throwable ex) {
            ex.printStackTrace();
        }
        return books;
    }

}
