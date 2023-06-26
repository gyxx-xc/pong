package org.pongdev.pong.setup.datagen;

import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;
import org.pongdev.pong.Pong;
import org.pongdev.pong.setup.Registration;

public class PongChineseLanguageProvider extends LanguageProvider {
    public PongChineseLanguageProvider(PackOutput gen) {
        super(gen, Pong.MODID, "zh_cn");
    }

    // TODO: change these into REAL Chinese
    @Override
    protected void addTranslations() {
        this.add(Registration.CHAMPAGNE.get(), "xb");
        this.add("itemGroup."+Registration.MODTAB_ID, "p");
    }
}
