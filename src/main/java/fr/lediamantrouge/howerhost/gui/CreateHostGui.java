package fr.lediamantrouge.howerhost.gui;

import fr.kotlini.supragui.bases.MultiGUI;
import fr.kotlini.supragui.classes.Filler;
import fr.kotlini.supragui.classes.SlotPosition;
import fr.lediamantrouge.howerhost.Main;
import fr.lediamantrouge.howerhost.util.ItemBuilder;
import fr.lediamantrouge.servermanager.CommonMain;
import org.bukkit.Color;
import org.bukkit.Material;

import java.util.UUID;

public class CreateHostGui extends MultiGUI {

    public CreateHostGui(UUID uuid) {
        super(uuid, "§f▪ §6Host > Créer", 4 * 9, 2, new Filler(new SlotPosition(1, 1), new SlotPosition(7, 2), 6 * 9, true), true);
    }

    @Override
    public void putItems() {
        for (String host : Main.getInstance().getConfig().getStringList("hosts")) {
            addFillerItem(new ItemBuilder(Material.getMaterial(Main.getInstance().getConfig().getString("host-material." + host))).setDisplayName("§e§lHost de " + Main.getInstance().getConfig().getString("host-type." + host)).build(false), e -> {
                CommonMain.getInstance().getServerCreator().createServer(host);
                getPlayer().sendMessage("§aVotre host à été créer !");
                getPlayer().closeInventory();
                new MainGui(getPlayer().getUniqueId()).open(true);
            });
        }
        setPatternItems(getCorners(), new ItemBuilder(Material.STAINED_GLASS_PANE).setLeatherArmorColor(Color.ORANGE).setDisplayName(" ").build(false), e -> {});
    }
}
