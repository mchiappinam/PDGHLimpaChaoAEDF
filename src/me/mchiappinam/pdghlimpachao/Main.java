package me.mchiappinam.pdghlimpachao;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.entity.Tameable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFormEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

	  public void onEnable() {
		getServer().getConsoleSender().sendMessage("§3[PDGHLimpaChao] §2registrando os eventos...");
		getServer().getPluginManager().registerEvents(this, this);
		getServer().getConsoleSender().sendMessage("§3[PDGHLimpaChao] §2eventos registrados com sucesso");
		getServer().getConsoleSender().sendMessage("§3[PDGHLimpaChao] §2ativando schedulers...");
    	/**getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
    		public void run() {
    	        for (World world : getServer().getWorlds()) {
    	            int removed = 0;

    	            for (Entity entity : world.getEntities()) {
    	                if (isIntensiveEntity(entity)) {
    	                    entity.remove();
    	                    removed++;
    	                }
    	            }
    	            //if (removed > 0) { //Coloquei como anotação para mostrar todos os mundos.
    	            	getServer().getConsoleSender().sendMessage("§3[PDGHLimpaChao] §c"+removed+" §2entidades eliminadas com sucesso no mundo §c"+world.getName());
    	            //}
    	        }
	      }
	    }, 100L);*/
		getServer().getConsoleSender().sendMessage("§3[PDGHRankupHeads] §2verificando se a config existe...");
		File file = new File(getDataFolder(),"config.yml");
		if(!file.exists()) {
			try {
				getServer().getConsoleSender().sendMessage("§3[PDGHLimpaChao] §2config inexistente, criando config...");
				saveResource("config_template.yml",false);
				File file2 = new File(getDataFolder(),"config_template.yml");
				file2.renameTo(new File(getDataFolder(),"config.yml"));
				getServer().getConsoleSender().sendMessage("§3[PDGHLimpaChao] §2config criada");
			}catch(Exception e) {getLogger().warning("ERRO: Não foi possível criar a config. Mais detalhes: "+e.toString());}
		}
    	IniciarTimers();
		getServer().getConsoleSender().sendMessage("§3[PDGHLimpaChao] §2schedulers ativados");
		getServer().getConsoleSender().sendMessage("§3[PDGHLimpaChao] §2ativado - Plugin by: mchiappinam");
		getServer().getConsoleSender().sendMessage("§3[PDGHLimpaChao] §2Acesse: http://pdgh.net/");
	  }
	    
	  public void onDisable() {
		getServer().getConsoleSender().sendMessage("§3[PDGHLimpaChao] §2desativado - Plugin by: mchiappinam");
		getServer().getConsoleSender().sendMessage("§3[PDGHLimpaChao] §2Acesse: http://pdgh.net/");
	  }

	    public static boolean isIntensiveEntity(Entity entity) {
	        return entity instanceof Item
	                || entity instanceof TNTPrimed
	                || entity instanceof ExperienceOrb
	                || entity instanceof FallingBlock
	                || (entity instanceof LivingEntity
	                    && !(entity instanceof Tameable)
	                    && !(entity instanceof Player));
	    }

	    /**@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	    public void onExplosionPrime(ExplosionPrimeEvent e) {
	        Entity ent = e.getEntity();
            ent.remove();
            e.setCancelled(true);
            return;
	    }*/
	    
	    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	    public void onEntityExplode(EntityExplodeEvent e) {
	        e.blockList().clear();
            /**Entity ent = e.getEntity();
	        if (ent != null) {
                ent.remove();
            }
            e.setCancelled(true);*/
            return;
	    }

	    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	    public void onLeavesDecay(LeavesDecayEvent e) {
	            e.setCancelled(true);
	            return;
	    }

	    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	    public void onBlockForm(BlockFormEvent e) {
	            e.setCancelled(true);
	            return;
	    }
	    
	    public void Alerta20Seg() {
	    	getServer().broadcastMessage("§d[Ⓐⓛⓔⓡⓣⓐ] "+ChatColor.BOLD+"➜ §dLimpando itens/mobs do chão em 20 segundos");
	    	Alerta5Seg();
	    }
	    
	    public void Alerta5Seg() {
	    	getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
	    		public void run() {
	    	    	getServer().broadcastMessage("§d[Ⓐⓛⓔⓡⓣⓐ] "+ChatColor.BOLD+"➜ §dLimpando itens/mobs do chão em 5 segundos");
	    	    	ExecutarLimpeza();
		      }
		    }, 300L);
	    }
	    
	    /**public void SpawnMobs() {
	    	getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
	    		public void run() {
	    	    	getServer().broadcastMessage("§d[Ⓐⓛⓔⓡⓣⓐ] "+ChatColor.BOLD+"➜ §9Mobs nascendo no §c/warp end §9e §c/warp jogar1§9...");
	    	    	getServer().getWorld("world").spawnEntity(new Location(null, -227.5, 71.5, 379.5), EntityType.ZOMBIE);
	    	    	getServer().getWorld("world").spawnEntity(new Location(null, -227.5, 71.5, 379.5), EntityType.ZOMBIE);
	    	    	getServer().getWorld("world").spawnEntity(new Location(null, -227.5, 71.5, 379.5), EntityType.ZOMBIE);
	    	    	getServer().getWorld("world").spawnEntity(new Location(null, -227.5, 71.5, 379.5), EntityType.ZOMBIE);
	    	    	getServer().getWorld("world").spawnEntity(new Location(null, -227.5, 71.5, 379.5), EntityType.ZOMBIE);
	    	    	getServer().getWorld("world").spawnEntity(new Location(null, -227.5, 71.5, 379.5), EntityType.ZOMBIE);
	    	    	getServer().getWorld("world").spawnEntity(new Location(null, -227.5, 71.5, 379.5), EntityType.ZOMBIE);
	    	    	getServer().getWorld("world").spawnEntity(new Location(null, -227.5, 71.5, 379.5), EntityType.ZOMBIE);
	    	    	getServer().getWorld("world").spawnEntity(new Location(null, -227.5, 71.5, 379.5), EntityType.ZOMBIE);
	    	    	getServer().getWorld("world").spawnEntity(new Location(null, -227.5, 71.5, 379.5), EntityType.ZOMBIE);
	    	    	getServer().getWorld("world").spawnEntity(new Location(null, -227.5, 71.5, 379.5), EntityType.ZOMBIE);
	    	    	getServer().getWorld("world").spawnEntity(new Location(null, -227.5, 71.5, 379.5), EntityType.ZOMBIE);
	    	    	getServer().getWorld("world").spawnEntity(new Location(null, -227.5, 71.5, 379.5), EntityType.ZOMBIE);
	    	    	getServer().getWorld("world").spawnEntity(new Location(null, -227.5, 71.5, 379.5), EntityType.ZOMBIE);
	    	    	getServer().getWorld("world").spawnEntity(new Location(null, -227.5, 71.5, 379.5), EntityType.ZOMBIE);
	    	    	getServer().getWorld("world").spawnEntity(new Location(null, -227.5, 71.5, 379.5), EntityType.ZOMBIE);
	    	    	getServer().getWorld("world").spawnEntity(new Location(null, -227.5, 71.5, 379.5), EntityType.ZOMBIE);
	    	    	getServer().getWorld("world").spawnEntity(new Location(null, -227.5, 71.5, 379.5), EntityType.ZOMBIE);
	    	    	getServer().getWorld("world").spawnEntity(new Location(null, -227.5, 71.5, 379.5), EntityType.ZOMBIE);
	    	    	getServer().getWorld("world").spawnEntity(new Location(null, -227.5, 71.5, 379.5), EntityType.ZOMBIE);
	    	    	getServer().getWorld("world").spawnEntity(new Location(null, -227.5, 71.5, 379.5), EntityType.ZOMBIE);
	    	    	getServer().getWorld("world").spawnEntity(new Location(null, -227.5, 71.5, 379.5), EntityType.ZOMBIE);
	    	    	getServer().getWorld("world").spawnEntity(new Location(null, -227.5, 71.5, 379.5), EntityType.ZOMBIE);
	    	    	getServer().getWorld("world").spawnEntity(new Location(null, -227.5, 71.5, 379.5), EntityType.ZOMBIE);
	    	    	getServer().getWorld("world").spawnEntity(new Location(null, -227.5, 71.5, 379.5), EntityType.ZOMBIE);
	    	    	getServer().getWorld("world").spawnEntity(new Location(null, -227.5, 71.5, 379.5), EntityType.ZOMBIE);
	    	    	getServer().getWorld("world").spawnEntity(new Location(null, -227.5, 71.5, 379.5), EntityType.ZOMBIE);
	    	    	getServer().getWorld("world").spawnEntity(new Location(null, -227.5, 71.5, 379.5), EntityType.ZOMBIE);
	    	    	getServer().getWorld("world").spawnEntity(new Location(null, -227.5, 71.5, 379.5), EntityType.ZOMBIE);
	    	    	getServer().getWorld("world").spawnEntity(new Location(null, -227.5, 71.5, 379.5), EntityType.ZOMBIE);
	    	    	getServer().getWorld("world").spawnEntity(new Location(null, -227.5, 71.5, 379.5), EntityType.ZOMBIE);
	    	    	getServer().getWorld("world").spawnEntity(new Location(null, -227.5, 71.5, 379.5), EntityType.ZOMBIE);
	    	    	getServer().getWorld("world").spawnEntity(new Location(null, -227.5, 71.5, 379.5), EntityType.ZOMBIE);
	    	    	getServer().getWorld("world").spawnEntity(new Location(null, -227.5, 71.5, 379.5), EntityType.ZOMBIE);
	    	    	getServer().getWorld("world").spawnEntity(new Location(null, -227.5, 71.5, 379.5), EntityType.ZOMBIE);
	    	    	getServer().getWorld("world").spawnEntity(new Location(null, -227.5, 71.5, 379.5), EntityType.ZOMBIE);
	    	    	getServer().getWorld("world").spawnEntity(new Location(null, -227.5, 71.5, 379.5), EntityType.ZOMBIE);
	    	    	getServer().getWorld("world").spawnEntity(new Location(null, -227.5, 71.5, 379.5), EntityType.ZOMBIE);
	    	    	getServer().getWorld("world").spawnEntity(new Location(null, -227.5, 71.5, 379.5), EntityType.ZOMBIE);
	    	    	getServer().getWorld("world").spawnEntity(new Location(null, -227.5, 71.5, 379.5), EntityType.ZOMBIE);
	    	    	getServer().getWorld("world_the_end").spawnEntity(new Location(null, 18.5, 64.5, -18.5), EntityType.ZOMBIE);
	    	    	getServer().getWorld("world_the_end").spawnEntity(new Location(null, 18.5, 64.5, -18.5), EntityType.ZOMBIE);
	    	    	getServer().getWorld("world_the_end").spawnEntity(new Location(null, 18.5, 64.5, -18.5), EntityType.ZOMBIE);
	    	    	getServer().getWorld("world_the_end").spawnEntity(new Location(null, 18.5, 64.5, -18.5), EntityType.ZOMBIE);
	    	    	getServer().getWorld("world_the_end").spawnEntity(new Location(null, 18.5, 64.5, -18.5), EntityType.ZOMBIE);
	    	    	getServer().getWorld("world_the_end").spawnEntity(new Location(null, 18.5, 64.5, -18.5), EntityType.ZOMBIE);
	    	    	getServer().getWorld("world_the_end").spawnEntity(new Location(null, 18.5, 64.5, -18.5), EntityType.ZOMBIE);
	    	    	getServer().getWorld("world_the_end").spawnEntity(new Location(null, 18.5, 64.5, -18.5), EntityType.ZOMBIE);
	    	    	getServer().getWorld("world_the_end").spawnEntity(new Location(null, 18.5, 64.5, -18.5), EntityType.ZOMBIE);
	    	    	getServer().getWorld("world_the_end").spawnEntity(new Location(null, 18.5, 64.5, -18.5), EntityType.ZOMBIE);
	    	    	getServer().getWorld("world_the_end").spawnEntity(new Location(null, 18.5, 64.5, -18.5), EntityType.ZOMBIE);
	    	    	getServer().getWorld("world_the_end").spawnEntity(new Location(null, 18.5, 64.5, -18.5), EntityType.ZOMBIE);
	    	    	getServer().getWorld("world_the_end").spawnEntity(new Location(null, 18.5, 64.5, -18.5), EntityType.ZOMBIE);
	    	    	getServer().getWorld("world_the_end").spawnEntity(new Location(null, 18.5, 64.5, -18.5), EntityType.ZOMBIE);
	    	    	getServer().getWorld("world_the_end").spawnEntity(new Location(null, 18.5, 64.5, -18.5), EntityType.ZOMBIE);
	    	    	getServer().getWorld("world_the_end").spawnEntity(new Location(null, 18.5, 64.5, -18.5), EntityType.ZOMBIE);
	    	    	getServer().getWorld("world_the_end").spawnEntity(new Location(null, 18.5, 64.5, -18.5), EntityType.ZOMBIE);
	    	    	getServer().getWorld("world_the_end").spawnEntity(new Location(null, 18.5, 64.5, -18.5), EntityType.ZOMBIE);
	    	    	getServer().getWorld("world_the_end").spawnEntity(new Location(null, 18.5, 64.5, -18.5), EntityType.ZOMBIE);
	    	    	getServer().getWorld("world_the_end").spawnEntity(new Location(null, 18.5, 64.5, -18.5), EntityType.ZOMBIE);
	    	    	getServer().getWorld("world_the_end").spawnEntity(new Location(null, 18.5, 64.5, -18.5), EntityType.ZOMBIE);
	    	    	getServer().getWorld("world_the_end").spawnEntity(new Location(null, 18.5, 64.5, -18.5), EntityType.ZOMBIE);
	    	    	getServer().getWorld("world_the_end").spawnEntity(new Location(null, 18.5, 64.5, -18.5), EntityType.ZOMBIE);
	    	    	getServer().getWorld("world_the_end").spawnEntity(new Location(null, 18.5, 64.5, -18.5), EntityType.ZOMBIE);
	    	    	getServer().getWorld("world_the_end").spawnEntity(new Location(null, 18.5, 64.5, -18.5), EntityType.ZOMBIE);
	    	    	getServer().getWorld("world_the_end").spawnEntity(new Location(null, 18.5, 64.5, -18.5), EntityType.ZOMBIE);
	    	    	getServer().getWorld("world_the_end").spawnEntity(new Location(null, 18.5, 64.5, -18.5), EntityType.ZOMBIE);
	    	    	getServer().getWorld("world_the_end").spawnEntity(new Location(null, 18.5, 64.5, -18.5), EntityType.ZOMBIE);
	    	    	getServer().getWorld("world_the_end").spawnEntity(new Location(null, 18.5, 64.5, -18.5), EntityType.ZOMBIE);
	    	    	getServer().getWorld("world_the_end").spawnEntity(new Location(null, 18.5, 64.5, -18.5), EntityType.ZOMBIE);
	    	    	getServer().getWorld("world_the_end").spawnEntity(new Location(null, 18.5, 64.5, -18.5), EntityType.ZOMBIE);
	    	    	getServer().getWorld("world_the_end").spawnEntity(new Location(null, 18.5, 64.5, -18.5), EntityType.ZOMBIE);
	    	    	getServer().getWorld("world_the_end").spawnEntity(new Location(null, 18.5, 64.5, -18.5), EntityType.ZOMBIE);
	    	    	getServer().getWorld("world_the_end").spawnEntity(new Location(null, 18.5, 64.5, -18.5), EntityType.ZOMBIE);
	    	    	getServer().getWorld("world_the_end").spawnEntity(new Location(null, 18.5, 64.5, -18.5), EntityType.ZOMBIE);
	    	    	getServer().getWorld("world_the_end").spawnEntity(new Location(null, 18.5, 64.5, -18.5), EntityType.ZOMBIE);
	    	    	getServer().getWorld("world_the_end").spawnEntity(new Location(null, 18.5, 64.5, -18.5), EntityType.ZOMBIE);
	    	    	getServer().getWorld("world_the_end").spawnEntity(new Location(null, 18.5, 64.5, -18.5), EntityType.ZOMBIE);
	    	    	getServer().getWorld("world_the_end").spawnEntity(new Location(null, 18.5, 64.5, -18.5), EntityType.ZOMBIE);
	    	    	getServer().getWorld("world_the_end").spawnEntity(new Location(null, 18.5, 64.5, -18.5), EntityType.ZOMBIE);
		      }
		    }, 100L);
	    }*/
	    
	    public void ExecutarLimpeza() {
	    	getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
	    		public void run() {
	    	        int removed = 0;
	    	        int entityIdDontRemove = 0;
	    	        boolean a=false;
	    	        for (World world : getServer().getWorlds()) {

	    	            for (Entity entity : world.getEntities()) {
	    	            	a=false;
	    	            	for(int i : getConfig().getIntegerList("entityIdDontRemove"))
	    	            		if(entity.getType().getTypeId()==i) {
	    	            			entityIdDontRemove++;
	    	            			a=true;
	    	            			break;
	    	            		}
	    	            	if(!a)
		    	                if (isIntensiveEntity(entity)) {
		    	                    entity.remove();
		    	                    removed++;
		    	                }
	    	            }

		    	    	//getServer().broadcastMessage("§d[Ⓐⓛⓔⓡⓣⓐ] "+ChatColor.BOLD+"➜ §dLimpando itens/mobs do chão em 5 segundos");
	    	            //if (removed > 0) { //Coloquei como anotação para mostrar todos os mundos.
	    	            	//getServer().getConsoleSender().sendMessage("§3[PDGHLimpaChao] §c"+removed+" §2entidades eliminadas com sucesso no mundo §c"+world.getName());
	    	            //}
	    	        }
	    	    	getServer().broadcastMessage("§d[Ⓐⓛⓔⓡⓣⓐ] "+ChatColor.BOLD+"➜ §d"+removed+" entidades removidas.");
	    	    	if(entityIdDontRemove>0)
	    	    		if(entityIdDontRemove==1)
	    	    			getServer().broadcastMessage("§d[Ⓐⓛⓔⓡⓣⓐ] "+ChatColor.BOLD+"➜ §d"+entityIdDontRemove+" mob especial não foi removido.");
	    	    		else
	    	    			getServer().broadcastMessage("§d[Ⓐⓛⓔⓡⓣⓐ] "+ChatColor.BOLD+"➜ §d"+entityIdDontRemove+" mobs especiais não foram removidos.");
	    	        IniciarTimers();
	    	        //SpawnMobs();
		      }
		    }, 100L);
	    }
	    
	    public void IniciarTimers() {
	    	getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
	    		public void run() {
	    			Alerta20Seg();
		      }
		    }, 6000L);
	    }
	  
}