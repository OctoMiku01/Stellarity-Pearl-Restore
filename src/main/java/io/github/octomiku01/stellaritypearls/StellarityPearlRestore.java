package io.github.octomiku01.stellaritypearls;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootTable;

public class StellarityPearlRestore implements ModInitializer {
	private static final ResourceKey<LootTable> ENDERMAN =
		ResourceKey.create(Registries.LOOT_TABLE, Identifier.withDefaultNamespace("entities/enderman"));

	@Override
	public void onInitialize() {
		// Stellarity overrides the enderman loot table so it drops chorus fruit outside the Nether and the End.
		// Swapping the finished drops keeps working no matter which data pack wins the loot table.
		LootTableEvents.MODIFY_DROPS.register((table, context, drops) -> {
			if (!table.is(ENDERMAN)) {
				return;
			}
			drops.replaceAll(stack -> stack.is(Items.CHORUS_FRUIT)
				? new ItemStack(Items.ENDER_PEARL, stack.getCount())
				: stack);
		});
	}
}
