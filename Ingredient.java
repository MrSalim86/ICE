package ICE;
public class Ingredient {


    String name;
    float quantity;
    String unit;
    float energyQuantity;
    String energyUnit;

    public Ingredient(String name, float quantity, String unit, float energyQuantity, String energyUnit) {
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
        this.energyQuantity = energyQuantity;
        this.energyUnit = energyUnit;
    }



    public String getName() {
        return name;
    }

    public float getQuantity() {
        return quantity;
    }

    public String getUnit() {
        return unit;
    }

    public float getEnergyQuantity() {
        return energyQuantity;
    }

    public String getEnergyUnit() {
        return energyUnit;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return  "\n" +
                "Navn : " + name + "\n" +
                "Mændge : " + quantity + unit + "\n" +
                "energyQuantity : " + energyQuantity + energyUnit + "\n";
    }
}
