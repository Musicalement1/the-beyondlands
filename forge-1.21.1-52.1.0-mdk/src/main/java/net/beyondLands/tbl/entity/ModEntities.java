package net.beyondLands.tbl.entity;

import net.beyondLands.tbl.TBL;
import net.beyondLands.tbl.entity.custom.AshZombie;
import net.beyondLands.tbl.entity.custom.AshlingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, TBL.MOD_ID);

    public static final RegistryObject<EntityType<AshlingEntity>> ASHLING =
            ENTITY_TYPES.register("ashling", () -> EntityType.Builder.of(AshlingEntity::new, MobCategory.CREATURE)
                    .sized(1.0f, 1.0f).build("ashling"));
    public static final RegistryObject<EntityType<AshZombie>> ASH_ZOMBIE =
            ENTITY_TYPES.register("ash_zombie", () -> EntityType.Builder.of(AshZombie::new, MobCategory.MONSTER)
                    .sized(0.6f, 1.95f).build("ash_zombie"));


    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}