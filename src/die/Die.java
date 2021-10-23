package die;
import java.lang.Math;

/**
 * Contains the die's face value
 * Added random method
 */
public class Die {

    private int face_value;

    public Die(int face_value){
        this.face_value = face_value;
    }

    private int random(long min, long max) {
        return (int) ((Math.random() * max) + min);
    }

    public int getFaceValue() {
        return face_value;
    }

    public void setFaceValue(int value) {
        face_value = value;
    }
}
