package ICE;

import java.util.ArrayList;

public class Day {
    private String name;
    private int personPrDay;
    private Meal meal;

    public Day(String name){
        personPrDay = 2;
        this.name = name;


    }

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    public String getName() {
        return name;
    }

    public int getPersonPrDay() {
        return personPrDay;
    }

    public void setPersonPrDay(int personPrDay) {
        this.personPrDay = personPrDay;
    }


    @Override
    public String toString() {
        return "Uge Dag : " + name + "\n" +
                "Antal Personer : " + personPrDay + "\n" +
                meal + "\n";
    }
}

