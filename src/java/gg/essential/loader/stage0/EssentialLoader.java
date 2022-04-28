/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package gg.essential.loader.stage0;

import java.io.InputStream;
import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.util.Enumeration;
import java.util.jar.Attributes;
import java.util.jar.JarInputStream;
import java.util.jar.Manifest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class EssentialLoader {
    static final String STAGE1_RESOURCE = "gg/essential/loader/stage0/stage1.jar";
    static final String STAGE1_PKG = "gg.essential.loader.stage1.";
    static final String STAGE1_PKG_PATH = "gg.essential.loader.stage1.".replace('.', '/');
    static final Logger LOGGER = LogManager.getLogger(EssentialLoader.class);
    private final String variant;

    public EssentialLoader(String variant) {
        this.variant = variant;
    }

    public Path loadStage1File(Path gameDir) throws Exception {
        Enumeration<URL> resources;
        Path dataDir = gameDir.resolve("essential").resolve("loader").resolve("stage0").resolve(this.variant);
        Path stage1UpdateFile = dataDir.resolve("stage1.update.jar");
        Path stage1File = dataDir.resolve("stage1.jar");
        URL stage1Url = stage1File.toUri().toURL();
        if (!Files.exists(dataDir, new LinkOption[0])) {
            Files.createDirectories(dataDir, new FileAttribute[0]);
        }
        if (Files.exists(stage1UpdateFile, new LinkOption[0])) {
            LOGGER.info("Found update for stage1.");
            Files.deleteIfExists(stage1File);
            Files.move(stage1UpdateFile, stage1File, new CopyOption[0]);
        }
        URL latestUrl = null;
        int latestVersion = -1;
        if (Files.exists(stage1File, new LinkOption[0])) {
            latestVersion = EssentialLoader.getVersion(stage1Url);
            LOGGER.debug("Found stage1 version {}: {}", new Object[]{latestVersion, stage1Url});
        }
        if (!(resources = EssentialLoader.class.getClassLoader().getResources(STAGE1_RESOURCE)).hasMoreElements()) {
            LOGGER.warn("Found no embedded stage1 jar files.");
        }
        while (resources.hasMoreElements()) {
            URL url = resources.nextElement();
            int version = EssentialLoader.getVersion(url);
            LOGGER.debug("Found stage1 version {}: {}", new Object[]{version, url});
            if (version <= latestVersion) continue;
            latestVersion = version;
            latestUrl = url;
        }
        if (latestUrl != null) {
            LOGGER.info("Updating stage1 to version {} from {}", new Object[]{latestVersion, latestUrl});
            try (InputStream in = latestUrl.openStream();){
                Files.deleteIfExists(stage1File);
                Files.copy(in, stage1File, new CopyOption[0]);
            }
        }
        return stage1File;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static int getVersion(URL file) {
        try (JarInputStream in = new JarInputStream(file.openStream(), false);){
            Manifest manifest = in.getManifest();
            Attributes attributes = manifest.getMainAttributes();
            if (!STAGE1_PKG_PATH.equals(attributes.getValue("Name"))) {
                int n2 = -1;
                return n2;
            }
            int n = Integer.parseInt(attributes.getValue("Implementation-Version"));
            return n;
        }
        catch (Exception e) {
            LOGGER.warn("Failed to read version from " + file, (Throwable)e);
            return -1;
        }
    }
}

