package lt.lukasa.xmlparse.api;

import java.util.function.Consumer;

/**
 * @author Lukas Alt
 * @since 22.04.2021
 */
public interface Visitable<T> {
    T visit(Consumer<T> tag);
}
