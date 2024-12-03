package whocraft.tardis_refined.command.sub;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.DimensionArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerLevel;
import whocraft.tardis_refined.common.capability.tardis.TardisLevelOperator;
import whocraft.tardis_refined.common.capability.tardis.upgrades.UpgradeHandler;
import whocraft.tardis_refined.common.util.CommandHelper;
import whocraft.tardis_refined.common.util.TardisHelper;
import whocraft.tardis_refined.constants.ModMessages;

public class LevelCommand {

    public static ArgumentBuilder<CommandSourceStack, ?> register(CommandDispatcher<CommandSourceStack> dispatcher) {
        return Commands.literal("level")
                .then(
                        Commands.literal("points")
                                .then(
                                        Commands.literal("get").then(Commands.argument("tardis", DimensionArgument.dimension()).suggests(CommandHelper.SUGGEST_TARDISES).executes(LevelCommand::getDimensionPoints))
                                )
                                .then(
                                        Commands.literal("set").then(Commands.argument("amount", IntegerArgumentType.integer()).then(Commands.argument("tardis", DimensionArgument.dimension()).suggests(CommandHelper.SUGGEST_TARDISES).executes(LevelCommand::setDimensionPoints)))
                                )
                                .then(
                                        Commands.literal("add").then(Commands.argument("amount", IntegerArgumentType.integer()).then(Commands.argument("tardis", DimensionArgument.dimension()).suggests(CommandHelper.SUGGEST_TARDISES).executes(LevelCommand::addDimensionPoints)))
                                )
                )
                .then(
                        Commands.literal("xp")
                                .then(
                                        Commands.literal("get").then(Commands.argument("tardis", DimensionArgument.dimension()).suggests(CommandHelper.SUGGEST_TARDISES).executes(LevelCommand::getDimensionXp))
                                )
                                .then(
                                        Commands.literal("set").then(Commands.argument("amount", IntegerArgumentType.integer()).then(Commands.argument("tardis", DimensionArgument.dimension()).suggests(CommandHelper.SUGGEST_TARDISES).executes(LevelCommand::setDimensionXp)))
                                )
                                .then(
                                        Commands.literal("add").then(Commands.argument("amount", IntegerArgumentType.integer()).then(Commands.argument("tardis", DimensionArgument.dimension()).suggests(CommandHelper.SUGGEST_TARDISES).executes(LevelCommand::addDimensionXp)))
                                )
                );


    }

    private static int getDimensionPoints(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        ServerLevel dimension = DimensionArgument.getDimension(context, "tardis");

        MutableComponent tardisId = TardisHelper.createTardisIdComponent(dimension.dimension().location());

        TardisLevelOperator.get(dimension).ifPresent(tardisLevelOperator -> {
            int points = tardisLevelOperator.getUpgradeHandler().getUpgradePoints();
            context.getSource().sendSystemMessage(Component.translatable(ModMessages.CMD_LEVEL_POINT_GET, tardisId, points));
        });

        return Command.SINGLE_SUCCESS;
    }

    private static int setDimensionPoints(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {

        int amount = IntegerArgumentType.getInteger(context, "amount");
        ServerLevel dimension = DimensionArgument.getDimension(context, "tardis");

        MutableComponent tardisId = TardisHelper.createTardisIdComponent(dimension.dimension().location());

        TardisLevelOperator.get(dimension).ifPresent(tardisLevelOperator -> {
            UpgradeHandler upgradeHandler = tardisLevelOperator.getUpgradeHandler();
            upgradeHandler.setUpgradePoints(amount);
            int points = upgradeHandler.getUpgradePoints();
            context.getSource().sendSystemMessage(Component.translatable(ModMessages.CMD_LEVEL_POINT_SET, tardisId, points));
        });

        return Command.SINGLE_SUCCESS;
    }

    private static int addDimensionPoints(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {

        int amount = IntegerArgumentType.getInteger(context, "amount");
        ServerLevel dimension = DimensionArgument.getDimension(context, "tardis");

        MutableComponent tardisId = TardisHelper.createTardisIdComponent(dimension.dimension().location());

        TardisLevelOperator.get(dimension).ifPresent(tardisLevelOperator -> {
            UpgradeHandler upgradeHandler = tardisLevelOperator.getUpgradeHandler();
            upgradeHandler.setUpgradePoints(upgradeHandler.getUpgradePoints() + amount);
            int points = upgradeHandler.getUpgradePoints();
            context.getSource().sendSystemMessage(Component.translatable(ModMessages.CMD_LEVEL_POINT_ADD, amount, tardisId, points));
        });

        return Command.SINGLE_SUCCESS;
    }

    private static int addDimensionXp(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {

        int amount = IntegerArgumentType.getInteger(context, "amount");
        ServerLevel dimension = DimensionArgument.getDimension(context, "tardis");

        MutableComponent tardisId = TardisHelper.createTardisIdComponent(dimension.dimension().location());

        TardisLevelOperator.get(dimension).ifPresent(tardisLevelOperator -> {
            UpgradeHandler upgradeHandler = tardisLevelOperator.getUpgradeHandler();
            upgradeHandler.setUpgradeXP(upgradeHandler.getUpgradeXP() + amount);
            int xp = upgradeHandler.getUpgradeXP();
            context.getSource().sendSystemMessage(Component.translatable(ModMessages.CMD_LEVEL_XP_ADD, amount, tardisId, xp));
        });

        return Command.SINGLE_SUCCESS;
    }

    private static int getDimensionXp(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        ServerLevel dimension = DimensionArgument.getDimension(context, "tardis");

        MutableComponent tardisId = TardisHelper.createTardisIdComponent(dimension.dimension().location());

        TardisLevelOperator.get(dimension).ifPresent(tardisLevelOperator -> {
            int xp = tardisLevelOperator.getUpgradeHandler().getUpgradeXP();
            context.getSource().sendSystemMessage(Component.translatable(ModMessages.CMD_LEVEL_XP_GET, tardisId, xp));
        });

        return Command.SINGLE_SUCCESS;
    }

    private static int setDimensionXp(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {

        int amount = IntegerArgumentType.getInteger(context, "amount");
        ServerLevel dimension = DimensionArgument.getDimension(context, "tardis");

        MutableComponent tardisId = TardisHelper.createTardisIdComponent(dimension.dimension().location());

        TardisLevelOperator.get(dimension).ifPresent(tardisLevelOperator -> {
            tardisLevelOperator.getUpgradeHandler().setUpgradeXP(amount);
            int xp = tardisLevelOperator.getUpgradeHandler().getUpgradeXP();
            context.getSource().sendSystemMessage(Component.translatable(ModMessages.CMD_LEVEL_XP_SET, tardisId, xp));
        });

        return Command.SINGLE_SUCCESS;
    }
}
