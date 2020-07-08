package com.tesseract.pokepling;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Arrays;

public class Pling {
    @SubscribeEvent
public void onChat(ClientChatReceivedEvent e) {
        EntityPlayerSP player = Minecraft.getMinecraft().player;
        ResourceLocation location = new ResourceLocation("minecraft", "block.note.pling");
        SoundEvent event = new SoundEvent(location);
        String s = e.getMessage().getUnformattedText();
        String username = player.getDisplayNameString();
        if (!s.split(" ", 0)[0].contains("<")&&!s.split(" ", 0)[0].contains("[")){
                return;
        }
        String[] sArr = s.split(" ", 0);
        sArr = Arrays.copyOfRange(sArr, 1, sArr.length);
        s = Arrays.toString(sArr);
        PokePling.logger.info(s);
        if (s.toLowerCase().contains(username.toLowerCase())) {
                player.playSound(event, 50, 5);
        }
}
}
