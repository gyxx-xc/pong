package org.pongdev.pong.setup.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.ItemTags;
import org.pongdev.pong.item.PlugItem;
import org.pongdev.pong.setup.Registration;

import java.util.function.Consumer;

public class PongRecipeProvider extends RecipeProvider {
    public PongRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        ShapedRecipeBuilder
                .shaped(RecipeCategory.DECORATIONS, Registration.PLUG.get())
                .define('X', ItemTags.PLANKS)
                .pattern(" X ")
                .pattern("XXX")
                .group("pong")
                .unlockedBy("has_logs", has(ItemTags.PLANKS))
                .save(pWriter);
    }
}
