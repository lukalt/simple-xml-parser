package lt.lukasa.xmlparse.reader;

import lt.lukasa.xmlparse.api.Document;
import lt.lukasa.xmlparse.exception.XmlParseException;
import lt.lukasa.xmlparse.lexer.Lexer;
import lt.lukasa.xmlparse.lexer.lexemes.Lexeme;
import lt.lukasa.xmlparse.parser.XmlParser;
import lt.lukasa.xmlparse.util.IOUtil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
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

    public XmlDocumentReader(File input) throws IOException {
        this.input = new StringBuilder(new String(Files.readAllBytes(input.toPath()), StandardCharsets.UTF_8));
    }

    public XmlDocumentReader(InputStream inputStream) throws IOException {
        this(inputStream, StandardCharsets.UTF_8);
    }

    public XmlDocumentReader(InputStream inputStream, Charset charset) throws IOException {
        this.input = new StringBuilder();
        IOUtil.readToString(inputStream, charset, this.input);
    }

    public Document read() throws XmlParseException {
        Lexer lexer = new Lexer();

        final List<Lexeme> lexer1 = lexer.lexer(input);
        LinkedList<Lexeme> lexemes = new LinkedList<>(lexer1);
        XmlParser parser = new XmlParser();
        return parser.parse(lexemes);
    }

}
