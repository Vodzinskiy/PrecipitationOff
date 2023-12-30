package vodzinskiy.precipitationoff.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class Owner {
    private UUID uuid;
    private String name;
}
