package targonski.com.sklep;

import androidx.appcompat.app.AppCompatActivity;
import targonski.com.sklep.database.MyDB;
import targonski.com.sklep.elements.OrderSumUp;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class MyOrders extends Extender {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders);

        ListView listView = findViewById(R.id.listView);
        ArrayList<String> comps = new ArrayList<>();
        ArrayList<String> numb = new ArrayList<>();
        ArrayList<String> mouse = new ArrayList<>();
        ArrayList<String> keyboard = new ArrayList<>();
        ArrayList<String> camera = new ArrayList<>();
        ArrayList<String> prices = new ArrayList<>();


        MyDB myDB = new MyDB(this, null,null, 2);
        String u = getIntent().getStringExtra("login");

        Cursor cursor = myDB.selectAll();

        if(cursor.moveToFirst())
        do{
            String user = cursor.getString(7);
            if(user.equals(u)){
                comps.add(cursor.getString(1));
                mouse.add(cursor.getString(2));
                keyboard.add(cursor.getString(3));
                camera.add(cursor.getString(4));
                numb.add(String.valueOf(cursor.getInt(5)));
                prices.add(String.valueOf(cursor.getInt(6)));
            }
        }while (cursor.moveToNext());

        listView.setAdapter(new OrderSumUp(this, comps.toArray(new String[0]),
                numb.toArray(new String[0]), camera.toArray(new String[0]),
                keyboard.toArray(new String[0]), mouse.toArray(new String[0]),
                prices.toArray(new String[0])
        ));


    }
}