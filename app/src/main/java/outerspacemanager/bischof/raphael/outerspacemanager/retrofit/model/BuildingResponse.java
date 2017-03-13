package outerspacemanager.bischof.raphael.outerspacemanager.retrofit.model;

import java.util.ArrayList;

public class BuildingResponse {
    public final int size;
    public final ArrayList<Building> buildings;

    public BuildingResponse(int size, ArrayList<Building> buildings) {
        this.size = size;
        this.buildings = buildings;
    }
}
