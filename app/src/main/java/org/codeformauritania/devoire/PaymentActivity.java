package org.codeformauritania.devoire;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PaymentActivity extends AppCompatActivity implements View.OnClickListener {
Button btnBraintree,btnStripe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        initViews();
        setListeners();
    }

    void initViews(){
btnBraintree = findViewById(R.id.braintree);
btnStripe = findViewById(R.id.stripe);

    }
    private void setListeners(){
        btnStripe.setOnClickListener(this);
        btnBraintree.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
    switch (v.getId()){
        case R.id.braintree:
            Intent intent1 = new Intent(getBaseContext(),BraintreeActivity.class);
            startActivity(intent1);
            break;
        case  R.id.stripe:
            Intent intent2 = new Intent(getBaseContext(),CheckoutActivityStripe.class);
            startActivity(intent2);
            break;

    }
    }
}