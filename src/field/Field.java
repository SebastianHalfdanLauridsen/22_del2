package field;

/**
 * A leaf class from the AbstractField composite in the field composite design pattern
 */
public class Field extends AbstractField {

    public Field(long id, String name, String text, long value) {
        this.id = id;
        this.name = name;
        this.text = text;
        this.value = value;
    }
}
