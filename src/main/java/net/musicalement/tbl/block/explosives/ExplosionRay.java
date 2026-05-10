package net.musicalement.tbl.block.explosives;

public class ExplosionRay {

    public double x;
    public double y;
    public double z;

    public final double dx;
    public final double dy;
    public final double dz;

    public float energy;

    public boolean dead = false;

    public double distanceTravelled = 0;

    public ExplosionRay(double x, double y, double z,
                        double dx, double dy, double dz,
                        float energy) {

        this.x = x;
        this.y = y;
        this.z = z;

        this.dx = dx;
        this.dy = dy;
        this.dz = dz;

        this.energy = energy;
    }
}