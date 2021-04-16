package lt.lukasa.xmlparse.reader;

import lt.lukasa.xmlparse.exception.XmlParseException;
import lt.lukasa.xmlparse.lexer.Lexer;
import lt.lukasa.xmlparse.lexer.lexemes.Lexeme;
import lt.lukasa.xmlparse.parser.XmlParser;
import lt.lukasa.xmlparse.parser.elements.XmlElement;
import lt.lukasa.xmlparse.writer.StringBuilderWriter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Lukas Alt
 * @since 14.04.2021
 */
public class XmlDocumentReader {
    private final StringBuilder input;

    public XmlDocumentReader(StringBuilder input) {
        this.input = input;
    }

    public XmlDocumentReader(String input) {
        this.input = new StringBuilder(input);
    }

    public XmlDocumentReader(InputStream inputStream, Charset charset) throws IOException {
        this.input = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, charset))) {
            String s;
            while ((s = reader.readLine()) != null) {
                this.input.append(s).append('\n');
            }
        }
    }

    public XmlElement read() throws XmlParseException {
        Lexer lexer = new Lexer();

        final List<Lexeme> lexer1 = lexer.lexer(input);
        LinkedList<Lexeme> lexemes = new LinkedList<>(lexer1);
        XmlParser parser = new XmlParser();
        return parser.parse(lexemes);
    }

}
