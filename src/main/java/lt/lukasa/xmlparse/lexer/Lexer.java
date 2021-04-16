package lt.lukasa.xmlparse.lexer;

import lt.lukasa.xmlparse.lexer.lexemes.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

/**
 * @author Lukas Alt
 * @since 13.04.2021
 */
public class Lexer {
    private final static List<LexemeRegex<?>> patterns = new ArrayList<>();

    static {
        patterns.add(LexemeRegex.compile("</([^>]+)>", matcher -> new ClosingTagLexeme(matcher.group(1))));
        patterns.add(LexemeRegex.compile("<!--", matcher -> new CommentStartLexeme()));
        patterns.add(LexemeRegex.compile("<\\?xml(\\s*)((?:[^=\\s\">]+(?:=\"[^\"]*\")?\\s*)*)\\?>", matcher -> {
            return new HeaderLexeme(AttributeData.parseTagBody(matcher.group(1), matcher.group(2)));
        }));
        patterns.add(LexemeRegex.compile("<([^>\\s\"]+)(\\s*)((?:(?!/>)[^=\\s\">]+(?:=\"[^\"]*\")?\\s*)*)(/?)>", matcher -> {
            String tag = matcher.group(1);
            String prefix = matcher.group(2);
            AttributeData attributes = AttributeData.parseTagBody(prefix, matcher.group(3));
            if (matcher.group(4).equals("/")) {
                return new AtomicTagLexeme(tag, attributes);
            } else {
                return new OpenTagLexeme(tag, attributes);
            }
        }));
        patterns.add(LexemeRegex.compile("-->", matcher -> new CommentEndLexeme()));
        patterns.add(LexemeRegex.compile("((?!-->|<).|\\s)+", matcher -> new TextLexeme(matcher.group())));
    }

    public List<Lexeme> lexer(String raw) {
        StringBuilder input = new StringBuilder(raw);
        return lexer(input);
    }

    public List<Lexeme> lexer(StringBuilder input) {
        List<Lexeme> out = new ArrayList<>();

        o:
        while (input.length() > 0) {
            for (LexemeRegex<?> pattern : patterns) {
                Matcher matcher = pattern.getPattern().matcher(input);
                if (matcher.find()) {
                    final Lexeme apply = pattern.getExtract().apply(matcher);
                    out.add(apply);
                    input.replace(0, matcher.end(), "");
                    continue o;
                }
            }
            throw new IllegalStateException("Lexerror: '" + input + "'");
        }
        return out;
    }
}
