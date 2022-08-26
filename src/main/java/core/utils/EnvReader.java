package core.utils;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

/**
 * Reads environment variables from json file by a singleton class.
 */
public final class EnvReader {
    private static final EnvReader envReader = new EnvReader();
    private DocumentContext documentContext;

    /**
     * Loads json file.
     */
    private EnvReader() {
        try {
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader(Constants.ENVIRONMENT_PATH));
            documentContext = JsonPath.parse(jsonObject);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public static EnvReader getInstance() {
        return envReader;
    }

    /**
     * Returns value json file given a property name.
     */
    public String getValue(final String property) {
        return documentContext.read(property);
    }
}
