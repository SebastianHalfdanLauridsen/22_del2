package die;

/**
 * Contains the die's face value
 */
public class Die {

    private int face_value;
    private final long id;

    /**
     * Creates a die with a face value and an ID
     * @param face_value is the value of the die
     * @param id used to refer to the specified die
     */
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

    @Override
    public String toString() {
        return "Die{" +
                "face_value=" + face_value +
                ", id=" + id +
                '}';
    }
}
