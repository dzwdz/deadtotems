package dzwdz.deadtotems.mixin;

import dzwdz.deadtotems.DeadTotems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {
    @Shadow @Final public PlayerInventory inventory;

    @Inject(
            at = @At("TAIL"),
            method = "vanishCursedItems"
    )
    public void vanishDeadTotems(CallbackInfo callbackInfo) {
        if (DeadTotems.config.vanishOnDeath) {
            for (int i = 0; i < inventory.size(); ++i) {
                ItemStack itemStack = inventory.getStack(i);
                if (!itemStack.isEmpty() && itemStack.getItem() == DeadTotems.DEAD_TOTEM) {
                    inventory.removeStack(i);
                }
            }
        }
    }
}
