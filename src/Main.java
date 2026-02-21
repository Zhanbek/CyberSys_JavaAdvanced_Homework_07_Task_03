import model.City;
import model.Street;
import model.House;
import parser.CitySAXParser;
import xml.XMLCreator;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final String XML_DATA_FILE_PATH = "cities.xml";
    public static void main(String[] args) {

        // Створюємо масив міст
        List<City> cities = createCitiesAndLoadData();

        // Генеруємо XML файл XML_DATA_FILE_PATH, що містить елементи списку cities

        XMLCreator.createXML(cities, XML_DATA_FILE_PATH);

        // C допомогою sax-парсера парсім XML-файл XML_DATA_FILE_PATH з даними про міста
        CitySAXParser parser = new CitySAXParser();
        System.out.println("\n--- Парсинг XML-файлу за допомогою SAX ---");
        List<City> parsedCities = parser.parseXML(XML_DATA_FILE_PATH);

        // Выводим результат парсинга
        System.out.println("Результат парсинга:");
        for (City city : parsedCities) {
            System.out.println(city);
        }
    }

    private static List<City> createCitiesAndLoadData() {
        List<City> cities = new ArrayList<>();

        // Город 1: Киев
        City kyiv = new City("Kyiv", "big");

        Street khreshchatyk = new Street("Khreshchatyk");
        khreshchatyk.addHouse(new House(1, 5));
        khreshchatyk.addHouse(new House(2, 4));

        Street andriivskyi = new Street("Andriivskyi Descent");
        andriivskyi.addHouse(new House(10, 3));
        andriivskyi.addHouse(new House(12, 2));

        kyiv.addStreet(khreshchatyk);
        kyiv.addStreet(andriivskyi);
        cities.add(kyiv);

        // Город 2: Львов
        City lviv = new City("Lviv", "medium");

        Street rynok = new Street("Rynok Square");
        rynok.addHouse(new House(5, 4));
        rynok.addHouse(new House(6, 4));

        Street shevchenka = new Street("Shevchenka Ave");
        shevchenka.addHouse(new House(15, 6));
        shevchenka.addHouse(new House(17, 5));

        lviv.addStreet(rynok);
        lviv.addStreet(shevchenka);
        cities.add(lviv);

        return cities;
    }
}