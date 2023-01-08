package net.somberfob.vikingmod.entity.client.bear;

import net.minecraft.resources.ResourceLocation;
import net.somberfob.vikingmod.VikingMod;
import net.somberfob.vikingmod.entity.custom.BearEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class BearModel extends AnimatedGeoModel<BearEntity> {
    @Override
    public ResourceLocation getModelResource(BearEntity object) {
        return new ResourceLocation(VikingMod.MOD_ID, "geo/bear.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BearEntity object) {
        return new ResourceLocation(VikingMod.MOD_ID, "textures/entity/bear/bear.png");
    }

    @Override
    public ResourceLocation getAnimationResource(BearEntity animatable) {
        return new ResourceLocation(VikingMod.MOD_ID, "animations/bear.animation.json");
    }
}
