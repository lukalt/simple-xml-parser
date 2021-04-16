package lt.lukasa.xmlparse.parser.elements;

import lt.lukasa.xmlparse.writer.StringWriter;

/**
 * @author Lukas Alt
 * @since 13.04.2021
 */
public interface XmlElement {
    String toString();

    void write(StringWriter builder);

    default XmlTag getAsTag() {
        return (XmlTag) this;
    }

    default XmlNode getAsNode() {
        return (XmlNode) this;
    }

    default XmlValue getAsValue() {
        return ((XmlValue) this);
    }
}
