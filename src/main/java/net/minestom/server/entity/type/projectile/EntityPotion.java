package net.minestom.server.entity.type.projectile;

import net.minestom.server.entity.Entity;
import net.minestom.server.entity.EntityType;
import net.minestom.server.entity.Metadata;
import net.minestom.server.entity.ObjectEntity;
import net.minestom.server.entity.type.Projectile;
import net.minestom.server.item.ItemStack;
import net.minestom.server.utils.Position;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EntityPotion extends AbstractProjectile {

    public EntityPotion(@Nullable Entity shooter, @NotNull Position spawnPosition, @NotNull ItemStack potion) {
        super(shooter, EntityType.POTION, spawnPosition);
        setBoundingBox(0.25f, 0.25f, 0.25f);
        setPotion(potion);
    }

    @Override
    public int getObjectData() {
        return 0;
    }

    @NotNull
    public ItemStack getPotion() {
        return metadata.getIndex((byte) 7, ItemStack.getAirItem());
    }

    public void setPotion(@NotNull ItemStack potion) {
        this.metadata.setIndex((byte) 7, Metadata.Slot(potion));
    }
}
