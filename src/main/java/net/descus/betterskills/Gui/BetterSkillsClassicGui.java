package net.descus.betterskills.Gui;

import net.descus.betterskills.Gui.Contextmenu.ContextButton;
import net.descus.betterskills.Handlers.ConfigHandler;
import net.descus.betterskills.SkillTreeElements.Perk;
import net.descus.betterskills.SkillTreeElements.SkillTreeElement;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.Sys;


import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BetterSkillsClassicGui extends GuiScreen {

    private static ResourceLocation guiElems;
    private List<SkillTreeElement> skillTreeElements = new ArrayList<>();

    private static boolean pausing = false;

    //                       0|     1|   2|    3|       4|       5|       6|       7|         8|
    //                     Top|Bottom|Left|Right|TLCorner|TRCorner|BLCorner|BRCorner|Background|
    int[] elemWidth  =  {   64,    64,  12,   12,      12,      12,      12,      12,        16};
    int[] elemHeight =  {   12,    12,  64,   64,      12,      12,      12,      12,        16};
    int[] posX       =  {    0,     0,   0,   52,      17,      33,      17,      33,        71};
    int[] posY       =  {    0,    86,  17,   17,      17,      17,      33,      33,         0};

    int centerX = width / 2;
    int centerY = height / 2;

    {
        try {
            System.out.println(new File(ConfigHandler.customTextureLocation + "/GuiElements.png").getPath());
            guiElems = Minecraft.getMinecraft().renderEngine.getDynamicTextureLocation("GuiElements.png", new DynamicTexture(ImageIO.read(new File(ConfigHandler.customTextureLocation + "/Gui/GuiElements.png"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initGui() {
        buttonList.add(new ContextButton("Test", 1, 10, 30, 0, 0) {
            @Override
            public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
                return false;
            }
        });
        super.initGui();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        Minecraft.getMinecraft().renderEngine.bindTexture(guiElems);
        //Background
        GlStateManager.pushMatrix();
        {
            float scalarWidth = ((float)width-12.0F) / (float)elemWidth[8] ;
            float scalarHeight = ((float)height-12.0F) / (float)elemHeight[8] ;
            GlStateManager.scale(scalarWidth, scalarHeight, 1);
            drawTexturedModalRect(6 / scalarWidth,6 / scalarHeight , posX[8], posY[8], elemWidth[8], elemHeight[8]);
        }
        GlStateManager.popMatrix();


        drawSkillTreeElements();
        Minecraft.getMinecraft().renderEngine.bindTexture(guiElems);
        //Top/Bottom Line
        drawTexturedModalRect(0,0, posX[4], posY[4], elemWidth[4], elemHeight[4]);
        drawTexturedModalRect(0, height - elemHeight[6], posX[6], posY[6], elemWidth[6], elemHeight[6]);
        float scalarHoriz =  (((float)width-(float)elemWidth[4]-(float)elemWidth[5]) / (float)elemWidth[0]);
        GlStateManager.pushMatrix();
        {
            GlStateManager.scale(scalarHoriz, 1, 1);
            drawTexturedModalRect(elemWidth[4] / scalarHoriz, 0, posX[0], posY[0], elemWidth[0], elemHeight[0]);
            drawTexturedModalRect(elemWidth[6] / scalarHoriz, height-elemHeight[1], posX[1], posY[1], elemWidth[1],  elemHeight[1]);
        }
        GlStateManager.popMatrix();

        drawTexturedModalRect(width-elemWidth[5],0, posX[5], posY[5], elemWidth[5], elemHeight[5]);
        drawTexturedModalRect(width-elemWidth[7], height - elemHeight[7], posX[7], posY[7], elemWidth[7], elemHeight[7]);
        //Left/Right Line
        float scalarVert = ((float)height-(float)elemHeight[4]-(float)elemHeight[6]) / (float)elemHeight[2];
        GlStateManager.pushMatrix();
        {
            GlStateManager.scale(1, scalarVert, 1);
            drawTexturedModalRect(0, elemHeight[4] / scalarVert, posX[2], posY[2], elemWidth[2], elemHeight[2]);
            drawTexturedModalRect(width-elemWidth[3], elemHeight[5] / scalarVert, posX[3], posY[3], elemWidth[3], elemHeight[3]);
        }
        GlStateManager.popMatrix();


        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return pausing;
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        if(mouseButton == 1) {
            SkillTreeElement ins = new Perk(mouseX, mouseY);
            if(!ins.isInList((ArrayList<SkillTreeElement>) skillTreeElements)) {
                skillTreeElements.add(ins);
            }
        }
        if (mouseButton == 0){
            for(SkillTreeElement e : skillTreeElements){
                e.onClicked(mouseX, mouseY);
            }
        }
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }


    private void drawSkillTreeElements(){
        for(SkillTreeElement se : skillTreeElements){
            se.draw();
        }
    }

    public static void setPausing(boolean pausing) {
        BetterSkillsClassicGui.pausing = pausing;
    }
}
