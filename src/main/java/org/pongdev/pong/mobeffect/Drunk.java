package org.pongdev.pong.mobeffect;

import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec2;
import org.jetbrains.annotations.Nullable;
import org.pongdev.pong.Pong;

import java.util.Hashtable;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

public class Drunk extends MobEffect {
    protected final Random random = new Random();
    private final Map<UUID, Vec2> velocity = new Hashtable<UUID, Vec2>();
    // UUID -> v:(x, y)
    public static final String DRUNK_LEVEL = "drunk_level";
    public Drunk() {
        super(MobEffectCategory.HARMFUL, 0);
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {

    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }
}
