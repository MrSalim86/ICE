package ICE;
import java.sql.*;
import java.util.ArrayList;

public class DbIO {

    Connection connection = null;
    Statement statement = null;



    String jdbcUrl = "jdbc:mysql://localhost/madplan?" + "autoReconnect=true&useSSL=false";
    String username = "root";
    String password = "Grycecilie0510";

    public ArrayList<Meal> generateMeals(){
        ArrayList<Meal> meals = new ArrayList<>();

        try {
            connection = DriverManager.getConnection(jdbcUrl, username, password);


            //Generating each meal
            statement = connection.createStatement();
            String sql = "SELECT * FROM dishes";
            ResultSet rs = statement.executeQuery(sql);

            while(rs.next()) {
                int dishId = rs.getInt("id");
                String dishName = rs.getString("name");
                String favorite = rs.getString("favorite");
                String description = rs.getString("description");
                String type = rs.getString("type");

                Meal meal = new Meal(dishName,type);
                meal.setDescription(description);
                meal.setFavorite(favorite);
                meals.add(meal);

                //Generating all ingredients for each Meal
                String ingredient = "SELECT * FROM collector JOIN ingredients ON collector.ingID = ingredients.id  WHERE collector.dishID = ?";
                PreparedStatement statement = connection.prepareStatement(ingredient);
                statement.setInt(1, dishId);
                ResultSet result = statement.executeQuery();

                while(result.next()){
                    String ingredientName = result.getString("name");
                    String ingredientUnit = result.getString("unit");
                    String ingredientEnergyUnit = result.getString("energyUnit");
                    float ingredientUnitValue = result.getFloat("unitValue");
                    // Calculate total energy of meal, based on ingredients
                    int ingredientEnergyPrUnit = result.getInt("energyValue");
                    float ingredientEnergyValue = ingredientUnitValue * ingredientEnergyPrUnit;
                    meal.setTotalEnergy(ingredientEnergyValue);

                    meal.addIngredient(ingredientName,ingredientUnitValue,ingredientUnit,ingredientEnergyValue,ingredientEnergyUnit);
                }




            }
            connection.close();

        } catch(SQLException e) {
            e.printStackTrace();


        }


        return meals;

    }


}