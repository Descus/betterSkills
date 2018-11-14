package net.descus.betterskills.SkillTreeElements;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PerkTier {
    private int prio = 0;
    private String name;
    private Color color;

    static List<PerkTier> tierList = new ArrayList<PerkTier>();

    public PerkTier(String name){
        this.name = name;
        prio++;
        tierList.add(this);
    }

    public static PerkTier getTierByName(String name){
        if(!tierList.isEmpty()){
            for (PerkTier tier: tierList) {
                if(tier.name.equals(name)){
                    return tier;
                }
            }
        }
        return null;
    }

    public static void swapPriority(PerkTier p1, PerkTier p2){
        int sav = p1.getPrio();
        p1.prio = p2.getPrio();
        p2.prio = sav;
    }

    public void setColor(int r, int g, int b){
        color = new Color(r, g, b);
    }

    public int getPrio() {
        return prio;
    }
}
