package main;

public class Main {
    public static void main(String[] args) {
        Field field2 = new PlainField(
                2, "Tower", "You climb the tower for its riches and you get them", 250);
        Field field3 = new PlainField(
                3, "Crater", "You stumple upon a crater, you fall in it, ouch", -100);

        FieldManager fieldManager = new FieldManager(0, "Field manager");

        fieldManager.addField(field2);
        fieldManager.addField(field3);

        fieldManager.getFieldDetails();
    }
}