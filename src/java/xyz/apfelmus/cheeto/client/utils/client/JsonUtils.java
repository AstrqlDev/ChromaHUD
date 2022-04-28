/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 */
package xyz.apfelmus.cheeto.client.utils.client;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import xyz.apfelmus.cheeto.client.utils.fishing.FishingJson;
import xyz.apfelmus.cheeto.client.utils.mining.MiningJson;

public class JsonUtils {
    private static Gson gson = new Gson();
    private static JsonParser parser = new JsonParser();

    public static MiningJson getMiningJson() {
        MiningJson mj = null;
        try {
            mj = (MiningJson)gson.fromJson(JsonUtils.getContent("https://gist.githubusercontent.com/Apfelmus1337/db85f05991a60ef4f1a6059353120764/raw"), MiningJson.class);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return mj;
    }

    public static FishingJson getFishingJson() {
        FishingJson mj = null;
        try {
            mj = (FishingJson)gson.fromJson(JsonUtils.getContent("https://gist.githubusercontent.com/Apfelmus1337/b4bbccd049846425cb89d76cc02eb29d/raw"), FishingJson.class);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return mj;
    }

    public static List<String> getListFromUrl(String url, String name) {
        ArrayList<String> ret = new ArrayList<String>();
        try {
            JsonObject json = (JsonObject)parser.parse(JsonUtils.getContent(url));
            json.getAsJsonArray(name).forEach(je -> ret.add(je.getAsString()));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    public static String getContent(String url) throws Exception {
        String inputLine;
        URL website = new URL(url);
        URLConnection connection = website.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }
}

