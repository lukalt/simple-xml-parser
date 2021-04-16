package lt.lukasa.xmlparse.lexer.lexemes;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lt.lukasa.xmlparse.lexer.AttributeData;
import lt.lukasa.xmlparse.writer.StringBuilderWriter;

/**
 * @author Lukas Alt
 * @since 13.04.2021
 */
@Getter
@RequiredArgsConstructor
@ToString
public class AtomicTagLexeme implements Lexeme {
    private final String tagName;
    private final AttributeData attributes;

    @Override
    public String toRawInput() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(">").append(tagName);
        attributes.write(new StringBuilderWriter(stringBuilder));
        stringBuilder.append(">");
        return stringBuilder.toString();
    }

    @Override
    public LexemeType getType() {
        return LexemeType.OPEN_TAG;
    }
}