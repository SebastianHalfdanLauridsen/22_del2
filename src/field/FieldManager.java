package field;

import java.util.ArrayList;
import java.util.List;

/**
 * A composite from the InterfaceField component in the field composite design pattern
 */
public class FieldManager implements InterfaceField {

    private final long id;
    private final String name;

    private final List<InterfaceField> childFields;

    public FieldManager(long id, String name) {
        this.id = id;
        this.name = name;
        this.childFields = new ArrayList<>();
    }

    @Override
    public void getFieldDetails() {
        childFields.forEach(InterfaceField::getFieldDetails);
    }

    /**
     * Takes an index to the ArrayList childFields and returns an object of type InterfaceField from childFields
     * @param index An index in childFields
     * @return An object of type InterfaceField from childFields
     */
    public InterfaceField getField(int index) {
        return childFields.get(index);
    }

    /** Takes an object of type InterfaceField and adds it to the ArrayList childFields
     * @param field An object of type InterfaceField
     */
    public void addField(InterfaceField field) {
        childFields.add(field);
    }

    /**
     * Removes an object of type InterfaceField from the ArrayList childFields
     * @param index An index in childFields
     */
    public void removeField(int index) {
        childFields.remove(index-1);
    }

    @Override
    public String toString() {
        return "FieldManager{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}