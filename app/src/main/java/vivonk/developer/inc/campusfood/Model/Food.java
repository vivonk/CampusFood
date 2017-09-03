package vivonk.developer.inc.campusfood.Model;

/**
 * Created by vivonk on 02-09-2017.
 */

public class Food {
    private  String Name="";
    private  String Price="";
    private  String ImageURL="";
    private String Quantity="0";
    public Food(String name, String price, String imageURL,String quantity) {
        Name = name;
        Price = price;
        ImageURL = imageURL ;
        Quantity = quantity;
    }
    public String getQuantity() {
        return Quantity;
    }

    public Food() {

    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }




    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getImageURL() {
        return ImageURL;
    }

    public void setImageURL(String imageURL) {
        ImageURL = imageURL;
    }




        /*********** Set Methods ******************/



}
