/*     */ package com.emoniph.witchery.infusion;
/*     */ 
/*     */ import com.emoniph.witchery.util.BlockUtil;
/*     */ import com.emoniph.witchery.util.Log;
/*     */ import com.emoniph.witchery.util.ParticleEffect;
/*     */ import com.emoniph.witchery.util.SoundEffect;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.crafting.FurnaceRecipes;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.event.entity.player.PlayerInteractEvent;
/*     */ import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;
/*     */ import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
/*     */ 
/*     */ public class PlayerEffects
/*     */ {
/*  23 */   private static final ArrayList<PlayerEffect> effects = new ArrayList();
/*     */   public static final String KEY_EFFECTS = "witchery.effects";
/*  25 */   public static final PlayerEffect IMP_FIRE_TOUCH = new PlayerEffect("witchery.imp.firetouch", effects) { protected void doUpdate(EntityPlayer player, int worldTicks) {}
/*     */     
/*     */     protected void doHarvest(EntityPlayer player, BlockEvent.HarvestDropsEvent event) {}
/*     */     
/*  29 */     protected void doInteract(EntityPlayer player, PlayerInteractEvent event) { World world = player.field_70170_p;
/*  30 */       if (world.field_73012_v.nextDouble() < 0.2D) {
/*  31 */         Block block = BlockUtil.getBlock(world, event.x, event.y, event.z);
/*  32 */         if ((block != null) && (block != Blocks.field_150350_a)) {
/*  33 */           int par4 = event.x;
/*  34 */           int par5 = event.y;
/*  35 */           int par6 = event.z;
/*  36 */           int par7 = event.face;
/*  37 */           if (par7 == 0)
/*     */           {
/*  39 */             par5--;
/*     */           }
/*     */           
/*  42 */           if (par7 == 1)
/*     */           {
/*  44 */             par5++;
/*     */           }
/*     */           
/*  47 */           if (par7 == 2)
/*     */           {
/*  49 */             par6--;
/*     */           }
/*     */           
/*  52 */           if (par7 == 3)
/*     */           {
/*  54 */             par6++;
/*     */           }
/*     */           
/*  57 */           if (par7 == 4)
/*     */           {
/*  59 */             par4--;
/*     */           }
/*     */           
/*  62 */           if (par7 == 5)
/*     */           {
/*  64 */             par4++;
/*     */           }
/*  66 */           if (event.action == PlayerInteractEvent.Action.LEFT_CLICK_BLOCK) {
/*  67 */             par4 = par4 - 1 + world.field_73012_v.nextInt(3);
/*  68 */             par6 = par6 - 1 + world.field_73012_v.nextInt(3);
/*     */           }
/*     */           
/*  71 */           if ((world.func_147437_c(par4, par5, par6)) && (!world.func_147437_c(par4, par5 - 1, par6)))
/*     */           {
/*  73 */             world.func_72908_a(par4 + 0.5D, par5 + 0.5D, par6 + 0.5D, SoundEffect.FIRE_FIRE.toString(), 1.0F, world.field_73012_v.nextFloat() * 0.4F + 0.8F);
/*  74 */             world.func_147449_b(par4, par5, par6, Blocks.field_150480_ab);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   };
/*     */   
/*  81 */   public static final PlayerEffect IMP_EVAPORATION = new PlayerEffect("witchery.imp.evaporation", effects) {
/*     */     protected void doUpdate(EntityPlayer player, int worldTicks) {
/*  83 */       if (player.field_70170_p.field_73012_v.nextInt(5) == 0) {
/*  84 */         int midX = MathHelper.func_76128_c(player.field_70165_t);
/*  85 */         int midY = MathHelper.func_76128_c(player.field_70163_u);
/*  86 */         int midZ = MathHelper.func_76128_c(player.field_70161_v);
/*  87 */         int R = 3;
/*  88 */         int RSq = 9;
/*  89 */         boolean found = false;
/*  90 */         for (int x = midX - 3; x <= midX + 3; x++) {
/*  91 */           for (int z = midZ - 3; z <= midZ + 3; z++) {
/*  92 */             for (int y = midY + 2; y >= midY - 1; y--) {
/*  93 */               if (player.func_70092_e(x, y, z) <= 9.0D) {
/*  94 */                 Block block = BlockUtil.getBlock(player.field_70170_p, x, y, z);
/*     */                 
/*  96 */                 if (((block == Blocks.field_150355_j) || (block == Blocks.field_150358_i)) && (player.field_70170_p.func_147437_c(x, y + 1, z))) {
/*  97 */                   player.field_70170_p.func_147468_f(x, y, z);
/*  98 */                   ParticleEffect.EXPLODE.send(SoundEffect.NONE, player.field_70170_p, x, y + 1, z, 1.0D, 1.0D, 16);
/*  99 */                   found = true;
/*     */                 }
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */         
/* 106 */         if (found)
/* 107 */           SoundEffect.RANDOM_FIZZ.playAt(player.field_70170_p, player.field_70165_t, player.field_70163_u, player.field_70161_v, 1.0F, 2.6F + (player.field_70170_p.field_73012_v.nextFloat() - player.field_70170_p.field_73012_v.nextFloat()) * 0.8F);
/*     */       }
/*     */     }
/*     */     
/*     */     protected void doHarvest(EntityPlayer player, BlockEvent.HarvestDropsEvent event) {}
/*     */     
/*     */     protected void doInteract(EntityPlayer player, PlayerInteractEvent event) {}
/*     */   };
/* 115 */   public static final PlayerEffect IMP_METLING_TOUCH = new PlayerEffect("witchery.im.meltingtouch", effects) {
/*     */     protected void doUpdate(EntityPlayer player, int worldTicks) {}
/*     */     
/* 118 */     protected void doHarvest(EntityPlayer player, BlockEvent.HarvestDropsEvent event) { ArrayList<ItemStack> newDrops = new ArrayList();
/* 119 */       for (ItemStack drop : event.drops) {
/* 120 */         ItemStack smeltedDrop = FurnaceRecipes.func_77602_a().func_151395_a(drop);
/*     */         
/* 122 */         if (smeltedDrop != null) {
/* 123 */           Log.instance().debug("Smelting Touch: " + drop.toString() + " -> " + smeltedDrop.toString());
/* 124 */           ItemStack smelted = smeltedDrop.func_77946_l();
/* 125 */           if (player.field_70170_p.field_73012_v.nextDouble() < 0.25D) {
/* 126 */             smelted.field_77994_a += 1;
/*     */           }
/* 128 */           newDrops.add(smelted);
/*     */         } else {
/* 130 */           Log.instance().debug("Smelting Touch: " + drop.toString() + " -> none");
/* 131 */           newDrops.add(drop);
/*     */         }
/*     */       }
/* 134 */       event.drops.clear();
/* 135 */       for (ItemStack newDrop : newDrops)
/* 136 */         event.drops.add(newDrop);
/*     */     }
/*     */     
/*     */     protected void doInteract(EntityPlayer player, PlayerInteractEvent event) {}
/*     */   };
/*     */   private static final int TICKS_PER_UPDATE = 20;
/*     */   
/* 143 */   public static void onDeath(EntityPlayer player) { NBTTagCompound nbtPlayer = Infusion.getNBT(player);
/* 144 */     if ((nbtPlayer != null) && (nbtPlayer.func_74764_b("witchery.effects"))) {
/* 145 */       NBTTagCompound nbtEffects = nbtPlayer.func_74775_l("witchery.effects");
/* 146 */       for (PlayerEffect effect : effects) {
/* 147 */         effect.removeFrom(nbtEffects);
/*     */       }
/* 149 */       if (nbtEffects.func_82582_d()) {
/* 150 */         nbtPlayer.func_82580_o("witchery.effects");
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public static void onUpdate(EntityPlayer player, long ticks)
/*     */   {
/* 158 */     if (ticks % 20L == 3L) {
/* 159 */       NBTTagCompound nbtPlayer = Infusion.getNBT(player);
/* 160 */       if ((nbtPlayer != null) && (nbtPlayer.func_74764_b("witchery.effects"))) {
/* 161 */         NBTTagCompound nbtEffects = nbtPlayer.func_74775_l("witchery.effects");
/* 162 */         for (PlayerEffect effect : effects) {
/* 163 */           effect.update(nbtEffects, 20, player);
/*     */         }
/* 165 */         if (nbtEffects.func_82582_d()) {
/* 166 */           nbtPlayer.func_82580_o("witchery.effects");
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static void onHarvestDrops(EntityPlayer player, BlockEvent.HarvestDropsEvent event) {
/* 173 */     NBTTagCompound nbtPlayer = Infusion.getNBT(player);
/* 174 */     if ((nbtPlayer != null) && (nbtPlayer.func_74764_b("witchery.effects"))) {
/* 175 */       NBTTagCompound nbtEffects = nbtPlayer.func_74775_l("witchery.effects");
/* 176 */       for (PlayerEffect effect : effects) {
/* 177 */         effect.harvest(nbtEffects, event, player);
/*     */       }
/* 179 */       if (nbtEffects.func_82582_d()) {
/* 180 */         nbtPlayer.func_82580_o("witchery.effects");
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static void onInteract(EntityPlayer player, PlayerInteractEvent event) {
/* 186 */     NBTTagCompound nbtPlayer = Infusion.getNBT(player);
/* 187 */     if ((nbtPlayer != null) && (nbtPlayer.func_74764_b("witchery.effects"))) {
/* 188 */       NBTTagCompound nbtEffects = nbtPlayer.func_74775_l("witchery.effects");
/* 189 */       for (PlayerEffect effect : effects) {
/* 190 */         effect.interact(nbtEffects, event, player);
/*     */       }
/* 192 */       if (nbtEffects.func_82582_d()) {
/* 193 */         nbtPlayer.func_82580_o("witchery.effects");
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static abstract class PlayerEffect {
/*     */     protected final String unlocalizedName;
/*     */     
/*     */     protected PlayerEffect(String unlocalizedName, ArrayList<PlayerEffect> effects) {
/* 202 */       this.unlocalizedName = unlocalizedName;
/* 203 */       effects.add(this);
/*     */     }
/*     */     
/*     */     public void interact(NBTTagCompound nbtEffects, PlayerInteractEvent event, EntityPlayer player) {
/* 207 */       if (nbtEffects.func_74764_b(this.unlocalizedName)) {
/* 208 */         doInteract(player, event);
/*     */       }
/*     */     }
/*     */     
/*     */     protected abstract void doInteract(EntityPlayer paramEntityPlayer, PlayerInteractEvent paramPlayerInteractEvent);
/*     */     
/*     */     public void harvest(NBTTagCompound nbtEffects, BlockEvent.HarvestDropsEvent event, EntityPlayer player) {
/* 215 */       if (nbtEffects.func_74764_b(this.unlocalizedName)) {
/* 216 */         doHarvest(player, event);
/*     */       }
/*     */     }
/*     */     
/*     */     protected abstract void doHarvest(EntityPlayer paramEntityPlayer, BlockEvent.HarvestDropsEvent paramHarvestDropsEvent);
/*     */     
/*     */     public void applyTo(EntityPlayer player, int durationTicks) {
/* 223 */       NBTTagCompound nbtPlayer = Infusion.getNBT(player);
/* 224 */       if (nbtPlayer != null) {
/* 225 */         if (!nbtPlayer.func_74764_b("witchery.effects")) {
/* 226 */           nbtPlayer.func_74782_a("witchery.effects", new NBTTagCompound());
/*     */         }
/* 228 */         NBTTagCompound nbtEffects = nbtPlayer.func_74775_l("witchery.effects");
/* 229 */         nbtEffects.func_74768_a(this.unlocalizedName, durationTicks);
/*     */       }
/*     */     }
/*     */     
/*     */     private void removeFrom(NBTTagCompound nbtEffects) {
/* 234 */       if (nbtEffects.func_74764_b(this.unlocalizedName)) {
/* 235 */         nbtEffects.func_82580_o(this.unlocalizedName);
/*     */       }
/*     */     }
/*     */     
/*     */     private void update(NBTTagCompound nbtEffects, int ticks, EntityPlayer player) {
/* 240 */       if (nbtEffects.func_74764_b(this.unlocalizedName)) {
/* 241 */         int remainingTicks = nbtEffects.func_74762_e(this.unlocalizedName);
/* 242 */         int newTicks = Math.max(remainingTicks - ticks, 0);
/* 243 */         if (newTicks == 0) {
/* 244 */           removeFrom(nbtEffects);
/*     */         } else {
/* 246 */           nbtEffects.func_74768_a(this.unlocalizedName, newTicks);
/* 247 */           doUpdate(player, ticks);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */     protected abstract void doUpdate(EntityPlayer paramEntityPlayer, int paramInt);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/infusion/PlayerEffects.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */