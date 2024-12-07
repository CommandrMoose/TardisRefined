package whocraft.tardis_refined.command.arguments;


import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import whocraft.tardis_refined.common.tardis.TardisDesktops;
import whocraft.tardis_refined.common.tardis.themes.DesktopTheme;
import whocraft.tardis_refined.common.tardis.themes.ShellTheme;
import whocraft.tardis_refined.constants.ModMessages;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ShellArgumentType implements ArgumentType<ResourceLocation> {

    public static final DynamicCommandExceptionType INVALID_SHELL_EXCEPTION = new DynamicCommandExceptionType((shell) -> Component.translatable(ModMessages.CMD_ARG_SHELL_INVALID, shell));
    private static final Collection<String> EXAMPLES = Stream.of(ShellTheme.FACTORY.get(), ShellTheme.POLICE_BOX.get()).map((shell) -> shell != null ? ShellTheme.SHELL_THEME_REGISTRY.getKey(shell).toString() : "").collect(Collectors.toList());

    public static ShellArgumentType shellArgumentType() {
        return new ShellArgumentType();
    }

    public static ShellTheme getShell(CommandContext<CommandSourceStack> context, String name) throws CommandSyntaxException {
        ResourceLocation resourcelocation = context.getArgument(name, ResourceLocation.class);
        ShellTheme shellTheme = ShellTheme.SHELL_THEME_REGISTRY.get(resourcelocation);
        if (shellTheme == null)
            throw INVALID_SHELL_EXCEPTION.create(resourcelocation);
        else
            return shellTheme;
    }

    public static ResourceLocation getShellId(CommandContext<CommandSourceStack> context, String name) throws CommandSyntaxException {
        ResourceLocation resourcelocation = context.getArgument(name, ResourceLocation.class);
        if (ShellTheme.SHELL_THEME_REGISTRY.get(resourcelocation) == null)
            throw INVALID_SHELL_EXCEPTION.create(resourcelocation);
        else
            return resourcelocation;
    }

    @Override
    public ResourceLocation parse(StringReader reader) throws CommandSyntaxException {
        return ResourceLocation.read(reader);
    }

    @Override
    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
        return SharedSuggestionProvider.suggestResource(ShellTheme.SHELL_THEME_REGISTRY.keySet(), builder);
    }

    @Override
    public Collection<String> getExamples() {
        return EXAMPLES;
    }
}