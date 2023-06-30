package org.pongdev.pong.item;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

public class Goblet extends Item {
    public static final String ID = "goblet";
    public Goblet() {
        super(new Properties().food(
                new FoodProperties.Builder()
                        .alwaysEat()
                        .fast()
                        .build()
        ));
    }


}
