package targonski.com.sklep;

import android.os.Bundle;
import android.widget.Button;

public class Main extends Extender {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button startBuy = findViewById(R.id.startBuy);
        startBuy.setOnClickListener(v->menuShop());
    }

}