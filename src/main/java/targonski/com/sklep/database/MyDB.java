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
        super(context, "Sklep.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE orders (" +
                "orderId INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "compName TEXT NOT NULL, mouse TEXT, camera TEXT, keyboard TEXT, numb INTEGER NOT NULL," +
                "price INTIGER NOT NULL)"
        );
    }
    public boolean updateOrder(int id, String comp, String mouse, String camera, String keyboard, int numb, int price){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("compName", comp);
        contentValues.put("mouse", mouse);
        contentValues.put("camera", camera);
        contentValues.put("keyboard", keyboard);
        contentValues.put("numb", numb);
        contentValues.put("price", price);
        db.update("orders", contentValues, "id = ? ",
                new String[]{Integer.toString(id)});
        return true;
    }
    public boolean insertInto(String comp, String mouse, String camera, String keyboard, int numb, int price){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("compName", comp);
        contentValues.put("mouse", mouse);
        contentValues.put("camera", camera);
        contentValues.put("keyboard", keyboard);
        contentValues.put("numb", numb);
        contentValues.put("price", price);
        db.insert("orders", null, contentValues);
        return true;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS products");
        onCreate(db);
    }
    public Cursor selectAll(){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM orders WHERE 1", null);
    }

    public Cursor getData(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM orders WHERE orderId = " + id + "", null);
    }
    public int deleteOrder(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("orders", "id = ? ",
                new String[] { Integer.toString(id) });
    }
}
