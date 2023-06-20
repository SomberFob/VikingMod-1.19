package net.somberfob.vikingmod.entity.client.armor;

import net.somberfob.vikingmod.item.custom.SaxonHelmetItem;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class SaxonHelmetRenderer extends GeoArmorRenderer<SaxonHelmetItem> {
    public SaxonHelmetRenderer() {
        super(new SaxonHelmetModel());

        this.headBone = "armorHead";
        this.bodyBone = "armorBody";
        this.rightArmBone = "armorRightArm";
        this.leftArmBone = "armorLeftArm";
        this.rightLegBone = "armorLeftLeg";
        this.leftLegBone = "armorRightLeg";
        this.rightBootBone = "armorLeftBoot";
        this.leftBootBone = "armorRightBoot";
    }
}
