import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Trivia
 */
public class Trivia {

    private static String baseUrl = "https://opentdb.com/api.php";

    public static String get(String... params) throws Exception {
        URL url = new URL(makeUrl(params));
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        InputStreamReader in = new InputStreamReader(conn.getInputStream());
        BufferedReader br = new BufferedReader(in);
        String output = br.readLine();
        conn.disconnect();
        return output; 
    }

    private static String makeUrl(String... params){
        String output = baseUrl + "?";
        for (int i = 0; i < params.length; i++) {
            if (i > 0){
                output += "&";
            }
            output += params[i];
        }
        return output;
    }
}