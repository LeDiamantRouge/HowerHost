package fr.lediamantrouge.howerhost.gui;

import fr.kotlini.supragui.bases.MultiGUI;
import fr.kotlini.supragui.classes.Filler;
import fr.kotlini.supragui.classes.SlotPosition;
import fr.lediamantrouge.howerhost.Main;
import fr.lediamantrouge.howerhost.manager.BungeeCordManager;
import fr.lediamantrouge.howerhost.util.CreateSkull;
import fr.lediamantrouge.howerhost.util.ItemBuilder;
import fr.lediamantrouge.servermanager.CommonMain;
import fr.lediamantrouge.servermanager.servermanager.Server;
import org.bukkit.Color;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class MainGui extends MultiGUI {

    public MainGui(UUID uuid) {
        super(uuid, "§f▪ §6Host > Liste", 6 * 9, 2, new Filler(new SlotPosition(1, 1), new SlotPosition(7, 4), 6 * 9, true), true);
    }

    @Override
    public void putItems() {
        setPatternItems(getCorners(), new ItemBuilder(Material.STAINED_GLASS_PANE).setLeatherArmorColor(Color.ORANGE).setDisplayName(" ").build(false), e -> {});
        boolean found = false;
        for (Server server : CommonMain.getInstance().getServerManager().getServers()) {
            if (Main.getInstance().getConfig().getStringList("hosts").contains(server.getTemplate())) {
                found = true;
                List<String> lore = new ArrayList<>();
                lore.add("§8" + server.getName());
                lore.add("");
                lore.add("    §f§l▪ §7Statut: §f?");
                lore.add("    §f§l▪ §7Créateur: §f?");
                lore.add("    §f§l▪ §7Serveur : §a" + server.getDisplayName());
                lore.add("");
                addFillerItem(new ItemBuilder(Material.getMaterial(Main.getInstance().getConfig().getString("host-material." + server.getTemplate()))).setDisplayName("§e§lHost de " + Main.getInstance().getConfig().getString("host-type." + server.getTemplate())).setLoreWithList(lore).build(false), e -> {
                    getPlayer().sendMessage("§aConnexion en cours vers " + server.getDisplayName());
                    BungeeCordManager.sendPlayerToServer(getPlayer(), server.getName());
                    getPlayer().closeInventory();
                });
            }
        }
        if (!found) {
            addFillerItem(new ItemBuilder(Material.SIGN).setDisplayName("§8§l» §cAucun Host").build(false), e -> {});
        }
        setItem(1, new SlotPosition(4, 5).toSlot(), new ItemBuilder(new CreateSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzkwZjYyZWM1ZmEyZTkzZTY3Y2YxZTAwZGI4YWY0YjQ3YWM3YWM3NjlhYTA5YTIwM2ExZjU3NWExMjcxMGIxMCJ9fX0=").toItemStack()).setDisplayName("§8§l» §aCréer un host").setLoreWithList(Arrays.asList("§7§oPermet de gérer votre host","","§8§l» §eCliquez pour choisir un host")).build(false), e -> {
            new CreateHostGui(getPlayer().getUniqueId()).open(true);
        });
    }
}
