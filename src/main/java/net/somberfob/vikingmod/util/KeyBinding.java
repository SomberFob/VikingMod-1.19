package net.somberfob.vikingmod.util;


import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class KeyBinding {
    public static final String KEY_CATEGORY_VIKINGMOD = "key.category.vikingmod.vikingmod";
    public static final String KEY_BATTLE_CHARGE = "key.vikingmod.battle_charge";

    public static final KeyMapping SHOUTING_KEY = new KeyMapping(KEY_BATTLE_CHARGE, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_F, KEY_CATEGORY_VIKINGMOD);
}