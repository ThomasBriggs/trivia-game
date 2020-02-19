import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Trivia
 */
public class TriviaAPI {

    private final static String TRIVIADB_URL = "https://opentdb.com/api.php";
    private final static String CATAGORY_URL = "https://opentdb.com/api_category.php";

    /**
     * Constructs a API URL from the base URL of the API and any paramaters for the
     * API
     * 
     * @param baseUrl the base URL of the API
     * @param params  any parameters to be given to the API
     * @return a URL object made from the base URL and the Parameters
     * @throws MalformedURLException
     */
    private static URL makeUrl(String baseUrl, String... params) throws MalformedURLException {
        String url = baseUrl + "?";
        for (int i = 0; i < params.length; i++) {
            if (i > 0) {
                url += "&";
            }
            url += params[i];
        }
        return new URL(url);
    }

    public static String get(String... params) throws IOException {
        URL url = makeUrl(TRIVIADB_URL, params);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        InputStreamReader in = new InputStreamReader(conn.getInputStream());
        BufferedReader br = new BufferedReader(in);
        String output = br.readLine();
        conn.disconnect();
        return output;
    }

    public static String getCatagoryList() throws IOException {
        URL url = makeUrl(CATAGORY_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        InputStreamReader in = new InputStreamReader(conn.getInputStream());
        BufferedReader br = new BufferedReader(in);
        String output = br.readLine();
        conn.disconnect();
        return output;
    }
}