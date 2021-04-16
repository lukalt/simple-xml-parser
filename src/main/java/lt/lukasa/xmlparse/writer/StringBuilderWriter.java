package lt.lukasa.xmlparse.writer;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Lukas Alt
 * @since 13.04.2021
 */
@RequiredArgsConstructor
@Getter
public class StringBuilderWriter implements StringWriter {
    private final StringBuilder builder;

    @Override
    public StringWriter append(String s) {
        builder.append(s);
        return this;
    }

    @Override
    public StringWriter append(long i) {
        builder.append(i);
        return this;
    }

    @Override
    public StringWriter append(double i) {
        builder.append(i);
        return this;
    }

    @Override
    public StringWriter append(char c) {
        builder.append(c);
        return this;
    }
}
