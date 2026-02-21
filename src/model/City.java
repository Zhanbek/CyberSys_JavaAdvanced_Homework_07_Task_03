package model;

import java.util.ArrayList;
import java.util.List;

public class City {
    private String name;
    private String size;
    private List<Street> streets = new ArrayList<>();

    public City() {}

    public City(String name, String size) {
        this.name = name;
        this.size = size;
    }

    // Геттеры и сеттеры
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }

    public List<Street> getStreets() { return streets; }
    public void setStreets(List<Street> streets) { this.streets = streets; }

    public void addStreet(Street street) {
        this.streets.add(street);
    }

    @Override
    public String toString() {
        return "City{name='" + name + "', size='" + size + "', streets=" + streets + "}";
    }
}