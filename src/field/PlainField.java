package field;

/**
 * A leaf class from the AbstractField composite in the field composite design pattern
 */
public class PlainField extends AbstractField {

    public PlainField(long id, String name, String text, long value) {
        this.id = id;
        this.name = name;
        this.text = text;
        this.value = value;
    }
}
