package com.example.android.musicshop;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class orderActivity extends AppCompatActivity {

    String[] addresses = {"denisignatjevs@live.com"};
    String subject = "Order from Music Shop";
    String emailText;
    Uri attachment; // убрать, чтобы открыть только почтовые прилож.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        // в новом активити, на который мы ходим создать переход, мы создаем переменную класса Intent
        // и при помощи метода .getIntent мы извлекаем Intent который запустил нашу активити и
        // помещаем в эту переменную
        Intent receivedOrderIntent = getIntent();
        String userName = receivedOrderIntent.getStringExtra("userNameForIntent");// пишем
        // ключ по которому будем извлекать
        String goodsName = receivedOrderIntent.getStringExtra("goodsName");
        int quantity = receivedOrderIntent.getIntExtra("quantity", 0);
        double price = receivedOrderIntent.getDoubleExtra("price", 0);
        double orderPrice = receivedOrderIntent.getDoubleExtra("orderPrice", 0);

        emailText = "Customer name: " + userName + "\nGoods name: " + goodsName + "\nQuantity: "
                + quantity + "\nPrice: " + price + "€"  + "\nOrder price: " + orderPrice
                + "€"  + "\n" + "\nThant you " + userName + "!";
        // создадим переменную класса TextView и свяжем ее с нашим TextView
        TextView orderTextView = findViewById(R.id.orderTextView);
        orderTextView.setText(emailText);
    }

    public void submitOrder(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
//        intent.setData(Uri.parse("mail:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, emailText);
        intent.putExtra(Intent.EXTRA_STREAM, attachment); // убрать, чтобы открыть только почтовые прил.
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

}
