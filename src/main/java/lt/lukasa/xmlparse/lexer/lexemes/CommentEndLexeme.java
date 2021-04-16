package lt.lukasa.xmlparse.lexer.lexemes;

import lombok.RequiredArgsConstructor;

/**
 * @author Lukas Alt
 * @since 13.04.2021
 */
@RequiredArgsConstructor
public class CommentEndLexeme implements Lexeme {
    @Override
    public LexemeType getType() {
        return LexemeType.COMMENT_END;
    }

    @Override
    public String toRawInput() {
        return "-->";
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
