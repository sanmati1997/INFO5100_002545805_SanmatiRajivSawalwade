import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


//# How to add org.json library (without Maven)
//        * Download the JSON library JAR file (included under lib dir locally along with my project):
//        https://repo1.maven.org/maven2/org/json/json/20231013/json-20231013.jar
//        * Open IntelliJ → File → Project Structure
//        * Click "Project Settings" and click "library"
//        * Click the + button → choose "JARs or Directories"
//        * Select the path for json-20231013.jar file (under lib dir locally in my submitted project)
//        * Click "Apply"

// Also kindly set the output path if prompted
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.*;

public class Main {

    // ---------- SAMPLE XML STRING ----------
    private static final String SAMPLE_XML =
            "<BookShelf>" +
                    "<Book>" +
                    "<title>Atomic Habits</title>" +
                    "<publishedYear>2018</publishedYear>" +
                    "<numberOfPages>320</numberOfPages>" +
                    "<authors>" +
                    "<author>James Clear</author>" +
                    "</authors>" +
                    "</Book>" +
                    "<Book>" +
                    "<title>The Alchemist</title>" +
                    "<publishedYear>1988</publishedYear>" +
                    "<numberOfPages>208</numberOfPages>" +
                    "<authors>" +
                    "<author>Paulo Coelho</author>" +
                    "</authors>" +
                    "</Book>" +
                    "<Book>" +
                    "<title>Clean Code</title>" +
                    "<publishedYear>2008</publishedYear>" +
                    "<numberOfPages>464</numberOfPages>" +
                    "<authors>" +
                    "<author>Robert C. Martin</author>" +
                    "</authors>" +
                    "</Book>" +
                    "</BookShelf>";

    // ---------- SAMPLE JSON STRING ----------
    private static final String SAMPLE_JSON =
            "{ \"BookShelf\": {" +
                    "\"Book\": [" +
                    "{ \"title\":\"Atomic Habits\", \"publishedYear\":2018, \"numberOfPages\":320, \"authors\":[\"James Clear\"] }," +
                    "{ \"title\":\"The Alchemist\", \"publishedYear\":1988, \"numberOfPages\":208, \"authors\":[\"Paulo Coelho\"] }," +
                    "{ \"title\":\"Clean Code\", \"publishedYear\":2008, \"numberOfPages\":464, \"authors\":[\"Robert C. Martin\"] }" +
                    "]}}";

    public static void main(String[] args) throws Exception {

        // ---------------- XML PARSING ----------------
        System.out.println("====== XML PARSER OUTPUT ======");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document xmlDoc = builder.parse(new ByteArrayInputStream(SAMPLE_XML.getBytes()));
        printXML(xmlDoc);

        // Add a new book to XML
        addBookToXML(xmlDoc, "Deep Work", 2016, 296, Arrays.asList("Cal Newport"));

        System.out.println("\n====== XML AFTER ADDING NEW BOOK ======");
        printXML(xmlDoc);


        // ---------------- JSON PARSING ----------------
        System.out.println("\n====== JSON PARSER OUTPUT ======");
        JSONObject json = new JSONObject(SAMPLE_JSON);
        printJSON(json);

        // Add new book to JSON
        addBookToJSON(json, "Deep Work", 2016, 296, Arrays.asList("Cal Newport"));

        System.out.println("\n====== JSON AFTER ADDING NEW BOOK ======");
        printJSON(json);
    }

    // ----------- PRINT XML ----------
    private static void printXML(Document doc) {
        NodeList books = doc.getElementsByTagName("Book");
        for (int i = 0; i < books.getLength(); i++) {
            Element book = (Element) books.item(i);

            String title = book.getElementsByTagName("title").item(0).getTextContent();
            String year = book.getElementsByTagName("publishedYear").item(0).getTextContent();
            String pages = book.getElementsByTagName("numberOfPages").item(0).getTextContent();

            System.out.println("Book:");
            System.out.println("  Title: " + title);
            System.out.println("  Year: " + year);
            System.out.println("  Pages: " + pages);

            System.out.print("  Authors: ");
            NodeList authorNodes = book.getElementsByTagName("author");
            for (int j = 0; j < authorNodes.getLength(); j++) {
                System.out.print(authorNodes.item(j).getTextContent());
                if (j < authorNodes.getLength() - 1) System.out.print(", ");
            }
            System.out.println("\n");
        }
    }

    // ----------- ADD BOOK TO XML ----------
    private static void addBookToXML(Document doc, String title, int year, int pages, List<String> authors) {
        Element root = doc.getDocumentElement();
        Element newBook = doc.createElement("Book");

        Element t = doc.createElement("title");
        t.appendChild(doc.createTextNode(title));

        Element y = doc.createElement("publishedYear");
        y.appendChild(doc.createTextNode(String.valueOf(year)));

        Element p = doc.createElement("numberOfPages");
        p.appendChild(doc.createTextNode(String.valueOf(pages)));

        Element auth = doc.createElement("authors");
        for (String a : authors) {
            Element e = doc.createElement("author");
            e.appendChild(doc.createTextNode(a));
            auth.appendChild(e);
        }

        newBook.appendChild(t);
        newBook.appendChild(y);
        newBook.appendChild(p);
        newBook.appendChild(auth);

        root.appendChild(newBook);
    }

    // ----------- PRINT JSON ----------
    private static void printJSON(JSONObject json) {
        JSONArray books = json.getJSONObject("BookShelf").getJSONArray("Book");

        for (int i = 0; i < books.length(); i++) {
            JSONObject b = books.getJSONObject(i);
            System.out.println("Book:");
            System.out.println("  Title: " + b.getString("title"));
            System.out.println("  Year: " + b.getInt("publishedYear"));
            System.out.println("  Pages: " + b.getInt("numberOfPages"));
            System.out.println("  Authors: " + b.getJSONArray("authors"));
            System.out.println();
        }
    }

    // ----------- ADD BOOK TO JSON ----------
    private static void addBookToJSON(JSONObject json, String title, int year, int pages, List<String> authors) {
        JSONObject book = new JSONObject();
        book.put("title", title);
        book.put("publishedYear", year);
        book.put("numberOfPages", pages);
        book.put("authors", authors);

        json.getJSONObject("BookShelf").getJSONArray("Book").put(book);
    }
}
