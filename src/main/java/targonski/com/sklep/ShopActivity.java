package targonski.com.sklep;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.slider.Slider;

import androidx.appcompat.app.AppCompatActivity;
import targonski.com.sklep.elements.AdapterViews;
import targonski.com.sklep.elements.MyAdapter;
import targonski.com.sklep.elements.MySet;
import targonski.com.sklep.elements.Products;

public class ShopActivity extends Extender implements AdapterView.OnItemSelectedListener {

    AdapterViews adapterViews;
    Products products;
    Button order;
    int totalOrder = 0;
    int chosenComputer, compNr;

    MyAdapter myAdapter;
    MyAdapter kamerki;
    MyAdapter myszki;
    MyAdapter klawy;

    public CheckBox mouseBox, keyboardBox, cameraBox;
    public Slider slider;


    Spinner comp, kl, cm, ms;
    String[] opis, mysze, klawiatury, camery;
    int[] pcs, msz, klaw, cams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop);

        adapterViews = new AdapterViews(this);

        slider = findViewById(R.id.numbPick);
        slider.setValueTo(30);
        slider.addOnChangeListener((v,a,c)-> ((TextView)findViewById(R.id.wieViel))
                .setText(String.valueOf((int)slider.getValue())));

        order = findViewById(R.id.order);
        order.setOnClickListener(v -> order());

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

        myAdapter = new MyAdapter(getApplicationContext(), pcs, opis);
        kamerki = new MyAdapter(getApplicationContext(), cams, camery);
        myszki = new MyAdapter(getApplicationContext(), msz, mysze);
        klawy = new MyAdapter(getApplicationContext(), klaw, klawiatury);

        comp.setAdapter(myAdapter);
        kl.setAdapter(klawy);
        ms.setAdapter(myszki);
        cm.setAdapter(kamerki);

    }

    public void sumAllUp(int[] sum){
        int sum1 = (mouseBox.isChecked()?sum[2]:0) +
                (keyboardBox.isChecked()?sum[1]:0) +
                (cameraBox.isChecked() ? sum[0]:0);
        totalOrder = (chosenComputer + sum1)*(int)slider.getValue();;
    }


    public void printTotal(){
        print(String.valueOf(totalOrder));
    }
    public void print(String s){
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }
    private void order(){
        adapterViews.sumUp();
        if (totalOrder == 0) {
            Toast.makeText(this, getText(R.string.cantSendOrder),
                    Toast.LENGTH_SHORT).show();
            return;
        }
        String orderText = createOrderText(adapterViews.orderedThings());
        sumAllUp(orderText, adapterViews.orderedThings());
    }
    private void sumAllUp(String finish, MySet[] set){
        Intent intent = new Intent(this, SumUp.class);
        intent.putExtra("finishString", finish);
        intent.putExtra("cmpnr", compNr);
        intent.putExtra("cmra", (cameraBox.isChecked()?set[0].getOpis():null));
        intent.putExtra("keybr", (keyboardBox.isChecked()?set[1].getOpis():null));
        intent.putExtra("mouse", (mouseBox.isChecked()?set[2].getOpis():null));
        intent.putExtra("count", (int)slider.getValue());
        intent.putExtra("totalSum", totalOrder);
        startActivity(intent);
    }


    private String createOrderText(MySet[] set){
        return "-"+Products.returnComps()[compNr] +"zł x"+(int)slider.getValue()+"\n\n" +
                (cameraBox.isChecked()?"-"+getString(R.string.camera)+": "+set[0]+"zł x"+(int)slider.getValue()+"\n\n":"")+
                (keyboardBox.isChecked()?"-"+getString(R.string.keyboard)+": "+set[1]+"zł x"+(int)slider.getValue()+"\n\n":"")+
                (mouseBox.isChecked()?"-"+getString(R.string.mouse)+": "+set[2]+"zł x"+(int)slider.getValue():"");
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (i){
            case 0: chosenComputer = 3024; break;
            case 1: chosenComputer = 5227; break;
            case 2: chosenComputer = 7760; break;
            default:
        }
        compNr = i;
    }
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) { }
}