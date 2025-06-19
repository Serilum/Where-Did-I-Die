package com.natamus.wheredididie.util;

import com.natamus.collective.functions.StringFunctions;
import com.natamus.collective.functions.TaskFunctions;
import com.natamus.wheredididie.config.ConfigHandler;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Screenshot;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;

public class Util {
	public static void processDeathCode(LocalPlayer localPlayer) {
		ClientLevel clientLevel = localPlayer.clientLevel;

		BlockPos playerPos = localPlayer.blockPosition();
		int x = playerPos.getX();
		int y = playerPos.getY();
		int z = playerPos.getZ();

		String dimension = StringFunctions.capitalizeEveryWord(clientLevel.dimension().location().getPath());

		if (ConfigHandler.showCoordinatesInChat) {
			Component deathComponent = Component.literal(" 🪦").withStyle(ChatFormatting.GRAY)
										.append(Component.literal(" " + x).withStyle(ChatFormatting.GOLD))
										.append(Component.literal(",").withStyle(ChatFormatting.GRAY))
										.append(Component.literal(" " + y).withStyle(ChatFormatting.GOLD))
										.append(Component.literal(",").withStyle(ChatFormatting.GRAY))
										.append(Component.literal(" " + z).withStyle(ChatFormatting.GOLD))
										.append(Component.literal(",").withStyle(ChatFormatting.GRAY))
										.append(Component.literal(" " + dimension).withStyle(ChatFormatting.GOLD));

			localPlayer.displayClientMessage(Component.literal(""), false);
			localPlayer.displayClientMessage(deathComponent, false);
			localPlayer.displayClientMessage(Component.literal(""), false);
		}

		if (ConfigHandler.broadcastCoordinatesToServer) {
			localPlayer.connection.sendChat("🪦 " + x + ", " + y + ", " + z + ", " + dimension);
		}

		if (ConfigHandler.screenshotDeathCoordinates && (ConfigHandler.showCoordinatesInChat || ConfigHandler.broadcastCoordinatesToServer)) {
			TaskFunctions.enqueueCollectiveClientTask(() -> {
				Minecraft mc = Minecraft.getInstance();
				Screenshot.grab(mc.gameDirectory, mc.getMainRenderTarget(), (context) -> {
					mc.execute(() -> { });
				});
			}, 10);
		}
	}
}
