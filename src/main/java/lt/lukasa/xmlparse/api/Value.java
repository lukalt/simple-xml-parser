package lt.lukasa.xmlparse.api;

/**
 * @author Lukas Alt
 * @since 22.04.2021
 */
public interface Value {
    /**
     * Returns if this tag has a value. The value can be empty.
     * @return True if this tag has a value
     */
    boolean hasValue();

    /**
     * Returns the value of this tag. If no tag is present, null will be returned.
     * @return Tag value as string or null.
     */
    String getTagValue();

    /**
     * Modifies the value of this tag. This can be done even if the tag has no value previously
     * @param value New value of this tag
     */
    Value setTagValue(String value);

    /**
     * Returns the value of this tag parsed to an int
     * @return Value as int
     */
    default int getIntValue() {
        return Integer.parseInt(this.getTagValue());
    }

    /**
     * Returns the value of this tag parsed to a boolean
     * @return Value as boolean
     */
    default boolean getBooleanValue() {
        return Boolean.parseBoolean(this.getTagValue());
    }

    /**
     * Returns the value of this tag parsed to a float
     * @return Value as float
     */
    default float getFloatValue() {
        return Float.parseFloat(this.getTagValue());
    }

    /**
     * Returns the value of this tag parsed to a double
     * @return Value as double
     */
    default double getDoubleValue() {
        return Double.parseDouble(this.getTagValue());
    }
}
