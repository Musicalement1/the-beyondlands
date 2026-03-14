package net.beyondlands.tbl.util;

import net.beyondlands.tbl.TBL;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> NEEDS_STEEL_TOOL = createTag("needs_steel_tool");
        public static final TagKey<Block> INCORRECT_FOR_STEEL_TOOL = createTag("incorrect_for_steel_tool");

        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(TBL.MOD_ID, name));
        }
    }
    public static class Items {
        public static final TagKey<Item> HYDROREACTIVE_ITEMS = createTag("hydroreactive_items");
        public static final TagKey<Item> BATTERY = createTag("battery");

        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(TBL.MOD_ID, name));
        }
    }
}
