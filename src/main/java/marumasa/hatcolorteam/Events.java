package marumasa.hatcolorteam;

import org.bukkit.Color;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class Events implements Listener {
    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        Entity entity = event.getEntity();


        if (entity instanceof Player player) {
            Color color = getColor(player);
            if (event.getDamager() instanceof Player player2) {
                Color color2 = getColor(player2);
                if (color.equals(color2)) {
                    event.setCancelled(true);
                }
            }
        }
    }

    public Color getColor(Player player) {
        ItemStack itemStack = player.getInventory().getHelmet();
        if (itemStack == null) return null;
        if (itemStack.getItemMeta() instanceof LeatherArmorMeta leatherArmorMeta) {
            return leatherArmorMeta.getColor();
        }
        return null;
    }
}
