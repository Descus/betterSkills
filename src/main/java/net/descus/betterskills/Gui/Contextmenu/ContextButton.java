package net.descus.betterskills.Gui.Contextmenu;

import net.descus.betterskills.util.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;

public abstract class ContextButton extends GuiButton {

    ResourceLocation tex = new ResourceLocation(Reference.MODID,"textures/GUI/EditorGuiElements.png");

    public ContextButton(String text, int buttonId, int height, int width, int x, int y){
        super(buttonId, x, y, width, height,text);
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks){
        System.out.println(tex.getResourceDomain());
        mc.renderEngine.bindTexture(tex);
        drawTexturedModalRect(100,100, 1,1, 40, 10);
    }

    @Override
    public abstract boolean mousePressed(Minecraft mc, int mouseX, int mouseY);

}
