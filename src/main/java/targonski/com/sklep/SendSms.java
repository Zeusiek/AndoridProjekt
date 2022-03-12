package targonski.com.sklep;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SendSms extends AppCompatActivity {
    private EditText number;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_sms);

        Intent intent = getIntent();
        number = findViewById(R.id.number);
        textView = findViewById(R.id.msga);
        String a = intent.getStringExtra("fs");
        textView.setText(a);

        Button cancel = findViewById(R.id.cancel);
        cancel.setOnClickListener(v->finish());

        Button send = findViewById(R.id.button);
        send.setOnClickListener(view -> {
            if(checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED){
                String nrTelefonu = number.getText().toString().trim();
                String sms = textView.getText().toString().trim();
                try {
                    SmsManager smsManager = SmsManager.getDefault();
                    ArrayList<String> sm = smsManager.divideMessage(sms);
                    smsManager.sendMultipartTextMessage(nrTelefonu,null,sm,null,null);
                    Toast.makeText(this, "Wiadomość wysłana", Toast.LENGTH_LONG).show();
                }catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(this,"Błąd wysyłania",Toast.LENGTH_LONG).show();
                }
                finish();
            }else {
                requestPermissions(new String[]{Manifest.permission.SEND_SMS}, 1);
                send.callOnClick();
            }
        });
    }

}