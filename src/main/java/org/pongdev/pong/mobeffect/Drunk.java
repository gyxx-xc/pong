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
import java.util.UUID;

public class Drunk extends MobEffect {
    protected final RandomSource random = RandomSource.create();
    private final Map<UUID, Vec2> velocity = new Hashtable<UUID, Vec2>();
    // UUID -> v:(x, y)
    public Drunk() {
        super(MobEffectCategory.HARMFUL, 0);
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        Vec2 d = velocity.get(pLivingEntity.getUUID());
        if(d != null) {
            Pong.LOGGER.info(Float.toString(d.x));
            d = d.add(new Vec2(random.nextFloat()*0.5F - 0.25F, random.nextFloat() - 0.5F));
            d = new Vec2(Math.min(d.x, 4), Math.min(d.y, 4));
            velocity.replace(pLivingEntity.getUUID(), d);
        } else {
            d = new Vec2(0, 0);
            velocity.put(pLivingEntity.getUUID(), d);
        }
        pLivingEntity.setXRot(pLivingEntity.getXRot() + d.x);
    }

    @Override
    public void applyInstantenousEffect(@Nullable Entity pSource, @Nullable Entity pIndirectSource, LivingEntity pLivingEntity, int pAmplifier, double pHealth) {
        super.applyInstantenousEffect(pSource, pIndirectSource, pLivingEntity, pAmplifier, pHealth);
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }
}
