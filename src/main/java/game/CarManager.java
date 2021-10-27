package game;

import gui_fields.GUI_Car;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Contains all instances of CarManager and constructs car object via createCar.
 * Create car contains all attributes of Car and childCars is for storage and listing of all car objects in the ArrayList
 */
public class CarManager {

    private final static List<GUI_Car> childCars = new ArrayList<>();

    /**
     * Creates a new car with parameters for customization and adds it to the CarManager
     * @param primary_color the primary color of the car of type Color
     * @param pattern_color the pattern color of the car of type Color
     * @param vehicle_type the vehicle type of the car of type GUI_Car.Type
     * @param paint_pattern the pattern type of the car of type GUI_Car.type
     */
    public static void createCar(Color primary_color, Color pattern_color, GUI_Car.Type vehicle_type, GUI_Car.Pattern paint_pattern) {
        GUI_Car car = new GUI_Car(
                primary_color,
                pattern_color,
                vehicle_type,
                paint_pattern
        );
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
