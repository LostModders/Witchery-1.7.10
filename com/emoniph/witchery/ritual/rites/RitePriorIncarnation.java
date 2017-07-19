/*     */ package com.emoniph.witchery.ritual.rites;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.blocks.BlockCircle.TileEntityCircle.ActivatedRitual;
/*     */ import com.emoniph.witchery.infusion.Infusion;
/*     */ import com.emoniph.witchery.ritual.RitualStep;
/*     */ import com.emoniph.witchery.ritual.RitualStep.Result;
/*     */ import com.emoniph.witchery.util.ChatUtil;
/*     */ import com.emoniph.witchery.util.Config;
/*     */ import com.emoniph.witchery.util.Coord;
/*     */ import com.emoniph.witchery.util.Log;
/*     */ import com.emoniph.witchery.util.ParticleEffect;
/*     */ import com.emoniph.witchery.util.SoundEffect;
/*     */ import cpw.mods.fml.common.eventhandler.SubscribeEvent;
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.monster.EntitySkeleton;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.world.GameRules;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldServer;
/*     */ import net.minecraftforge.event.entity.item.ItemExpireEvent;
/*     */ import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
/*     */ import net.minecraftforge.event.entity.player.PlayerDropsEvent;
/*     */ 
/*     */ public class RitePriorIncarnation extends com.emoniph.witchery.ritual.Rite
/*     */ {
/*     */   private static final String PRIOR_INV_KEY = "WITCPriIncInv";
/*     */   private static final String PRIOR_USR_KEY = "WITCPriIncUsr";
/*     */   private static final String PRIOR_LOC_KEY = "WITCPriIncLoc";
/*     */   private final int radius;
/*     */   private final int aoe;
/*     */   
/*     */   public static class EventHooks
/*     */   {
/*     */     @SubscribeEvent
/*     */     public void onItemExpire(ItemExpireEvent event)
/*     */     {
/*  44 */       if ((event.entityItem != null) && (!event.entityItem.field_70170_p.field_72995_K) && (RitePriorIncarnation.isRiteAllowed()) && (!event.isCanceled())) {
/*  45 */         ItemStack stack = event.entityItem.func_92059_d();
/*  46 */         NBTTagCompound nbtItem = stack.func_77978_p();
/*  47 */         if ((nbtItem != null) && (nbtItem.func_74764_b("WITCPriIncUsr"))) {
/*  48 */           String username = nbtItem.func_74779_i("WITCPriIncUsr");
/*  49 */           if ((username != null) && (!username.isEmpty())) {
/*  50 */             MinecraftServer server = MinecraftServer.func_71276_C();
/*  51 */             for (WorldServer world : server.field_71305_c) {
/*  52 */               EntityPlayer player = world.func_72924_a(username);
/*  53 */               if (player != null) {
/*  54 */                 if (Config.instance().traceRites()) {
/*  55 */                   Log.instance().debug(String.format("Saving stack %s for player %s", new Object[] { stack.toString(), player.func_70005_c_() }));
/*     */                 }
/*  57 */                 NBTTagCompound nbt = Infusion.getNBT(player);
/*  58 */                 if (!nbt.func_74764_b("WITCPriIncInv")) {
/*  59 */                   NBTTagList tagList = new NBTTagList();
/*  60 */                   nbt.func_74782_a("WITCPriIncInv", tagList);
/*     */                 }
/*  62 */                 NBTTagList list = nbt.func_150295_c("WITCPriIncInv", 10);
/*  63 */                 NBTTagCompound tagCompound = new NBTTagCompound();
/*  64 */                 nbtItem.func_82580_o("WITCPriIncUsr");
/*  65 */                 if (nbtItem.func_82582_d()) {
/*  66 */                   stack.func_77982_d(null);
/*     */                 }
/*  68 */                 stack.func_77955_b(tagCompound);
/*  69 */                 list.func_74742_a(tagCompound);
/*  70 */                 break;
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */     @SubscribeEvent
/*     */     public void onEntityItemPickup(EntityItemPickupEvent event) {
/*  80 */       if ((!event.item.field_70170_p.field_72995_K) && (RitePriorIncarnation.isRiteAllowed()) && (!event.isCanceled())) {
/*  81 */         ItemStack stack = event.item.func_92059_d();
/*  82 */         removePriorUserTag(stack);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */     public static void removePriorUserTag(ItemStack stack)
/*     */     {
/*  89 */       if (stack != null) {
/*  90 */         NBTTagCompound nbtItem = stack.func_77978_p();
/*  91 */         if ((nbtItem != null) && (nbtItem.func_74764_b("WITCPriIncUsr"))) {
/*  92 */           if (Config.instance().traceRites()) {
/*  93 */             Log.instance().debug(String.format("removing prio incarnation tag for player %s", new Object[] { nbtItem.func_74779_i("WITCPriIncUsr") }));
/*     */           }
/*  95 */           nbtItem.func_82580_o("WITCPriIncUsr");
/*  96 */           if (nbtItem.func_82582_d()) {
/*  97 */             stack.func_77982_d(null);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */     @SubscribeEvent
/*     */     public void onPlayerDrops(PlayerDropsEvent event) {
/* 105 */       if ((event.entityPlayer != null) && (!event.entityPlayer.field_70170_p.field_72995_K) && 
/* 106 */         (event.entityPlayer.func_70644_a(Witchery.Potions.KEEP_INVENTORY))) {
/* 107 */         event.setCanceled(true);
/* 108 */         return;
/*     */       }
/*     */       
/*     */ 
/* 112 */       if ((event.entityPlayer != null) && (!event.entityPlayer.field_70170_p.field_72995_K) && (RitePriorIncarnation.isRiteAllowed()) && (!event.isCanceled())) {
/* 113 */         if (event.entityPlayer.field_70170_p.func_82736_K().func_82766_b("keepInventory")) {
/* 114 */           return;
/*     */         }
/*     */         
/* 117 */         ArrayList<EntityItem> drops = event.drops;
/* 118 */         if ((drops != null) && (drops.size() > 0)) {
/* 119 */           EntityPlayer player = event.entityPlayer;
/* 120 */           World world = player.field_70170_p;
/* 121 */           for (int i = 0; i < drops.size(); i++) {
/* 122 */             ItemStack stack = ((EntityItem)drops.get(i)).func_92059_d();
/* 123 */             if (stack != null) {
/* 124 */               NBTTagCompound nbt = stack.func_77978_p();
/* 125 */               if (nbt == null) {
/* 126 */                 nbt = new NBTTagCompound();
/* 127 */                 stack.func_77982_d(nbt);
/*     */               }
/* 129 */               if (Config.instance().traceRites()) {
/* 130 */                 Log.instance().debug(String.format("Tagging stack %s for player %s", new Object[] { stack.toString(), player.func_70005_c_() }));
/*     */               }
/* 132 */               nbt.func_74778_a("WITCPriIncUsr", player.func_70005_c_());
/*     */             }
/*     */           }
/*     */           
/* 136 */           NBTTagCompound nbt = Infusion.getNBT(player);
/* 137 */           if (nbt.func_74764_b("WITCPriIncInv")) {
/* 138 */             nbt.func_82580_o("WITCPriIncInv");
/*     */           }
/*     */           
/* 141 */           nbt.func_74780_a("WITCPriIncLocX", player.field_70165_t);
/* 142 */           nbt.func_74780_a("WITCPriIncLocY", player.field_70163_u);
/* 143 */           nbt.func_74780_a("WITCPriIncLocZ", player.field_70161_v);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static boolean isRiteAllowed() {
/* 150 */     return (Config.instance().allowDeathItemRecoveryRite) && (!Witchery.isDeathChestModInstalled);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public RitePriorIncarnation(int radius, int aoe)
/*     */   {
/* 157 */     this.radius = radius;
/* 158 */     this.aoe = aoe;
/*     */   }
/*     */   
/*     */   public void addSteps(ArrayList<RitualStep> steps, int initialStage)
/*     */   {
/* 163 */     steps.add(new StepPriorIncarnation(this, initialStage));
/*     */   }
/*     */   
/*     */   private static class StepPriorIncarnation extends RitualStep
/*     */   {
/*     */     private final RitePriorIncarnation rite;
/* 169 */     private int stage = 0;
/*     */     
/*     */     public StepPriorIncarnation(RitePriorIncarnation rite, int initialStage) {
/* 172 */       super();
/* 173 */       this.rite = rite;
/* 174 */       this.stage = initialStage;
/*     */     }
/*     */     
/*     */ 
/*     */     public int getCurrentStage()
/*     */     {
/* 180 */       return this.stage;
/*     */     }
/*     */     
/*     */     public RitualStep.Result process(World world, int posX, int posY, int posZ, long ticks, BlockCircle.TileEntityCircle.ActivatedRitual ritual)
/*     */     {
/* 185 */       if ((!RitePriorIncarnation.isRiteAllowed()) || (world.func_82736_K().func_82766_b("keepInventory"))) {
/* 186 */         EntityPlayer player = ritual.getInitiatingPlayer(world);
/* 187 */         if (player != null) {
/* 188 */           ChatUtil.sendTranslated(net.minecraft.util.EnumChatFormatting.RED, player, "witchery.rite.disabled", new Object[0]);
/*     */         }
/* 190 */         return RitualStep.Result.ABORTED_REFUND;
/*     */       }
/*     */       
/* 193 */       if ((this.stage == 0) && (ticks % 20L != 0L)) {
/* 194 */         return RitualStep.Result.STARTING;
/*     */       }
/*     */       
/* 197 */       if (!world.field_72995_K) {
/* 198 */         int r = this.rite.radius;
/* 199 */         AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(posX - r, posY, posZ - r, posX + r, posY + 1, posZ + r);
/*     */         
/* 201 */         boolean found = false;
/* 202 */         for (Object obj : world.func_72872_a(EntityPlayer.class, bounds)) {
/* 203 */           EntityPlayer player = (EntityPlayer)obj;
/* 204 */           if (Coord.distance(player.field_70165_t, player.field_70163_u, player.field_70161_v, posX, posY, posZ) <= r) {
/* 205 */             NBTTagCompound nbt = Infusion.getNBT(player);
/* 206 */             if (Config.instance().traceRites()) {
/* 207 */               Log.instance().debug(String.format("Prior invocation for %s", new Object[] { player.func_70005_c_() }));
/*     */             }
/* 209 */             if ((nbt.func_74764_b("WITCPriIncInv")) && (nbt.func_74764_b("WITCPriIncLocX")) && (nbt.func_74764_b("WITCPriIncLocY")) && (nbt.func_74764_b("WITCPriIncLocZ"))) {
/* 210 */               NBTTagList tagList = nbt.func_150295_c("WITCPriIncInv", 10);
/* 211 */               double x = nbt.func_74769_h("WITCPriIncLocX");
/* 212 */               double y = nbt.func_74769_h("WITCPriIncLocY");
/* 213 */               double z = nbt.func_74769_h("WITCPriIncLocZ");
/* 214 */               double dSq = Coord.distanceSq(posX, posY, posZ, x, y, z);
/* 215 */               if (Config.instance().traceRites()) {
/* 216 */                 Log.instance().debug(String.format("Distance to death %f items %d", new Object[] { Double.valueOf(Math.sqrt(dSq)), Integer.valueOf(tagList.func_74745_c()) }));
/*     */               }
/* 218 */               if ((dSq <= this.rite.aoe * this.rite.aoe) && (tagList.func_74745_c() > 0)) {
/* 219 */                 if (Config.instance().traceRites()) {
/* 220 */                   Log.instance().debug(String.format("Recovering %d items", new Object[] { Integer.valueOf(tagList.func_74745_c()) }));
/*     */                 }
/* 222 */                 for (int i = 0; i < tagList.func_74745_c(); i++) {
/* 223 */                   net.minecraft.nbt.NBTBase baseTag = tagList.func_150305_b(i);
/* 224 */                   if ((baseTag != null) && ((baseTag instanceof NBTTagCompound))) {
/* 225 */                     NBTTagCompound tag = (NBTTagCompound)baseTag;
/* 226 */                     ItemStack stack = ItemStack.func_77949_a(tag);
/* 227 */                     if (stack != null) {
/* 228 */                       if (Config.instance().traceRites()) {
/* 229 */                         Log.instance().debug(String.format(" - Recovered %s", new Object[] { stack.toString() }));
/*     */                       }
/* 231 */                       world.func_72838_d(new EntityItem(world, posX, posY, posZ, stack));
/*     */                     } else {
/* 233 */                       Log.instance().warning("Prior Incarnation stack is null");
/*     */                     }
/*     */                   } else {
/* 236 */                     Log.instance().warning("Prior Incarnation item has incorrect NBT type or is null " + baseTag);
/*     */                   }
/*     */                 }
/*     */                 
/* 240 */                 EntitySkeleton skeleton = new EntitySkeleton(world);
/* 241 */                 skeleton.func_70012_b(posX, posY, posZ, 0.0F, 0.0F);
/* 242 */                 skeleton.func_94058_c(player.func_70005_c_());
/* 243 */                 world.func_72838_d(skeleton);
/*     */                 
/* 245 */                 nbt.func_82580_o("WITCPriIncInv");
/* 246 */                 nbt.func_82580_o("WITCPriIncLocX");
/* 247 */                 nbt.func_82580_o("WITCPriIncLocY");
/* 248 */                 nbt.func_82580_o("WITCPriIncLocZ");
/* 249 */                 found = true;
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */         
/* 255 */         if (found) {
/* 256 */           ParticleEffect.HUGE_EXPLOSION.send(SoundEffect.RANDOM_FIZZ, world, posX, posY, posZ, 3.0D, 3.0D, 16);
/*     */         } else {
/* 258 */           ParticleEffect.SMOKE.send(SoundEffect.NOTE_SNARE, world, posX, posY, posZ, 1.0D, 2.0D, 16);
/*     */         }
/*     */       }
/*     */       
/* 262 */       return RitualStep.Result.COMPLETED;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/ritual/rites/RitePriorIncarnation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */