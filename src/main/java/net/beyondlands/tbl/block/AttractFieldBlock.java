package net.beyondlands.tbl.block;

public class AttractFieldBlock extends ForceFieldBlock {
    public AttractFieldBlock(Properties props) {
        super(props);
        this.registerDefaultState(this.stateDefinition.any().setValue(ATTRACT, true));
    }
}
