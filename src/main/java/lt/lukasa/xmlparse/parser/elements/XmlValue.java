package lt.lukasa.xmlparse.parser.elements;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lt.lukasa.xmlparse.writer.StringWriter;

/**
 * @author Lukas Alt
 * @since 13.04.2021
 */
@AllArgsConstructor
@Getter
@Setter
public class XmlValue implements XmlElement {
    private String value;

    @Override
    public String toString() {
        return value;
    }

    @Override
    public void write(StringWriter builder) {
        builder.append(value);
    }
}
