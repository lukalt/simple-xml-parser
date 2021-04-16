package lt.lukasa.xmlparse.parser.elements;

import lombok.RequiredArgsConstructor;
import lt.lukasa.xmlparse.writer.StringWriter;

/**
 * @author Lukas Alt
 * @since 13.04.2021
 */
@RequiredArgsConstructor
public class XmlComment implements XmlElement {
    private final String comment;

    @Override
    public String toString() {
        return this.comment;
    }

    @Override
    public void write(StringWriter builder) {
        builder.append(this.comment);
    }
}
