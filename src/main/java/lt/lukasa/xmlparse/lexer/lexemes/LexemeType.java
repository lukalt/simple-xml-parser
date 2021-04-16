package lt.lukasa.xmlparse.lexer.lexemes;

/**
 * @author Lukas Alt
 * @since 13.04.2021
 */
public enum LexemeType {
    OPEN_TAG,
    CLOSING_TAG,
    TEXT,
    COMMENT_START,
    COMMENT_END,
    HEADER
}