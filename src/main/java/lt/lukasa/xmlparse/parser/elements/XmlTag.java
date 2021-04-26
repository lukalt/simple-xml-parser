package lt.lukasa.xmlparse.parser.elements;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lt.lukasa.xmlparse.api.Tag;
import lt.lukasa.xmlparse.lexer.AttributeData;
import lt.lukasa.xmlparse.writer.StringWriter;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Lukas Alt
 * @since 13.04.2021
 */
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
public class XmlTag implements XmlElement, Tag {
    @Setter
    private String tagName;
    // value either is a XmlValue or a XmlNode containing multiple child tags
    private XmlElement value;
    private AttributeData attributes;

    @Override
    public String toString() {
        return "Tag{" +
                "tagName='" + tagName + '\'' +
                ", value=" + value +
                ", attributes=" + attributes +
                '}';
    }

    @Override
    public void write(StringWriter builder) {
        if (value == null) {
            builder.append("<").append(tagName);
            if(this.attributes != null) {
                attributes.write(builder);
            }
            builder.append("/>");
        } else {
            builder.append("<").append(tagName);
            if(this.attributes != null) {
                attributes.write(builder);
            }
            builder.append(">");
            value.write(builder);
            builder.append("</").append(tagName).append(">");
        }
    }

    @Override
    public boolean hasValue() {
        return this.value instanceof XmlValue;
    }

    @Override
    public String getTagValue() {
        if (this.value instanceof XmlValue) {
            return ((XmlValue) this.value).getValue();
        }
        if (this.value instanceof XmlNode) {
            XmlNode node = (XmlNode) this.value;
            if(node.getTags().isEmpty()) {
                return null;
            }
            for (XmlElement tag : node.getTags()) {
                if(tag instanceof XmlValue) {
                    return ((XmlValue) tag).getValue();
                }
            }
        }
        return null;
    }

    @Override
    public Tag setTagValue(String value) {
        if(this.value == null) {
            this.value = new XmlValue(value);
        } else if(this.value instanceof XmlValue) {
            ((XmlValue) this.value).setValue(value);
        } else if(this.value instanceof XmlNode) {
            for (XmlElement tag : ((XmlNode) this.value).getTags()) {
                if(tag instanceof XmlValue) {
                    ((XmlValue) tag).setValue(value);
                    return this;
                }
            }
            throw new IllegalStateException("Could not set value");
        }
        return this;
    }

    private Stream<Tag> getChildrenAsStream() {
        if (this.value != null && this.value instanceof XmlNode) {
            return ((XmlNode) this.value).getTags().stream().filter(a -> a instanceof XmlTag).map(a -> (XmlTag) a);
        }
        return Stream.empty();
    }
    @Override
    public Collection<Tag> getChildren() {
       return this.getChildrenAsStream().collect(Collectors.toList());
    }

    @Override
    public Collection<Tag> getChildren(String tagName) {
        return this.getChildrenAsStream().filter(t -> tagName.equals(t.getTagName())).collect(Collectors.toList());
    }

    @Override
    public Collection<Tag> getChildren(Predicate<Tag> predicate) {
        return this.getChildrenAsStream().filter(predicate).collect(Collectors.toList());
    }

    @Override
    public boolean hasChild() {
        return this.getChildrenAsStream().findAny().isPresent();
    }

    @Override
    public boolean hasChild(String tagName) {
        return this.getChildrenAsStream().anyMatch(t -> tagName.equals(t.getTagName()));
    }

    @Override
    public boolean hasChild(Predicate<Tag> predicate) {
        return this.getChildrenAsStream().anyMatch(predicate);
    }

    @Override
    public Tag getFirstChild() {
        if (this.value != null && this.value instanceof XmlNode) {
            for (XmlElement tag : ((XmlNode) this.value).getTags()) {
                return (Tag) tag;
            }
        }
        return null;
    }

    @Override
    public void removeAllChildren() {
        this.removeAllChildren(t -> true);
    }

