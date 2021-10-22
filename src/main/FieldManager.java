package main;

import java.util.ArrayList;
import java.util.List;

public class FieldManager implements Field {
    /**
     * The composite from the Field component in the composite design pattern
     */

    private long id;
    private String name;

    private List<Field> childFields;

    public FieldManager(long id, String name) {
        this.id = id;
        this.name = name;
        this.childFields = new ArrayList<>();
    }

    public void getFieldDetails() {
        childFields.forEach(Field::getFieldDetails);
    }

    public void addField(Field field) {
        childFields.add(field);
    }

    public void removeField(Field field) {
        childFields.remove(field);
    }
}
