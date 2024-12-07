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
import whocraft.tardis_refined.common.capability.tardis.upgrades.Upgrade;
import whocraft.tardis_refined.registry.TRUpgrades;
import whocraft.tardis_refined.constants.ModMessages;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UpgradeArgumentType implements ArgumentType<ResourceLocation> {

    public static final DynamicCommandExceptionType INVALID_UPGRADE_EXCEPTION = new DynamicCommandExceptionType((upgrade) -> Component.translatable(ModMessages.CMD_ARG_UPGRADE_INVALID, upgrade));
    private static final Collection<String> EXAMPLES = Stream.of(TRUpgrades.ARCHITECTURE_SYSTEM).map((upgrade) -> upgrade != null ? upgrade.getId().toString() : "").collect(Collectors.toList());

    public static UpgradeArgumentType upgradeArgumentType() {
        return new UpgradeArgumentType();
    }

    public static Upgrade getUpgrade(CommandContext<CommandSourceStack> context, String name) throws CommandSyntaxException {
        ResourceLocation resourcelocation = context.getArgument(name, ResourceLocation.class);
        Upgrade upgrade = TRUpgrades.UPGRADE_REGISTRY.get(resourcelocation);
        if (upgrade == null)
            throw INVALID_UPGRADE_EXCEPTION.create(resourcelocation);
        else
            return upgrade;
    }

    public static ResourceLocation getUpgradeId(CommandContext<CommandSourceStack> context, String name) throws CommandSyntaxException {
        ResourceLocation resourcelocation = context.getArgument(name, ResourceLocation.class);
        if (TRUpgrades.UPGRADE_REGISTRY.get(resourcelocation) == null)
            throw INVALID_UPGRADE_EXCEPTION.create(resourcelocation);
        else
            return resourcelocation;
    }

    @Override
    public ResourceLocation parse(StringReader reader) throws CommandSyntaxException {
        return ResourceLocation.read(reader);
    }

    @Override
    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
        return SharedSuggestionProvider.suggestResource(TRUpgrades.UPGRADE_REGISTRY.keySet(), builder);
    }

    @Override
    public Collection<String> getExamples() {
        EXAMPLES.add("*");
        return EXAMPLES;
    }
}