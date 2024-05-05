package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.hibernate.type.format.jackson.JacksonXmlFormatMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.List;

@RestController
public class BookController {

    @GetMapping("/convert")
    public String convertXmlToJson() {
        try {
            // Read XML file and parse it to list of books
            XmlMapper xmlMapper = new XmlMapper();
            List<Book> books = xmlMapper.readValue(new File("D:\\xml\\books.xml"), xmlMapper.getTypeFactory().constructCollectionType(List.class, Book.class));
            // Convert list of books to JSON
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            String json = objectMapper.writeValueAsString(books);
            // Write JSON to file (optional)
            objectMapper.writeValue(new File("D:\\xml\\books_2.json"), books);
            return json;

        } catch (Exception e) {
            e.printStackTrace();
            return "Error occures" + e.getMessage();
        }
    }

}
