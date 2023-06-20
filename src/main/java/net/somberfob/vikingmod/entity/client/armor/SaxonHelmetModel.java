package net.somberfob.vikingmod.entity.client.armor;

import net.minecraft.resources.ResourceLocation;
import net.somberfob.vikingmod.VikingMod;
import net.somberfob.vikingmod.item.custom.SaxonHelmetItem;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SaxonHelmetModel extends AnimatedGeoModel<SaxonHelmetItem> {
    @Override
    public ResourceLocation getModelResource(SaxonHelmetItem object) {
        return new ResourceLocation(VikingMod.MOD_ID, "geo/saxon_helmet.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SaxonHelmetItem object) {
        return new ResourceLocation(VikingMod.MOD_ID, "textures/models/armor/saxon_helmet.png");
    }

    @Override
    public ResourceLocation getAnimationResource(SaxonHelmetItem animatable) {
        return new ResourceLocation(VikingMod.MOD_ID, "animations/armor_animation.json");
    }
}
