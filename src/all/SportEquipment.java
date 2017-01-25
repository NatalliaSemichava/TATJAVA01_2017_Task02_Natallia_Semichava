package all;
import java.util.Locale.*;


public class SportEquipment {
    private String category;
    private String title;
    private int price;

    public SportEquipment(String category, String title, int price){
        this.category = category;
        this.title = title;
        this.price = price;
    }

    public void showInfoAboutEquipment(){
        System.out.print("Category "+category+", Title "+title+", Price "+price);
    }

    public String getTitle(){
        return title;
    }

    public String getCategory(){
        return category;
    }

    public int getPrice(){
        return price;
    }
}
