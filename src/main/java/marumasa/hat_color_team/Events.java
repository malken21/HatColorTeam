package marumasa.hat_color_team;

import org.bukkit.Color;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class Events implements Listener {
    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player player) {
            final Color color = getColor(player);
            if (color == null) return;
            final Entity attacker = event.getDamager();

            if (attacker instanceof Player player2)
                event.setCancelled(isSameColor(player2, color));

            else if (attacker instanceof Projectile projectile)
                if (projectile.getShooter() instanceof Player player2)
                    event.setCancelled(isSameColor(player2, color));
        }
    }

    private static Color getColor(Player player) {
        ItemStack itemStack = player.getInventory().getHelmet();
        if (itemStack == null) return null;
        if (itemStack.getItemMeta() instanceof LeatherArmorMeta leatherArmorMeta)
            return leatherArmorMeta.getColor();
        return null;
    }

    private static boolean isSameColor(Player player, Color color) {
        final Color color2 = getColor(player);
        if (color2 == null) return false;
        return color.equals(color2);
    }
}
