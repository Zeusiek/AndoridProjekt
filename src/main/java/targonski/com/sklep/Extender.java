package targonski.com.sklep;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Extender extends AppCompatActivity {

    public void menuMain(){
        if(this.getClass().getSimpleName().equals("Main")) return;

        finish();
    }
    public void menuShop(){
        if(this.getClass().getSimpleName().equals("ShopActivity")) return;
        Intent intent = new Intent(this, ShopActivity.class);
        startActivity(intent);
        if(!this.getClass().getSimpleName().equals("Main")) finish();
    }
    public void menuOrders(){
        if(this.getClass().getSimpleName().equals("MyOrders")) return;
        Intent intent = new Intent(this, MyOrders.class);
        startActivity(intent);
        if(!this.getClass().getSimpleName().equals("Main")) finish();
    }
    public void menuAuthor(){
        AlertDialog.Builder d = new AlertDialog.Builder(this);
        d.setMessage("Mateusz Targoński")
                .setCancelable(true).setTitle(getString(R.string.author)).setNeutralButton("OK", (v,a) ->{ });
        d.create().show();
        String s = getString(R.string.author) + ": Mateusz Targoński";
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuMain:
                menuMain();
                break;
            case R.id.menuShop:
                menuShop();
                break;
            case R.id.menuOrders:
                menuOrders();
                break;
            case R.id.menuAuthor:
                menuAuthor();
                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
}
