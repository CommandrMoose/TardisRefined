package whocraft.tardis_refined.command.sub;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.command.arguments.DesktopArgumentType;
import whocraft.tardis_refined.command.arguments.ShellArgumentType;
import whocraft.tardis_refined.common.tardis.themes.DesktopTheme;
import whocraft.tardis_refined.common.util.TardisHelper;
import whocraft.tardis_refined.constants.ModMessages;

import java.util.UUID;

public class CreateCommand {
    public static ArgumentBuilder<CommandSourceStack, ?> register(CommandDispatcher<CommandSourceStack> dispatcher) {
        return Commands.literal("create")
                .then(
                        Commands.argument("shell", ShellArgumentType.shellArgumentType())
                                .then(Commands.argument("desktop", DesktopArgumentType.desktopArgumentType())
                                        .executes(CreateCommand::createTardis)
                                )
                );
    }

    private static int createTardis(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {

        ResourceLocation shellTheme = ShellArgumentType.getShellId(context, "shell");

        DesktopTheme desktopTheme = DesktopArgumentType.getDesktop(context, "desktop");

        BlockPos pos = context.getSource().getEntity().getOnPos().above(); //Offset up by one block because entity position is at block below their feet
        ServerLevel level = context.getSource().getLevel();

        //Create a Level Key with a randomised UUID
        ResourceKey<Level> generatedLevelKey = ResourceKey.create(Registries.DIMENSION, ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, UUID.randomUUID().toString()));

        MutableComponent tardisId = TardisHelper.createTardisIdComponent(generatedLevelKey.location());

        context.getSource().sendSystemMessage(Component.translatable(ModMessages.CMD_CREATE_TARDIS_IN_PROGRESS, tardisId));

        if (TardisHelper.createTardis(pos, level, generatedLevelKey, shellTheme, desktopTheme)) {
            context.getSource().sendSystemMessage(Component.translatable(ModMessages.CMD_CREATE_TARDIS_SUCCESS, tardisId));
        }

        return Command.SINGLE_SUCCESS;
    }
}
