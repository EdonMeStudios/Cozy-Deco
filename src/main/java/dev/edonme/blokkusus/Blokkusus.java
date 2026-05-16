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
	public static final Identifier OAK_LOG_PANEL_ID = Identifier.of(MOD_ID, "oak_log_panel");

	public static final RegistryKey<Block> OAK_LOG_PANEL_KEY = RegistryKey.of(RegistryKeys.BLOCK, OAK_LOG_PANEL_ID);

	public static final RegistryKey<Item> OAK_LOG_PANEL_ITEM_KEY = RegistryKey.of(RegistryKeys.ITEM, OAK_LOG_PANEL_ID);

	public static final Block OAK_LOG_PANEL = new Block(
		AbstractBlock.Settings.create()
				.strength(3.0f)
				.requiresTool()
				.sounds(BlockSoundGroup.BAMBOO)
				.registryKey(OAK_LOG_PANEL_KEY)
	);

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		Registry.register(Registries.BLOCK, OAK_LOG_PANEL_KEY, OAK_LOG_PANEL);

		Item.Settings itemSettings = new Item.Settings()
				.registryKey(OAK_LOG_PANEL_ITEM_KEY)
				.useBlockPrefixedTranslationKey();

		BlockItem blockItem = new BlockItem(OAK_LOG_PANEL, itemSettings);
		Registry.register(Registries.ITEM, OAK_LOG_PANEL_ID, blockItem);

		ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS)
				.register(entries -> entries.add(blockItem));

		LOGGER.info("Hello Fabric world!");
	}
}