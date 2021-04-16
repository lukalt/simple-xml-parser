package lt.lukasa.xmlparse.lexer;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lt.lukasa.xmlparse.lexer.lexemes.Lexeme;

import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Lukas Alt
 * @since 13.04.2021
 */
@RequiredArgsConstructor
@Getter
public class LexemeRegex<L extends Lexeme> {
    private final Pattern pattern;
    private final Function<Matcher, L> extract;

    public static <L extends Lexeme> LexemeRegex<L> compile(String regex, Function<Matcher, L> extract) {
        return new LexemeRegex<>(Pattern.compile("^" + regex, Pattern.MULTILINE & Pattern.UNIX_LINES), extract);
    }
}