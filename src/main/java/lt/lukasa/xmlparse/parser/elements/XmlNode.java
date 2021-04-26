package lt.lukasa.xmlparse.parser.elements;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lt.lukasa.xmlparse.writer.StringWriter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Lukas Alt
 * @since 13.04.2021
 */
@RequiredArgsConstructor
@Getter
public class XmlNode implements XmlElement {
    private final List<XmlElement> tags;

    @Override
    public String toString() {
        if(tags.isEmpty()) {
            return "";
        } else if(tags.size() == 1) {
            return tags.get(0).toString();
        } else {
            return "[" + tags.stream().map(a -> a.toString()).collect(Collectors.joining(",")) + "]";
        }
    }

    @Override
    public void write(StringWriter builder) {
        for (XmlElement tag : tags) {
            tag.write(builder);
        }
    }

    public XmlTag getFirstChild(String tag) {
        for (XmlElement element : this.tags) {
            if(element instanceof XmlTag && ((XmlTag)element).getTagName().equals(tag)) {
                return (XmlTag) element;
            }
        }
        return null;
    }

    public List<XmlTag> getChildTags() {
        return this.tags.stream().filter(a -> a instanceof XmlTag).map(a -> (XmlTag) a).collect(Collectors.toList());
    }
}
