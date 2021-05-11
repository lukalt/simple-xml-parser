package lt.lukasa.xmlparse.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author Lukas Alt
 * @since 23.04.2021
 */
public class IOUtil {
    public static String readToString(InputStream inputStream) throws IOException {
        return readToString(inputStream, StandardCharsets.UTF_8);
    }

    public static String readToString(InputStream inputStream, Charset charset) throws IOException {
        StringBuilder sb = new StringBuilder();
        readToString(inputStream, charset, sb);
        return sb.toString();
    }

    public static void readToString(InputStream inputStream, Charset charset, StringBuilder input) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, charset))) {
            String s;
            while ((s = reader.readLine()) != null) {
                input.append(s);
                if (input.length() > 0) {
                    input.append('\n');
                }
            }
        }
    }
}
