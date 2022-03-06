package targonski.com.sklep;

import android.view.View;
import android.widget.AdapterView;

import java.util.Arrays;

public class AdapterViews {
     public int[] add = new int[3];
     MainActivity mainActivity;
     AdapterViews(MainActivity activity){
         mainActivity = activity;
     }

     public AdapterView.OnItemSelectedListener camInt(){
        return new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                add[0] = 0;
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
                switch (i) {
                        case 0: add[1] = 19;break;
                        case 1: add[1] = 50;break;
                        case 2: add[1] = 119;break; default:
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
         mainActivity.sumUp(add);
        mainActivity.print(Arrays.toString(add));
        mainActivity.printTotal();
    }
}
