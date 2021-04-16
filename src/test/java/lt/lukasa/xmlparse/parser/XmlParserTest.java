package lt.lukasa.xmlparse.parser;


import lt.lukasa.xmlparse.exception.XmlParseException;
import lt.lukasa.xmlparse.lexer.Lexer;
import lt.lukasa.xmlparse.lexer.lexemes.Lexeme;
import lt.lukasa.xmlparse.parser.elements.XmlElement;
import lt.lukasa.xmlparse.writer.StringBuilderWriter;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Lukas Alt
 * @since 13.04.2021
 */
class XmlParserTest {
    public static void main(String[] args) throws IOException, XmlParseException {
        String input = new String(Files.readAllBytes(new File("G:\\projects\\skywars\\pom.xml").toPath()), StandardCharsets.UTF_8);
        Lexer lexer = new Lexer();

        long s1 = System.nanoTime();
        final List<Lexeme> lexer1 = lexer.lexer(input);
        long s2 = System.nanoTime() - s1;
        System.out.println("LEXER TOOK" + s2 /1000 + "us");
        LinkedList<Lexeme> lexemes = new LinkedList<>(lexer1);
        XmlParser parser = new XmlParser();
        long p1 = System.nanoTime();
        final XmlElement parse = parser.parse(lexemes);
        long p2 = System.nanoTime() - p1;
        System.out.println("PARSER TOOK" + p2 / 1000 + "us");

        StringBuilder builder = new StringBuilder();
        parse.write(new StringBuilderWriter(builder));
        System.out.println(builder);
    }
}