/*     */ package com.emoniph.witchery.entity.ai;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.entity.EntityGoblin;
/*     */ import com.emoniph.witchery.util.BlockUtil;
/*     */ import com.mojang.authlib.GameProfile;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Random;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemPickaxe;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.crafting.FurnaceRecipes;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.MovingObjectPosition.MovingObjectType;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldServer;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.event.world.BlockEvent.BreakEvent;
/*     */ import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
/*     */ import net.minecraftforge.oredict.OreDictionary;
/*     */ 
/*     */ public class EntityAIDigBlocks extends net.minecraft.entity.ai.EntityAIBase
/*     */ {
/*     */   protected final EntityGoblin entity;
/*     */   protected final double range;
/*     */   protected final double kobolditeChance;
/*     */   
/*     */   public EntityAIDigBlocks(EntityGoblin entity, double range, double kobolditeChance)
/*     */   {
/*  40 */     this.entity = entity;
/*  41 */     this.range = range;
/*  42 */     this.kobolditeChance = kobolditeChance;
/*  43 */     func_75248_a(7);
/*     */   }
/*     */   
/*  46 */   public static final GameProfile NORMAL_MINER_PROFILE = new GameProfile(UUID.fromString("AB06ACB0-0CDB-11E4-9191-0800200C9A66"), "[Minecraft]");
/*     */   
/*  48 */   public static final GameProfile KOBOLDITE_MINER_PROFILE = new GameProfile(UUID.fromString("24818AE0-0CDE-11E4-9191-0800200C9A66"), "[Minecraft]");
/*     */   
/*     */ 
/*  51 */   MovingObjectPosition mop = null;
/*  52 */   int failedChecks = 0;
/*     */   
/*     */   public boolean func_75250_a()
/*     */   {
/*  56 */     if ((this.entity != null) && (!this.entity.isWorshipping()) && (this.entity.func_70694_bm() != null) && ((this.entity.func_70694_bm().func_77973_b() instanceof ItemPickaxe)) && (this.entity.func_110167_bD()) && (this.entity.field_70170_p.field_73012_v.nextInt(2) == 0))
/*     */     {
/*     */ 
/*  59 */       MovingObjectPosition mop = raytraceBlocks(this.entity.field_70170_p, this.entity, true, this.failedChecks == 15 ? 1.0D : 4.0D, this.failedChecks == 15);
/*  60 */       if ((mop == null) || (mop.field_72313_a != MovingObjectPosition.MovingObjectType.BLOCK)) {
/*  61 */         this.failedChecks += 1;
/*  62 */         mop = null;
/*  63 */         return false;
/*     */       }
/*     */       
/*  66 */       Block block = BlockUtil.getBlock(this.entity.field_70170_p, mop);
/*  67 */       if (isMineable(block, this.entity.field_70170_p, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d)) {
/*  68 */         this.failedChecks = 0;
/*  69 */         this.mop = mop;
/*  70 */         return true;
/*     */       }
/*  72 */       this.mop = null;
/*  73 */       this.failedChecks += 1;
/*  74 */       return false;
/*     */     }
/*     */     
/*  77 */     return false;
/*     */   }
/*     */   
/*     */   private boolean isMineable(Block block, World world, int x, int y, int z)
/*     */   {
/*  82 */     if ((block.func_149688_o() != Material.field_151576_e) && (block.func_149688_o() != Material.field_151595_p) && (block.func_149688_o() != Material.field_151577_b) && (block.func_149688_o() != Material.field_151597_y) && (block.func_149688_o() != Material.field_151578_c))
/*     */     {
/*  84 */       return false;
/*     */     }
/*     */     
/*  87 */     if (block.func_149712_f(world, x, y, z) < 0.0F) {
/*  88 */       return false;
/*     */     }
/*     */     
/*     */ 
/*  92 */     return true;
/*     */   }
/*     */   
/*     */   private static MovingObjectPosition raytraceBlocks(World world, EntityLiving player, boolean collisionFlag, double reachDistance, boolean down)
/*     */   {
/*  97 */     Vec3 playerPosition = Vec3.func_72443_a(player.field_70165_t, player.field_70163_u + player.func_70047_e(), player.field_70161_v);
/*     */     
/*  99 */     float rotationYaw = world.field_73012_v.nextInt(360);
/* 100 */     player.field_70177_z = rotationYaw;
/* 101 */     float rotationPitch = down ? 90.0F : 0.0F;
/* 102 */     float f1 = MathHelper.func_76134_b(-rotationYaw * 0.017453292F - 3.1415927F);
/* 103 */     float f2 = MathHelper.func_76126_a(-rotationYaw * 0.017453292F - 3.1415927F);
/* 104 */     float f3 = -MathHelper.func_76134_b(-rotationPitch * 0.017453292F);
/* 105 */     float f4 = MathHelper.func_76126_a(-rotationPitch * 0.017453292F);
/* 106 */     Vec3 playerLook = Vec3.func_72443_a(f2 * f3, f4, f1 * f3);
/*     */     
/* 108 */     Vec3 playerViewOffset = Vec3.func_72443_a(playerPosition.field_72450_a + playerLook.field_72450_a * reachDistance, playerPosition.field_72448_b + playerLook.field_72448_b * reachDistance, playerPosition.field_72449_c + playerLook.field_72449_c * reachDistance);
/*     */     
/*     */ 
/* 111 */     return world.func_147447_a(playerPosition, playerViewOffset, collisionFlag, !collisionFlag, false);
/*     */   }
/*     */   
/*     */   public void func_75249_e()
/*     */   {
/* 116 */     double SPEED = 0.6D;
/* 117 */     this.entity.func_70661_as().func_75492_a(this.mop.field_72311_b, this.mop.field_72312_c, this.mop.field_72309_d, 0.6D);
/*     */   }
/*     */   
/*     */   public boolean func_75253_b()
/*     */   {
/* 122 */     return (this.entity != null) && (!this.entity.isWorshipping()) && (this.entity.func_70694_bm() != null) && ((this.entity.func_70694_bm().func_77973_b() instanceof ItemPickaxe)) && (this.entity.func_110167_bD()) && (this.mop != null);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_75251_c()
/*     */   {
/* 128 */     if (this.entity.isWorking()) {
/* 129 */       this.entity.setWorking(false);
/*     */     }
/*     */   }
/*     */   
/* 133 */   private int waitTimer = 60;
/*     */   
/*     */   public void func_75246_d()
/*     */   {
/* 137 */     double DROP_RANGE = 2.5D;
/* 138 */     double DROP_RANGE_SQ = 6.25D;
/* 139 */     double dist = this.entity.func_70092_e(this.mop.field_72311_b + 0.5D, this.mop.field_72312_c + 0.5D, this.mop.field_72309_d + 0.5D);
/* 140 */     boolean retry = true;
/* 141 */     if (dist <= 6.25D) {
/* 142 */       if (!this.entity.isWorking()) {
/* 143 */         this.entity.setWorking(true);
/*     */       }
/* 145 */       if (--this.waitTimer == 0) {
/* 146 */         if (!tryHarvestBlock(this.entity.field_70170_p, this.mop.field_72311_b, this.mop.field_72312_c, this.mop.field_72309_d, this.entity)) {
/* 147 */           retry = false;
/*     */         }
/*     */         
/* 150 */         this.mop = null;
/* 151 */         this.waitTimer = getNextHarvestDelay();
/*     */       }
/* 153 */     } else if (this.entity.func_70661_as().func_75500_f()) {
/* 154 */       this.mop = null;
/* 155 */       this.waitTimer = getNextHarvestDelay();
/*     */       
/* 157 */       if (this.entity.isWorking()) {
/* 158 */         this.entity.setWorking(false);
/*     */       }
/*     */     }
/* 161 */     else if (!this.entity.isWorking()) {
/* 162 */       this.entity.setWorking(true);
/*     */     }
/*     */     
/*     */ 
/* 166 */     if ((this.mop == null) && (retry) && (this.entity.field_70170_p.field_73012_v.nextInt(20) != 0)) {
/* 167 */       MovingObjectPosition mop = raytraceBlocks(this.entity.field_70170_p, this.entity, true, 4.0D, false);
/* 168 */       if ((mop != null) && (mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK))
/*     */       {
/* 170 */         Block block = BlockUtil.getBlock(this.entity.field_70170_p, mop);
/* 171 */         if (isMineable(block, this.entity.field_70170_p, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d)) {
/* 172 */           this.mop = mop;
/* 173 */           this.waitTimer = getNextHarvestDelay();
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private int getNextHarvestDelay() {
/* 180 */     return isHoldingKobolditePick(this.entity) ? 4 : 60;
/*     */   }
/*     */   
/*     */   private static boolean isHoldingKobolditePick(EntityLivingBase entity) {
/* 184 */     return (entity.func_70694_bm() != null) && (entity.func_70694_bm().func_77973_b() == Witchery.Items.KOBOLDITE_PICKAXE);
/*     */   }
/*     */   
/*     */   public static boolean tryHarvestBlock(World world, int par1, int par2, int par3, EntityLivingBase harvester)
/*     */   {
/* 189 */     boolean kobolditePick = isHoldingKobolditePick(harvester);
/* 190 */     EntityPlayer minerPlayer = net.minecraftforge.common.util.FakePlayerFactory.get((WorldServer)world, kobolditePick ? KOBOLDITE_MINER_PROFILE : NORMAL_MINER_PROFILE);
/*     */     
/* 192 */     return tryHarvestBlock(world, par1, par2, par3, harvester, minerPlayer);
/*     */   }
/*     */   
/*     */   public static boolean tryHarvestBlock(World world, int par1, int par2, int par3, EntityLivingBase harvester, EntityPlayer minerPlayer) {
/* 196 */     Block block = world.func_147439_a(par1, par2, par3);
/* 197 */     int blockMeta = world.func_72805_g(par1, par2, par3);
/* 198 */     BlockEvent.BreakEvent event = new BlockEvent.BreakEvent(par1, par2, par3, world, block, blockMeta, minerPlayer);
/* 199 */     event.setCanceled(false);
/* 200 */     MinecraftForge.EVENT_BUS.post(event);
/*     */     
/* 202 */     if (event.isCanceled()) {
/* 203 */       return false;
/*     */     }
/* 205 */     ItemStack stack = harvester.func_70694_bm();
/* 206 */     if ((stack != null) && (stack.func_77973_b().onBlockStartBreak(stack, par1, par2, par3, minerPlayer))) {
/* 207 */       return false;
/*     */     }
/*     */     
/* 210 */     world.func_72926_e(2001, par1, par2, par3, Block.func_149682_b(block) + (blockMeta << 12));
/* 211 */     boolean canHarvest = false;
/*     */     
/* 213 */     if (block.func_149712_f(world, par1, par2, par3) >= 0.0F) {
/* 214 */       if (block.func_149688_o().func_76229_l()) {
/* 215 */         canHarvest = true;
/*     */       }
/*     */       
/* 218 */       String tool = block.getHarvestTool(blockMeta);
/*     */       
/* 220 */       int toolLevel = stack != null ? stack.func_77973_b().getHarvestLevel(stack, tool) : 0;
/* 221 */       if (toolLevel < 0) {
/* 222 */         canHarvest = true;
/*     */       }
/*     */       
/* 225 */       if (toolLevel >= block.getHarvestLevel(blockMeta)) {
/* 226 */         canHarvest = true;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 232 */     if (canHarvest) {
/* 233 */       canHarvest = removeBlock(world, par1, par2, par3, minerPlayer);
/* 234 */       if (canHarvest) {
/* 235 */         block.func_149636_a(world, minerPlayer, par1, par2, par3, blockMeta);
/*     */       }
/*     */     }
/*     */     
/* 239 */     return canHarvest;
/*     */   }
/*     */   
/*     */   private static boolean removeBlock(World world, int x, int y, int z, EntityPlayer player)
/*     */   {
/* 244 */     Block block = world.func_147439_a(x, y, z);
/* 245 */     int metadata = world.func_72805_g(x, y, z);
/* 246 */     block.func_149681_a(world, x, y, z, metadata, player);
/* 247 */     boolean flag = block.removedByPlayer(world, player, x, y, z, true);
/*     */     
/* 249 */     if (flag) {
/* 250 */       block.func_149664_b(world, x, y, z, metadata);
/*     */     }
/*     */     
/* 253 */     return flag;
/*     */   }
/*     */   
/*     */   public static void onHarvestDrops(EntityPlayer harvester, BlockEvent.HarvestDropsEvent event) {
/* 257 */     if ((harvester != null) && (!harvester.field_70170_p.field_72995_K) && (!event.isCanceled()) && (
/* 258 */       (isEqual(harvester.func_146103_bH(), KOBOLDITE_MINER_PROFILE)) || (isEqual(harvester.func_146103_bH(), NORMAL_MINER_PROFILE)))) {
/* 259 */       boolean hasKobolditePick = isEqual(harvester.func_146103_bH(), KOBOLDITE_MINER_PROFILE);
/* 260 */       ArrayList<ItemStack> newDrops = new ArrayList();
/* 261 */       double kobolditeChance = hasKobolditePick ? 0.02D : 0.01D;
/* 262 */       for (ItemStack drop : event.drops) {
/* 263 */         int[] oreIDs = OreDictionary.getOreIDs(drop);
/* 264 */         boolean addOriginal = true;
/* 265 */         if (oreIDs.length > 0) {
/* 266 */           String oreName = OreDictionary.getOreName(oreIDs[0]);
/* 267 */           if ((oreName != null) && (oreName.startsWith("ore"))) {
/* 268 */             ItemStack smeltedDrop = FurnaceRecipes.func_77602_a().func_151395_a(drop);
/*     */             
/* 270 */             if ((smeltedDrop != null) && (hasKobolditePick) && (harvester.field_70170_p.field_73012_v.nextDouble() < 0.5D)) {
/* 271 */               addOriginal = false;
/* 272 */               newDrops.add(smeltedDrop.func_77946_l());
/* 273 */               newDrops.add(smeltedDrop.func_77946_l());
/* 274 */               if (harvester.field_70170_p.field_73012_v.nextDouble() < 0.25D) {
/* 275 */                 newDrops.add(smeltedDrop.func_77946_l());
/*     */               }
/*     */             }
/*     */             
/* 279 */             kobolditeChance = hasKobolditePick ? 0.08D : 0.05D;
/*     */           }
/*     */         }
/* 282 */         if (addOriginal) {
/* 283 */           newDrops.add(drop);
/*     */         }
/*     */       }
/* 286 */       event.drops.clear();
/* 287 */       for (ItemStack newDrop : newDrops) {
/* 288 */         event.drops.add(newDrop);
/*     */       }
/* 290 */       if ((kobolditeChance > 0.0D) && (harvester.field_70170_p.field_73012_v.nextDouble() < kobolditeChance)) {
/* 291 */         event.drops.add(Witchery.Items.GENERIC.itemKobolditeDust.createStack());
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private static boolean isEqual(GameProfile a, GameProfile b)
/*     */   {
/* 298 */     if ((a == null) || (b == null) || (a.getId() == null) || (b.getId() == null)) {
/* 299 */       return false;
/*     */     }
/*     */     
/* 302 */     return a.getId().equals(b.getId());
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/entity/ai/EntityAIDigBlocks.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */