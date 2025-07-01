package com.natamus.wheredididie.config;

import com.natamus.collective.config.DuskConfig;
import com.natamus.wheredididie.util.Reference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ConfigHandler extends DuskConfig {
	public static HashMap<String, List<String>> configMetaData = new HashMap<String, List<String>>();

	@Entry public static boolean showCoordinatesInChat = true;
	@Entry public static boolean broadcastCoordinatesToServer = false;
	@Entry public static boolean screenshotDeathCoordinates = true;

	public static void initConfig() {
		configMetaData.put("showCoordinatesInChat", Arrays.asList(
			"If the death coordinates should be sent in the chat. This is visible for you only, the client."
		));
		configMetaData.put("broadcastCoordinatesToServer", Arrays.asList(
			"If the death coordinates should be broadcasted to the server. The message sent is as if you've typed it yourself."
		));
		configMetaData.put("screenshotDeathCoordinates", Arrays.asList(
			"If a screenshot should be taken when the coordinates are visible to save them across instances."
		));

		DuskConfig.init(Reference.NAME, Reference.MOD_ID, ConfigHandler.class);
	}
}