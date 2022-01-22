package com.kamesuta.improvedcmdblockgui.mixin;

import com.kamesuta.improvedcmdblockgui.client.OffsetCalculator;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.AbstractCommandBlockScreen;
import net.minecraft.client.gui.widget.CyclingButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractCommandBlockScreen.class)
public class AbstractCommandBlockScreenMixin extends Screen {
    @Shadow
    protected TextFieldWidget consoleCommandTextField;
    @Shadow
    protected TextFieldWidget previousOutputTextField;
    @Shadow
    protected CyclingButtonWidget<Boolean> toggleTrackingOutputButton;

    protected AbstractCommandBlockScreenMixin(Text title) {
        super(title);
    }

    private int calcOffsetY() {
        return OffsetCalculator.calcOffsetY(height);
    }

    @Inject(method = "init", at = @At(value = "TAIL"))
    public void init(CallbackInfo ci) {
        consoleCommandTextField.width -= 20;
        consoleCommandTextField.x += 10;
        previousOutputTextField.width -= 20;
        previousOutputTextField.x += 10;

        toggleTrackingOutputButton.x -= 10;
    }

    @ModifyConstant(method = "render", constant = @Constant(intValue = 20))
    public int render20(int constant) {
        return constant + calcOffsetY();
    }

    @ModifyConstant(method = "render", constant = @Constant(intValue = 40))
    public int render40(int constant) {
        return constant + calcOffsetY();
    }

    @ModifyConstant(method = "render", constant = @Constant(intValue = 150))
    public int render150(int constant) {
        return constant - 10;
    }

    @ModifyConstant(method = "render", constant = @Constant(intValue = 4))
    public int render4(int constant) {
        return constant + calcOffsetY();
    }
}
