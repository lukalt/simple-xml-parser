package lt.lukasa.xmlparse.lexer.lexemes;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lt.lukasa.xmlparse.lexer.AttributeData;

/**
 * @author Lukas Alt
 * @since 13.04.2021
 */
@RequiredArgsConstructor
@Getter
public class HeaderLexeme implements Lexeme {
    private final AttributeData attributeData;

    @Override
    public LexemeType getType() {
        return LexemeType.HEADER;
    }

    @Override
    public String toRawInput() {
        return attributeData.toRawInput();
    }
}
