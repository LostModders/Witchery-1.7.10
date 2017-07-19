/*    */ package com.emoniph.witchery.brewing;
/*    */ 
/*    */ import com.emoniph.witchery.Witchery;
/*    */ import com.emoniph.witchery.util.BlockActionCircle;
/*    */ import com.emoniph.witchery.util.BlockPosition;
/*    */ import com.emoniph.witchery.util.BlockUtil;
/*    */ import com.emoniph.witchery.util.Coord;
/*    */ import com.emoniph.witchery.util.EntityPosition;
/*    */ import com.emoniph.witchery.util.EntityUtil;
/*    */ import com.emoniph.witchery.util.ParticleEffect;
/*    */ import com.emoniph.witchery.util.SoundEffect;
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.server.MinecraftServer;
/*    */ import net.minecraft.util.MovingObjectPosition;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.common.util.ForgeDirection;
/*    */ 
/*    */ public class DispersalGas extends Dispersal
/*    */ {
/*    */   public void onImpactSplashPotion(World world, NBTTagCompound nbtBrew, MovingObjectPosition mop, ModifiersImpact modifiers)
/*    */   {
/* 27 */     Coord coord = new Coord(mop, modifiers.impactPosition, true);
/* 28 */     boolean replaceable = BlockUtil.isReplaceableBlock(world, coord.x, coord.y, coord.z, modifiers.thrower);
/*    */     
/* 30 */     if (replaceable) {
/* 31 */       coord.setBlock(world, Witchery.Blocks.BREW_GAS);
/* 32 */       TileEntityBrewFluid gas = (TileEntityBrewFluid)coord.getTileEntity(world, TileEntityBrewFluid.class);
/* 33 */       if (gas != null) {
/* 34 */         gas.initalise(modifiers, nbtBrew);
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   public String getUnlocalizedName()
/*    */   {
/* 41 */     return "witchery:brew.dispersal.gas";
/*    */   }
/*    */   
/*    */ 
/*    */   public RitualStatus onUpdateRitual(World world, int x, int y, int z, final NBTTagCompound nbtBrew, final ModifiersRitual modifiers, ModifiersImpact impactModifiers)
/*    */   {
/* 47 */     BlockPosition target = modifiers.getTarget();
/* 48 */     World targetWorld = target.getWorld(MinecraftServer.func_71276_C());
/*    */     
/* 50 */     int height = 8;
/* 51 */     boolean blackMagic = false;
/*    */     
/* 53 */     new BlockActionCircle()
/*    */     {
/*    */       public void onBlock(World world, int x, int y, int z) {
/* 56 */         BlockPosition coords = null;
/* 57 */         for (int i = 0; i < 8; i++)
/*    */         {
/* 59 */           if ((world.func_147439_a(x, y + i, z).func_149688_o() != Material.field_151579_a) && (world.func_147437_c(x, y + i + 1, z)))
/*    */           {
/* 61 */             coords = new BlockPosition(world, x, y, z);
/* 62 */             break;
/*    */           }
/* 64 */           if ((i > 0) && (world.func_147439_a(x, y - i, z).func_149688_o() != Material.field_151579_a) && (world.func_147437_c(x, y - i + 1, z)))
/*    */           {
/* 66 */             coords = new BlockPosition(world, x, y, z);
/* 67 */             break; } }
/*    */         EntityPlayer player;
/*    */         ModifiersEffect effectModifiers;
/* 70 */         if (coords != null) {
/* 71 */           DispersalGas.showSpellParticles(world, coords.x, coords.y, coords.z, false);
/* 72 */           player = EntityUtil.playerOrFake(world, (EntityPlayer)null);
/* 73 */           effectModifiers = new ModifiersEffect(0.0D, 1.0D, false, new EntityPosition(coords), true, modifiers.covenSize, player);
/*    */           
/* 75 */           if (world.field_73012_v.nextDouble() < 0.01D) {
/* 76 */             WitcheryBrewRegistry.INSTANCE.applyToBlock(world, coords.x, coords.y, coords.z, ForgeDirection.UP, 1, nbtBrew, effectModifiers);
/*    */           }
/*    */           
/* 79 */           java.util.List<EntityLivingBase> entities = EntityUtil.getEntitiesInRadius(EntityLivingBase.class, world, coords.x, coords.y, coords.z, 1.5D);
/*    */           
/* 81 */           for (EntityLivingBase entity : entities) {
/* 82 */             effectModifiers = new ModifiersEffect(1.0D, 1.0D, false, new EntityPosition(coords), true, modifiers.covenSize, player);
/*    */             
/* 84 */             WitcheryBrewRegistry.INSTANCE.applyToEntity(world, entity, nbtBrew, effectModifiers); } } } }.processHollowCircle(targetWorld, target.x, target.y, target.z, modifiers.pulses);
/*    */     
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 90 */     return modifiers.pulses < 8 + impactModifiers.extent * 8 ? RitualStatus.ONGOING : RitualStatus.COMPLETE;
/*    */   }
/*    */   
/*    */   private static void showSpellParticles(World world, int x, int y, int z, boolean blackMagic) {
/* 94 */     if (blackMagic) {
/* 95 */       ParticleEffect.MOB_SPELL.send(SoundEffect.NONE, world, 0.5D + x, y + 1, 0.5D + z, 1.0D, 1.0D, 16);
/*    */     } else {
/* 97 */       ParticleEffect.SPELL.send(SoundEffect.NONE, world, 0.5D + x, y + 1, 0.5D + z, 1.0D, 1.0D, 16);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/DispersalGas.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */