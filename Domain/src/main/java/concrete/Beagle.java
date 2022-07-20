package concrete;

import lombok.Getter;
import lombok.Setter;

public class Beagle {
    @Setter
    @Getter
    private String name;

    public Beagle(String name) {
        this.name = name;
    }
}
