package ICE;
import java.util.ArrayList;

public class Meal {
    String name;
    String type;
    String description;
    ArrayList<Ingredient> ingredients;
    Boolean favorite;
    float totalEnergy = 0;


    public Meal(String name, String type){
        this.name = name;
        this.type = type;
        ingredients = new ArrayList<>();


    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public Boolean getFavorite() {
        return favorite;
    }

    public void setFavorite(String favorite) {
        this.favorite = Boolean.parseBoolean(favorite);
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public void addIngredient(String name, float quantity, String unit, float energyQuantity, String energyUnit) {
        Ingredient i = new Ingredient(name,quantity,unit,energyQuantity,energyUnit);
        ingredients.add(i);

    }

    public void setTotalEnergy(float totalEnergy) {
        this.totalEnergy += totalEnergy;
    }

    public float getTotalEnergy() {
        return totalEnergy;
    }

    @Override
    public String toString() {
        return "Ret: " + "\n" +
                "Navn : " + name + "\n" +
                "Type : " + type + "\n" +
                "Energi indhold pr Person : " + totalEnergy + ingredients.get(0).energyUnit +"\n" +
                "Tilberedning : " + description + "\n" +
                "Favorite : " + favorite + "\n" +
                "\nIngredienser" + ingredients + "\n";
    }
}