package die;
import java.lang.Math;

/**
 * Contains the die's face value
 * Added random method
 */
public class Die {

    private int face_value;
    private final long id;

    public Die(int face_value, long id){
        this.face_value = face_value;
        this.id = id;
    }

    public int getFaceValue() {
        return face_value;
    }

    public void setFaceValue(int value) {
        face_value = value;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Die{" +
                "face_value=" + face_value +
                ", id=" + id +
                '}';
    }
}
