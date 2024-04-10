package plugin.sneakinventorymax;

import java.util.Arrays;
import java.util.Objects;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;


public final class Main extends JavaPlugin implements Listener {

  @Override
  public void onEnable() {
    Bukkit.getPluginManager().registerEvents(this, this);
    // Plugin startup logic
  }

  @EventHandler
  public void onPlayerToggleSneak(PlayerToggleSneakEvent e) {
    // イベント発生時　プレイヤーのインベントリ内のアイテム情報を取得
    Player player = e.getPlayer();

    // プレイヤーのインベントリ情報の取得
    ItemStack[] itemStacks = player.getInventory().getContents();

    // アイテム所持数が64ではない場合、アイテム所持数を64にする
    Arrays.stream(itemStacks)
        .filter(item -> !Objects.isNull(item) && item.getMaxStackSize() == 64
            && item.getAmount() < 64)
        .forEach(item -> item.setAmount(64));

    // プレイヤーのインベントリ内のアイテム情報を上書きする
    player.getInventory().setContents(itemStacks);
  }
}


