package lt.lukasa.xmlparse.lexer.lexemes;

import lombok.RequiredArgsConstructor;

/**
 * @author Lukas Alt
 * @since 13.04.2021
 */
@RequiredArgsConstructor
public class CommentStartLexeme implements Lexeme {
    @Override
    public LexemeType getType() {
        return LexemeType.COMMENT_START;
    }

    @Override
    public String toRawInput() {
        return "<!--";
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
