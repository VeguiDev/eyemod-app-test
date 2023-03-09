package com.example.examplemod.network.server;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

import com.example.api.PoliceCall;
import com.example.examplemod.ExampleMod;
import com.example.examplemod.network.client.CallPolice;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.OutgoingPlayerChatMessage;
import net.minecraft.network.chat.PlayerChatMessage;
import net.minecraft.network.chat.contents.LiteralContents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;
import net.minecraft.world.scores.PlayerTeam;
import net.minecraft.world.scores.Team;
import net.minecraftforge.internal.TextComponentMessageFormatHandler;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.server.ServerLifecycleHooks;
import net.minecraftforge.server.command.TextComponentHelper;

public class CallPoliceServerHandler {
    public static void onCallPolice(CallPolice message, Supplier<NetworkEvent.Context> ctx) {

        PoliceCall call = message.getPoliceCall();

        System.out.println("Issuer: " + call.issuer + " Reason: " + call.reason);

        MinecraftServer server = ServerLifecycleHooks.getCurrentServer();

        PlayerTeam team = server.getScoreboard().getPlayerTeam("police");

        if (team == null) {
            System.out.println("The team 'police' not exists.");
            return;
        }

        List<String> playersName = new ArrayList<String>(team.getPlayers());

        PlayerList list = server.getPlayerList();

        for (int i = 0; i < playersName.size(); i++) {

            ServerPlayer player = list.getPlayerByName(playersName.get(i));

            if (player != null) {

                JsonArray jsonMsg = new JsonArray();

                JsonObject calledMessage = new JsonObject();

                calledMessage.addProperty("text", call.issuer + " is calling to the police.\n");

                JsonObject reasonMSG = new JsonObject();

                reasonMSG.addProperty("text", "Reason: " + call.reason + "\n");

                JsonObject coordsMSG = new JsonObject();

                coordsMSG.addProperty("text",
                        "X: " + Math.round(call.x) + " Y: " + Math.round(call.y) + " Z: " + Math.round(call.z));

                jsonMsg.add(calledMessage);
                jsonMsg.add(reasonMSG);
                jsonMsg.add(coordsMSG);

                MutableComponent chatMSG = Component.Serializer.fromJson(jsonMsg);

                player.sendSystemMessage(chatMSG);

            }

        }

    }
}
