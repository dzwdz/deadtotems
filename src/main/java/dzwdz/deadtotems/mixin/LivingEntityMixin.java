package dzwdz.deadtotems.mixin;

import dzwdz.deadtotems.DeadTotems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Shadow
    public void setStackInHand(Hand hand, ItemStack stack) {}

    @Inject(
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/item/ItemStack;decrement(I)V",
                    shift = At.Shift.AFTER
            ),
            method = "tryUseTotem",
            locals = LocalCapture.CAPTURE_FAILEXCEPTION
    )
    public void totemHook(DamageSource damageSource, CallbackInfoReturnable<Boolean> cir, ItemStack totemCopy, ItemStack totemOriginal, Hand var4[], int var5, int var6, Hand hand) {
        ItemStack brokenTotem = new ItemStack(DeadTotems.DEAD_TOTEM);
        if (totemOriginal.isEmpty()) {
            setStackInHand(hand, brokenTotem);
        } else if (((Object)this) instanceof ServerPlayerEntity) {
            ((ServerPlayerEntity)(Object)this).giveItemStack(brokenTotem);
        }
    }
}
