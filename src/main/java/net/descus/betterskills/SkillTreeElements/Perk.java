package net.descus.betterskills.SkillTreeElements;


import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;

import java.util.ArrayList;
import java.util.List;

public class Perk extends SkillTreeElement{


    private boolean canBeSkilled = true;
    private boolean isSkilled;

    String reqOperand = "AND";

    List<Perk> reqPerks = new ArrayList<>();
    List<Perk> blacklist = new ArrayList<>();

    private PerkTier tier;


    public Perk(int x, int y){
        super(x, y);
        id++;

    }

    public void refreshSkillable(){
        boolean b = true;
        if(!reqPerks.isEmpty()) {
            for (Perk p: reqPerks) {
                switch(reqOperand.toUpperCase()){
                    case "AND":
                        b = b && p.isSkilled();
                        break;
                    case "OR":
                        b = b || p.isSkilled();
                        break;
                    case "NAND":
                        b = !(b && p.isSkilled());
                        break;
                    case "NOR":
                        b = !(b || p.isSkilled());
                        break;
                    case "XOR":
                        b = b ^ p.isSkilled();
                        break;
                    case "XNOR":
                        b = b == p.isSkilled();
                }
            }
        }
        if(!blacklist.isEmpty()){
            for (Perk bl : blacklist){
                if(bl.isSkilled()){
                    b = false;
                }
            }
        }
        canBeSkilled = b;
    }

    @Override
    protected void performAction() {

    }

    private boolean isSkilled(){
        return isSkilled;
    }

    public void setPerkTier(PerkTier tier){
        this.tier = tier;
    }


}
