package lt.lukasa.xmlparse.lexer.lexemes;

import lombok.Getter;

/**
 * @author Lukas Alt
 * @since 13.04.2021
 */
@Getter
public class ClosingTagLexeme implements Lexeme {
    private final String tagName;

    public ClosingTagLexeme(String tagName) {
        this.tagName = tagName;
    }

    @Override
    public String toString() {
        return "ClosingTagLexeme{" +
                "tagName='" + tagName + '\'' +
                '}';
    }

    @Override
    public String toRawInput() {
        return "</" + tagName + ">";
    }

    @Override
    public LexemeType getType() {
        return LexemeType.CLOSING_TAG;
    }
}