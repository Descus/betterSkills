package net.descus.betterskills.Handlers;


import net.descus.betterskills.BetterSkills;
import net.descus.betterskills.Gui.BetterSkillsClassicGui;
import net.descus.betterskills.proxy.ClientProxy;
import net.descus.betterskills.util.Reference;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class ConfigHandler {

    private static Configuration config;
    public static File customTextureLocation;

    private static final String[] expGuiTextures = new String[]{"GuiElements.png"};
    private static final String[] expItemTextures = new String[]{};

    private static void init(File file){

        config = new Configuration(file);

        String category = "GUI Access";
        config.addCustomCategoryComment(category, "Enable/Disable certain access methods for the Better Skills GUI");

        ClientProxy.useKeybind = config.getBoolean("Use Keybinding", category, true, "Allows access via Keybinding");
        config.getBoolean("Use Skill Book", category, true, "Allow access via Skill Book");

        category = "Map/Modpack Maker Options";
        config.addCustomCategoryComment(category, "Some Custom Map/Modpack Maker options");

        BetterSkillsClassicGui.setPausing(config.getBoolean("Gui Pauses Game", category, false, "enables/disables pause in the Better Skills Gui"));
        config.save();
    }

    public static void registerConfig(FMLPreInitializationEvent event){
        BetterSkills.config = new File(event.getModConfigurationDirectory() + "/" + Reference.MODID);
        BetterSkills.config.mkdirs();
        createCustomTextureLocation(event);
        init(new File(BetterSkills.config.getPath(), Reference.MODID + ".cfg"));
    }

    private static void createCustomTextureLocation(FMLPreInitializationEvent event){
        customTextureLocation = new File(event.getModConfigurationDirectory() + "/" + Reference.MODID + "/textures");
        if(!customTextureLocation.exists()) {
            customTextureLocation.mkdirs();
            try {
                if (expGuiTextures.length > 0){
                    for (String file : expGuiTextures) {
                        new File(customTextureLocation.getPath() + "/Gui/").mkdirs();
                        Files.copy(ConfigHandler.class.getResourceAsStream("/assets/betterskills/textures/GUI/" + file), new File(customTextureLocation.getPath() + "/Gui/" + file).toPath(), StandardCopyOption.REPLACE_EXISTING);
                    }
                }

                if (expItemTextures.length > 0){
                    for(String file : expItemTextures) {
                        Files.copy(ConfigHandler.class.getResourceAsStream("/assets/betterskills/textures/Items/" + file), new File(customTextureLocation.getPath()+ "/Items/" + file).toPath(), StandardCopyOption.REPLACE_EXISTING);
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
