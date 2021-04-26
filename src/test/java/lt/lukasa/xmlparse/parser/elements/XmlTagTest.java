package lt.lukasa.xmlparse.parser.elements;

import lt.lukasa.xmlparse.lexer.AttributeData;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Lukas Alt
 * @since 23.04.2021
 */
class XmlTagTest {

    @Test
    void hasValue() {
        assertTrue(new XmlTag("a", new XmlValue("1"), AttributeData.newAttributeData()).hasValue());
        assertFalse(new XmlTag("a", null, AttributeData.newAttributeData()).hasValue());
        assertFalse(new XmlTag("a", new XmlNode(new ArrayList<>(Collections.singletonList(new XmlTag("b", new XmlValue("1"), AttributeData.newAttributeData())))), AttributeData.newAttributeData()).hasValue());
    }

    @Test
    void getTagValue() {
        assertEquals("1", new XmlTag("a", new XmlValue("1"), AttributeData.newAttributeData()).getTagValue());
        assertEquals("", new XmlTag("a", new XmlValue(""), AttributeData.newAttributeData()).getTagValue());
        assertNotNull(new XmlTag("a", new XmlValue(""), AttributeData.newAttributeData()).getTagValue());
    }

    @Test
    void setTagValue() {
        final XmlTag t1 = new XmlTag("a", new XmlValue("1"), AttributeData.newAttributeData());
        assertEquals("1", t1.getTagValue());
        t1.setTagValue("2");
        assertEquals("2", t1.getTagValue());

        final XmlTag t2 = new XmlTag("a", new XmlValue(""), AttributeData.newAttributeData());
        assertEquals("", t2.getTagValue());
        t2.setTagValue("2");
        assertEquals("2", t2.getTagValue());

        final XmlTag t3 = new XmlTag("a", null, AttributeData.newAttributeData());
        assertNull(t3.getTagValue());
        t3.setTagValue("2");
        assertEquals("2", t3.getTagValue());

        final XmlTag t4 = new XmlTag("a", new XmlNode(Collections.singletonList(new XmlTag("b", null, AttributeData.newAttributeData()))), AttributeData.newAttributeData());
        assertNull(t4.getTagValue());
        t3.setTagValue("2");
        assertNull(t4.getTagValue());
    }

    @Test
    void getChildren() {
    }

    @Test
    void getFirstChild() {
    }

    @Test
    void removeAllChildren() {
    }

    @Test
    void testRemoveAllChildren() {
    }

    @Test
    void testRemoveAllChildren1() {
    }

    @Test
    void removeChild() {
    }

    @Test
    void removeFirstChild() {
    }

    @Test
    void testRemoveFirstChild() {
    }

    @Test
    void testGetFirstChild() {
    }

    @Test
    void testGetFirstChild1() {
    }

    @Test
    void createChild() {
    }

    @Test
    void testCreateChild() {
    }

    @Test
    void addChild() {
    }

    @Test
    void testAddChild() {
    }

    @Test
    void setTagName() {
        XmlTag tag = new XmlTag("a", new XmlValue("1"), AttributeData.newAttributeData());
        assertEquals("a", tag.getTagName());
        tag.setTagName("b");
        assertEquals("b", tag.getTagName());
    }

    @Test
    void getTagName() {
        assertEquals("a", new XmlTag("a", new XmlValue("1"), AttributeData.newAttributeData()).getTagName());
        assertNotEquals("b", new XmlTag("a", new XmlValue("1"), AttributeData.newAttributeData()).getTagName());
        assertNotEquals("A", new XmlTag("a", new XmlValue("1"), AttributeData.newAttributeData()).getTagName());
        assertNotEquals(null, new XmlTag("a", new XmlValue("1"), AttributeData.newAttributeData()).getTagName());
    }

    @Test
    void getValue() {
    }
}