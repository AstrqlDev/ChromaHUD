/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  com.google.gson.Gson
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  org.apache.commons.io.FileUtils
 */
package xyz.apfelmus.cheeto.client.configs;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import xyz.apfelmus.cf4m.CF4M;
import xyz.apfelmus.cf4m.annotation.config.Config;
import xyz.apfelmus.cf4m.annotation.config.Load;
import xyz.apfelmus.cf4m.annotation.config.Save;
import xyz.apfelmus.cheeto.client.settings.BooleanSetting;
import xyz.apfelmus.cheeto.client.settings.FloatSetting;
import xyz.apfelmus.cheeto.client.settings.IntegerSetting;
import xyz.apfelmus.cheeto.client.settings.ModeSetting;
import xyz.apfelmus.cheeto.client.settings.StringSetting;

@Config(name="Client")
public class ClientConfig {
    public static Map<String, JsonArray> configs = Maps.newHashMap();
    private static String activeConfig = "Default";
    public static boolean swapping = false;

    @Load
    public void load() {
        JsonObject fullCfg = new JsonObject();
        if (!Files.exists(Paths.get(CF4M.INSTANCE.configManager.getPath(this), new String[0]), new LinkOption[0])) {
            this.save();
        }
        try {
            fullCfg = (JsonObject)new Gson().fromJson(this.read(CF4M.INSTANCE.configManager.getPath(this)), JsonObject.class);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        activeConfig = fullCfg.get("activeConfig").getAsString();
        if (activeConfig == null) {
            System.out.println("Nah legit not possible");
            return;
        }
        JsonArray configsArray = fullCfg.get("configs").getAsJsonArray();
        for (JsonElement config : configsArray) {
            JsonObject a = config.getAsJsonObject();
            configs.put(a.get("name").getAsString(), a.get("config").getAsJsonArray());
        }
        ClientConfig.activate(activeConfig);
    }

    @Save
    public void save() {
        JsonObject fullCfg = new JsonObject();
        if (configs.isEmpty()) {
            configs.put("Default", this.generateModuleJson());
        }
        fullCfg.addProperty("activeConfig", activeConfig);
        JsonArray configsArray = new JsonArray();
        for (Map.Entry<String, JsonArray> e : configs.entrySet()) {
            JsonObject config = new JsonObject();
            config.addProperty("name", e.getKey());
            if (e.getKey().equals(activeConfig)) {
                config.add("config", (JsonElement)this.generateModuleJson());
            } else {
                config.add("config", (JsonElement)e.getValue());
            }
            configsArray.add((JsonElement)config);
        }
        fullCfg.add("configs", (JsonElement)configsArray);
        try {
            this.write(CF4M.INSTANCE.configManager.getPath(this), new Gson().toJson((JsonElement)fullCfg));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        this.load();
    }

    private JsonArray generateModuleJson() {
        JsonArray modules = new JsonArray();
        for (Object module : CF4M.INSTANCE.moduleManager.getModules()) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("name", CF4M.INSTANCE.moduleManager.getName(module));
            jsonObject.addProperty("enable", Boolean.valueOf(CF4M.INSTANCE.moduleManager.isEnabled(module)));
            jsonObject.addProperty("key", (Number)CF4M.INSTANCE.moduleManager.getKey(module));
            ArrayList<Object> settings = CF4M.INSTANCE.settingManager.getSettings(module);
            if (settings != null && settings.size() > 0) {
                JsonObject sets = this.getModuleSettings(module, settings);
                jsonObject.add("settings", (JsonElement)sets);
            }
            modules.add((JsonElement)jsonObject);
        }
        return modules;
    }

    private JsonObject getModuleSettings(Object module, List<Object> settings) {
        JsonObject sets = new JsonObject();
        for (Object set : settings) {
            String setName = CF4M.INSTANCE.settingManager.getName(module, set);
            if (set instanceof BooleanSetting) {
                sets.addProperty(setName, Boolean.valueOf(((BooleanSetting)set).isEnabled()));
                continue;
            }
            if (set instanceof FloatSetting) {
                sets.addProperty(setName, (Number)((FloatSetting)set).getCurrent());
                continue;
            }
            if (set instanceof IntegerSetting) {
                sets.addProperty(setName, (Number)((IntegerSetting)set).getCurrent());
                continue;
            }
            if (set instanceof ModeSetting) {
                sets.addProperty(setName, ((ModeSetting)set).getCurrent());
                continue;
            }
            if (!(set instanceof StringSetting)) continue;
            sets.addProperty(setName, ((StringSetting)set).getCurrent());
        }
        return sets;
    }

    public static boolean renameConfig(String configName) {
        JsonArray cfg = configs.get(activeConfig);
        configs.remove(activeConfig);
        configs.put(configName, cfg);
        activeConfig = configName;
        CF4M.INSTANCE.configManager.save();
        return true;
    }

    public static boolean removeConfig(String configName) {
        if (configs.size() > 1) {
            for (String s : configs.keySet()) {
                if (!s.equalsIgnoreCase(configName)) continue;
                configName = s;
            }
            JsonArray cfg = configs.get(activeConfig);
            configs.remove(configName, (Object)cfg);
            if (activeConfig.equalsIgnoreCase(configName)) {
                Map.Entry nextCfg = configs.entrySet().stream().findFirst().orElse(null);
                if (nextCfg == null) {
                    return false;
                }
                activeConfig = (String)nextCfg.getKey();
            }
            if (!ClientConfig.activate(activeConfig)) {
                return false;
            }
            CF4M.INSTANCE.configManager.save();
            return true;
        }
        return false;
    }

    public static boolean createConfig(String configName) {
        for (String s : configs.keySet()) {
            if (!s.equalsIgnoreCase(configName)) continue;
            return false;
        }
        JsonArray cfg = configs.get(activeConfig);
        configs.put(configName, cfg);
        activeConfig = configName;
        CF4M.INSTANCE.configManager.save();
        return true;
    }

    private static boolean activate(String config) {
        if (!configs.containsKey(config)) {
            return false;
        }
        swapping = true;
        JsonArray jsonArray = configs.get(config);
        for (Object module : CF4M.INSTANCE.moduleManager.getModules()) {
            for (JsonElement jsonElement : jsonArray) {
                JsonObject jsonObject = jsonElement.getAsJsonObject();
                if (!CF4M.INSTANCE.moduleManager.getName(module).equals(((JsonObject)new Gson().fromJson((JsonElement)jsonObject, JsonObject.class)).get("name").getAsString())) continue;
                CF4M.INSTANCE.moduleManager.setEnabled(module, jsonObject.get("enable").getAsBoolean());
                if (jsonObject.has("settings")) {
                    JsonObject setObj = jsonObject.get("settings").getAsJsonObject();
                    ArrayList<Object> settings = CF4M.INSTANCE.settingManager.getSettings(module);
                    if (settings != null && settings.size() > 0) {
                        for (Object e : settings) {
                            String setName = CF4M.INSTANCE.settingManager.getName(module, e);
                            if (!setObj.has(setName)) continue;
                            if (e instanceof BooleanSetting) {
                                ((BooleanSetting)e).setState(setObj.get(setName).getAsBoolean());
                                continue;
                            }
                            if (e instanceof FloatSetting) {
                                ((FloatSetting)e).setCurrent(Float.valueOf(setObj.get(setName).getAsFloat()));
                                continue;
                            }
                            if (e instanceof IntegerSetting) {
                                ((IntegerSetting)e).setCurrent(setObj.get(setName).getAsInt());
                                continue;
                            }
                            if (e instanceof ModeSetting) {
                                ((ModeSetting)e).setCurrent(setObj.get(setName).getAsString());
                                continue;
                            }
                            if (!(e instanceof StringSetting)) continue;
                            ((StringSetting)e).setCurrent(setObj.get(setName).getAsString());
                        }
                    }
                }
                CF4M.INSTANCE.moduleManager.setKey(module, jsonObject.get("key").getAsInt());
            }
        }
        swapping = false;
        return true;
    }

    public static List<String> getConfigs() {
        return new ArrayList<String>(configs.keySet());
    }

    private String read(String path) throws IOException {
        return FileUtils.readFileToString((File)new File(path));
    }

    private void write(String path, String string) throws IOException {
        FileUtils.writeStringToFile((File)new File(path), (String)string, (String)"UTF-8");
    }

    public static String getActiveConfig() {
        return activeConfig;
    }

    public static boolean setActiveConfig(String cfg) {
        for (String s : configs.keySet()) {
            if (!s.equalsIgnoreCase(cfg)) continue;
            cfg = s;
        }
        if (ClientConfig.activate(cfg)) {
            activeConfig = cfg;
            return true;
        }
        return false;
    }
}

