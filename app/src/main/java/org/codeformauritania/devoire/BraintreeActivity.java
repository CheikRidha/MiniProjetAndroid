package org.codeformauritania.devoire;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.braintreepayments.api.dropin.DropInRequest;
import com.braintreepayments.api.dropin.DropInResult;
import com.braintreepayments.api.models.PaymentMethodNonce;

import org.codeformauritania.devoire.Model.BraintreeToken;
import org.codeformauritania.devoire.Model.BraintreeTransaction;
import org.codeformauritania.devoire.Retrofit.IBraintreeAPI;
import org.codeformauritania.devoire.Retrofit.RetrofitClient;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class BraintreeActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 1234;
    Button btn_pay;
    EditText edt_amount;
    LinearLayout group_waiting, group_payment;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    IBraintreeAPI myApi;
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_braintree);


        myApi = RetrofitClient.getInstance().create(IBraintreeAPI.class);
        group_payment = (LinearLayout) findViewById(R.id.payment_group);
        group_waiting = (LinearLayout) findViewById(R.id.waiting_group);
        btn_pay = (Button) findViewById(R.id.btn_pay);
        edt_amount = (EditText) findViewById(R.id.edt_amount);


        btn_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitPayment();
            }
        });
        compositeDisposable.add(myApi.getToken().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BraintreeToken>() {
                    @Override
                    public void accept(BraintreeToken braintreeToken) throws Exception {
                        if (braintreeToken.isSuccess()) {
                            group_waiting.setVisibility(View.INVISIBLE);
                            group_payment.setVisibility(View.VISIBLE);
                            token = braintreeToken.getClientToken();
                        }
                    }
                }, new Consumer<Throwable>() {

                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(BraintreeActivity.this, "22222" + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }));


    }


    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }

    private void submitPayment() {
        DropInRequest dropInRequest=new DropInRequest().clientToken(token);

        startActivityForResult(dropInRequest.getIntent(this),REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE){
            if(resultCode==RESULT_OK){
                DropInResult result=data.getParcelableExtra(DropInResult.EXTRA_DROP_IN_RESULT);
                PaymentMethodNonce nonce = result.getPaymentMethodNonce();

                if(!TextUtils.isEmpty(edt_amount.getText().toString())){
                    String amount=edt_amount.getText().toString();
                    compositeDisposable.add(myApi.submitPayment(amount,nonce.getNonce())
                            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<BraintreeTransaction>() {
                                @Override
                                public void accept(BraintreeTransaction braintreeTransaction) throws Exception {
                                    if(braintreeTransaction.isSuccess()){
                                        Toast.makeText(BraintreeActivity.this,"braintreeTransaction.getTransaction().getId()",Toast.LENGTH_SHORT);
                                    }
                                    else{
                                        Toast.makeText(BraintreeActivity.this,"Transaction Failed",Toast.LENGTH_SHORT);
                                    }
                                }
                            }, new Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable throwable) throws Exception {
                                    Toast.makeText(BraintreeActivity.this,"1111111"+throwable.getMessage(),Toast.LENGTH_SHORT);
                                }
                            }));

                }
            }
        }

    }

}