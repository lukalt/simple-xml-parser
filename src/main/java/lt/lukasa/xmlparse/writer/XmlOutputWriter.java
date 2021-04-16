package lt.lukasa.xmlparse.writer;

import lombok.RequiredArgsConstructor;

import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * @author Lukas Alt
 * @since 14.04.2021
 */
@RequiredArgsConstructor
public class XmlOutputWriter implements StringWriter, Closeable {
    private final PrintWriter printWriter;

    public XmlOutputWriter(OutputStream outputStream) {
        this(new PrintWriter(outputStream));
    }

    @Override
    public void close() throws IOException {
        this.printWriter.close();
    }

    @Override
    public StringWriter append(String s) {
        this.printWriter.print(s);
        return this;
    }

    @Override
    public StringWriter append(long i) {
        this.printWriter.print(i);
        return this;
    }

    @Override
    public StringWriter append(double i) {
        this.printWriter.print(i);
        return this;
    }

    @Override
    public StringWriter append(char c) {
        this.printWriter.print(c);
        return this;
    }
}
