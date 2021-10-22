package main;

public abstract class AbstractField implements InterfaceField {
    /**
     * A composite class from the InterfaceField component in the composite design pattern
     */

    //attributes are protected because the subclasses should have access to them
    protected String name;
    protected long id;
    protected String text;
    protected long value; //please more specific

    @Override
    public void getFieldDetails() {
        System.out.println("Field " + id + " '" + name + "' '" + text + "' " + value);
    }

}
