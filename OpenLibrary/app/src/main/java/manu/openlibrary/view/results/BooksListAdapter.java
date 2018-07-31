package manu.openlibrary.view.results;

import android.content.Context;
import android.database.DataSetObserver;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import manu.openlibrary.R;
import manu.openlibrary.model.data.Book;

public class BooksListAdapter extends ArrayAdapter<Book> {

    private ArrayList<Book> books;

    public BooksListAdapter(@NonNull Context context, ArrayList<Book> books) {
        super(context, 0);
        this.books = books;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }

    @Override
    public int getCount() {
        return books.size();
    }

    @Override
    public Book getItem(int position) {
        return books.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @NonNull
    @Override
    public View getView(int position, View view, @NonNull ViewGroup viewGroup) {
        View v = View.inflate(getContext(), R.layout.book_list_item, null);

        TextView tvTitle = v.findViewById(R.id.tvTitle);
        TextView tvAuthors = v.findViewById(R.id.tvAuthors);

        tvTitle.setText(books.get(position).title);
        tvAuthors.setText(books.get(position).getAuthoNamesString());

        return v;
    }

    @Override
    public int getItemViewType(int i) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return books.size() == 0;
    }
}
