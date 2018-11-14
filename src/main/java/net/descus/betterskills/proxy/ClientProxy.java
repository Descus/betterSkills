package net.descus.betterskills.proxy;

import net.descus.betterskills.BetterSkills;
import net.descus.betterskills.Gui.BetterSkillsClassicGui;
import net.descus.betterskills.util.CustomSpriteLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;
import java.util.List;

public class ClientProxy implements IProxy {

    public static List<KeyBinding> keyBind;
    public static boolean useKeybind;

    @Override
    public void preInit(FMLPreInitializationEvent event) {


    }

    @Override
    public void init(FMLInitializationEvent event) {

    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {

        keyInit();
        CustomSpriteLoader.init();
    }

    @Override
    public void serverStarting(FMLServerStartingEvent event) {

    }

    @Override
    public EntityPlayer getPlayerEntityFromContext(MessageContext ctx)
    {
        return (ctx.side.isClient() ? Minecraft.getMinecraft().player : BetterSkills.proxy.getPlayerEntityFromContext(ctx));
    }

    private void keyInit(){
        keyBind = new ArrayList<KeyBinding>();
        if(useKeybind) {
            keyBind.add(new KeyBinding("key.betterskills.desc", Keyboard.KEY_P, "key.betterskills.category"));
        }
        for (KeyBinding keyBinding : keyBind) {
            ClientRegistry.registerKeyBinding(keyBinding);
        }
    }
}
