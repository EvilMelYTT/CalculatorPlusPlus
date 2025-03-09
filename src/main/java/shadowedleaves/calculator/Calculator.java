package shadowedleaves.calculator;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Calculator implements ModInitializer {
	public static final String MOD_ID = "calculator";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("[Calculator] Initializing Calculator Mod!");
	}
}