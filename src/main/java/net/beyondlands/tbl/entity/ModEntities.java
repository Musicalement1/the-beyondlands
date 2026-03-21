package net.beyondlands.tbl.entity;

import net.beyondlands.tbl.TBL;
import net.beyondlands.tbl.entity.custom.AshZombie;
import net.beyondlands.tbl.entity.custom.AshlingEntity;
import net.beyondlands.tbl.entity.custom.NukerEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, TBL.MOD_ID);

    public static final Supplier<EntityType<AshlingEntity>> ASHLING =
            ENTITY_TYPES.register("ashling", () -> EntityType.Builder.of(AshlingEntity::new, MobCategory.CREATURE)
                    .sized(1.0f, 1.0f).build("ashling"));
    public static final Supplier<EntityType<AshZombie>> ASH_ZOMBIE =
            ENTITY_TYPES.register("ash_zombie", () -> EntityType.Builder.of(AshZombie::new, MobCategory.MONSTER)
                    .sized(0.6f, 1.95f).build("ash_zombie"));
    public static final Supplier<EntityType<NukerEntity>> NUKER =
            ENTITY_TYPES.register("nuker", () -> EntityType.Builder.of(NukerEntity::new, MobCategory.MONSTER)
                    .sized(0.6f * 1.3f, 1.7f * 1.3f).build("nuker"));



    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}