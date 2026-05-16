package dev.edonme.blokkusus;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Blokkusus implements ModInitializer {
    public static final String MOD_ID = "blokkusus";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    // ==========================================
    // 1. IDENTIFIERS
    // ==========================================
    public static final Identifier OAK_LOG_PANEL_ID = Identifier.of(MOD_ID, "oak_log_panel");
    public static final Identifier VINES_PANEL_ID = Identifier.of(MOD_ID, "vines_panel");
    public static final Identifier COMPACTED_DIRT_ID = Identifier.of(MOD_ID, "compacted_dirt");
    public static final Identifier ANCIENT_FOSSIL_BLOCK_ID = Identifier.of(MOD_ID, "ancient_fossil_block");

    // ==========================================
    // 2. REGISTRY KEYS (BLOCKS & ITEMS)
    // ==========================================
    public static final RegistryKey<Block> OAK_LOG_PANEL_KEY = RegistryKey.of(RegistryKeys.BLOCK, OAK_LOG_PANEL_ID);
    public static final RegistryKey<Item> OAK_LOG_PANEL_ITEM_KEY = RegistryKey.of(RegistryKeys.ITEM, OAK_LOG_PANEL_ID);

    public static final RegistryKey<Block> VINES_PANEL_KEY = RegistryKey.of(RegistryKeys.BLOCK, VINES_PANEL_ID);
    public static final RegistryKey<Item> VINES_PANEL_ITEM_KEY = RegistryKey.of(RegistryKeys.ITEM, VINES_PANEL_ID);

    public static final RegistryKey<Block> COMPACTED_DIRT_KEY = RegistryKey.of(RegistryKeys.BLOCK, COMPACTED_DIRT_ID);
    public static final RegistryKey<Item> COMPACTED_DIRT_ITEM_KEY = RegistryKey.of(RegistryKeys.ITEM, COMPACTED_DIRT_ID);

    public static final RegistryKey<Block> ANCIENT_FOSSIL_BLOCK_KEY = RegistryKey.of(RegistryKeys.BLOCK, ANCIENT_FOSSIL_BLOCK_ID);
    public static final RegistryKey<Item> ANCIENT_FOSSIL_BLOCK_ITEM_KEY = RegistryKey.of(RegistryKeys.ITEM, ANCIENT_FOSSIL_BLOCK_ID);

    // ==========================================
    // 3. BLOCK DEFINITIONS
    // ==========================================
    public static final Block OAK_LOG_PANEL = new Block(AbstractBlock.Settings.create()
            .strength(3.0f)
            .requiresTool()
            .sounds(BlockSoundGroup.BAMBOO)
            .registryKey(OAK_LOG_PANEL_KEY));

    public static final Block VINES_PANEL = new Block(AbstractBlock.Settings.create()
            .strength(0.5f) // Weaker since it's just vines
            .sounds(BlockSoundGroup.VINE)
            .nonOpaque() // CRITICAL: Allows transparency/custom shapes without x-raying the world
            .registryKey(VINES_PANEL_KEY));

    public static final Block COMPACTED_DIRT = new Block(AbstractBlock.Settings.create()
            .strength(1.5f) // Harder than normal dirt
            .sounds(BlockSoundGroup.GRAVEL)
            .registryKey(COMPACTED_DIRT_KEY));

    public static final Block ANCIENT_FOSSIL_BLOCK = new Block(AbstractBlock.Settings.create()
            .strength(3.0f)
            .requiresTool()
            .sounds(BlockSoundGroup.BONE)
            .registryKey(ANCIENT_FOSSIL_BLOCK_KEY));

    @Override
    public void onInitialize() {
        // ==========================================
        // 4. REGISTER BLOCKS
        // ==========================================
        Registry.register(Registries.BLOCK, OAK_LOG_PANEL_KEY, OAK_LOG_PANEL);
        Registry.register(Registries.BLOCK, VINES_PANEL_KEY, VINES_PANEL);
        Registry.register(Registries.BLOCK, COMPACTED_DIRT_KEY, COMPACTED_DIRT);
        Registry.register(Registries.BLOCK, ANCIENT_FOSSIL_BLOCK_KEY, ANCIENT_FOSSIL_BLOCK);

        // ==========================================
        // 5. REGISTER BLOCK ITEMS
        // ==========================================
        BlockItem oakLogPanelItem = new BlockItem(OAK_LOG_PANEL, new Item.Settings().registryKey(OAK_LOG_PANEL_ITEM_KEY).useBlockPrefixedTranslationKey());
        Registry.register(Registries.ITEM, OAK_LOG_PANEL_ID, oakLogPanelItem);

        BlockItem vinesPanelItem = new BlockItem(VINES_PANEL, new Item.Settings().registryKey(VINES_PANEL_ITEM_KEY).useBlockPrefixedTranslationKey());
        Registry.register(Registries.ITEM, VINES_PANEL_ID, vinesPanelItem);

        BlockItem compactedDirtItem = new BlockItem(COMPACTED_DIRT, new Item.Settings().registryKey(COMPACTED_DIRT_ITEM_KEY).useBlockPrefixedTranslationKey());
        Registry.register(Registries.ITEM, COMPACTED_DIRT_ID, compactedDirtItem);

        BlockItem ancientFossilBlockItem = new BlockItem(ANCIENT_FOSSIL_BLOCK, new Item.Settings().registryKey(ANCIENT_FOSSIL_BLOCK_ITEM_KEY).useBlockPrefixedTranslationKey());
        Registry.register(Registries.ITEM, ANCIENT_FOSSIL_BLOCK_ID, ancientFossilBlockItem);

        // ==========================================
        // 6. ADD TO CREATIVE TAB
        // ==========================================
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS)
                .register(entries -> {
                    entries.add(oakLogPanelItem);
                    entries.add(vinesPanelItem);
                    entries.add(compactedDirtItem);
                    entries.add(ancientFossilBlockItem);
                });

        LOGGER.info("Blokkusus custom blocks successfully loaded!");
    }
}
