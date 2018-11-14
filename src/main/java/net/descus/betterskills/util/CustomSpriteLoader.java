package net.descus.betterskills.util;

import net.descus.betterskills.BetterSkills;
import net.descus.betterskills.Handlers.ConfigHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CustomSpriteLoader {

    public static List<CustomSprite> spriteList = new ArrayList<>();

    @SideOnly(Side.CLIENT)
    public static void init(){
        File spriteLocation = new File(ConfigHandler.customTextureLocation + "/Sprites");
        if(!spriteLocation.exists()){
            spriteLocation.mkdirs();
        }
        File[] sprites = spriteLocation.listFiles();
        BetterSkills.printMessage("Found " + sprites.length + " Sprites");
        populateSpriteList(sprites);

    }

    private static void populateSpriteList(File[] spriteFiles){
        if(spriteFiles.length != 0) {
            for (File file : spriteFiles) {
                if (file.isFile() && file.getName().substring(file.getName().length() - 4).equals(".png")) {
                    try {
                        BufferedImage img = ImageIO.read(file);
                        ResourceLocation loc = Minecraft.getMinecraft().renderEngine.getDynamicTextureLocation(file.getName(), new DynamicTexture(img));
                        int width = img.getWidth();
                        int height = img.getHeight();
                        spriteList.add(new CustomSprite(loc, width, height));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    public static class CustomSprite{

        ResourceLocation location;
        int x = 0;
        int y = 0;
        int width;
        int height;

        private CustomSprite(ResourceLocation location, int width, int height){
            this.location = location;
            this.width = width;
            this.height = height;
        }

        public ResourceLocation getLocation() {
            return location;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }
    }
}

