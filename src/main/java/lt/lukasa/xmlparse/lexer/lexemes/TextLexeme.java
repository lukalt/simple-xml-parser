package lt.lukasa.xmlparse.lexer.lexemes;

import lombok.Getter;

/**
 * @author Lukas Alt
 * @since 13.04.2021
 */
@Getter
public class TextLexeme implements Lexeme {
    private final String value;

    public TextLexeme(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "TextLexeme{" +
                "value='" + value + '\'' +
                '}';
    }

    @Override
    public String toRawInput() {
        return value;
    }

    @Override
    public LexemeType getType() {
        return LexemeType.TEXT;
    }
}