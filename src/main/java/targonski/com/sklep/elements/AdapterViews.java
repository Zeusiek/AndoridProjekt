package targonski.com.sklep.elements;

import android.view.View;
import android.widget.AdapterView;

import java.util.Arrays;

import targonski.com.sklep.MainActivity;

public class AdapterViews {
     public int[] add = new int[3];
     private final byte[] chosenItems = new byte[3];
     MainActivity mainActivity;
     public AdapterViews(MainActivity activity){
         mainActivity = activity;
     }

     public AdapterView.OnItemSelectedListener camInt(){
        return new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                add[0] = 0;
                chosenItems[0] = (byte)i;
                switch (i){
                    case 0: add[0] = 39; break;
                    case 1: add[0] = 50; break;
                    case 2: add[0] = 129; break;
                    default:
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        };
    }
    public AdapterView.OnItemSelectedListener klawInt(){
        return new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                add[1] = 0;
                chosenItems[1] = (byte)i;
                switch (i) {
                        case 0: add[1] = 19; break;
                        case 1: add[1] = 50; break;
                        case 2: add[1] = 119;break;
                        default:
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        };
    }
    public AdapterView.OnItemSelectedListener myszInt(){
        return new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                add[2] = 0;
                chosenItems[2] = (byte)i;
                switch (i){
                    case 0: add[2] = 35; break;
                    case 1: add[2] = 75; break;
                    case 2: add[2] = 120; break;
                    default:
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        };
    }
    public void sumUp(){
         mainActivity.sumAllUp(add);
    }

    public MySet[] orderedThings(){
        return new MySet[]{
                Products.returnCmrs()[chosenItems[0]],
                Products.returnKbrds()[chosenItems[1]],
                Products.returnMaus()[chosenItems[2]]
        };
    }


}
