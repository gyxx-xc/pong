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
        this.add(Registration.CHAMPAGNE.get(), "香槟");
        this.add(Registration.CHAMPAGNE_SABRE.get(), "香槟刀");
        this.add(Registration.GOBLET.get(), "酒杯");
        this.add(Registration.PLUG.get(), "塞子");
        this.add(Registration.PLUG_ENTITY.get(), "瓶塞");
        this.add(Registration.DRUNK.get(), "醉酒");
        this.add("itemGroup."+Registration.MODTAB_ID, "开香槟");
    }
}
