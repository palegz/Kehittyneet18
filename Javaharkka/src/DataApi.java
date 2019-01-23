import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import com.google.gson.Gson;

class Response {
    List<Crypto> data;
}
public class DataApi implements Runnable {                      //DataApi, logiikka apin käsittelylle.

    private String url_;
    private String dataStr_;

    DataApi(String url) {
        this.url_ = url;
    }

    @Override
    public void run() {
        try {
            HttpsURLConnection connection = (HttpsURLConnection) new URL(this.url_)
                    .openConnection();
            connection.setRequestMethod("GET");
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream())
            );
            StringBuilder stringBuilder = new StringBuilder();
            String input;
            while ((input = bufferedReader.readLine()) != null) {
                stringBuilder.append(input);
            }
            bufferedReader.close();
            connection.disconnect();
            this.dataStr_ = stringBuilder.toString();
            System.out.println(this.dataStr_);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Crypto> getCrypto() {
        Gson gson = new Gson();
        Response response = gson.fromJson(this.dataStr_, Response.class);
        return response.data;
    }

}
