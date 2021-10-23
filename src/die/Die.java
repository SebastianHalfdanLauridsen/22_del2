package die;
//TODO make le class:
// - Make 'random' method.
// - Make sufficient attributes and their respective get and set methods.
// - Make a constructor for when we want a new Die.

/**
 *
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