    @Override
    public void removeAllChildren(String tagName) {
        this.removeAllChildren(tag -> tag.getTagName().equals(tagName));
    }

    @Override
    public void removeAllChildren(Predicate<Tag> predicate) {
        if(this.value instanceof XmlNode) {
            ((XmlNode) this.value).getTags().removeIf(t -> t instanceof XmlTag && predicate.test((XmlTag)t));
        }
    }

    @Override
    public void removeChild(Tag tag) {
        this.removeFirstChild(t -> t.equals(tag));
    }

    @Override
    public void removeFirstChild(String tagName) {
        this.removeFirstChild(t -> t.getTagName().equals(tagName));
    }

    @Override
    public void removeFirstChild(Predicate<Tag> predicate) {
        if(this.value instanceof XmlNode) {
            Iterator<XmlElement> it = ((XmlNode) this.value).getTags().iterator();
            while (it.hasNext()) {
                XmlElement el = it.next();
                if(el instanceof XmlTag && predicate.test((XmlTag) el)) {
                    it.remove();
                    return;
                }
            }
        }
    }

    @Override
    public Tag getFirstChild(String tagName) {
        if (this.value != null && this.value instanceof XmlNode) {
            for (XmlElement tag : ((XmlNode) this.value).getTags()) {
                if (tag instanceof Tag && tagName.equals(((Tag) tag).getTagName())) {
                    return (Tag) tag;
                }
            }
        }
        return null;
    }

    @Override
    public Tag getFirstChild(Predicate<Tag> predicate) {
        if (this.value != null && this.value instanceof XmlNode) {
            for (XmlElement tag : ((XmlNode) this.value).getTags()) {
                if (tag instanceof Tag && predicate.test((Tag) tag)) {
                    return (Tag) tag;
                }
            }
        }
        return null;
    }

    @Override
    public Tag createChild(String name) {
        XmlTag tag = newTag(name);
        pushChild(-1, tag);
        return tag;
    }

    @Override
    public Tag createChild(String name, int index) {
        XmlTag tag = newTag(name);
        pushChild(index, tag);
        return tag;
    }

    private void pushChild(int index, XmlTag tag) {
        if(this.value instanceof XmlNode) {
                final List<XmlElement> tags = ((XmlNode) this.value).getTags();
            if(index >= tags.size() || index < 0) {
                tags.add(tag);
            } else {
                tags.add(index, tag);
            }
        } else if(this.value == null) {
            List<XmlElement> tags = new ArrayList<>();
            tags.add(tag);
            this.value = new XmlNode(tags);
        } else if(this.value instanceof XmlValue) {
           List<XmlElement> tags = new ArrayList<>();
           tags.add(this.value);
           tags.add(tag);
            this.value = new XmlNode(tags);
        } else {
            throw new IllegalStateException("Could not add tag to element of type " + getClass().getName());
        }
    }

    @Override
    public Tag addChild(String name, String value) {
        Tag child = createChild(name);
        child.setTagValue(value);
        return this;
    }

    @Override
    public Tag addChild(String name, Consumer<Tag> callback) {
        Tag child = createChild(name);
        callback.accept(child);
        return this;
    }

    @Override
    public boolean hasAttribute(String attributeName) {
        if(this.attributes == null) {
            return false;
        }
        return this.attributes.hasAttribute(attributeName);
    }

    @Override
    public String getAttributeValue(String attributeName) {
        if(this.attributes == null) {
            return null;
        }
        return this.attributes.getAttributeValue(attributeName);
    }

    @Override
    public void setAttributeValue(String attributeName, String attributeValue) {
        if(this.attributes == null) {
            this.attributes = AttributeData.newAttributeData();
        }
        this.attributes.setAttributeValue(attributeName, attributeValue);
    }

    public static XmlTag newTag(String name) {
        return new XmlTag(name, null, AttributeData.newAttributeData());
    }

    @Override
    public XmlTag visit(Consumer<Tag> tag) {
        tag.accept(this);
        return this;
    }
}
