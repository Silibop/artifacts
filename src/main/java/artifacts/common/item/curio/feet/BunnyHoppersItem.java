package artifacts.common.item.curio.feet;

import artifacts.common.init.ModGameRules;
import artifacts.common.item.curio.HurtSoundModifyingItem;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import top.theillusivec4.curios.api.SlotContext;

public class BunnyHoppersItem extends HurtSoundModifyingItem {

    public BunnyHoppersItem() {
        super(SoundEvents.RABBIT_HURT);
        addListener(EventPriority.HIGH, LivingFallEvent.class, this::onLivingFall);
    }

    private void onLivingFall(LivingFallEvent event, LivingEntity wearer) {
        if (ModGameRules.BUNNY_HOPPERS_DO_CANCEL_FALL_DAMAGE.get()) {
            event.setDamageMultiplier(0);
        }
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        int jumpBoostLevel = Math.min(127, ModGameRules.BUNNY_HOPPERS_JUMP_BOOST_LEVEL.get() - 1);
        if (jumpBoostLevel >= 0 && !slotContext.entity().level.isClientSide() && slotContext.entity().tickCount % 15 == 0) {
            slotContext.entity().addEffect(new MobEffectInstance(MobEffects.JUMP, 39, jumpBoostLevel, true, false));
        }
    }
}
