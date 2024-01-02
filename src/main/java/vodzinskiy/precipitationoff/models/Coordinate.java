package vodzinskiy.precipitationoff.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Coordinate {
    private int x;
    private int y;
    private int z;

    @Override
    public String toString() {
        return String.format("(%s, %s, %s)", x, y, z);
    }
}
