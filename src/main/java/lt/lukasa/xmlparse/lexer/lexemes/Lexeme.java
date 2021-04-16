package lt.lukasa.xmlparse.lexer.lexemes;

/**
 * @author Lukas Alt
 * @since 13.04.2021
 */
public interface Lexeme {
    LexemeType getType();
    String toString();
    String toRawInput();
}
