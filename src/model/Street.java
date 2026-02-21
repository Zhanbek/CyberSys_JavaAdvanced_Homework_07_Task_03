package model;

import java.util.ArrayList;
import java.util.List;

public class Street {
    private String name;
    private List<House> houses = new ArrayList<>();

    public Street() {}

    public Street(String name) {
        this.name = name;
    }

    // Геттеры и сеттеры
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<House> getHouses() { return houses; }
    public void setHouses(List<House> houses) { this.houses = houses; }

    public void addHouse(House house) {
        this.houses.add(house);
    }

    @Override
    public String toString() {
        return "Street{name='" + name + "', houses=" + houses + "}";
    }
}