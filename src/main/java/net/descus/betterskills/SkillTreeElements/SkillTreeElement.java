package net.descus.betterskills.SkillTreeElements;

import net.descus.betterskills.util.CustomSpriteLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.ArrayList;

public abstract class SkillTreeElement extends GuiScreen implements IMovable{
    int id;
    int width;
    int height;
    int texX;
    int texY;

    protected float posX;
    protected float posY;
    protected float centerX = posX + width/2F;
    protected float centerY = posY + height/2F;

    boolean pausing = false;

    CustomSpriteLoader.CustomSprite sprite;

    private boolean isVisible;
    private boolean isActive;

    static float zLevel = 1F;
    static int lastId = 0;

    public SkillTreeElement(int posX, int posY) {
        super();
        id = lastId++;
        this.posX = posX;
        this.posY = posY;
        setSprite(CustomSpriteLoader.spriteList.get(0));
        width = sprite.getWidth();
        height = sprite.getHeight();
    }

    public void draw(){
        updateZLevel();
        float spriteFacX = sprite.getHeight()/256F;
        float spriteFacY = sprite.getWidth()/256F;
        GlStateManager.pushMatrix();
        Minecraft.getMinecraft().renderEngine.bindTexture(sprite.getLocation());
        GlStateManager.scale((spriteFacX) * zLevel, (spriteFacY) * zLevel, 1);
        drawTexturedModalRect(posX * 1/spriteFacX, posY * 1/spriteFacY, sprite.getX(), sprite.getY(), 256, 256);
        GlStateManager.popMatrix();
    }

    private static void updateZLevel(){
        int wheel = Integer.signum(Mouse.getDWheel());
        if(wheel > 0){
            if(zLevel < 4) zLevel *= 2;
        } else if(wheel < 0 ){
            if(zLevel > 0.5) zLevel /= 2;
        }
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

    private boolean isMouseOnElement(int mouseX, int mouseY){
        float startPosX = posX;
        float startPosY = posY;
        float endPosX = (posX + width);
        float endPosY = (posY + height);

        if(mouseX >= startPosX * zLevel && mouseX <= endPosX * zLevel && mouseY >= startPosY * zLevel &&  mouseY <= endPosY * zLevel){
            return true;
        }
        return false;
    }

    public void onClicked(int mouseX ,int mouseY){
        if(isMouseOnElement(mouseX, mouseY)){
            performAction();
        }
    }

    protected abstract void performAction();


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

    public boolean isInList(ArrayList<SkillTreeElement> list){
        if(!list.isEmpty()){
            for (SkillTreeElement e : list) {
                if(e.posY == posY && e.posX == posX){
                  return true;
                }
            }
        }
        return false;
    }


    @Override
    public void move(float deltaX, float deltaY) {

    }
}
