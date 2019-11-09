package com.tesseract.pokepling;

import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = PokePling.MODID, name = PokePling.NAME, version = PokePling.VERSION, acceptableRemoteVersions = "*", clientSideOnly = true)
public class PokePling {
    public static final String MODID = "pokepling";
    public static final String NAME = "PokePling";
    public static final String VERSION = "1.0";

    public static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        FMLCommonHandler.instance().bus().register(new Pling());
    }
    @EventHandler
    public void init(FMLServerStartingEvent event) {
        logger.info("initalise FMLServerStartingEvent :" + NAME);
    }


}