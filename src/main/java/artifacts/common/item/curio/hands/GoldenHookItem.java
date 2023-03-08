package artifacts.common.item.curio.hands;

import artifacts.common.init.ModGameRules;
import artifacts.common.item.curio.CurioItem;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingExperienceDropEvent;

public class GoldenHookItem extends CurioItem {

    public GoldenHookItem() {
        addListener(LivingExperienceDropEvent.class, this::onLivingExperienceDrop, LivingExperienceDropEvent::getAttackingPlayer);
    }

    private void onLivingExperienceDrop(LivingExperienceDropEvent event, LivingEntity wearer) {
        if (event.getEntity() instanceof Player) {
            return; // players shouldn't drop extra XP
        }

        int experienceBonus = (int) (event.getOriginalExperience() * ModGameRules.GOLDEN_HOOK_EXPERIENCE_BONUS.get() / 100F);
        if (experienceBonus > 0) {
            event.setDroppedExperience(event.getDroppedExperience() + experienceBonus);
        }
    }
}
