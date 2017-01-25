package all;
import java.util.*;
import java.io.*;

public class Shop{
    private HashMap<SportEquipment,Integer> goods = new HashMap<SportEquipment,Integer>();
    private ArrayList<String> goodsFromFile=new ArrayList<String>();
    private HashMap<String,RentUnit> units = new HashMap<String,RentUnit>();
    private Scanner scan = new Scanner(System.in);


    public Shop()  throws IOException{
        readFile("./Goods.txt", "goods");

        readFile("./Units.txt", "units");
    }

    private void readFile(String path, String type) throws IOException {
        goodsFromFile.clear();
        File file = new File(path);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String S;
        while ((S = br.readLine()) != null) {
            goodsFromFile.add(S);
        }
        br.close();
        if (type.equals("goods")) {
            convertGoodsToList();
        }
        if (type.equals("units")) {
            convertUnitsToList();
        }
    }

    private void convertGoodsToList(){
        SportEquipment sportEquipment;
        String[] infoAboutEquipment;

        for (String s : goodsFromFile) {
            infoAboutEquipment = s.split(" ");
            sportEquipment = new SportEquipment(infoAboutEquipment[0], infoAboutEquipment[1], Integer.parseInt(infoAboutEquipment[2]));
            goods.put(sportEquipment, Integer.parseInt(infoAboutEquipment[3]));
        }
    }

    private void convertUnitsToList(){
        SportEquipment sportEquipment;
        String[] infoAboutEquipment;

        for (String s : goodsFromFile) {
            infoAboutEquipment = s.split(" ");
            sportEquipment = new SportEquipment(infoAboutEquipment[0], infoAboutEquipment[1], Integer.parseInt(infoAboutEquipment[2]));
            RentUnit rentUnit = new RentUnit();
            if (!units.containsKey(infoAboutEquipment[3])) {
                rentUnit.addEquipment(sportEquipment);
                units.put(infoAboutEquipment[3], rentUnit);
            }
            else{
                units.get(infoAboutEquipment[3]).addEquipment(sportEquipment);
            }
        }
    }

    public void showAllEquipment(){
        int i=1;
        for (SportEquipment sp  : goods.keySet()){
            if (goods.get(sp)>0) {
                System.out.print(i + ". ");
                sp.showInfoAboutEquipment();
                System.out.println();
                i++;
            }
        }
    }

    public void showEquipmentInHire() {
        int i = 1;
        for (RentUnit ru : units.values()) {
            for (SportEquipment sp : ru.getUnits()) {
                System.out.print(i + ". ");
                sp.showInfoAboutEquipment();
                System.out.println();
                i++;
            }
        }
    }

    public void searchEquipment(String s) throws IOException{
        boolean flag=false;
        for (SportEquipment sp  : goods.keySet()){
            if ((goods.get(sp)>0) && (s.equals(sp.getTitle()))) {
                flag=true;
                break;
            }
        }
        System.out.println(flag ? "There is such equipment" : "There is no such equipment");

        if (flag) {
            System.out.println("Do you want to rent this equipment (enter 1(yes) enter 2(no))");
            int index=scan.nextInt();
            if (index==1){
                rentEquipment(s);
            }

        }
    }

    private void rentEquipment(String equipment)  throws IOException{
        System.out.println("Write your name");
        String name=scan.next();
        if (checkCount(name)) {
            for (SportEquipment sp : goods.keySet()) {
                if (equipment.equals(sp.getTitle())) {

                    goods.put(sp, goods.get(sp) - 1);

                    if (!units.containsKey(name)) {
                        RentUnit rentUnit = new RentUnit();
                        rentUnit.addEquipment(sp);
                        units.put(name, rentUnit);
                    } else {
                        units.get(name).addEquipment(sp);
                    }
                }
            }
            saveChanges();
            System.out.println("You rent this equipment");
        }
    }

    private void saveChanges() throws IOException{
        writeInFileUnits();
        writeInFileGoods();
    }

    private void writeInFileGoods()  throws IOException{
        PrintWriter pw = new PrintWriter(new FileWriter("./Goods.txt"));
        pw.flush();
        for (SportEquipment sp : goods.keySet()) {
            pw.write(sp.getCategory()+" "+sp.getTitle()+" "+sp.getPrice()+ " "+goods.get(sp)+ System.lineSeparator());
        }
        pw.close();
    }

    private void writeInFileUnits()  throws IOException{
        PrintWriter pw = new PrintWriter(new FileWriter("./Units.txt"));
        pw.flush();
        for (String s : units.keySet()) {
            for (SportEquipment sp : units.get(s).getUnits()) {
                pw.write(sp.getCategory() + " " + sp.getTitle() + " " + sp.getPrice() + " " + s + System.lineSeparator());
            }
        }
        pw.close();
    }

    public void chooseEquipment() throws IOException {
        System.out.println("Choose equipment (write a title)");
        String title = scan.next();
        boolean flag=false;
        for (SportEquipment sp  : goods.keySet()){
            if ((goods.get(sp)>0) && (title.equals(sp.getTitle()))) {
                flag=true;
                break;
            }
        }
        if (flag){
            rentEquipment(title);
        }
        else{
            System.out.println("Write correctly");
        }
    }

    private boolean checkCount(String key){
        if (units.containsKey(key)){
            if (units.get(key).getUnits().size()==3){
                System.out.println("Sorry, you rent 3 goods");
                return false;
            }
        }
        return true;
    }
}
