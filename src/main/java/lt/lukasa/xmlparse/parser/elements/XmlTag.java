package lt.lukasa.xmlparse.parser.elements;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lt.lukasa.xmlparse.lexer.AttributeData;
import lt.lukasa.xmlparse.writer.StringWriter;

/**
 * @author Lukas Alt
 * @since 13.04.2021
 */
@RequiredArgsConstructor
@Getter
public class XmlTag implements XmlElement {
    private final String tagName;
    private final XmlElement value;
    private final AttributeData attributes;

    @Override
    public String toString() {
        if (value == null) {
            return "<" + tagName + "/>";
        }
        return "<" + tagName + ">" + value + "</" + tagName + ">";
    }

    @Override
    public void write(StringWriter builder) {
        if (value == null) {
            builder.append("<").append(tagName);
            attributes.write(builder);
            builder.append("/>");
        } else {
            builder.append("<").append(tagName);
            attributes.write(builder);
            builder.append(">");
            value.write(builder);
            builder.append("</").append(tagName).append(">");
        }
    }
}
