package ICE;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;


public class Search{

    public static <resultSet> void search() {


        int dishNr = 1;
        boolean m = true;
        Scanner input = new Scanner(System.in);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ice", "root", "Fifa86");
            Statement statement = connection.createStatement();



            System.out.println("Press 1 to search for dishes");
            System.out.println("Press 2 to search for a specific dish.");
            System.out.println("Press 3 Select by calorie content");
            System.out.println("Press 4 Search for meat or vegan.");
            System.out.println("pres 5 to see description");


            switch (input.nextInt()) {


                case 1:
                    System.out.println("Search for dishes by typing the dish name:");
                    String text = input.next();
                    ResultSet resultSet = statement.executeQuery("SELECT * FROM ice.dishes where namee LIKE '" + text + "%'");

                    while (resultSet.next()) {
                        System.out.println("Dish:" + dishNr + " " + resultSet.getInt("dish_id") + " " + resultSet.getString("namee"));
                        dishNr++;

                    }
                    System.out.println("select dish number to view the recipe");
                    System.out.println("press M to go back to menu");
                    String text1 = input.next();

                        ResultSet resultSet1 = statement.executeQuery("SELECT * FROM ice.ingredients join collector on ice.ingredients.id = collector.ingID join dishes on collector.dishID = dishes.dish_id  where dish_id LIKE '" + text1 + "%'");
                        while (resultSet1.next()) {
                            System.out.println(text1 + " " + resultSet1.getString("name") + " " + resultSet1.getFloat("unitValue") + " " + resultSet1.getString("unit"));
                        }
                    System.out.println("press dish number to view the description");
                    System.out.println("press M to go back to menu");
                        String text2 = input.next();
                        ResultSet resultSet2 = statement.executeQuery("SELECT * FROM ice.ingredients join collector on ice.ingredients.id = collector.ingID join dishes on collector.dishID = dishes.dish_id  where dish_id LIKE  '" + text2 + "%'");
                      while (resultSet2.next()) {
                          System.out.println(text2 + " " + resultSet2.getString("description"));
                       }
                      search();

                    break;
                case 2:
                    System.out.println("Search for specific dish by typing your ingredients:");
                    String text3 = input.next();
                    ResultSet resultSet3 = statement.executeQuery("SELECT * FROM ice.ingredients join collector on ice.ingredients.id = collector.ingID join dishes on collector.dishID = dishes.dish_id  where name LIKE '" + text3 + "%'");
                    while (resultSet3.next()) {
                    System.out.println("Dish:" + dishNr + " " + resultSet3.getInt("dish_id") + " " + resultSet3.getString("namee"));
                    dishNr++;
                    }
                    System.out.println("select dish number to view the recipe");
                    System.out.println("press M to go back to menu");
                    String text4 = input.next();
                    ResultSet resultSet4 = statement.executeQuery("SELECT * FROM ice.ingredients join collector on ice.ingredients.id = collector.ingID join dishes on collector.dishID = dishes.dish_id  where dish_id LIKE '" + text4 + "%'");
                        while (resultSet4.next()) {
                            System.out.println("Dish : " + resultSet4.getInt("dish_id") + " " + resultSet4.getString("name") + " " + resultSet4.getFloat("unitValue") + " " + resultSet4.getString("unit"));
                            dishNr++;
                        }
                    System.out.println("press dish number to view the description");
                    System.out.println("press M to go back to menu");
                    String text5 = input.next();
                    ResultSet resultSet5 = statement.executeQuery("SELECT * FROM ice.ingredients join collector on ice.ingredients.id = collector.ingID join dishes on collector.dishID = dishes.dish_id  where dish_id LIKE  '" + text5 + "%'");
                    while (resultSet5.next()) {
                        System.out.println(text5 + " " + resultSet5.getString("description"));
                    }
                    search();

                    break;

                case 3:
                    System.out.println("Press 3 Select by calorie content");
                    String text6 = input.next();
                    System.out.println("Skriv antal personer:");
                    float numberOfpersons = input.nextFloat();
                    ResultSet resultSet6 = statement.executeQuery("SELECT * FROM ice.dishes join collector on dishes.dish_id=collector.dishID join ingredients on collector.ingID=ingredients.id WHERE name LIKE '" + text6 + "%'");

                    while (resultSet6.next()) {
                        System.out.println(resultSet6.getString("dishName") +" - Ingrediens: "+resultSet6.getString("ingName")+", mængde: "+((resultSet6.getFloat("unitValue")*numberOfpersons)+" "+resultSet6.getString("unit") +", energi: "+(resultSet6.getFloat("energyValue")*resultSet6.getFloat("unitValue")*numberOfpersons))+" "+resultSet6.getString("energyUnit"));
                    }
                    break;

                case 4:
                    System.out.println("Search for meat or vegan.");
                    String text7 = input.next();
                    ResultSet resultSet7 = statement.executeQuery("SELECT * FROM ice.dishes where type LIKE '" + text7 + "%'");
                    while (resultSet7.next()) {
                        System.out.println("Dish:" + dishNr + " " + resultSet7.getInt("dish_id") + " " + resultSet7.getString("type") + ": " + resultSet7.getString("namee"));
                        dishNr++;
                    }
                    System.out.println("select dish number to view the recipe");
                    System.out.println("press M to go back to menu");
                    String text8 = input.next();
                    ResultSet resultSet8 = statement.executeQuery("SELECT * FROM ice.ingredients join collector on ice.ingredients.id = collector.ingID join dishes on collector.dishID = dishes.dish_id  where dish_id LIKE '" + text8 + "%'");
                    while (resultSet8.next()) {
                        System.out.println( "Dish: " + text8 + " " + resultSet8.getString("name") + " " + resultSet8.getFloat("unitValue") + " " + resultSet8.getString("unit"));
                        dishNr++;
                    }
                    System.out.println("press dish number to view the description");
                    System.out.println("press M to go back to menu");
                    String text9 = input.next();
                    ResultSet resultSet9 = statement.executeQuery("SELECT * FROM ice.ingredients join collector on ice.ingredients.id = collector.ingID join dishes on collector.dishID = dishes.dish_id  where dish_id LIKE  '" + text9 + "%'");
                    while (resultSet9.next()) {
                        System.out.println(text9 + " " + resultSet9.getString("description"));
                    }
                    search();

                    break;
                case 5:
                    System.out.println("pres 5 to see description");
                    String text10 = input.next();
                    ResultSet resultSet10 = statement.executeQuery("SELECT * FROM ice.ingredients join collector on ice.ingredients.id = collector.ingID join dishes on collector.dishID = dishes.dish_id  where dish_id LIKE  '" + text10 + "%'");

            }


            connection.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
