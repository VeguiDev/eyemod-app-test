package com.example.examplemod.network.client;

import java.util.function.Supplier;

import com.example.api.PoliceCall;
import com.example.examplemod.network.server.CallPoliceServerHandler;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

public class CallPolice {
    String issuer;
    double x;
    double y;
    double z;
    String reason;

    public PoliceCall getPoliceCall() {
        return new PoliceCall(issuer, x, y, z, reason);
    }

    public CallPolice() {}

    public CallPolice(PoliceCall call) {
        issuer = call.issuer;
        x = call.x;
        y = call.y;
        z = call.z;
        reason = call.reason;
    }

    // Encode message to bytes.
    public static void encode(CallPolice message, FriendlyByteBuf buf) {
        buf.writeUtf(message.issuer);
        buf.writeDouble(message.x);
        buf.writeDouble(message.y);
        buf.writeDouble(message.z);
        buf.writeUtf(message.reason);
    }

    // Decode message from bytes.
    public static CallPolice decode(FriendlyByteBuf buf) {
		CallPolice message = new CallPolice();

		message.issuer = buf.readUtf();
        message.x = buf.readDouble();
        message.y = buf.readDouble();
        message.z = buf.readDouble();
        message.reason = buf.readUtf();

		return message;
	}

    // Handles all messages and executes only if on the server.
    public static void onMessage(CallPolice message, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            
            DistExecutor.unsafeRunWhenOn(Dist.DEDICATED_SERVER, () -> () -> CallPoliceServerHandler.onCallPolice(message, ctx));

        });
        ctx.get().setPacketHandled(true);
    }

}
