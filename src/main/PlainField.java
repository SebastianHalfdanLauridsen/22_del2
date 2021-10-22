package main;


public class PlainField implements Field {
    /**
     * A leaf class from the Field component in the composite design pattern
     */
    String name;
    long id;
    String text;
    long value; //please more specific

    public PlainField(long id, String name, String text, long value) {
        this.id = id;
        this.name = name;
        this.text = text;
        this.value = value;
    }

    @Override
    public void getFieldDetails() {
        System.out.println("Field " + id + " '" + name + "' '" + text + "' " + value);
    }
}
