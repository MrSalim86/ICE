package ICE;
import java.util.ArrayList;
import java.util.Random;

public class RandomGenerator {
    int fishDay = 0;
    //Type settings
    int meat = 1;
    int vegan = 1;
    int fish = 0;

    //Based on meat, vegan and fish selection
    ArrayList<Meal> sortedMeals = new ArrayList<>();

    Random random = new Random();


    public RandomGenerator() {



    }


    public void RandomGenerate(ArrayList<Meal> meals, ArrayList<Day> week) {
        if (fishDay == 1) {
            fishDayMethod(meals, week);
        }

        if (meat == 1) {
            typeSorter(meals,"kød");
        }

        if (vegan == 1) {
            typeSorter(meals,"vegan");
        }

        if (fish == 1) {
            typeSorter(meals,"fisk");
        }

        mealRandomizer(sortedMeals, week);
    }



    private void mealRandomizer(ArrayList<Meal> mealsSorted, ArrayList<Day> weekPlan){
        for(Day d : weekPlan){
            int mealRandom = random.nextInt(0, mealsSorted.size());
            if(d.getMeal() == null){
                d.setMeal(mealsSorted.get(mealRandom));
                mealsSorted.remove(mealRandom);
            }
        }
    }


    private void typeSorter(ArrayList<Meal> meals, String searchType){
        for (int i = 0; i < meals.size(); i++) {
            if (meals.get(i).getType().equalsIgnoreCase(searchType)) {
                sortedMeals.add(meals.get(i));
            }
        }
    }

    private void fishDayMethod(ArrayList<Meal> meals, ArrayList<Day> weekPlan) {
        ArrayList<Meal> fish = new ArrayList<>();
        int fishRan;


        for (int i = 0; i < meals.size(); i++) {
            if (meals.get(i).getType().equalsIgnoreCase("fisk")) {
                fish.add(meals.get(i));

            }
        }

        if (fish.size() < 1) {
            fishRan = 0;
        } else {
            fishRan = random.nextInt(0, fish.size());
        }

        int ranDay = random.nextInt(0, weekPlan.size());

        for(Meal m : meals) {
            if (weekPlan.get(ranDay).getMeal() == null) {
                weekPlan.get(ranDay).setMeal(fish.get(fishRan));
                break;
            }
        }

    }


    public String getFishDay() {
        String status = "";
        if(fishDay == 1){
            status = "Aktiveret";
        } else if(fishDay == 0) {
            status = "Deaktiveret";
        }

        return status;
    }

    public void setFishDay() {
        if(this.fishDay == 0){
            fishDay = 1;
        } else if (this.fishDay == 1){
            fishDay = 0;
        }
    }

    public String getMeat() {
        String status = "";
        if(meat == 1){
            status = "Aktiveret";
        } else if(meat == 0){
            status = "Deaktiveret";
        }
        return status;
    }

    public void setMeat() {
        if(this.meat == 0){
            meat = 1;
        }else if(this.meat == 1){
            meat = 0;
        }
    }

    public String getVegan() {
        String status = "";
        if(vegan == 1){
            status = "Aktiveret";
        } else if(vegan == 0){
            status = "Deaktiveret";
        }
        return status;
    }

    public void setVegan() {
        if(this.vegan == 0){
            vegan = 1;
        } else if(this.vegan == 1){
            vegan = 0;
        }
    }

    public void setFish() {

        if(this.fish == 0){
            fish = 1;
        }else if(this.fish == 1) {
            this.fish = 1;
        }
    }

    public String getFish() {
        String status = "";
        if(fish == 1){
            status = "Aktiveret";
        }else if(fish == 0){
            status = "Deaktiveret";
        }
        return status;
    }

}