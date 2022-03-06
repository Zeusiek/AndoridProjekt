package targonski.com.sklep;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {
    Context context;
    int[] pcs;
    String[] opisy;
    LayoutInflater inflater;
    ImageView view;
    TextView textView;

    public MyAdapter(Context context, int[] pcs, String[] opisy){
        super();
        this.context = context;
        this.pcs = pcs;
        this.opisy = opisy;
        this.inflater = LayoutInflater.from(context);

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.my_spinner_items, null);
        this.view = view.findViewById(R.id.imageView);
        this.textView = view.findViewById(R.id.textView);
        this.view.setImageResource(pcs[i]);
        this.textView.setText(opisy[i]);
        return view;
    }

    @Override
    public int getCount() {
        return pcs.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
}
