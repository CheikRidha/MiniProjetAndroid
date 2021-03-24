package org.codeformauritania.devoire;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import org.codeformauritania.devoire.Controller.Books_Adapter;
import org.codeformauritania.devoire.Model.Book;

import java.util.ArrayList;
import java.util.List;

public class BooksActivity extends AppCompatActivity {
    private List<Book> bookList = new ArrayList<>();
    private RecyclerView recyclerView;
    private Books_Adapter books_adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);
        initViews();
        booksData();
    }

    private void booksData() {

        Book book1 = new Book("Biologie Moleculaire","2000 mru",R.drawable.bio2);
        bookList.add(book1);
        Book book2 = new Book("L'autodidact En Informatique","8000 mru",R.drawable.info1);
        bookList.add(book2);
        Book book3 = new Book("Reseaux informatique","6000 mru",R.drawable.info2);
        bookList.add(book3);
        Book book4 = new Book("Les Maths pour Les nules","7000 mru",R.drawable.math1);
        bookList.add(book4);
        Book book5 = new Book("Notre Univers Mathematique","12000 mru",R.drawable.math2);
        bookList.add(book5);
        Book book6 = new Book("La Physique Quantique","5000 mru",R.drawable.phy2);
        bookList.add(book6);

        books_adapter.notifyDataSetChanged();
    }

    private void initViews() {
        recyclerView = findViewById(R.id.my_Layout);
        books_adapter = new Books_Adapter(bookList,this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(books_adapter);
    }


}