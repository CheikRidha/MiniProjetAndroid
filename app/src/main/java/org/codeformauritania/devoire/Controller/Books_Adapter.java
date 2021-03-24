package org.codeformauritania.devoire.Controller;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import org.codeformauritania.devoire.BraintreeActivity;
import org.codeformauritania.devoire.MapsActivity;
import org.codeformauritania.devoire.Model.Book;
import org.codeformauritania.devoire.PaymentActivity;
import org.codeformauritania.devoire.R;

import java.util.List;

public class Books_Adapter extends  RecyclerView.Adapter<Books_Adapter.MyViewHolder>{

    private List<Book> bookList;
    private Context context;

    public Books_Adapter(List<Book> bookList, Context context) {
        this.bookList = bookList;
        this.context=context;

    }
//cree un view holder a apartir du layout
    @NonNull
    @Override
    public Books_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.book_list_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Books_Adapter.MyViewHolder holder, int position) {
        Book book =bookList.get(position);
        holder.title.setText(book.getTitle());
        holder.price.setText(( book.getPrice()));
        holder.image.setImageResource(book.getImage());
    }
//le nombre de lignes que peut contenir la RecycleView
    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public class MyViewHolder  extends  RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView title,price;
        private ImageView image;
        private Button button;
        private Dialog dialog;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            title =itemView.findViewById(R.id.mytitle);
            price =itemView.findViewById(R.id.price);
            image = itemView.findViewById(R.id.imageView);
            dialog = new Dialog(context);
//            dialog.setContentView(R.i);
        }

        @Override
        public void onClick(View v) {

            dialogPay();

        }
    }

    private void dialogPay() {

        AlertDialog alertDialog =new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Mode Payment");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Ligne", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent =new Intent(context, PaymentActivity.class);
                context.startActivity(intent);
            }
        });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Espece", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent =new Intent(context, MapsActivity.class);
              context.startActivity(intent);
            }
        });
        alertDialog.show();
    }

}
