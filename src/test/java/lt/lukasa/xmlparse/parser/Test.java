package lt.lukasa.xmlparse.parser;

import lt.lukasa.xmlparse.api.Document;
import lt.lukasa.xmlparse.exception.XmlParseException;
import lt.lukasa.xmlparse.reader.XmlDocumentReader;
import lt.lukasa.xmlparse.writer.ConsoleWriter;

import java.io.File;
import java.io.IOException;

/**
 * @author Lukas Alt
 * @since 22.04.2021
 */
public class Test {
    public static void main(String[] args) throws IOException, XmlParseException {
        XmlDocumentReader reader = new XmlDocumentReader(new File("pom.xml"));
        Document document = reader.read();
        document.write(new ConsoleWriter());
        document.getRoot().visit(tag -> {
            tag.getFirstChild("properties")
                    .getFirstChild("maven.compiler.source")
                    .setTagValue("7");
        }).visit(tag -> {
            tag.getFirstChild("dependencies")
                    .createChild("dependency")
                    .addChild("groupdId", "net.gommehd.goframe")
                    .addChild("artifactId", "goframe")
                    .addChild("version", "1.0-SNAPSHOT");
        });
        document.write(new ConsoleWriter());
    }
}
