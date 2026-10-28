package com.serilum.wheredididie.mixin;

import com.serilum.wheredididie.util.Util;
import net.minecraft.client.player.LocalPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = LocalPlayer.class, priority = 1001)
public class LocalPlayerMixin {
	@Inject(method = "tickDeath()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;remove(Lnet/minecraft/world/entity/Entity$RemovalReason;)V"))
	protected void tickDeath(CallbackInfo ci) {
		Util.processDeathCode((LocalPlayer)(Object)this);
	}
}
