package shadowedleaves.calculator;

import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

import static com.mojang.brigadier.Command.SINGLE_SUCCESS;

public class CalculatorClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
			LiteralArgumentBuilder<FabricClientCommandSource> command = LiteralArgumentBuilder.<FabricClientCommandSource>literal("math")
					.then(RequiredArgumentBuilder.<FabricClientCommandSource, Double>argument("num1", DoubleArgumentType.doubleArg())
							.then(RequiredArgumentBuilder.<FabricClientCommandSource, String>argument("operation", StringArgumentType.string())
									.then(RequiredArgumentBuilder.<FabricClientCommandSource, Double>argument("num2", DoubleArgumentType.doubleArg())
											.executes(context -> {
												double num1 = DoubleArgumentType.getDouble(context, "num1");
												String operation = StringArgumentType.getString(context, "operation");
												double num2 = DoubleArgumentType.getDouble(context, "num2");
												double result;
												String message;

												switch (operation) {
													case "+", "plus", "add":
														result = num1 + num2;
														message = num1 + " + " + num2 + " = " + result;
														break;
                                                    case "-", "minus", "subtract":
														result = num1 - num2;
														message = num1 + " - " + num2 + " = " + result;
														break;
													case "*", "times", "multiply":
														result = num1 * num2;
														message = num1 + " * " + num2 + " = " + result;
														break;
													case "/", "dividedby", "divide":
														if (num2 == 0) {
															message = "Cannot divide by zero!";
														} else {
															result = num1 / num2;
															message = num1 + " / " + num2 + " = " + result;
														}
														break;
													default:
														message = "Invalid operation!";
												}

												MinecraftClient.getInstance().player.sendMessage(Text.literal(message), false);
												return SINGLE_SUCCESS;
											})
									)
							)
					);

			dispatcher.register(command);
		});
	}
}
