package lt.lukasa.xmlparse.writer;

/**
 * @author Lukas Alt
 * @since 22.04.2021
 */
public class ConsoleWriter implements StringWriter{
    @Override
    public StringWriter append(String s) {
        System.out.print(s);
        return this;
    }

    @Override
    public StringWriter append(long i) {
        System.out.print(i);
        return this;
    }

    @Override
    public StringWriter append(double i) {
        System.out.print(i);
        return this;
    }

    @Override
    public StringWriter append(char c) {
        System.out.print(c);
        return this;
    }
}
