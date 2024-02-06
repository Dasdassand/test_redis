package com.example.kafka.parser;

import com.example.kafka.dto.GetCurrency;
import com.example.kafka.entity.Currency;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.springframework.stereotype.Component;

import java.io.StringWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Component
public class CurrencyParserCB implements CurrencyParser {
    public GetCurrency parse(String xml) {
        Map<String, Currency> currencyMap = new HashMap<>();
        String[] xmlSplits = prettyPrintByDom4j(xml, 1, true).split("\n");
        String id, charCode, name;
        int numCode, nominal;
        double value, valueRate;
        for (int i = 2; i < xmlSplits.length - 8; i += 8) {
            id = xmlSplits[i].replaceAll("ID=\"(\\w+)\"", "").replaceAll(" ", "");
            numCode = Integer.parseInt(xmlSplits[i + 1].replaceAll("<[^>]*>", "").replaceAll(" ", ""));
            charCode = xmlSplits[i + 2].replaceAll("<[^>]*>", "").replaceAll(" ", "");
            nominal = Integer.parseInt(xmlSplits[i + 3].replaceAll("<[^>]*>", "").replaceAll(" ", ""));
            name = xmlSplits[i + 4].replaceAll("<[^>]*>", "");
            value = Double.parseDouble(xmlSplits[i + 5].replaceAll("<[^>]*>", "").replaceAll(" ", "").replaceAll(",", "."));
            valueRate = Double.parseDouble(xmlSplits[i + 5].replaceAll("<[^>]*>", "").replaceAll(" ", "").replaceAll(",", "."));
            currencyMap.put(name, new Currency(id, numCode, charCode, nominal, name, value, valueRate));
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate date = LocalDate.parse(xmlSplits[1]
                .replaceAll(".*Date=\"(.*?)\".*", "$1").replaceAll(" ", ""), formatter);
        return new GetCurrency(date, currencyMap);
    }

    private String prettyPrintByDom4j(String xmlString, int indent, boolean skipDeclaration) {
        try {
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setIndentSize(indent);
            format.setSuppressDeclaration(skipDeclaration);
            format.setEncoding("windows-1251");

            org.dom4j.Document document = DocumentHelper.parseText(xmlString);
            StringWriter sw = new StringWriter();
            XMLWriter writer = new XMLWriter(sw, format);
            writer.write(document);
            return sw.toString();
        } catch (Exception e) {
            throw new RuntimeException("Error occurs when pretty-printing xml:\n" + xmlString, e);
        }
    }
}
