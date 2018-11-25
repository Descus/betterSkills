package net.descus.betterskills.Handlers;

import net.descus.betterskills.BetterSkills;
import net.descus.betterskills.Gui.BetterSkillsClassicGui;
import net.descus.betterskills.proxy.ClientProxy;
import net.descus.betterskills.proxy.ServerProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class KeyHandler {



    @SideOnly(Side.CLIENT)
    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
    public static void onEvent(InputEvent.KeyInputEvent event)
    {
        List<KeyBinding> keyBindings = ClientProxy.keyBind;

        if (keyBindings.get(0).isKeyDown())
        {
            Minecraft.getMinecraft().player.sendChatMessage("P gedrückt");
            // do stuff for this key binding here
            // remember you may need to send packet to server
            Minecraft.getMinecraft().displayGuiScreen(new BetterSkillsClassicGui());
        }

    }
}
