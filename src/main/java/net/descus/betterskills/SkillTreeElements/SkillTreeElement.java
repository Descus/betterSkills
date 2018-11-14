package net.descus.betterskills.SkillTreeElements;

import net.descus.betterskills.BetterSkills;
import net.descus.betterskills.util.CustomSpriteLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class SkillTreeElement extends GuiScreen implements IMovable{
    int id;
    int width;
    int height;
    int texX;
    int texY;

    protected float posX;
    protected float posY;
    protected float centerX = posX + width/2F;
    protected float centerY = posY + height/2F;
    float zLevel;
    boolean pausing = false;

    CustomSpriteLoader.CustomSprite sprite;

    private boolean isVisible;
    private boolean isActive;


    public SkillTreeElement(int posX, int posY) {
        super();
        this.posX = posX;
        this.posY = posY;
        setSprite(CustomSpriteLoader.spriteList.get(0));
    }

    public void draw(){
        BetterSkills.printMessage("Drawn");
        Minecraft.getMinecraft().renderEngine.bindTexture(sprite.getLocation());
        drawTexturedModalRect(posX, posY, sprite.getX(), sprite.getY(), sprite.getHeight(), sprite.getHeight());
    }

    public SkillTreeElement getById(int id){
        if(this.id == id){
            return this;
        }
        return null;
    }

    @Override
    public void initGui() {

        super.initGui();
    }

    public void setSprite(CustomSpriteLoader.CustomSprite sprite) {
        this.sprite = sprite;
    }



    public void drawLine(SkillTreeElement elem, float width, Color color)
    {
        GlStateManager.pushMatrix();

        GlStateManager.disableTexture2D();
        GlStateManager.color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
        GL11.glLineWidth(width);

        GL11.glBegin(GL11.GL_LINES);
        GL11.glVertex2f(centerX, centerY);
        GL11.glVertex2f(elem.centerX, elem.centerY);
        GL11.glEnd();

        GlStateManager.enableTexture2D();
        GlStateManager.color(1F, 1F, 1F, 1F);

        GlStateManager.popMatrix();
    }


    @Override
    public void move(float deltaX, float deltaY) {

    }
}
