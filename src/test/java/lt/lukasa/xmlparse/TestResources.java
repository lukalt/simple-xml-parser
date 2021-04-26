package lt.lukasa.xmlparse;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @author Lukas Alt
 * @since 23.04.2021
 */
public class TestResources {
    public static InputStream getResource(String name) throws FileNotFoundException {
        File f = new File(new File("src", "test"), "resources");
        return new FileInputStream(new File(f, name));
    }
}
