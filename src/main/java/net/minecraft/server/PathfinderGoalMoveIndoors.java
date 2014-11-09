package net.minecraft.server;

import de.minetick.pathsearch.PositionPathSearchType; // Poweruser

public class PathfinderGoalMoveIndoors extends PathfinderGoal {

    private EntityCreature a;
    private VillageDoor b;
    private int c = -1;
    private int d = -1;

    public PathfinderGoalMoveIndoors(EntityCreature entitycreature) {
        this.a = entitycreature;
        this.a(1);
    }

    public boolean a() {
        if ((!this.a.world.v() || this.a.world.Q()) && !this.a.world.worldProvider.g) {
            if (this.a.aD().nextInt(50) != 0) {
                return false;
            } else if (this.c != -1 && this.a.e((double) this.c, this.a.locY, (double) this.d) < 4.0D) {
                return false;
            } else {
                Village village = this.a.world.villages.getClosestVillage(MathHelper.floor(this.a.locX), MathHelper.floor(this.a.locY), MathHelper.floor(this.a.locZ), 14);

                if (village == null) {
                    return false;
                } else {
                    this.b = village.c(MathHelper.floor(this.a.locX), MathHelper.floor(this.a.locY), MathHelper.floor(this.a.locZ));
                    return this.b != null;
                }
            }
        } else {
            return false;
        }
    }

    public boolean b() {
        return !this.a.getNavigation().g();
    }

    public void c() {
        this.c = -1;
        if (this.a.e((double) this.b.getIndoorsX(), (double) this.b.locY, (double) this.b.getIndoorsZ()) > 256.0D) {
            Vec3D vec3d = RandomPositionGenerator.a(this.a, 14, 3, this.a.world.getVec3DPool().create((double) this.b.getIndoorsX() + 0.5D, (double) this.b.getIndoorsY(), (double) this.b.getIndoorsZ() + 0.5D));

            if (vec3d != null) {
                //this.a.getNavigation().a(vec3d.c, vec3d.d, vec3d.e, 1.0D);
                this.a.getNavigation().a(PositionPathSearchType.MOVEINDOORS, vec3d.c, vec3d.d, vec3d.e, 1.0D); // Poweruser
            }
        } else {
            //this.a.getNavigation().a((double) this.b.getIndoorsX() + 0.5D, (double) this.b.getIndoorsY(), (double) this.b.getIndoorsZ() + 0.5D, 1.0D);
            this.a.getNavigation().a(PositionPathSearchType.MOVEINDOORS, (double) this.b.getIndoorsX() + 0.5D, (double) this.b.getIndoorsY(), (double) this.b.getIndoorsZ() + 0.5D, 1.0D); // Poweruser
        }
    }

    public void d() {
        this.c = this.b.getIndoorsX();
        this.d = this.b.getIndoorsZ();
        this.b = null;
    }
}
