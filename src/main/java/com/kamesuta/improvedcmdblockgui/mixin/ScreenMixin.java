package com.kamesuta.improvedcmdblockgui.mixin;

import com.kamesuta.improvedcmdblockgui.client.OffsetCalculator;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.AbstractCommandBlockScreen;
import net.minecraft.client.gui.screen.world.CreateWorldScreen;
import net.minecraft.client.gui.widget.ClickableWidget;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(Screen.class)
public class ScreenMixin {
    @Final
    @Shadow
    private List<Element> children;
    @Shadow
    public int height;

    private int calcOffsetY() {
        return OffsetCalculator.calcOffsetY(height);
    }

    @Inject(method = "init(Lnet/minecraft/client/MinecraftClient;II)V", at = @At(value = "TAIL"))
    public void init(MinecraftClient mc, int width, int height, CallbackInfo ci) {
        Screen that = (Screen) (Object) this;
        if (that instanceof AbstractCommandBlockScreen
                || that instanceof CreateWorldScreen
        ) {
            children.stream()
                    .filter(e -> e instanceof ClickableWidget)
                    .map(ClickableWidget.class::cast)
                    .forEach(e -> e.y += calcOffsetY());
        }
    }
}
