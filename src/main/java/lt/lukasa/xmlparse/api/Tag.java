package lt.lukasa.xmlparse.api;

import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * @author Lukas Alt
 * @since 22.04.2021
 */
public interface Tag extends Value, Visitable<Tag> {
    /**
     * Returns all tags which are a directly child of this tag
     * @return Child tags as a collection
     */
    Collection<Tag> getChildren();

    /**
     * Returns all tags which are a directly child of this tag and matching the tag name
     * @param tagName Name of the tag
     * @return Child tags as a collection
     */
    Collection<Tag> getChildren(String tagName);

    /**
     * Returns all tags which are a directly child of this tag and matching the predicate
     * @param predicate Predicate to filter child tags
     * @return Child tags as a collection
     */
    Collection<Tag> getChildren(Predicate<Tag> predicate);

    /**
     * Checks if this tag has any direct child tag
     * @return True if a child is present
     */
    boolean hasChild();

    /**
     * Checks if this tag has any direct child tag matching the provided tag name
     * @param tagName The tag name to check
     * @return True if a matching child is present
     */
    boolean hasChild(String tagName);

    /**
     * Checks if this tag has any direct child tag matching the provided tag name
     * @param predicate The predicate to match the children
     * @return True if a matching child is present
     */
    boolean hasChild(Predicate<Tag> predicate);

    /**
     * Returns the first child of this tag or null
     * @return First child tag
     */
    Tag getFirstChild();

    /**
     * Removes all direct child tags
     */
    void removeAllChildren();

    /**
     * Removes all direct child tags with the provided tagName
     * @param tagName Name of the tags to remove
     */
    void removeAllChildren(String tagName);

    /**
     * Removes all direct child tags which match the predicate
     * @param predicate Predicate to check whether child tags should be removed. If true is returned, the child is removed. Otherwise, it is kept
     */
    void removeAllChildren(Predicate<Tag> predicate);

    /**
     * Removes a direct child tag
     * @param tag The tag to remove
     */
    void removeChild(Tag tag);

    /**
     * Removes the first direct child tags matching the provided tag name
     * @param tagName Name of tag to remove
     */
    void removeFirstChild(String tagName);

    /**
     * Removes the first direct child tag matching the predicate
     * @param predicate Predicate to check whether child tags should be removed. If true is returned, the child is removed. Otherwise, it is kept
     */
    void removeFirstChild(Predicate<Tag> predicate);


    /**
     * Returns the first child of this tag matching the name or null if none is present
     * @param tagName Name of the tag
     * @return First child tag matching tag
     */
    Tag getFirstChild(String tagName);

    /**
     * Returns the first child of this tag matching the predicate or null if none is present
     * @param predicate Predicate to check tag
     * @return First child tag matching the predicate
     */
    Tag getFirstChild(Predicate<Tag> predicate);

    /**
     * Creates a new tag and adds it to the end
     * @param name Name of tag
     * @return Newly created tag
     */
    Tag createChild(String name);

    /**
     * Creates a new tag and adds it to the end
     * @param name Name of tag
     * @param index Index of the tag add which the element should be inserted
     * @return Newly created tag
     */
    Tag createChild(String name, int index);

    /**
     * Adds a new tag and adds it to the end
     * @param name Name of tag
     * @param value The value of the tag
     * @return current tag
     */
    Tag addChild(String name, String value);

    /**
     * Adds a new tag and adds it to the end
     * @param name Name of tag
     * @param callback Callback which will be invoked when the tag is created
     * @return current tag
     */
    Tag addChild(String name, Consumer<Tag> callback);

    /**
     * Returns the name of this xml tag
     * @return Tag Name
     */
    String getTagName();

    /**
     * Modifies the name of this xml tag
     * @param tagName The new name of the tag
     */
    void setTagName(String tagName);

    /**
     * Checks if the attribute is present. This returns true if no value is associated with this attribute.
     * @param attributeName the name of the attribute
     * @return true if the tag is present
     */
    boolean hasAttribute(String attributeName);

    /**
     * Gets the value of this attribute. If the attribute is not present or no value is associated, null will be returned
     * @param attributeName The name of the attribute
     * @return The value of the attribute or null
     */
    String getAttributeValue(String attributeName);

    /**
     * Sets the value of an existing attribute or adds a new attribute
     * @param attributeName The name of the attribute
     * @param attributeValue The value of the attribute
     */
    void setAttributeValue(String attributeName, String attributeValue);

    /**
     * {@inheritDoc}
     */
    @Override
    Tag setTagValue(String value);
}
