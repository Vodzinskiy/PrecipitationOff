package vodzinskiy.precipitationoff.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static vodzinskiy.precipitationoff.models.Type.STANDARD;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Area {
    private Coordinate start;
    private Coordinate end;
    private Type type = STANDARD;

    public Area(Coordinate start) {
        this.start = start;
        this.end = null;
    }
}

