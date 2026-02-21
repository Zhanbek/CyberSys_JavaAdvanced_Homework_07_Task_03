package model;

public class House {
    private int number;
    private int floors;

    public House() {}

    public House(int number, int floors) {
        this.number = number;
        this.floors = floors;
    }

    // Геттеры и сеттеры
    public int getNumber() { return number; }
    public void setNumber(int number) { this.number = number; }

    public int getFloors() { return floors; }
    public void setFloors(int floors) { this.floors = floors; }

    @Override
    public String toString() {
        return "House{number=" + number + ", floors=" + floors + "}";
    }
}