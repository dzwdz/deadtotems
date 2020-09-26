package dzwdz.deadtotems;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class DeadTotems implements ModInitializer {

    public static final Item DEAD_TOTEM = new Item(new FabricItemSettings().group(ItemGroup.COMBAT));

    @Override
    public void onInitialize() {
        Registry.register(Registry.ITEM, new Identifier("deadtotems", "dead_totem"), DEAD_TOTEM);
    }
}
