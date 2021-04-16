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
public class XmlHeader implements XmlElement {
    private final AttributeData attributes;

    @Override
    public void write(StringWriter builder) {
        builder.append("<?xml");
        this.attributes.write(builder);
        builder.append("?>");
    }
}
