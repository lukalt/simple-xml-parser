package lt.lukasa.xmlparse.writer;

/**
 * @author Lukas Alt
 * @since 13.04.2021
 */
public interface StringWriter {
    StringWriter append(String s);
    StringWriter append(long i);
    StringWriter append(double i);
    StringWriter append(char c);
}
