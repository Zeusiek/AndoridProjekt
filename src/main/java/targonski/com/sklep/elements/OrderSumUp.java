package targonski.com.sklep.elements;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import targonski.com.sklep.R;

public class OrderSumUp extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] comps;
    private final String[] numb;
    private final String[] cmrs;
    private final String[] keyb;
    private final String[] maus;
    private final String[] price;


    public OrderSumUp(Activity context, String[] comps, String[] numb, String[] cmrs,
                      String[] keyb, String[] maus, String[] price) {
        super(context, R.layout.activity_order_sum_up, comps);
        this.context = context;
        this.comps = comps;
        this.numb = numb;
        this.cmrs = cmrs;
        this.keyb = keyb;
        this.maus = maus;
        this.price = price;
    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.activity_order_sum_up, null, true);

        TextView compTXT = rowView.findViewById(R.id.compText);
        TextView sumTXT = rowView.findViewById(R.id.priceText);
        TextView cmrsTXT = rowView.findViewById(R.id.camrText);
        TextView keybTXT = rowView.findViewById(R.id.keybText);
        TextView mausTXT = rowView.findViewById(R.id.mousText);

        if(cmrs[position] != null) cmrsTXT.setTextSize(0);
        else cmrsTXT.setTextSize(15);
        if(keyb[position] != null) keybTXT.setTextSize(0);
        else keybTXT.setTextSize(15);
        if(maus[position] != null) mausTXT.setTextSize(0);
        else mausTXT.setTextSize(15);

        String x = " X"+numb[position];
        String[] s = new String[]{
                comps[position] + x,
                (cmrs[position] != null ? cmrs[position] + x: ""),
                (keyb[position] != null ? keyb[position] + x: ""),
                (maus[position] != null ? maus[position] + x: "")
        };
        compTXT.setText(s[0]);
        sumTXT.setText(price[position]);
        cmrsTXT.setText(s[1]);
        keybTXT.setText(s[2]);
        mausTXT.setText(s[3]);
        return rowView;

    };
}