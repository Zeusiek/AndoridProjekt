package targonski.com.sklep;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import targonski.com.sklep.database.MyDB;
import targonski.com.sklep.elements.Products;
import targonski.com.sklep.messages.SendMail;

public class SumUp extends AppCompatActivity {
    String finishText = "test";
    String[] orderedAddons;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.podsumowanie);

        Intent i = getIntent();
        finishText = i.getStringExtra("finishString");
        orderedAddons = new String[]{
                i.getStringExtra("cmra"),
                i.getStringExtra("keybr"),
                i.getStringExtra("mouse")
        };

        TextView textView = findViewById(R.id.finishText);
        textView.setText(finishText);

        TextView s = findViewById(R.id.priceTextBox);
        String sum = getString(R.string.price)+ ": " + i.getIntExtra("totalSum",0)+" zł";
        s.setText(sum);

        Button sendMail = findViewById(R.id.sendMailButton);
        Button sendSmsbtn = findViewById(R.id.sendSMS);

        sendMail.setOnClickListener(v->
                SendMail.sendMail(this, finishText)
        );
        sendSmsbtn.setOnClickListener(view -> {
            if(checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(this, SendSms.class);
                intent.putExtra("fs",String.valueOf(finishText));
                startActivity(intent);
            }else {
                requestPermissions(new String[]{Manifest.permission.SEND_SMS}, 1);
            }
        });

        Button accept = findViewById(R.id.okayButton);
        String stringUser = getIntent().getStringExtra("login");

        accept.setOnClickListener(v -> {
            System.out.println(stringUser);
            MyDB d = new MyDB(this, null, null, 3);
            System.out.println(d.insertInto(Products.returnComps()[i.getIntExtra("cmpnr", 0)]
                            .getOpis(), orderedAddons[0], orderedAddons[1], orderedAddons[2],
                    i.getIntExtra("count", 1), i.getIntExtra("totalSum",0),
                    stringUser
            ));
            d.close();
            Intent intent = new Intent(this, Main.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("login", stringUser);
            startActivity(intent);
            finish();

        });
        Button cancelBtn = findViewById(R.id.goBackBtn);
        cancelBtn.setOnClickListener(view -> finish());
    }

}
