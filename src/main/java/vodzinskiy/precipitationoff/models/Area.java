package vodzinskiy.precipitationoff.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

import static vodzinskiy.precipitationoff.models.Type.NOT_DEFINED;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Area {
    private String name;
    private UUID id;
    private Coordinate start;
    private Coordinate end;
    private Type type = NOT_DEFINED;
    private Owner owner;

    public Area(Coordinate start, Owner owner) {
        this.id = UUID.randomUUID();
        this.owner = owner;
        this.start = start;
        this.end = null;
    }
}

