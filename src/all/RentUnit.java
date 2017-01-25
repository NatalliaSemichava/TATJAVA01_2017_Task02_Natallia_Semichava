package all;

import java.util.ArrayList;

public class RentUnit {
    private ArrayList<SportEquipment> units = new ArrayList<SportEquipment>();


    public void addEquipment(SportEquipment equipment){;
        units.add(equipment);
    }

    public ArrayList<SportEquipment> getUnits(){
        return units;
    }
}
