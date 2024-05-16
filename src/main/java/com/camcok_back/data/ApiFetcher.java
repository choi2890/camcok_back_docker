package com.camcok_back.data;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class ApiFetcher {
    public static List<Map<String, Object>> fetchData(String urlString) throws Exception {
        URL url = new URL("https://apis.data.go.kr/B551011/GoCamping/basedList?serviceKey=U2v8FkBtZre3faTP2NxqCqGArEkkO%2FvbnC7DDbFYtQPe0TpytXrzQywGbyZc9AgNXDrskylfErU4EddyuXAuKg%3D%3D&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }

            // Gson을 사용하여 JSON 문자열을 파싱합니다.
            Gson gson = new Gson();
            return gson.fromJson(content.toString(), new TypeToken<List<Map<String, Object>>>() {}.getType());
        } finally {
            con.disconnect();
        }
    }

}
