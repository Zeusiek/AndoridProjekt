package targonski.com.sklep.elements;

import android.content.Context;

import targonski.com.sklep.R;

public class Products {
    Context context;
    public Products(Context context){
        this.context = context;
    }
    public String[] returnComputers(){
        String pr = context.getString(R.string.price);
        return new String[]{
                "Intel Core i5 12400 6 X 2,4GHz 16GB RAM DDR4 SSD 250GB M.2 + HDD 1TB Sata GTX 1050 Ti 4GB,"+pr+": 3024zł",
                "Intel Core i7 11700 6 X 2,9GHz 16GB RAM DDR4 SSD 500GB M.2 + HDD 1TB Sata GTX 1660 Ti 6GB, "+pr+": 5227zł",
                "RYZEN 9 3900X 12 X 3,8GHz 32GB RAM DDR4 SSD 500GB M.2 GTX 2060 Ti 6GB, "+pr+": 7760zł"
        };
    }
    public static MySet[] returnComps(){
        return new MySet[]{
                new MySet("Intel Core i5 12400 6 X 2,4GHz 16GB RAM DDR4 SSD 250GB M.2 + HDD 1TB Sata GTX 1050 Ti 4GB", 3024),
                new MySet("Intel Core i7 11700 6 X 2,9GHz 16GB RAM DDR4 SSD 500GB M.2 + HDD 1TB Sata GTX 1660 Ti 6GB", 5227),
                new MySet("RYZEN 9 3900X 12 X 3,8GHz 32GB RAM DDR4 SSD 500GB M.2 GTX 2060 Ti 6GB", 7760)
        };
    }
    public String[] returnMouses(){
        String s = context.getString(R.string.mouse);
        String pr = context.getString(R.string.price);
        return new String[]{
                s + " Dell MS116, "+pr+": 35zł",
                s + " GForce 1245, "+pr+": 75zł",
                s + " ASUS A234, "+pr+": 120zł"
        };
    }
    public static MySet[] returnMaus(){
        return new MySet[]{
                new MySet("Dell MS116",35),
                new MySet("GForce 1245",75),
                new MySet("ASUS A234",120)
        };
    }
    public String[] returnKeyboards(){
        String s = context.getString(R.string.keyboard);
        String pr = context.getString(R.string.price);
        return new String[]{
                s+ " TITANUM TK101 USB, "+pr+": 19zł",
                s + " ASUS A144 USB, "+pr+": 50zł",
                s + " ASUS A233 USB, "+pr+": 119zł"
        };
    }
    public static MySet[] returnKbrds(){
        return new MySet[]{
                new MySet("TITANUM TK101 USB",19),
                new MySet("ASUS A144 USB",50),
                new MySet("ASUS A233 USB",119)
        };
    }
    public String[] returnCameras(){
        String s = context.getString(R.string.camera);
        String pr = context.getString(R.string.price);
        return new String[]{
                s + " WEBCAM A300 USB, "+pr+": 39zł",
                s + " DUXO CAM 200, "+pr+": 50zł",
                s + " SONY WEBCAMERA S2330, "+pr+": 129zł"
        };
    }
    public static MySet[] returnCmrs(){
        return new MySet[]{
                new MySet("WEBCAM A300 USB",39),
                new MySet("DUXO CAM 200",50),
                new MySet("SONY WEBCAMERA S2330",129)
        };
    }
    public int[] returnCompPhotos(){
        return new int[]{R.drawable.komp1, R.drawable.komp2, R.drawable.komp3};
    }
    public int[] returnKeyboardPhotos(){
        return new int[]{R.drawable.klaw1, R.drawable.klaw2, R.drawable.klaw3};
    }
    public int[] returnMousesPhotos(){
        return new int[]{R.drawable.mysz1, R.drawable.mysz2, R.drawable.mysz3};
    }
    public int[] returnCameraPhotos(){
        return new int[]{R.drawable.kam1, R.drawable.kam2, R.drawable.kam3};
    }
}
