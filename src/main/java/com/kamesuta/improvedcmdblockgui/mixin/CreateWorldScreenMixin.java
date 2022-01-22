package com.kamesuta.improvedcmdblockgui.mixin;

import com.kamesuta.improvedcmdblockgui.client.OffsetCalculator;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.world.CreateWorldScreen;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(CreateWorldScreen.class)
public class CreateWorldScreenMixin extends Screen {
    protected CreateWorldScreenMixin(Text title) {
        super(title);
    }

    private int calcOffsetY() {
        return OffsetCalculator.calcOffsetY(height);
    }

    @ModifyConstant(method = "init", constant = @Constant(intValue = 28))
    public int init28(int constant) {
        return constant + calcOffsetY();
    }

    @ModifyConstant(method = "render", constant = @Constant(intValue = 20))
    public int render20(int constant) {
        return constant + calcOffsetY();
    }

    @ModifyConstant(method = "render", constant = @Constant(intValue = 47))
    public int render47(int constant) {
        return constant + calcOffsetY();
    }

    @ModifyConstant(method = "render", constant = @Constant(intValue = 85))
    public int render85(int constant) {
        return constant + calcOffsetY();
    }

    @ModifyConstant(method = "render", constant = @Constant(intValue = 122))
    public int render122(int constant) {
        return constant + calcOffsetY();
    }

    @ModifyConstant(method = "render", constant = @Constant(intValue = 134))
    public int render134(int constant) {
        return constant + calcOffsetY();
    }

    @ModifyConstant(method = "render", constant = @Constant(intValue = 172))
    public int render172(int constant) {
        return constant + calcOffsetY();
    }
}
