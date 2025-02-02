package whocraft.tardis_refined.command.sub;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.DimensionArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import whocraft.tardis_refined.common.capability.tardis.TardisLevelOperator;
import whocraft.tardis_refined.common.items.KeyItem;
import whocraft.tardis_refined.common.tardis.TardisNavLocation;
import whocraft.tardis_refined.common.tardis.manager.TardisPilotingManager;
import whocraft.tardis_refined.common.util.CommandHelper;
import whocraft.tardis_refined.common.util.PlayerUtil;
import whocraft.tardis_refined.constants.ModMessages;
import whocraft.tardis_refined.registry.TRDimensionTypes;

import java.util.Optional;

public class SummonCommand implements Command<CommandSourceStack> {

    public static ArgumentBuilder<CommandSourceStack, ?> register(CommandDispatcher<CommandSourceStack> dispatcher) {
        return Commands.literal("summon")
                .then(Commands.argument("tardis", DimensionArgument.dimension())
                        .suggests(CommandHelper.SUGGEST_TARDISES)
                        .executes(context -> summonTardis(context, DimensionArgument.getDimension(context, "tardis"))))
                .executes(SummonCommand::summonTardisFromKey);
    }

    private static int summonTardis(CommandContext<CommandSourceStack> context, ServerLevel tardis) {
        Optional<TardisLevelOperator> tardisData = TardisLevelOperator.get(tardis);
        ServerPlayer sender = context.getSource().getPlayer();

        if (sender.level().dimensionTypeId() == TRDimensionTypes.TARDIS) {
            PlayerUtil.sendMessage(sender, Component.literal(ChatFormatting.RED + "You cannot Summon a TARDIS within a TARDIS."), false);
            return 0;
        }

        if (tardisData.isPresent()) {
            TardisLevelOperator data = tardisData.get();
            TardisPilotingManager pilot = data.getPilotingManager();
            pilot.setHandbrakeOn(false);
            pilot.setTargetLocation(new TardisNavLocation(sender.blockPosition(), sender.getDirection().getOpposite(), (ServerLevel) sender.level()));
            pilot.beginFlight(true);
            PlayerUtil.sendMessage(sender, Component.translatable(ModMessages.TARDIS_IS_ON_THE_WAY), true);

            return Command.SINGLE_SUCCESS;
        }
        return 0;
    }

    private static int summonTardisFromKey(CommandContext<CommandSourceStack> context) {
        ServerPlayer sender = context.getSource().getPlayer();
        ItemStack mainHandItem = sender.getMainHandItem();

        if (mainHandItem.getItem() instanceof KeyItem) {
            var keychain = KeyItem.getKeychain(mainHandItem);
            if (!keychain.isEmpty()) {
                ResourceKey<Level> tardisDimKey = keychain.get(0);
                ServerLevel tardisLevel = sender.server.getLevel(tardisDimKey);
                if (tardisLevel != null) {
                    return summonTardis(context, tardisLevel);
                }
            }
        }

        PlayerUtil.sendMessage(sender, Component.literal(ChatFormatting.RED + "You must specify a TARDIS dimension or hold a valid TARDIS key."), false);
        return 0;
    }

    @Override
    public int run(CommandContext<CommandSourceStack> context) {
        return 0;
    }
}
