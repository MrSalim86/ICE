package ICE;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

public class DisplayFoodPlan {

    Map<String, Float> shoppinglist = new Hashtable<>();
    ArrayList<Ingredient> allIngredients = new ArrayList<>();

    public DisplayFoodPlan(ArrayList<Day> weekPlan){


    }

    public void showShoppingList(){
        Iterator<Map.Entry<String, Float>> iterator = shoppinglist.entrySet().iterator();
        System.out.println("Indkøbsliste: \n");
        while (iterator.hasNext()) {
            Map.Entry<String, Float> entry = iterator.next();
            System.out.println(entry.getKey() + " : " + entry.getValue() + getUnitType(entry.getKey()));

        }


    }

    public void shoppingList(ArrayList<Ingredient> allIngredientList) {


        for (Ingredient i : allIngredientList) {
            String key = i.getName();
            if (shoppinglist.containsKey(key)) {
                float value = shoppinglist.get(key);
                shoppinglist.put(key, value + i.getQuantity());

            } else {
                shoppinglist.put(key, i.getQuantity());
            }
        }

    }

    public String getUnitType(String ingredintName){
        for(Ingredient i : allIngredients){
            if(i.getName() == ingredintName){
                return i.getUnit();
            }
        }
        return null;
    }




    public ArrayList<Ingredient> combineIngredientList(ArrayList<Day> weekplan){

        for(int i = 0; i < weekplan.size(); i++){
            for(int j = 0; j < weekplan.get(i).getMeal().getIngredients().size(); j++){
                allIngredients.add(weekplan.get(i).getMeal().getIngredients().get(j));
            }
        }
        return allIngredients;
    }



    public void quantityUpdate(Day day) {
        //
        //for (Ingredient ing : weekplan.get(i).getMeal().getIngredients()) {
        int range = day.getMeal().getIngredients().size();
        for (int i = 0; i < range; i++) {
            float tmp = day.getMeal().getIngredients().get(i).getQuantity();
            float tmpPerson = day.getPersonPrDay() * tmp;
                /*
                if (tmp == tmpPerson / weekplan.get(i).getPersonPrDay()) {
                    break;
                } else {*/
            day.getMeal().getIngredients().get(i).setQuantity(tmpPerson);
        }

    }

    //}

    public void showDayMeal(Day day){
        System.out.println(day.getName());
        System.out.println("Antal personer: " + day.getPersonPrDay());
        System.out.println("-------------------------------------");
        System.out.println("*"+day.getMeal().getName()+"*");
        System.out.println("Energi i alt: " + day.getMeal().getTotalEnergy() + day.getMeal().getIngredients().get(0).getEnergyUnit());
        ArrayList<Ingredient> ing = day.getMeal().getIngredients();
        System.out.println("-------------------------------------");
        System.out.println("Ingredienser:\n");
        for(Ingredient i : ing){
            System.out.println(i.getName() + " - " + i.getQuantity() + i.getUnit());
        }
        System.out.println("-------------------------------------");
        System.out.println("Opskrift:\n");

        System.out.println(day.getMeal().getDescription() + "\n");
        System.out.println("*********************************************************");


    }



}
