package org.pongdev.pong.setup.datagen;

import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;
import org.pongdev.pong.Pong;
import org.pongdev.pong.setup.Registration;

public class PongEnglishLanguageProvider extends LanguageProvider {
    public PongEnglishLanguageProvider(PackOutput output) {
        super(output, Pong.MODID, "en_us");
    }

    //TODO: also as Chinese
    @Override
    protected void addTranslations() {
        this.add(Registration.CHAMPAGNE.get(), "xb");
        this.add("itemGroup."+Registration.MODTAB_ID, "p");
    }
}
