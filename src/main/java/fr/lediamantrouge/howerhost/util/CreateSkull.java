package fr.lediamantrouge.howerhost.util;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.UUID;

public class CreateSkull {

    private final ItemStack item;

    public CreateSkull(String value) {
        this.item = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
        if (!value.isEmpty()) {
            SkullMeta itemMeta = (SkullMeta)this.item.getItemMeta();
            GameProfile profile = new GameProfile(UUID.randomUUID(), (String)null);
            profile.getProperties().put("textures", new Property("textures", value));

            try {
                Field profileField = itemMeta.getClass().getDeclaredField("profile");
                profileField.setAccessible(true);
                profileField.set(itemMeta, profile);
            } catch (NoSuchFieldException | SecurityException | IllegalAccessException | IllegalArgumentException var5) {
                var5.printStackTrace();
            }

            this.item.setItemMeta(itemMeta);
        }
    }

    public ItemStack toItemStack() {
        return this.item;
    }
}
