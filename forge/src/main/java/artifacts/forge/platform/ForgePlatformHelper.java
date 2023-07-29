package artifacts.forge.platform;

import artifacts.client.item.renderer.ArtifactRenderer;
import artifacts.forge.client.InputEventHandler;
import artifacts.item.wearable.WearableArtifactItem;
import artifacts.platform.PlatformHelper;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.ForgeMod;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.SlotResult;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;
import top.theillusivec4.curios.api.client.ICurioRenderer;

import java.util.function.Supplier;
import java.util.stream.Stream;

public class ForgePlatformHelper implements PlatformHelper {

    @Override
    public boolean isEquippedBy(@Nullable LivingEntity entity, Item item) {
        return entity != null && CuriosApi.getCuriosHelper().findFirstCurio(entity, item).isPresent();
    }

    @Override
    public Stream<ItemStack> findAllEquippedBy(LivingEntity entity, Item item) {
        return CuriosApi.getCuriosHelper().findCurios(entity, item).stream().map(SlotResult::stack);
    }

    @Override
    public KeyMapping getToggleKey(WearableArtifactItem item) {
        return InputEventHandler.getToggleKey(item);
    }

    @Override
    public Attribute getStepHeightAttribute() {
        return ForgeMod.STEP_HEIGHT_ADDITION.get();
    }

    @Override
    public void registerArtifactRenderer(WearableArtifactItem item, Supplier<ArtifactRenderer> rendererSupplier) {
        CuriosRendererRegistry.register(item, () -> new ArtifactCurioRenderer(rendererSupplier.get()));
    }

    @Override
    public ArtifactRenderer getArtifactRenderer(Item item) {
        return ((ArtifactCurioRenderer) CuriosRendererRegistry.getRenderer(item).orElseThrow()).renderer;
    }

    private record ArtifactCurioRenderer(ArtifactRenderer renderer) implements ICurioRenderer {

        @Override
        public <T extends LivingEntity, M extends EntityModel<T>> void render(
                ItemStack stack,
                SlotContext slotContext,
                PoseStack poseStack,
                RenderLayerParent<T, M> renderLayerParent,
                MultiBufferSource multiBufferSource,
                int light,
                float limbSwing,
                float limbSwingAmount,
                float partialTicks,
                float ageInTicks,
                float netHeadYaw,
                float headPitch
        ) {
            renderer.render(
                    stack,
                    slotContext.entity(),
                    slotContext.index(),
                    poseStack,
                    renderLayerParent,
                    multiBufferSource,
                    light,
                    limbSwing,
                    limbSwingAmount,
                    partialTicks,
                    ageInTicks,
                    netHeadYaw,
                    headPitch
            );
        }
    }
}
