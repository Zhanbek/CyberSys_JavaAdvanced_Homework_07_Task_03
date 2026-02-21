package parser;

import model.City;
import model.Street;
import model.House;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CitySAXParser extends DefaultHandler {

    private List<City> cities;
    private City currentCity;
    private Street currentStreet;
    private StringBuilder currentValue;
    private boolean inCity;

    public List<City> parseXML(String filePath) {
        cities = new ArrayList<>();
        currentValue = new StringBuilder();
        inCity = false;

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            saxParser.parse(new File(filePath), this);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return cities;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        System.out.println("Начало элемента: " + qName);

        switch (qName) {
            case "city":
                currentCity = new City();
                String size = attributes.getValue("size");
                if (size != null) {
                    currentCity.setSize(size);
                }
                inCity = true;
                currentValue.setLength(0);
                System.out.println("  Найден город с размером: " + size);
                break;

            case "street":
                currentStreet = new Street();
                String streetName = attributes.getValue("name");
                if (streetName != null) {
                    currentStreet.setName(streetName);
                    System.out.println("  Найдена улица: " + streetName);
                }
                break;

            case "house":
                House house = new House();
                String number = attributes.getValue("number");
                String floors = attributes.getValue("floors");

                if (number != null) {
                    house.setNumber(Integer.parseInt(number));
                }
                if (floors != null) {
                    house.setFloors(Integer.parseInt(floors));
                }

                if (currentStreet != null) {
                    currentStreet.addHouse(house);
                    System.out.println("  Добавлен дом: №" + number + ", этажей: " + floors);
                }
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String text = new String(ch, start, length);
        if (!text.trim().isEmpty()) {
            System.out.println("Текст: '" + text + "'");
            if (inCity) {
                currentValue.append(text);
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        System.out.println("Конец элемента: " + qName);

        switch (qName) {
            case "city":
                if (currentCity != null) {
                    String cityName = currentValue.toString().trim();
                    System.out.println("  Имя города из текста: '" + cityName + "'");
                    currentCity.setName(cityName);
                    cities.add(currentCity);
                    currentCity = null;
                }
                inCity = false;
                break;

            case "street":
                if (currentStreet != null && currentCity != null) {
                    currentCity.addStreet(currentStreet);
                }
                currentStreet = null;
                break;
        }
    }

    @Override
    public void startDocument() {
        System.out.println("=== Начало парсинга документа ===");
    }

    @Override
    public void endDocument() {
        System.out.println("=== Парсинг документа завершен ===");
    }
}