package net.beyondLands.tbl.block;

import net.minecraft.world.level.block.state.BlockBehaviour;

public class AttractFieldBlock extends ForceFieldBlock {
    public AttractFieldBlock(BlockBehaviour.Properties props) {
        super(props);
        this.registerDefaultState(this.stateDefinition.any().setValue(ATTRACT, true));
    }
}
