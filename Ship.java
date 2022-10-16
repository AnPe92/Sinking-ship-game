import java.util.HashMap;

public class Ship {
    String name;
    int length;
    int value;

    public Ship(String name, int length, int value) {
        this.name = name;
        this.length = length;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
