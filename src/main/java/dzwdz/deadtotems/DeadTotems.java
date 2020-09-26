package dzwdz.deadtotems;

import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class DeadTotems implements ModInitializer {
    public static final Item DEAD_TOTEM = new Item(new FabricItemSettings().group(ItemGroup.COMBAT).maxCount(1));

    public static ModConfig config;

    @Override
    public void onInitialize() {
        AutoConfig.register(ModConfig.class, JanksonConfigSerializer::new);
        config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();

        Registry.register(Registry.ITEM, new Identifier("deadtotems", "dead_totem"), DEAD_TOTEM);
    }
}
