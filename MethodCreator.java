package ICE;

import java.util.ArrayList;
import java.util.Random;

public class MethodCreator {


    public MethodCreator(){


    }

    //Calls meals based on "type"
    public ArrayList<Meal> mealTypeSearch(ArrayList<Meal> meals, String searchType) {
        ArrayList<Meal> typeMeals = new ArrayList<>();


        for (int i = 0; i < meals.size(); i++) {
            if (meals.get(i).getType().equalsIgnoreCase(searchType)) {
                typeMeals.add(meals.get(i));
            }
        }
        return typeMeals;
    }




    public ArrayList<Day> createWeek(){
        ArrayList<Day> week = new ArrayList<>();
        Day monday = new Day("Mandag");
        Day tuesday = new Day("Tirsdag");
        Day wednesday = new Day("Onsdag");
        Day thursday = new Day("Torsdag");
        Day friday = new Day("Fredag");
        Day saturday = new Day("Lørdag");
        Day sunday = new Day("Sunday");
        week.add(monday);
        week.add(tuesday);
        week.add(wednesday);
        week.add(thursday);
        week.add(friday);
        week.add(saturday);
        week.add(sunday);

        return week;


    }
   /* public ArrayList<Day> AddToweekDay(String input, ArrayList<Meal> meals){
        ArrayList<Day> days = new ArrayList<>();
        Day monday = new Day("Mandag");
        Day tuesday = new Day("Tirsdag");
        Day wednesday = new Day("Onsdag");
        Day thursday = new Day("Torsdag");
        Day friday = new Day("Fredag");
        Day saturday = new Day("Lørdag");
        Day sunday = new Day("Sunday");
        days.add(monday);
        days.add(tuesday);
        days.add(wednesday);
        days.add(thursday);
        days.add(friday);
        days.add(saturday);
        days.add(sunday);

        return days;

    }*/







}