package all;
import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args)  throws IOException {

        Shop shop = new Shop();

        Scanner s = new Scanner(System.in);
        int index = 0;
        System.out.println("Hello. Choose what you want to do (enter only number)");
        while (index!=-1) {
            System.out.println("\n1. Show all equipment\n2. Search equipment\n3. Show what in hire\n4.Exit");
            index = s.nextInt();
            switch (index) {
                case 1:
                    shop.showAllEquipment();
                    shop.chooseEquipment();

                    break;
                case 2:
                    System.out.println("Enter title of equipment");
                    String str = s.next();
                    shop.searchEquipment(str);
                    break;
                case 3:
                    shop.showEquipmentInHire();
                    break;
                case 4:
                    index=-1;
                    break;
            }

        }
    }
}
