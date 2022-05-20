package ICE;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class Menu {
    DbIO db = new DbIO();
    TextUI ui = new TextUI();
    DisplayFoodPlan planner;

    ArrayList<Meal> meals;
    ArrayList<Day> weekplan;
    RandomGenerator randomFood;


    public Menu() {
        randomFood = new RandomGenerator();
        meals = db.generateMeals();
        weekplan = createWeek();
        planner = new DisplayFoodPlan(weekplan);
        menuCollection();

    }

    public void menuCollection(){
        String[] mainMenuChoice = {"Generer Madplan","Sæt Madplan","Se Madplan"};
        int mainMenu = ui.standardMenu("Velkommen til Din Madplan",mainMenuChoice,"Vælg et tal svarende til punkterne");

        if(mainMenu == 0){
            generateMenu();
        }

        if(mainMenu == 1){
            setMealMenu();
        }

        if(mainMenu == 2){
            errorMissingMeals(weekplan);
            showFoodPlan();
        }







    }


    //Set mealplan menus

    public void setMealMenu(){
        String[] choices = {"Søg","Antal Kuverter","Tilbage"};
        int pick = ui.standardMenu("Sæt Madplan",choices,"Vælg et tal svarende til punkterne");
        if(pick == 0){
            searchMenu();

        }
        if(pick == 1){
            personSeated(weekplan);
        }
        if(pick == 2){
            menuCollection();
        }

    }

    public void searchMenu(){
        String[] choices = {"Søg på Måltidsnavn","Kategorier","tilbage"};
        int pick = ui.standardMenu("Find et måltid:",choices,"Vælg et tal svarende til punkterne");
        if(pick == 0){
            System.out.println("Skriv rettensnavn eller dele af navnet");
            String input = ui.get();
            Meal m = pickMeal(meals,input);
            addToWeekDay(m, weekplan);
            searchMenu();
        }
        if(pick == 1){
            String[] choicesType = {"Kød","Fisk","Vegansk"};
            int pickType = ui.standardMenu("Vælg en kategori:",choicesType,"Vælg et tal svarende til punkterne");
            if(pickType == 0) {
                Meal m = pickType(meals, "kød");
                addToWeekDay(m, weekplan);
                searchMenu();
            }
            if(pickType == 1) {
                Meal m = pickType(meals, "fisk");
                addToWeekDay(m, weekplan);
                searchMenu();
            }
            if(pickType == 2) {
                Meal m = pickType(meals, "vegan");
                addToWeekDay(m, weekplan);
                searchMenu();
            }
        }
        if(pick == 2){
            setMealMenu();
        }


    }

    public void addToWeekDay(Meal meal, ArrayList<Day> weekPlan){
        String[] choices = new String[weekPlan.size()];
        for(int i = 0; i < weekPlan.size(); i++) {
            choices[i] = weekPlan.get(i).getName() + " : " + showMealName(weekPlan.get(i).getMeal());
        }

        int pick = ui.standardMenu("Vælg en dag og tilføj retten hertil :\n" + "Den valgte ret: " + meal.getName() + "\n", choices, "Vælg et tal svarende til punkterne");
        weekplan.get(pick).setMeal(meal);


    }
    public String showMealName(Meal meal){
        if(meal == null){
            return " ";
        } else {
            return meal.getName();
        }

    }


    public Meal pickMeal(ArrayList<Meal> meals, String input){
        ArrayList<Meal> tmpmeals = findmeals(input,meals);
        String[] mealNames = new String[tmpmeals.size()];
        for(int i = 0; i < tmpmeals.size(); i++) {
            mealNames[i] = tmpmeals.get(i).getName();
        }
        int dishChoice = ui.standardMenu("Vælg en ret:",mealNames,"Vælg et tal svarende til punkterne");
        return tmpmeals.get(dishChoice);
    }

    public Meal pickType(ArrayList<Meal> meals, String input){
        ArrayList<Meal> tmpmeals = findType(input,meals);
        String[] mealNames = new String[tmpmeals.size()];
        for(int i = 0; i < tmpmeals.size(); i++) {
            mealNames[i] = tmpmeals.get(i).getName();
        }
        int dishChoice = ui.standardMenu("Vælg en ret:",mealNames,"Vælg et tal svarende til punkterne");
        return tmpmeals.get(dishChoice);
    }


    //Generate mealPlan menus
    public void generateMenu(){
        String[] generateMealPlan = {"Indstilling","Antal Kuverter","Generer Madplan","Tilbage"};
        int generateMeal = ui.standardMenu("Generer tilfældig madplan",generateMealPlan,"Vælg et tal svarende til punkterne");
        if(generateMeal == 0){
            settingsMenu();
        }
        if(generateMeal == 1){
            personSeated(weekplan);
        }
        if(generateMeal == 2){
            randomFood.RandomGenerate(meals,weekplan);
            String[] choices = {"Se madplan","Hovedmenu"};
            int pick = ui.standardMenu("Madplanen er genereret!",choices,"Vælg et tal svarende til punkterne");
            if(pick == 0){
                showFoodPlan();
            }
            if(pick == 1){
                menuCollection();
            }
        }
        if(generateMeal == 3){
            menuCollection();
        }
    }

    public void settingsMenu(){
        String[] settings = {"Fiske Dag : " + randomFood.getFishDay() + "\n     Kategorier:", "Kød : " + randomFood.getMeat(),"Vegansk : " + randomFood.getVegan(), "Fisk : " + randomFood.getFish(),"Tilbage"};
        int settingsPick = ui.standardMenu("Indstillinger",settings,"Vælg et tal svarende til punkterne");
        if(settingsPick == 0){
            randomFood.setFishDay();
            settingsMenu();

        }
        if(settingsPick == 1){
            randomFood.setMeat();
            settingsMenu();
        }
        if(settingsPick == 2){
            randomFood.setVegan();
            settingsMenu();
        }
        if(settingsPick == 3){
            randomFood.setFish();
            settingsMenu();
        }
        if(settingsPick == 4){
            generateMenu();
        }

    }

    public void personSeated(ArrayList<Day> weekplan){
        String[] settings = {"Mandag" + " : " + weekplan.get(0).getPersonPrDay(),"Tirsdag" +" : " + weekplan.get(1).getPersonPrDay(),"Onsdag" + " : " + weekplan.get(2).getPersonPrDay(),"Torsdag" +" : " + weekplan.get(3).getPersonPrDay(),"Fredag" + " : " + weekplan.get(4).getPersonPrDay(),"Lørdag" + " : " + weekplan.get(5).getPersonPrDay(),"Søndag" + " : " + weekplan.get(6).getPersonPrDay(),"Tilbage"};
        int settingsPick = ui.standardMenu("Antal kuverter pr dag",settings,"Vælg et tal svarende til punkterne");
        if(settingsPick == 0){
            setSeated(settingsPick);
            personSeated(weekplan);
        }

        if(settingsPick == 1){
            setSeated(settingsPick);
            personSeated(weekplan);
        }

        if(settingsPick == 2){
            setSeated(settingsPick);
            personSeated(weekplan);
        }

        if(settingsPick == 3){
            setSeated(settingsPick);
            personSeated(weekplan);
        }

        if(settingsPick == 4){
            setSeated(settingsPick);
            personSeated(weekplan);
        }

        if(settingsPick == 5){
            setSeated(settingsPick);
            personSeated(weekplan);
        }

        if(settingsPick == 6){
            setSeated(settingsPick);
            personSeated(weekplan);
        }

        if(settingsPick == 7){
            menuCollection();
        }

    }

    public void setSeated(int pick){
        System.out.println("Intast antal af person for" + weekplan.get(pick).getName());
        int i = parseInt(ui.get());
        weekplan.get(pick).setPersonPrDay(i);
    }



    public ArrayList<Meal> findmeals(String input, ArrayList<Meal> meals) {
        ArrayList<Meal> tmp = new ArrayList<>();
        for (Meal m : meals)
            if (m.name.contains(input)) {
                tmp.add(m);
            }
        return tmp;
    }

    public ArrayList<Meal> findType(String input, ArrayList<Meal> meals) {
        ArrayList<Meal> tmp = new ArrayList<>();
        for (Meal m : meals)
            if (m.type.contains(input)) {
                tmp.add(m);
            }
        return tmp;
    }

    public void showFoodPlan(){

        for(int i = 0; i < weekplan.size(); i++){

            planner.quantityUpdate(weekplan.get(i));
            planner.showDayMeal(weekplan.get(i));

        }

        planner.shoppingList(planner.combineIngredientList(weekplan));
        planner.showShoppingList();
    }

    public void errorMissingMeals(ArrayList<Day> check){
        ArrayList<String> days = new ArrayList<String>();
        for(Day d : check){
            if(d.getMeal() == null){
                days.add(d.getName());
            }
        }
        if(days.size() > 0){
            System.out.println("Hov! du mangler at vælge måltider for følgende dag(e) :");
            for(int i = 0; i < days.size(); i++){
                System.out.println(days.get(i) + "\n");

            }
            menuCollection();

        }

    }


    //Creating an array of weekdays
    public ArrayList<Day> createWeek(){
        ArrayList<Day> week = new ArrayList<>();
        Day monday = new Day("Mandag");
        Day tuesday = new Day("Tirsdag");
        Day wednesday = new Day("Onsdag");
        Day thursday = new Day("Torsdag");
        Day friday = new Day("Fredag");
        Day saturday = new Day("Lørdag");
        Day sunday = new Day("Søndag");
        week.add(monday);
        week.add(tuesday);
        week.add(wednesday);
        week.add(thursday);
        week.add(friday);
        week.add(saturday);
        week.add(sunday);

        return week;


    }


}