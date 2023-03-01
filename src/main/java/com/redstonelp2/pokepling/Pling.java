package com.redstonelp2.pokepling;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.Mod;


import java.util.Arrays;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.FORGE)
public class Pling {

        SoundEvent sound;
        Pling(){
                ResourceLocation location = new ResourceLocation("minecraft", "block.note_block.pling");
                sound = new SoundEvent(location);
                MinecraftForge.EVENT_BUS.register(this);
        }

        @SubscribeEvent
        public void onChat(ClientChatReceivedEvent e) {
                ClientPlayerEntity player = Minecraft.getInstance().player;
                String username = player.getDisplayName().getString();
                String s = e.getMessage().getString();
                if (!s.split(" ", 0)[0].contains("<")&&!s.split(" ", 0)[0].contains("[")){
                        return;
                }
                PokePling.logger.info(s);
                PokePling.logger.info("PLING!");
                String[] sArr = s.split(" ", 0);
                sArr = Arrays.copyOfRange(sArr, 1, sArr.length);
                s = Arrays.toString(sArr);
                if (s.toLowerCase().contains(username.toLowerCase())) {
                        player.playSound(sound, 50, 5);
                }
}
}
