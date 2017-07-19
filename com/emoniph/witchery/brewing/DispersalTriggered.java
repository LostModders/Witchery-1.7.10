/*     */ package com.emoniph.witchery.brewing;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryBlocks;
/*     */ import com.emoniph.witchery.familiar.Familiar;
/*     */ import com.emoniph.witchery.util.BlockProtect;
/*     */ import com.emoniph.witchery.util.BlockUtil;
/*     */ import com.emoniph.witchery.util.Coord;
/*     */ import com.emoniph.witchery.util.EntityPosition;
/*     */ import com.emoniph.witchery.util.EntityUtil;
/*     */ import com.emoniph.witchery.util.ParticleEffect;
/*     */ import com.emoniph.witchery.util.SoundEffect;
/*     */ import cpw.mods.fml.common.eventhandler.SubscribeEvent;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockDoor;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.event.entity.player.PlayerInteractEvent;
/*     */ import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;
/*     */ 
/*     */ public class DispersalTriggered extends Dispersal
/*     */ {
/*     */   public static class EventHooks
/*     */   {
/*     */     @SubscribeEvent
/*     */     public void onPlayerInteract(PlayerInteractEvent event)
/*     */     {
/*  38 */       if ((!event.entityPlayer.field_70170_p.field_72995_K) && ((event.action == PlayerInteractEvent.Action.RIGHT_CLICK_AIR) || (event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK)) && (event.entityPlayer.func_70694_bm() != null) && (event.entityPlayer.func_70694_bm().func_77942_o()) && (event.entityPlayer.func_70694_bm().func_77978_p().func_74764_b("WITCCurse")))
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*  43 */         NBTTagCompound root = event.entityPlayer.func_70694_bm().func_77978_p().func_74775_l("WITCCurse");
/*     */         
/*  45 */         NBTTagCompound tag = root.func_74775_l("Curse");
/*  46 */         int charges = root.func_74762_e("Count");
/*  47 */         long delay = root.func_74763_f("Delay");
/*  48 */         if ((delay == 0L) || (MinecraftServer.func_130071_aq() > delay)) {
/*  49 */           if (charges > 0) {
/*  50 */             WitcheryBrewRegistry.INSTANCE.applyToEntity(event.entityPlayer.field_70170_p, event.entityPlayer, tag, new ModifiersEffect(1.0D, 1.0D, false, new EntityPosition(event.entityPlayer), false, 0, EntityUtil.playerOrFake(event.entityPlayer.field_70170_p, (EntityLivingBase)null)));
/*     */             
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  57 */             ParticleEffect.SPELL_COLORED.send(SoundEffect.RANDOM_POP, event.entityPlayer, 1.0D, 1.0D, 16);
/*  58 */             charges--;
/*     */           }
/*     */           
/*  61 */           if (charges > 0) {
/*  62 */             root.func_74768_a("Count", charges);
/*  63 */             root.func_74772_a("Delay", MinecraftServer.func_130071_aq() + 5000L);
/*     */           } else {
/*  65 */             event.entityPlayer.func_70694_bm().func_77978_p().func_82580_o("WITCCurse");
/*  66 */             if (event.entityPlayer.func_70694_bm().func_77978_p().func_82582_d()) {
/*  67 */               event.entityPlayer.func_70694_bm().func_77982_d(null);
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void onImpactSplashPotion(World world, NBTTagCompound nbtBrew, MovingObjectPosition mop, ModifiersImpact modifiers)
/*     */   {
/*  78 */     Coord coord = new Coord(mop, modifiers.impactPosition, true);
/*  79 */     boolean replaceable = BlockProtect.checkModsForBreakOK(world, coord.x, coord.y, coord.z, modifiers.thrower);
/*     */     
/*  81 */     if (replaceable) {
/*  82 */       Block block = coord.getBlock(world);
/*  83 */       if (block == Blocks.field_150430_aB) {
/*  84 */         Witchery.Blocks.CURSED_BUTTON_STONE.replaceButton(world, coord.x, coord.y, coord.z, modifiers, nbtBrew);
/*     */         
/*  86 */         return; }
/*  87 */       if (block == Blocks.field_150471_bO) {
/*  88 */         Witchery.Blocks.CURSED_BUTTON_WOOD.replaceButton(world, coord.x, coord.y, coord.z, modifiers, nbtBrew);
/*     */         
/*  90 */         return; }
/*  91 */       if (block == Blocks.field_150442_at) {
/*  92 */         Witchery.Blocks.CURSED_LEVER.replaceButton(world, coord.x, coord.y, coord.z, modifiers, nbtBrew);
/*  93 */         return; }
/*  94 */       if (block.hasTileEntity(coord.getBlockMetadata(world))) {
/*  95 */         TileEntityCursedBlock tile = (TileEntityCursedBlock)BlockUtil.getTileEntity(world, coord.x, coord.y, coord.z, TileEntityCursedBlock.class);
/*  96 */         if (tile != null) {
/*  97 */           tile.updateCurse(modifiers, nbtBrew);
/*     */         }
/*     */       }
/*     */       
/* 101 */       coord = new Coord(mop, modifiers.impactPosition, false);
/* 102 */       block = coord.getBlock(world);
/* 103 */       if (block == Blocks.field_150452_aw) {
/* 104 */         Witchery.Blocks.CURSED_WOODEN_PRESSURE_PLATE.replaceButton(world, coord.x, coord.y, coord.z, modifiers, nbtBrew);
/*     */       }
/* 106 */       else if (block == Blocks.field_150456_au) {
/* 107 */         Witchery.Blocks.CURSED_STONE_PRESSURE_PLATE.replaceButton(world, coord.x, coord.y, coord.z, modifiers, nbtBrew);
/*     */       }
/* 109 */       else if (block == Witchery.Blocks.SNOW_PRESSURE_PLATE) {
/* 110 */         Witchery.Blocks.CURSED_SNOW_PRESSURE_PLATE.replaceButton(world, coord.x, coord.y, coord.z, modifiers, nbtBrew);
/*     */       }
/* 112 */       else if (block == Blocks.field_150466_ao) {
/* 113 */         Witchery.Blocks.CURSED_WOODEN_DOOR.replaceButton(world, coord.x, coord.y, coord.z, modifiers, nbtBrew);
/*     */       }
/* 115 */       else if (block.hasTileEntity(coord.getBlockMetadata(world))) {
/* 116 */         int y = coord.y;
/* 117 */         if (block == Witchery.Blocks.CURSED_WOODEN_DOOR) {
/* 118 */           int i1 = ((BlockDoor)Blocks.field_150466_ao).func_150012_g(world, coord.x, coord.y, coord.z);
/* 119 */           if ((i1 & 0x8) != 0) {
/* 120 */             y--;
/*     */           }
/*     */         }
/* 123 */         TileEntityCursedBlock tile = (TileEntityCursedBlock)BlockUtil.getTileEntity(world, coord.x, y, coord.z, TileEntityCursedBlock.class);
/* 124 */         if (tile != null) {
/* 125 */           tile.updateCurse(modifiers, nbtBrew);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public String getUnlocalizedName()
/*     */   {
/* 133 */     return "witchery:brew.dispersal.triggered";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public RitualStatus onUpdateRitual(World world, int x, int y, int z, NBTTagCompound nbtBrew, ModifiersRitual modifiers, ModifiersImpact impactModifiers)
/*     */   {
/* 140 */     AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(x, y + 1, z, x + 1, y + 2, z + 1);
/* 141 */     List<EntityItem> items = world.func_72872_a(EntityItem.class, bounds);
/* 142 */     for (EntityItem item : items) {
/* 143 */       ItemStack stack = item.func_92059_d();
/* 144 */       if (stack != null) {
/* 145 */         if (stack.field_77994_a > 1) {
/* 146 */           stack = stack.func_77979_a(1);
/* 147 */           EntityItem newEntity = new EntityItem(item.field_70170_p, item.field_70165_t, item.field_70163_u, item.field_70161_v, stack);
/*     */           
/* 149 */           world.func_72838_d(newEntity);
/*     */         }
/* 151 */         if (!stack.func_77942_o()) {
/* 152 */           stack.func_77982_d(new NBTTagCompound());
/*     */         }
/* 154 */         NBTTagCompound nbtRoot = stack.func_77978_p();
/*     */         
/* 156 */         AxisAlignedBB playerBounds = bounds.func_72314_b(3.0D, 3.0D, 3.0D);
/* 157 */         List<EntityPlayer> players = world.func_72872_a(EntityPlayer.class, playerBounds);
/* 158 */         boolean catNear = false;
/* 159 */         for (EntityPlayer player : players) {
/* 160 */           if (Familiar.hasActiveCurseMasteryFamiliar(player)) {
/* 161 */             catNear = true;
/* 162 */             break;
/*     */           }
/*     */         }
/*     */         
/*     */ 
/* 167 */         if ((nbtRoot.func_74764_b("WITCCurse")) && (((NBTTagCompound)nbtRoot.func_74775_l("WITCCurse").func_74781_a("Curse")).func_150295_c("Items", 10).equals(nbtBrew.func_150295_c("Items", 10))))
/*     */         {
/*     */ 
/* 170 */           NBTTagCompound nbtCurse = nbtRoot.func_74775_l("WITCCurse");
/* 171 */           nbtCurse.func_74768_a("Count", nbtCurse.func_74762_e("Count") + (catNear ? 2 : 1));
/*     */         } else {
/* 173 */           NBTTagCompound nbtCurse = new NBTTagCompound();
/* 174 */           nbtCurse.func_74782_a("Curse", nbtBrew.func_74737_b());
/* 175 */           nbtCurse.func_74768_a("Count", catNear ? 2 : 1);
/* 176 */           nbtRoot.func_74782_a("WITCCurse", nbtCurse);
/*     */         }
/* 178 */         ParticleEffect.EXPLODE.send(SoundEffect.RANDOM_ORB, item, 0.5D, 0.5D, 16);
/*     */         
/* 180 */         return RitualStatus.COMPLETE;
/*     */       }
/*     */     }
/*     */     
/* 184 */     return RitualStatus.FAILED;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/DispersalTriggered.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */