/*    */ package com.emoniph.witchery.brewing;
/*    */ 
/*    */ import com.emoniph.witchery.util.BlockPosition;
/*    */ import com.emoniph.witchery.util.EntityPosition;
/*    */ import com.emoniph.witchery.util.EntityUtil;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.util.AxisAlignedBB;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.util.MovingObjectPosition;
/*    */ import net.minecraft.util.MovingObjectPosition.MovingObjectType;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.common.util.ForgeDirection;
/*    */ 
/*    */ public class DispersalInstant
/*    */   extends Dispersal
/*    */ {
/*    */   public void onImpactSplashPotion(World world, NBTTagCompound nbtBrew, MovingObjectPosition mop, ModifiersImpact modifiers)
/*    */   {
/* 22 */     double R = 3 + modifiers.extent;
/* 23 */     double R_SQ = R * R;
/*    */     
/*    */ 
/* 26 */     EntityPosition position = modifiers.impactPosition;
/* 27 */     AxisAlignedBB bb = AxisAlignedBB.func_72330_a(position.x - R, position.y - R, position.z - R, position.x + R, position.y + R, position.z + R);
/*    */     
/* 29 */     List<EntityLivingBase> list1 = world.func_72872_a(EntityLivingBase.class, bb);
/*    */     
/* 31 */     for (EntityLivingBase targetEntity : list1) {
/* 32 */       double distanceSq = position.getDistanceSqToEntity(targetEntity);
/*    */       
/* 34 */       if (distanceSq <= R_SQ) {
/* 35 */         boolean directHit = targetEntity == mop.field_72308_g;
/* 36 */         double effectScalingFactor = directHit ? 1.0D : 1.0D - Math.sqrt(distanceSq) / R;
/* 37 */         WitcheryBrewRegistry.INSTANCE.applyToEntity(world, targetEntity, nbtBrew, new ModifiersEffect(effectScalingFactor, 0.5D * effectScalingFactor, !directHit, position, modifiers));
/*    */       }
/*    */     }
/*    */     
/*    */ 
/*    */ 
/* 43 */     if (mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
/* 44 */       WitcheryBrewRegistry.INSTANCE.applyToBlock(world, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d, ForgeDirection.getOrientation(mop.field_72310_e), MathHelper.func_76143_f(R), nbtBrew, new ModifiersEffect(1.0D, 1.0D, false, position, modifiers));
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public String getUnlocalizedName()
/*    */   {
/* 52 */     return "witchery:brew.dispersal.splash";
/*    */   }
/*    */   
/*    */ 
/*    */   public RitualStatus onUpdateRitual(World world, int x, int y, int z, NBTTagCompound nbtBrew, ModifiersRitual modifiers, ModifiersImpact impactModifiers)
/*    */   {
/* 58 */     BlockPosition target = modifiers.getTarget();
/* 59 */     ModifiersEffect effectModifiers = new ModifiersEffect(1.0D, 1.0D, false, new EntityPosition(target), true, modifiers.covenSize, EntityUtil.playerOrFake(world, (EntityPlayer)null));
/*    */     
/* 61 */     WitcheryBrewRegistry.INSTANCE.applyRitualToBlock(world, target.x, target.y, target.z, ForgeDirection.UP, 3 + impactModifiers.extent, nbtBrew, modifiers, effectModifiers);
/*    */     
/* 63 */     return modifiers.getRitualStatus();
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/DispersalInstant.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */