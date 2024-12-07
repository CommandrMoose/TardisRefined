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

public class DesktopArgumentType implements ArgumentType<ResourceLocation> {

    public static final DynamicCommandExceptionType INVALID_DESKTOP_EXCEPTION = new DynamicCommandExceptionType((desktop) -> Component.translatable(ModMessages.CMD_ARG_DESKTOP_INVALID, desktop));
    private static final Collection<String> EXAMPLES = Stream.of(TardisDesktops.FACTORY_THEME, TardisDesktops.DEFAULT_OVERGROWN_THEME).map((desktop) -> desktop != null ? desktop.getIdentifier().toString() : "").collect(Collectors.toList());

    public static DesktopArgumentType desktopArgumentType() {
        return new DesktopArgumentType();
    }

    public static DesktopTheme getDesktop(CommandContext<CommandSourceStack> context, String name) throws CommandSyntaxException {
        ResourceLocation resourcelocation = context.getArgument(name, ResourceLocation.class);
        DesktopTheme desktop = TardisDesktops.getRegistry().get(resourcelocation);
        if (desktop == null)
            throw INVALID_DESKTOP_EXCEPTION.create(resourcelocation);
        else
            return desktop;
    }

    @Override
    public ResourceLocation parse(StringReader reader) throws CommandSyntaxException {
        return ResourceLocation.read(reader);
    }

    @Override
    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
        Collection<ResourceLocation> desktops = TardisDesktops.getRegistry().keySet();
        return SharedSuggestionProvider.suggestResource(desktops, builder);
    }

    @Override
    public Collection<String> getExamples() {
        return EXAMPLES;
    }
}