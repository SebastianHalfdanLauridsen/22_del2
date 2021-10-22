package main;

public class PlainField extends AbstractField {
    /**
     * A leaf class from the AbstractField composite in the composite design pattern
     */

    public PlainField(long id, String name, String text, long value) {
        this.id = id;
        this.name = name;
        this.text = text;
        this.value = value;
    }
}
