package lt.lukasa.xmlparse.parser.elements;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lt.lukasa.xmlparse.api.Document;
import lt.lukasa.xmlparse.writer.StringWriter;

/**
 * @author Lukas Alt
 * @since 22.04.2021
 */
@Getter
@AllArgsConstructor
public class XmlDocument implements XmlElement, Document {
    private final String firstPrefix;
    private final XmlHeader header;
    private final String secondPrefix;
    private final XmlTag root;

    @Override
    public void write(StringWriter builder) {
        if(firstPrefix != null) {
            builder.append(firstPrefix);
        }
        if(header != null) {
            header.write(builder);
        }
        if(secondPrefix != null) {
            builder.append(secondPrefix);
        }
        root.write(builder);
    }
}
