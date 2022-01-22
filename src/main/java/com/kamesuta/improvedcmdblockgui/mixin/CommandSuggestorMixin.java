package com.kamesuta.improvedcmdblockgui.mixin;

import com.kamesuta.improvedcmdblockgui.client.OffsetCalculator;
import net.minecraft.client.gui.screen.CommandSuggestor;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(CommandSuggestor.class)
public class CommandSuggestorMixin {
    @Final
    @Shadow
    Screen owner;

    private int calcOffsetY() {
        return OffsetCalculator.calcOffsetY(owner.height);
    }

    @ModifyConstant(method = "showSuggestions", constant = @Constant(intValue = 72))
    public int showSuggestions(int constant) {
        return constant + calcOffsetY();
    }

    @ModifyConstant(method = "render", constant = @Constant(intValue = 72))
    public int render(int constant) {
        return constant + calcOffsetY();
    }
}