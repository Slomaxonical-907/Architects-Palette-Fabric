package com.slomaxonical.architectspalette.core.data;

import com.google.common.collect.Sets;
import com.google.gson.GsonBuilder;
import com.slomaxonical.architectspalette.core.ArchitectsPalette;
import com.slomaxonical.architectspalette.core.integration.advancement.CarveTotemTrigger;
import com.slomaxonical.architectspalette.core.registry.APBlocks;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.CriterionMerger;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.advancement.criterion.PlacedBlockCriterion;
import net.minecraft.data.DataCache;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.server.AdvancementsProvider;
import net.minecraft.item.ItemConvertible;
import net.minecraft.text.MutableText;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Set;
import java.util.function.Consumer;

// This was heavily referenced from Farmer's Delight by vectorwing, you might notice a trend here.
// Thanks again!
public class Advancements extends AdvancementsProvider {

    private final Path PATH;

    public Advancements(DataGenerator generatorIn) {
        super(generatorIn);
        PATH = generatorIn.getOutput();
    }

    @Override
    public void run(DataCache cache) {
        Set<Identifier> set = Sets.newHashSet();
        Consumer<Advancement> consumer = (advancement) -> {
            if (!set.add(advancement.getId())) {
                throw new IllegalStateException("Duplicate Advancement" + advancement.getId());
            }
            else {
                Path path1 = getOutput(PATH, advancement);
                try {
                    DataProvider.writeToPath((new GsonBuilder()).setPrettyPrinting().create(), cache, advancement.createTask().toJson(), path1);
                }
                catch (IOException ioException){
                    ArchitectsPalette.LOGGER.error("Couldn't save advancement {}", path1, ioException);
                }
            }
        };

        new APAdvancements().accept(consumer);
    }

    // Feels kinda bad to copy even convenience scripts, but like, it's just a good idea to have this.
    // I feel clever enough having done Abyssaline, so I'll just let myself have it.
    private static Path getOutput(Path pathIn, Advancement advancementIn) {
        return pathIn.resolve("data/" + advancementIn.getId().getNamespace() + "/advancements/" + advancementIn.getId().getPath() + ".json");
    }

    public static class APAdvancements implements Consumer<Consumer<Advancement>> {

        private static MutableText getTranslationKey(String key, Object... args) {
            return new TranslatableText(ArchitectsPalette.MOD_ID + "." + key, args);
        }

        private String getNameId(String id) {
            return ArchitectsPalette.MOD_ID + ":" + id;
        }

        // Not even gonna skirt around the fact that I straight up copy pasted this part. vectorwing seriously thought of everything.
        protected static Advancement.Task getAdvancement(Advancement parent, ItemConvertible display, String name, AdvancementFrame frame, boolean showToast, boolean announceToChat, boolean hidden) {
            return Advancement.Task.create().parent(parent).display(display,
                getTranslationKey("advancement." + name),
                getTranslationKey("advancement." + name + ".desc"),
                null, frame, showToast, announceToChat, hidden);
        }

        protected Advancement buyAdvancement(Advancement parent, ItemConvertible display, String name, String item, Consumer<Advancement> advancementConsumer) {
            return getAdvancement(parent, display, name, AdvancementFrame.TASK, true, false, false)
                    .criterion(item, InventoryChangedCriterion.Conditions.items(display))
                    .build(advancementConsumer, getNameId("main/" + name));
        }

        @Override
        public void accept(Consumer<Advancement> advancementConsumer) {
            Advancement architectsPalette = Advancement.Task.create()
                    .display(APBlocks.CHISELED_ABYSSALINE_BRICKS.asItem(),
                            getTranslationKey("advancement.root"),
                            getTranslationKey("advancement.root.desc"),
                            new Identifier("architects_palette:textures/block/limestone_bricks.png"),
                            AdvancementFrame.TASK, false, false, false)
                    .criterion("craftingtable", InventoryChangedCriterion.Conditions.items(new ItemConvertible[]{}))
                    .build(advancementConsumer, getNameId("main/root"));

            Advancement totemCarving = getAdvancement(architectsPalette, APBlocks.PLACID_ACACIA_TOTEM.asItem(), "totem_carving", AdvancementFrame.TASK, true ,true, false)
                    .criterion("carve_totem", CarveTotemTrigger.Instance.simple())
                    .build(advancementConsumer, getNameId("main/totem_carving"));

            Advancement whatACatch = getAdvancement(architectsPalette, APBlocks.COD_LOG.asItem(), "buy_fish_block", AdvancementFrame.TASK, true, false, false)
                    .criterion("cod_log", InventoryChangedCriterion.Conditions.items(APBlocks.COD_LOG.asItem()))
                    .criterion("salmon_log", InventoryChangedCriterion.Conditions.items(APBlocks.SALMON_LOG.asItem()))
                    .criteriaMerger(CriterionMerger.OR)
                    .build(advancementConsumer, getNameId("main/buy_fish_block"));

            Advancement buyPipes = buyAdvancement(architectsPalette, APBlocks.PIPE.asItem(), "buy_pipe", "pipe", advancementConsumer);
            Advancement buyEntrails = buyAdvancement(architectsPalette, APBlocks.ENTRAILS.asItem(), "buy_entrails", "entrails", advancementConsumer);
            Advancement placeEntrails = getAdvancement(buyEntrails, APBlocks.ENTRAILS.asItem(), "place_entrails", AdvancementFrame.TASK, true, true, true)
                    .criterion("entrails", PlacedBlockCriterion.Conditions.block(APBlocks.ENTRAILS))
                    .build(advancementConsumer, getNameId("main/place_entrails"));

            Advancement buyPlating = buyAdvancement(architectsPalette, APBlocks.PLATING_BLOCK.get().asItem(), "buy_plating", "plating", advancementConsumer);
            Advancement buySpool = buyAdvancement(architectsPalette, APBlocks.SPOOL.asItem(), "buy_spool", "spool", advancementConsumer);

            Advancement buyCelestialStone = getAdvancement(architectsPalette, APBlocks.SUNSTONE.asItem(), "buy_celestial_stone", AdvancementFrame.TASK, true, false, false)
                    .criterion("moonstone", InventoryChangedCriterion.Conditions.items(APBlocks.MOONSTONE.asItem()))
                    .criterion("sunstone", InventoryChangedCriterion.Conditions.items(APBlocks.SUNSTONE.asItem()))
                    .criteriaMerger(CriterionMerger.OR)
                    .build(advancementConsumer, getNameId("main/buy_celestial_stone"));

            Advancement getWarpstone = buyAdvancement(architectsPalette, APBlocks.WARPSTONE.get().asItem(), "find_warpstone", "warpstone", advancementConsumer);
            Advancement getTwistedSapling = buyAdvancement(getWarpstone, APBlocks.TWISTED_SAPLING.asItem(), "find_twisted_sapling", "sapling", advancementConsumer);

        }
    }

}
