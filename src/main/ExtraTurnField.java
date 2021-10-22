package main;

public class ExtraTurnField extends AbstractField{
    /**
     * A leaf class from the AbstractField composite in the composite design pattern
     */

    public ExtraTurnField(long id, String name, String text, long value) {
        this.id = id;
        this.name = name;
        this.text = text;
        this.value = value;
    }

    public void skipTurn() {

    }
    //method for skipping other players here
}
