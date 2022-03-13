package targonski.com.sklep;

import androidx.appcompat.app.AppCompatActivity;
import targonski.com.sklep.database.MyDB;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText login;
    EditText pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = findViewById(R.id.login);
        pass = findViewById(R.id.passw);
        Button loginBtn = findViewById(R.id.loginBtn);
        Button registerBtn = findViewById(R.id.registerBtn);
        loginBtn.setOnClickListener(view -> login());
        registerBtn.setOnClickListener(view -> register());
    }
    private void login(){
        MyDB db = new MyDB(this, null,null, 1);
        Cursor cursor = db.returnUserPass(login.getText().toString());
        if(cursor.moveToFirst()){
            try {
                String pass = cursor.getString(0);
                if(!pass.equals(this.pass.getText().toString())){
                    Toast.makeText(this, getString(R.string.wrongPass), Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(this, Main.class);
                intent.putExtra("login", login.getText().toString());
                startActivity(intent);
            }catch (Exception e){
                System.out.println(e);
            }
        }else Toast.makeText(this, getString(R.string.notFount), Toast.LENGTH_SHORT).show();


    }
    private void register(){
        String a = login.getText().toString();
        String b = pass.getText().toString();
        if(!a.equals("") && !b.equals("")){
            MyDB myDB = new MyDB(this, null,null, 1);
            System.out.println();
            if(myDB.register(a, b)) {
                Toast.makeText(this, "Zarejestrowano!", Toast.LENGTH_SHORT).show();
                return;
            }
            Toast.makeText(this, "Już zarejestrowano tego użytkownika!", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(this, "Błędne dane!", Toast.LENGTH_SHORT).show();
    }
    public static void logOut(Activity activity){
        Intent i = new Intent(activity, Login.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        activity.startActivity(i);
        activity.finish();
    }
}