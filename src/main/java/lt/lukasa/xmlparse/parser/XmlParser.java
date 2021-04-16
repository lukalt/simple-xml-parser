package lt.lukasa.xmlparse.parser;

import lt.lukasa.xmlparse.exception.XmlParseException;
import lt.lukasa.xmlparse.lexer.lexemes.*;
import lt.lukasa.xmlparse.parser.elements.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * @author Lukas Alt
 * @since 13.04.2021
 */
public class XmlParser {
    XmlElement parseComment(Queue<Lexeme> lexemes) throws XmlParseException {
        Lexeme start = lexemes.poll();
        if (!(start instanceof CommentStartLexeme)) {
            if (start == null) {
                throw new XmlParseException("Expected comment start but got EOF");
            } else {
                throw new XmlParseException("Expected comment start but got " + start.getType());
            }
        }
        StringBuilder comment = new StringBuilder(start.toRawInput());
        Lexeme next;
        while (((next = lexemes.poll())) != null) {
            comment.append(next.toRawInput());
            if (next instanceof CommentEndLexeme) {
                return new XmlComment(comment.toString());
            }
        }
        return new XmlComment(comment.toString());
    }

    public XmlElement parse(Queue<Lexeme> lexemes) throws XmlParseException {
        List<XmlElement> elements = new ArrayList<>();
        if(lexemes.peek() instanceof HeaderLexeme) {
            elements.add(new XmlHeader(((HeaderLexeme)lexemes.poll()).getAttributeData()));
        }
        return parse(lexemes, elements);
    }

    XmlElement parse(Queue<Lexeme> lexemes, List<XmlElement> tags) throws XmlParseException {
        if (lexemes.isEmpty()) {
            return new XmlNode(tags);
        }

        Lexeme next = lexemes.peek();
        if (next instanceof TextLexeme) {
            lexemes.poll();
            tags.add(new XmlValue(((TextLexeme) next).getValue()));
            return parse(lexemes, tags);
        } else if (next instanceof AtomicTagLexeme) {
            lexemes.poll();
            tags.add(new XmlTag(((AtomicTagLexeme) next).getTagName(), null, ((AtomicTagLexeme) next).getAttributes()));
            return parse(lexemes, tags);
        } else if (next instanceof OpenTagLexeme) {
            lexemes.poll();
            String openTag = ((OpenTagLexeme) next).getTagName();
            XmlElement value = parse(lexemes, new ArrayList<>());
            tags.add(new XmlTag(openTag, value, ((OpenTagLexeme) next).getAttributes()));
            next = lexemes.peek();
            if (next instanceof ClosingTagLexeme) {
                if (!((ClosingTagLexeme) next).getTagName().equals(openTag)) {
                    throw new XmlParseException("Unexpected closing tag, expected </" + openTag + "> but got </" + ((ClosingTagLexeme) next).getTagName() + ">");
                }
                lexemes.poll();
            }
            return parse(lexemes, tags);
        } else if (next instanceof CommentStartLexeme) {
            tags.add(parseComment(lexemes));
            return parse(lexemes, tags);
        } else if(next instanceof ClosingTagLexeme) {
            return new XmlNode(tags);
        } else {
            throw new XmlParseException("Parser exception, unexpected lexeme" + next.getType());
        }
    }
}
