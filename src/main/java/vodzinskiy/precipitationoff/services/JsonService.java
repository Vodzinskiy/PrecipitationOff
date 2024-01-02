package vodzinskiy.precipitationoff.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import vodzinskiy.precipitationoff.models.Area;

import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static vodzinskiy.precipitationoff.PrecipitationOff.logger;

public class JsonService {

    private final Gson gson;

    private final String path = "./plugins/PrecipitationOff/area-list.json";

    public JsonService() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }



    public List<Area> get() {
        Type type = new TypeToken<List<Area>>() {}.getType();
        try (FileReader reader = new FileReader(path)) {
            List<Area> json = gson.fromJson(reader, type);
            return json != null ? json : new ArrayList<>();
        } catch (Exception e) {
            logger.error("area-list.json file is missing!\n");
            return null;
        }
    }

    public void set(List<Area> data) {
        try (FileWriter writer = new FileWriter(path)) {
            gson.toJson(data, writer);
        } catch (Exception e) {
            logger.error("area-list.json file is missing!\n");
        }
    }

    public Area getLastArea(List<Area> data, String name) {
        return data.stream()
                .filter(a -> a.getOwner().getName().equals(name))
                .reduce((a,b) -> b)
                .orElse(null);
    }

    public void deleteArea(UUID id) {
        List<Area> areas = get();
        for(int i = 0; i < areas.size(); i++) {
            Area area = areas.get(i);
            if(area.getId().equals(id)) {
                areas.remove(i);
                break;
            }
        }
        set(areas);
    }
}
