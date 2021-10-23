package field;

/**
 * A composite class from the InterfaceField component in the field composite design pattern
 */
public abstract class AbstractField implements InterfaceField {

    //attributes are protected because the subclasses should have access to them
    protected String name;
    protected long id;
    protected String text;
    protected long value; //please more specific

    @Override
    public void getFieldDetails() {
        System.out.println("Field " + id + " '" + name + "' '" + text + "' " + value);
    }

    @Override
    public String toString() {
        return "AbstractField{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", text='" + text + '\'' +
                ", value=" + value +
                '}';
    }
}
