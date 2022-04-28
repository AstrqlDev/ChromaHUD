/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  org.apache.commons.codec.digest.DigestUtils
 */
package xyz.apfelmus.cheeto.client.utils.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import net.minecraft.client.Minecraft;
import org.apache.commons.codec.digest.DigestUtils;
import xyz.apfelmus.cheeto.Cheeto;
import xyz.apfelmus.cheeto.client.utils.client.ChatUtils;
import xyz.apfelmus.cheeto.client.utils.client.JsonUtils;

public class VersionCheck {
    private static Minecraft mc = Minecraft.func_71410_x();
    public static boolean fuckNiggers = true;

    public static void check() {
        List<String> blacks = JsonUtils.getListFromUrl("https://gist.githubusercontent.com/Apfelmus1337/514d0bc111377f0a77ba9dac6b931057/raw", "uuids");
        if (!blacks.isEmpty() && !blacks.contains(DigestUtils.sha1Hex((String)mc.func_110432_I().func_148255_b()))) {
            fuckNiggers = false;
        }
        new Thread(() -> {
            double version = VersionCheck.getVersion();
            String msg = version == -1.0 ? "There was an error during Update Check" : (version > Cheeto.clientVersion ? "There's a newer version of Cheeto available! Download it from the Discord if you haven't already!" : "Latest version, Cheeto on top");
            while (true) {
                try {
                    do {
                        Thread.sleep(100L);
                    } while (VersionCheck.mc.field_71441_e == null);
                    Thread.sleep(1000L);
                    ChatUtils.send(msg, new String[0]);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                    continue;
                }
                break;
            }
        }).start();
    }

    private static double getVersion() {
        try {
            HttpURLConnection a = (HttpURLConnection)new URL("http://apfelmus.xyz:6969/cheeto/version").openConnection();
            a.setRequestProperty("User-Agent", "Cheeto/6.9");
            a.setRequestProperty("Content-Type", "application/json");
            a.setRequestMethod("GET");
            InputStreamReader i = new InputStreamReader(a.getInputStream());
            BufferedReader br = new BufferedReader(i);
            return Double.parseDouble(br.readLine());
        }
        catch (IOException e) {
            return -1.0;
        }
    }
}

