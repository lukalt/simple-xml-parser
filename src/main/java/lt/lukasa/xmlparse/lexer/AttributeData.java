package lt.lukasa.xmlparse.lexer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lt.lukasa.xmlparse.writer.StringBuilderWriter;
import lt.lukasa.xmlparse.writer.StringWriter;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author Lukas Alt
 * @since 13.04.2021
 */
@RequiredArgsConstructor
@ToString
public class AttributeData {
    private static final Pattern ATTRIBUTE_PATTERN = Pattern.compile("([^=\\s\">]+)(?:=\"([^\"]*)\")?(\\s*)", Pattern.MULTILINE);

    @AllArgsConstructor
    @Getter
    @ToString
    public static class AttributeVal {
        private String value;
        private final String suffix;
    }

    final String prefix;
    final Map<String, AttributeVal> data = new LinkedHashMap<>();

    public boolean hasAttribute(String key) {
        return data.containsKey(key);
    }

    public String getAttributeValue(String key) {
        AttributeVal val;
        return (val = data.get(key)) != null ? val.value : null;
    }

    public void setAttributeValue(String key, String value) {
        AttributeVal val = this.data.get(key);
        if (val != null) {
            val.value = value;
        } else {
            this.data.put(key, new AttributeVal(value, " "));
        }
    }

    public boolean hasAttributeValue(String key) {
        AttributeVal val = this.data.get(key);
        if (val != null) {
            return val.value != null;
        }
        return false;
    }

    public static AttributeData newAttributeData() {
        return new AttributeData("");
    }

    public static AttributeData parseTagBody(String prefix, String input) {
        Matcher matcher = ATTRIBUTE_PATTERN.matcher(input);
        AttributeData a = new AttributeData(prefix);
        while (matcher.find()) {
            a.data.put(matcher.group(1), new AttributeVal(matcher.group(2), matcher.group(3)));
        }
        return a;
    }

    public String toRawInput() {
        StringBuilder sb = new StringBuilder();
        write(new StringBuilderWriter(sb));
        return sb.toString();
    }

    @Override
    public String toString() {
        return data.entrySet().stream().map(a -> a.getKey() + "=" + a.getValue().getValue()).collect(Collectors.joining(","));
    }

    public void write(StringWriter builder) {
        builder.append(this.prefix);
        for (Map.Entry<String, AttributeVal> stringAttributeValEntry : this.data.entrySet()) {
            builder.append(stringAttributeValEntry.getKey()).append("=\"")
                    .append(stringAttributeValEntry.getValue()
                            .getValue()).append("\"")
                    .append(stringAttributeValEntry.getValue().getSuffix());
        }
    }
}
