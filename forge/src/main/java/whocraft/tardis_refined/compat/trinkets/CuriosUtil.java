package whocraft.tardis_refined.compat.trinkets;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.fml.InterModComms;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.event.lifecycle.InterModEnqueueEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotTypeMessage;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;
import whocraft.tardis_refined.TardisRefined;
import whocraft.tardis_refined.compat.CuriosTrinketsSlotInv;
import whocraft.tardis_refined.compat.CuriosTrinketsUtil;

public class CuriosUtil extends CuriosTrinketsUtil {

    public static void init() {
        CuriosTrinketsUtil.setInstance(new CuriosUtil());
        ModLoadingContext.get().getActiveContainer().getEventBus().addListener(CuriosUtil::interModQueue);
    }

    public static void interModQueue(InterModEnqueueEvent e) {
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> new SlotTypeMessage.Builder("timelord_sight").size(1).icon(ResourceLocation.fromNamespaceAndPath(TardisRefined.MODID, "item/timelord_sight")).build());
    }

    @Override
    public boolean isCurios() {
        return true;
    }

    @Override
    public CuriosTrinketsSlotInv getSlot(LivingEntity entity, String slot) {
        final CuriosTrinketsSlotInv[] slotHandler = {CuriosTrinketsSlotInv.EMPTY};
        CuriosApi.getCuriosHelper().getCuriosHandler(entity).ifPresent(curios -> {
            curios.getStacksHandler(slot).ifPresent(stacks -> {
                slotHandler[0] = new SlotInv(stacks.getStacks());
            });
        });
        return slotHandler[0];
    }

    public static class SlotInv implements CuriosTrinketsSlotInv {

        private final IDynamicStackHandler stackHandler;

        public SlotInv(IDynamicStackHandler stackHandler) {
            this.stackHandler = stackHandler;
        }

        @Override
        public int getSlots() {
            return this.stackHandler.getSlots();
        }

        @Override
        public ItemStack getStackInSlot(int index) {
            return this.stackHandler.getStackInSlot(index);
        }

        @Override
        public void setStackInSlot(int index, ItemStack stack) {
            this.stackHandler.setStackInSlot(index, stack);
        }
    }


}