package com.example.kafka.parser;

import com.example.kafka.entity.GetCurrency;
import com.example.kafka.entity.Currency;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class CurrencyParserCB implements CurrencyParser {
    public GetCurrency parse(String xml) {
        String date = "";
        List<Currency> currencies = new ArrayList<>();
        try {
            try (StringReader reader = new StringReader(xml)) {
                InputSource source = new InputSource(reader);
                DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                Document document = builder.parse(source);
                date = document.getFirstChild().getAttributes().item(0).getNodeValue();
                NodeList list = document.getElementsByTagName("Valute");
                Element element;
                for (int valuteIdx = 0; valuteIdx < list.getLength(); valuteIdx++) {
                    element = (Element) list.item(valuteIdx);
                    var currency = Currency.builder()
                            .id(element.getAttributes().item(0).getNodeValue())
                            .numCode(Integer.parseInt(element.getElementsByTagName("NumCode").item(0).getTextContent()))
                            .charCode(element.getElementsByTagName("CharCode").item(0).getTextContent())
                            .nominal(Integer.parseInt(element.getElementsByTagName("Nominal").item(0).getTextContent()))
                            .name(element.getElementsByTagName("Name").item(0).getTextContent())
                            .value(Double.parseDouble(element.getElementsByTagName("Value").item(0).getTextContent().replaceAll(",", ".")))
                            .vunitRate(Double.parseDouble(element.getElementsByTagName("VunitRate").item(0).getTextContent().replaceAll(",", ".")))
                            .build();
                    currencies.add(currency);
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate dateL = LocalDate.parse(date, formatter);
        return new GetCurrency(dateL, currencies);
    }
}


