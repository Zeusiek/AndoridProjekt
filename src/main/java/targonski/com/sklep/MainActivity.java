package targonski.com.sklep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.slider.Slider;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    AdapterViews adapterViews;
    Products products;
    Button order;
    int totalOrder;
    int chosenComputer;

    public CheckBox mouseBox, keyboardBox, cameraBox;
    public Slider slider;

    Spinner comp, kl, cm, ms;
    String[] opis, mysze, klawiatury, camery;
    int[] pcs, msz, klaw, cams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adapterViews = new AdapterViews(this);

        slider = findViewById(R.id.numbPick);
        slider.setValueTo(30);
        slider.addOnChangeListener((v,a,c)-> ((TextView)findViewById(R.id.wieViel))
                .setText(String.valueOf((int)slider.getValue())));

        order = findViewById(R.id.order);
        order.setOnClickListener(v -> adapterViews.sumUp());

        mouseBox = findViewById(R.id.AddMouse);
        keyboardBox = findViewById(R.id.AddKeyboard);
        cameraBox = findViewById(R.id.AddCamera);


        products = new Products(getApplicationContext());

        opis = products.returnComputers();
        mysze = products.returnMouses();
        klawiatury = products.returnKeyboards();
        camery = products.returnCameras();

        pcs = products.returnCompPhotos();
        msz = products.returnMousesPhotos();
        klaw = products.returnKeyboardPhotos();
        cams = products.returnCameraPhotos();

        comp = findViewById(R.id.mainspiner);
        kl = findViewById(R.id.KeyboardSpinner);
        cm = findViewById(R.id.CameraSpinner);
        ms = findViewById(R.id.MouseSpinner);

        comp.setOnItemSelectedListener(this);
        kl.setOnItemSelectedListener(adapterViews.klawInt());
        cm.setOnItemSelectedListener(adapterViews.camInt());
        ms.setOnItemSelectedListener(adapterViews.myszInt());

        MyAdapter myAdapter = new MyAdapter(getApplicationContext(), pcs, opis);
        MyAdapter kamerki = new MyAdapter(getApplicationContext(), cams, camery);
        MyAdapter myszki = new MyAdapter(getApplicationContext(), msz, mysze);
        MyAdapter klawy = new MyAdapter(getApplicationContext(), klaw, klawiatury);

        comp.setAdapter(myAdapter);
        kl.setAdapter(klawy);
        ms.setAdapter(myszki);
        cm.setAdapter(kamerki);

    }

    public void sumUp(int[] sum){
        int sum1 = (mouseBox.isChecked()?sum[2]:0) +
                (keyboardBox.isChecked()?sum[1]:0) +
                (cameraBox.isChecked() ? sum[0]:0);
        totalOrder = (chosenComputer + sum1)*(int)slider.getValue();;
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (i){
            case 0: chosenComputer = 3024; break;
            case 1: chosenComputer = 5227; break;
            case 2: chosenComputer = 7760; break;
            default:
        }
    }


    @Override
    public void onNothingSelected(AdapterView<?> adapterView) { }


    public void printTotal(){
        print(String.valueOf(totalOrder));
    }
    public void print(String s){
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }
    public void order(){
        sendMail();
    }
    private void sendMail(){
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL, new String[]{"recipient@example.com"});
        i.putExtra(Intent.EXTRA_SUBJECT, "Zamówienie");
        i.putExtra(Intent.EXTRA_TEXT, "test");
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }
}