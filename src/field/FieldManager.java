package field;

import java.util.ArrayList;
import java.util.List;

public class FieldManager implements InterfaceField {
    /**
     * A composite from the InterfaceField component in the composite design pattern
     */

    private long id;
    private String name;

    private List<InterfaceField> childFields;

    public FieldManager(long id, String name) {
        this.id = id;
        this.name = name;
        this.childFields = new ArrayList<>();
    }

    public void getFieldDetails() {
        childFields.forEach(InterfaceField::getFieldDetails);
    }

    public void addField(InterfaceField field) {
        childFields.add(field);
    }

    public void removeField(InterfaceField field) {
        childFields.remove(field);
    }

    @Override
    public String toString() {
        return "FieldManager{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}