package net.minecraft.server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PathfinderGoalNearestAttackableTarget extends PathfinderGoalTarget {

    private final Class a;
    private final int b;
    private final DistanceComparator e;
    private final IEntitySelector f;
    private EntityLiving g;
    private List playerSortList = new ArrayList(); // Poweruser

    public PathfinderGoalNearestAttackableTarget(EntityCreature entitycreature, Class oclass, int i, boolean flag) {
        this(entitycreature, oclass, i, flag, false);
    }

    public PathfinderGoalNearestAttackableTarget(EntityCreature entitycreature, Class oclass, int i, boolean flag, boolean flag1) {
        this(entitycreature, oclass, i, flag, flag1, (IEntitySelector) null);
    }

    public PathfinderGoalNearestAttackableTarget(EntityCreature entitycreature, Class oclass, int i, boolean flag, boolean flag1, IEntitySelector ientityselector) {
        super(entitycreature, flag, flag1);
        this.a = oclass;
        this.b = i;
        this.e = new DistanceComparator(entitycreature);
        this.a(1);
        this.f = new EntitySelectorNearestAttackableTarget(this, ientityselector);
    }

    public boolean a() {
        if (this.b > 0 && this.c.aI().nextInt(this.b) != 0) {
            return false;
        } else {
            double d0 = this.f();
            //List list = this.c.world.a(this.a, this.c.boundingBox.grow(d0, 4.0D, d0), this.f);
            // Poweruser start
            List list;
            if(this.a == EntityHuman.class) {
                this.playerSortList.clear();
                AxisAlignedBB region = this.c.boundingBox.grow(d0, 4.0D, d0);
                for(int i = 0; i < this.c.world.players.size(); i++) {
                    EntityPlayer ep = (EntityPlayer) this.c.world.players.get(i);
                    if (this.a.isAssignableFrom(ep.getClass()) && ep.boundingBox.b(region) && (this.f == null || this.f.a(ep))) {
                        this.playerSortList.add(ep);
                    }
                }
                list = this.playerSortList;
            } else {
                list = this.c.world.a(this.a, this.c.boundingBox.grow(d0, 4.0D, d0), this.f);
            }
            // Poweruser end

            Collections.sort(list, this.e);
            if (list.isEmpty()) {
                return false;
            } else {
                this.g = (EntityLiving) list.get(0);
                return true;
            }
        }
    }

    public void c() {
        this.c.setGoalTarget(this.g);
        super.c();
    }
}
