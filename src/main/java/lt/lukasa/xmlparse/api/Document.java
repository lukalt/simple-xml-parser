package lt.lukasa.xmlparse.api;

import lt.lukasa.xmlparse.writer.StringWriter;

/**
 * @author Lukas Alt
 * @since 22.04.2021
 */
public interface Document {
    Tag getRoot();
    void write(StringWriter writer);
}
