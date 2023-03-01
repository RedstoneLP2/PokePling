package com.redstonelp2.pokepling;

import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("pokepling")
public class PokePling {
    public static final String MODID = "pokepling";
    public static final String NAME = "PokePling";

    // Directly reference a log4j logger.
    protected static final Logger logger = LogManager.getLogger();

    public PokePling() {
        logger.info("POKEPLING INITIATED!");
        Pling pling = new Pling();
        
    }

}