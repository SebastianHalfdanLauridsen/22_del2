package main;

public class Main {
    public static void main(String[] args) {
        InterfaceField field2 = new PlainField(
                2, "Tower", "You climb the tower for its riches and you get them", 250);
        InterfaceField field3 = new PlainField(
                3, "Crator", "You come across a crater and fall into it, ouch", -100);
        InterfaceField field4 = new PlainField(
                4, "Palace gates",
                "You arrive at the foot of the giant Palace gates" +
                        " and are greeted by the Palace maids," +
                        " who hand you gifts",
                100);
        InterfaceField field7 = new ExtraTurnField(
                7, "Monastery",
                "You come across a monastery, " +
                        "the monks inside feed you and let you sleep in their quarters, " +
                        "you feel well rested and get an extra turn",
                -100);

        FieldManager fieldManager = new FieldManager(0, "Field manager");

        fieldManager.addField(field2);
        fieldManager.addField(field3);
        fieldManager.addField(field4);
        fieldManager.addField(field7);

        fieldManager.getFieldDetails();
        System.out.println();

        fieldManager.removeField(field4);

        fieldManager.getFieldDetails();

    }
}