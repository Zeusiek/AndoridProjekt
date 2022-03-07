package targonski.com.sklep;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import targonski.com.sklep.messages.SendMessage;

public class SumUp extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.podsumowanie);

        Intent i = getIntent();

        TextView textView = findViewById(R.id.finishText);
        textView.setText(i.getStringExtra("finishString"));

        TextView s = findViewById(R.id.priceTextBox);
        String sum = getString(R.string.price)+ ": " + i.getIntExtra("totalSum",0)+" zł";
        s.setText(sum);

        Button button = findViewById(R.id.okayButton);
        button.setOnClickListener(v -> {
            SendMessage.sendMail(this, i.getStringExtra("finishString"));
            //SendMessage.sendSMS(i.getStringExtra("finishString"));
        });
        Button cancelBtn = findViewById(R.id.goBackBtn);
        cancelBtn.setOnClickListener(view -> finish());
    }
}
