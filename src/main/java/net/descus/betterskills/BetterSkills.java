package net.descus.betterskills;

import net.descus.betterskills.Handlers.ConfigHandler;
import net.descus.betterskills.Handlers.KeyHandler;
import net.descus.betterskills.proxy.IProxy;
import net.descus.betterskills.util.Reference;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import org.apache.logging.log4j.Logger;

import java.io.File;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION, acceptedMinecraftVersions = Reference.MCVERSION)
public class BetterSkills {

    private static Logger logger;
    public static File config;

    @SidedProxy(clientSide = "net.descus.betterskills.proxy.ClientProxy", serverSide = "net.descus.betterskills.proxy.ServerProxy")
    public static IProxy proxy;

    @Mod.Instance(Reference.MODID)
    public BetterSkills instance;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        ConfigHandler.registerConfig(event);
        logger = event.getModLog();

        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(KeyHandler.class);
        proxy.postInit(event);
    }

    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event)
    {
        proxy.serverStarting(event);
    }

    public static void printMessage(String message){
        logger.info(message);
    }
}
