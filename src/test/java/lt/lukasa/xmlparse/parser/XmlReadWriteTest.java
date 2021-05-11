package lt.lukasa.xmlparse.parser;

import lt.lukasa.xmlparse.TestResources;
import lt.lukasa.xmlparse.api.Document;
import lt.lukasa.xmlparse.exception.XmlParseException;
import lt.lukasa.xmlparse.reader.XmlDocumentReader;
import lt.lukasa.xmlparse.util.IOUtil;
import lt.lukasa.xmlparse.writer.StringBuilderWriter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @author Lukas Alt
 * @since 23.04.2021
 */
public class XmlReadWriteTest {

    @Test
    void readWriteBooks() throws IOException, XmlParseException {
        testNoModification("./xml-examples/books.xml");
    }

    @Test
    void readWritePlants() throws IOException, XmlParseException {
        testNoModification("./xml-examples/plants.xml");
    }

    private static void testNoModification(String path) throws IOException, XmlParseException {
        String s = IOUtil.readToString(TestResources.getResource(path));
        XmlDocumentReader reader = new XmlDocumentReader(new StringBuilder(s));
        Document document = reader.read();
        StringBuilder sb = new StringBuilder();
        document.write(new StringBuilderWriter(sb));
        for(int i = 0; i < Math.max(s.length(), sb.length()); i++) {
            int c1 = i < s.length() ? s.charAt(i) : '$';
            int c2 = i < sb.length() ? sb.charAt(i) : '$';
            if(c1 != c2) {
                throw new AssertionError("ERROR MISMATCH " + i + " Expected: '" + c1 + "' But got: '" + c2 + "'");
            }
        }
        //Assertions.assertEquals(s, sb.toString());
    }
}
