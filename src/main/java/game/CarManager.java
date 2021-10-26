package game;

import die.Die;
import gui_fields.GUI_Car;
import main.Main;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Contains all instances of CarManager
 * Constructs car object via createCar
 * Create car contains all attributes of Car
 * childCars is for storage and listing of all car objects in the ArrayList
 */

public class CarManager {

    private static List<GUI_Car> childCars = new ArrayList<>();

    public static void createCar(Color primary_color, Color pattern_color, GUI_Car.Type vehicle_type, GUI_Car.Pattern paint_pattern) {
        //add new car with primary color, pattern color, car type and paint pattern as parameters
        GUI_Car car = new GUI_Car(
                primary_color,
                pattern_color,
                vehicle_type,
                paint_pattern
        );
        //Add the car to the CarManager ArrayList
        CarManager.addCar(car);

    }

    public static void addCar(GUI_Car car) {
        childCars.add(car);
    }

    public static GUI_Car getCar(int index) {
        return childCars.get(index-1);
    }

    public void removeCar(int index) {
        childCars.remove(index-1);
    }

}
