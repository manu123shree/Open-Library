package manu.openlibrary.model.data;

import java.util.ArrayList;

public class Book {

    public String title;
    public String cover_image_id;
    public ArrayList<String> ISBNs;
    public ArrayList<Author> authors;

    public Book(String title, String cover_image_id, ArrayList<String> ISBNs, ArrayList<Author> authors) {
        this.title = title;
        this.cover_image_id = cover_image_id;
        this.ISBNs = ISBNs;
        this.authors = authors;
    }

    public String getAuthoNamesString() {
        StringBuilder names = new StringBuilder();
        for(Author author : authors) {
            if(names.length() > 0)
                names.append(", ");
            names.append(author.name);
        }
        return names.toString();
    }
    public String getIsbnString() {
        StringBuilder builder = new StringBuilder();
        for(String ISBN : ISBNs) {
             builder.append(ISBN);

        }
        return builder.toString();
    }
}
