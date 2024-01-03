package vodzinskiy.precipitationoff.commands;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import me.lucko.commodore.Commodore;

public class CommodoreCommands {
    public static void registerCompletions(Commodore commodore) {

        LiteralArgumentBuilder<?> literalBuilder = LiteralArgumentBuilder.literal("pptoff")
                .then(LiteralArgumentBuilder.literal("select")
                        .then(LiteralArgumentBuilder.literal("cancel")))
                .then(LiteralArgumentBuilder.literal("list"))
                .then(LiteralArgumentBuilder.literal("delete")
                        .then(LiteralArgumentBuilder.literal("all"))
                        .then(RequiredArgumentBuilder.argument("name", StringArgumentType.word())))
                .then(LiteralArgumentBuilder.literal("show")
                        .then(LiteralArgumentBuilder.literal("all"))
                        .then(LiteralArgumentBuilder.literal("stop")
                                .then(RequiredArgumentBuilder.argument("name", StringArgumentType.word())))
                        .then(RequiredArgumentBuilder.argument("name", StringArgumentType.word())))
                .then(LiteralArgumentBuilder.literal("setNoSnowFormation")
                        .then(RequiredArgumentBuilder.argument("name", StringArgumentType.word())));

        commodore.register(literalBuilder);
    }
}
