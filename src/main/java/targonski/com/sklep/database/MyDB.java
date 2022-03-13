package targonski.com.sklep.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Arrays;

import androidx.annotation.Nullable;
import targonski.com.sklep.elements.MySet;
import targonski.com.sklep.elements.Products;

public class MyDB extends SQLiteOpenHelper {

    public MyDB(@Nullable Context context, @Nullable String name,
                     @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "Sklep.db", null, 9);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE orders (" +
                "orderId INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "compName TEXT NOT NULL, mouse TEXT, camera TEXT, keyboard TEXT, numb INTEGER NOT NULL," +
                "price INTEGER NOT NULL, user TEXT)"
        );
        db.execSQL("CREATE TABLE users(" +
                "login TEXT NOT NULL," +
                "password TEXT NOT NULL)");
    }

    public Cursor returnUser(String login){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM users WHERE TRIM(login) = '"+login.trim() + "';", null);
    }
    public Cursor returnUserPass(String login){
        SQLiteDatabase db = this.getReadableDatabase();
        String s = "SELECT password FROM users WHERE TRIM(login) = '"+login.trim() + "';";
        return db.rawQuery(s, null);
    }
    public boolean register(String login, String pass){
        Cursor cursor = returnUser(login);
        if (cursor.moveToFirst()) return false;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("login", login.trim());
        contentValues.put("password", pass.trim());
        db.insert("users", null, contentValues);
        return true;
    }
    public boolean insertInto(String comp, String mouse, String camera, String keyboard
            , int numb, int price, String user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("compName", comp);
        contentValues.put("mouse", mouse);
        contentValues.put("camera", camera);
        contentValues.put("keyboard", keyboard);
        contentValues.put("numb", numb);
        contentValues.put("price", price);
        contentValues.put("user", user);
        db.insert("orders", null, contentValues);
        return true;
    }

    public Cursor selectAll(){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM orders WHERE 1", null);
    }
    public Cursor selectAll(String login){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM orders WHERE TRIM(user) = '"
                + login.trim() + "';", null);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS users;");
        db.execSQL("DROP TABLE IF EXISTS orders;");
        onCreate(db);
    }
}
