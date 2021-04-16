package lt.lukasa.xmlparse.lexer;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Lukas Alt
 * @since 13.04.2021
 */
class AttributeDataTest {
    @Test
    void hasAttribute() {
        AttributeData attributeData = AttributeData.parseTagBody("", "x=\"1\"");
        assertTrue(attributeData.hasAttribute("x"));
        assertFalse(attributeData.hasAttribute("y"));
        assertTrue(attributeData.hasAttributeValue("x"));
        assertFalse(attributeData.hasAttributeValue("y"));
        attributeData = AttributeData.parseTagBody("", "x");
        assertTrue(attributeData.hasAttribute("x"));
        assertFalse(attributeData.hasAttribute("y"));
        assertFalse(attributeData.hasAttributeValue("x"));
        assertFalse(attributeData.hasAttributeValue("y"));

    }

    @Test
    void getAttributeValue() {
    }

    @Test
    void setAttributeValue() {
    }

    @Test
    void parseTagBody() {
        AttributeData a1 = AttributeData.parseTagBody("42", "x=\"2\"");
        AttributeData a1b = AttributeData.parseTagBody("", "x=\"2\"");
        AttributeData a1c = AttributeData.parseTagBody("", "x=\"2\" y");
        AttributeData a2 = AttributeData.parseTagBody("42", "x");
        AttributeData a3 = AttributeData.parseTagBody("42", "");

        assertEquals("42", a1.prefix);
        assertEquals("", a1b.prefix);
        assertEquals("42", a2.prefix);
        assertEquals("42", a3.prefix);

        assertEquals(1, a1.data.size());
        assertEquals(1, a1b.data.size());
        assertEquals(2, a1c.data.size());
        assertEquals(1, a2.data.size());
        assertEquals(0, a3.data.size());

        Map.Entry<String, AttributeData.AttributeVal> v1 = a1.data.entrySet().iterator().next();
        assertEquals("x", v1.getKey());
        assertEquals("2", v1.getValue().getValue());
        assertEquals("", v1.getValue().getSuffix());

        Iterator<Map.Entry<String, AttributeData.AttributeVal>> it = a1c.data.entrySet().iterator();
        Map.Entry<String, AttributeData.AttributeVal> w1 = it.next();
        assertEquals("x", w1.getKey());
        assertEquals("2", w1.getValue().getValue());
        assertEquals(" ", w1.getValue().getSuffix());

        Map.Entry<String, AttributeData.AttributeVal> w2 = it.next();
        assertEquals("y", w2.getKey());
        assertNull(w2.getValue().getValue());
        assertEquals("", w2.getValue().getSuffix());
    }
}