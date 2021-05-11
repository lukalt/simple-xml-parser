package lt.lukasa.xmlparse.parser;

import lt.lukasa.xmlparse.TestResources;
import lt.lukasa.xmlparse.api.Document;
import lt.lukasa.xmlparse.api.Tag;
import lt.lukasa.xmlparse.exception.XmlParseException;
import lt.lukasa.xmlparse.reader.XmlDocumentReader;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;


/**
 * @author Lukas Alt
 * @since 26.04.2021
 */
public class DocumentReadTest {
    @Test
    void checkBookRead() throws IOException, XmlParseException {
        Document document = new XmlDocumentReader(TestResources.getResource("./xml-examples/books.xml")).read();
        Tag firstBook = document.getRoot().getFirstChild("book");
        assertEquals("bk101", firstBook.getAttributeValue("id"));
        assertEquals("Gambardella, Matthew", firstBook.getFirstChild("author").getTagValue());
        assertEquals("XML Developer's Guide", firstBook.getFirstChild("title").getTagValue());
        assertEquals("Computer", firstBook.getFirstChild("genre").getTagValue());
        assertEquals("44.95", firstBook.getFirstChild("price").getTagValue());
        assertEquals(44.95, firstBook.getFirstChild("price").getDoubleValue());
        assertEquals(44.95F, firstBook.getFirstChild("price").getFloatValue());
        assertThrows(NumberFormatException.class, () -> firstBook.getFirstChild("genre").getIntValue());
        assertThrows(NumberFormatException.class, () -> firstBook.getFirstChild("title").getIntValue());
        assertEquals("2000-10-01", firstBook.getFirstChild("publish_date").getTagValue());
       /* assertEquals("An in-depth look at creating applications\n" +
                "            with XML.", firstBook.getFirstChild("description").getTagValue());*/

        for (Tag book : document.getRoot().getChildren("book")) {
            assertTrue(book.hasAttribute("id"));
            assertFalse(book.hasAttribute("idx"));
            assertFalse(book.hasValue());
            assertTrue(book.hasChild("author"));
            assertTrue(book.hasChild("title"));
            assertTrue(book.hasChild("genre"));
            assertTrue(book.hasChild("price"));
            assertTrue(book.hasChild("publish_date"));
            assertTrue(book.hasChild("description"));
        }
    }
}
