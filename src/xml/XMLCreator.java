package xml;

import model.City;
import model.Street;
import model.House;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;

public class XMLCreator {

    public static void createXML(List<City> cities, String filePath) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // Создаем корневой элемент
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("cities");
            doc.appendChild(rootElement);

            // Добавляем города
            for (City city : cities) {
                Element cityElement = doc.createElement("city");
                cityElement.setAttribute("size", city.getSize());
                cityElement.setTextContent(city.getName());
                rootElement.appendChild(cityElement);

                // Создаем элемент streets для группировки улиц
                Element streetsElement = doc.createElement("streets");
                cityElement.appendChild(streetsElement);

                // Добавляем улицы для города
                for (Street street : city.getStreets()) {
                    Element streetElement = doc.createElement("street");
                    streetElement.setAttribute("name", street.getName()); // Имя улицы как атрибут
                    streetsElement.appendChild(streetElement);

                    // Создаем элемент houses для группировки домов
                    Element housesElement = doc.createElement("houses");
                    streetElement.appendChild(housesElement);

                    // Добавляем дома для улицы
                    for (House house : street.getHouses()) {
                        Element houseElement = doc.createElement("house");
                        houseElement.setAttribute("number", String.valueOf(house.getNumber()));
                        houseElement.setAttribute("floors", String.valueOf(house.getFloors()));
                        housesElement.appendChild(houseElement);
                    }
                }
            }

            // Записываем в XML файл
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filePath));

            transformer.transform(source, result);

            System.out.println("XML файл успешно создан: " + filePath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}