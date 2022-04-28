/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.launchwrapper.ITweaker
 *  net.minecraft.launchwrapper.Launch
 *  net.minecraft.launchwrapper.LaunchClassLoader
 */
package gg.essential.loader.stage0;

import gg.essential.loader.stage0.EssentialLoader;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.util.List;
import net.minecraft.launchwrapper.ITweaker;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.launchwrapper.LaunchClassLoader;

public class EssentialSetupTweaker
implements ITweaker {
    private static final String STAGE1_CLS = "gg.essential.loader.stage1.EssentialSetupTweaker";
    private final EssentialLoader loader = new EssentialLoader("launchwrapper");
    private final ITweaker stage1;

    public EssentialSetupTweaker() {
        try {
            this.stage1 = this.loadStage1(this);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private ITweaker loadStage1(ITweaker stage0) throws Exception {
        if (Launch.minecraftHome == null) {
            Launch.minecraftHome = new File(".");
        }
        Path stage1File = this.loader.loadStage1File(Launch.minecraftHome.toPath());
        URL stage1Url = stage1File.toUri().toURL();
        LaunchClassLoader classLoader = Launch.classLoader;
        classLoader.addURL(stage1Url);
        classLoader.addClassLoaderExclusion("gg.essential.loader.stage1.");
        EssentialSetupTweaker.addUrlHack(classLoader.getClass().getClassLoader(), stage1Url);
        return (ITweaker)Class.forName(STAGE1_CLS, true, (ClassLoader)classLoader).getConstructor(ITweaker.class).newInstance(new Object[]{stage0});
    }

    public void acceptOptions(List<String> args, File gameDir, File assetsDir, String profile) {
        this.stage1.acceptOptions(args, gameDir, assetsDir, profile);
    }

    public void injectIntoClassLoader(LaunchClassLoader classLoader) {
        this.stage1.injectIntoClassLoader(classLoader);
    }

    public String getLaunchTarget() {
        return this.stage1.getLaunchTarget();
    }

    public String[] getLaunchArguments() {
        return this.stage1.getLaunchArguments();
    }

    private static void addUrlHack(ClassLoader loader, URL url) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        ClassLoader classLoader = Launch.classLoader.getClass().getClassLoader();
        Method method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
        method.setAccessible(true);
        method.invoke(classLoader, url);
    }
}

