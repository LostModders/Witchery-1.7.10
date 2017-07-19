/*    */ package com.emoniph.witchery.infusion.infusions.symbols;
/*    */ 
/*    */ import com.emoniph.witchery.entity.EntitySpellEffect;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.util.MovingObjectPosition;
/*    */ import net.minecraft.util.Vec3;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public abstract class SymbolEffectProjectile
/*    */   extends SymbolEffect
/*    */ {
/* 14 */   private float size = 1.0F;
/* 15 */   private int color = 16711680;
/* 16 */   private int timetolive = -1;
/*    */   
/*    */   public SymbolEffectProjectile(int effectID, String unlocalisedName) {
/* 19 */     super(effectID, unlocalisedName);
/*    */   }
/*    */   
/*    */   public SymbolEffectProjectile(int effectID, String unlocalisedName, int spellCost, boolean curse, boolean fallsToEarth, String knowledgeKey, int cooldown) {
/* 23 */     super(effectID, unlocalisedName, spellCost, curse, fallsToEarth, knowledgeKey, cooldown);
/*    */   }
/*    */   
/*    */   public SymbolEffectProjectile(int effectID, String unlocalisedName, int spellCost, boolean curse, boolean fallsToEarth, String knowledgeKey, int cooldown, boolean isVisible) {
/* 27 */     super(effectID, unlocalisedName, spellCost, curse, fallsToEarth, knowledgeKey, cooldown, isVisible);
/*    */   }
/*    */   
/*    */   public SymbolEffectProjectile setColor(int color) {
/* 31 */     this.color = color;
/* 32 */     return this;
/*    */   }
/*    */   
/*    */   public SymbolEffectProjectile setSize(float size) {
/* 36 */     this.size = size;
/* 37 */     return this;
/*    */   }
/*    */   
/*    */   public SymbolEffectProjectile setTimeToLive(int ticks) {
/* 41 */     this.timetolive = ticks;
/* 42 */     return this;
/*    */   }
/*    */   
/*    */   public int getColor() {
/* 46 */     return this.color;
/*    */   }
/*    */   
/*    */   public float getSize() {
/* 50 */     return this.size;
/*    */   }
/*    */   
/*    */   public void perform(World world, EntityPlayer player, int effectLevel)
/*    */   {
/* 55 */     world.func_72889_a((EntityPlayer)null, 1008, (int)player.field_70165_t, (int)player.field_70163_u, (int)player.field_70161_v, 0);
/* 56 */     float f = 1.0F;
/* 57 */     double motionX = -MathHelper.func_76126_a(player.field_70177_z / 180.0F * 3.1415927F) * MathHelper.func_76134_b(player.field_70125_A / 180.0F * 3.1415927F) * f;
/* 58 */     double motionZ = MathHelper.func_76134_b(player.field_70177_z / 180.0F * 3.1415927F) * MathHelper.func_76134_b(player.field_70125_A / 180.0F * 3.1415927F) * f;
/* 59 */     double motionY = -MathHelper.func_76126_a(player.field_70125_A / 180.0F * 3.1415927F) * f;
/* 60 */     EntitySpellEffect entity = new EntitySpellEffect(world, player, motionX, motionY, motionZ, this, effectLevel);
/* 61 */     if (this.timetolive > 0) {
/* 62 */       entity.setLifeTime(this.timetolive);
/*    */     }
/* 64 */     entity.func_70012_b(player.field_70165_t, player.field_70163_u + player.func_70047_e(), player.field_70161_v, entity.field_70177_z, entity.field_70125_A);
/* 65 */     entity.func_70107_b(player.field_70165_t, player.field_70163_u + player.func_70047_e(), player.field_70161_v);
/* 66 */     double d6 = MathHelper.func_76133_a(motionX * motionX + motionY * motionY + motionZ * motionZ);
/* 67 */     entity.accelerationX = (motionX / d6 * 0.3D);
/* 68 */     entity.accelerationY = (motionY / d6 * 0.3D);
/* 69 */     entity.accelerationZ = (motionZ / d6 * 0.3D);
/* 70 */     double d8 = 1.5D;
/* 71 */     Vec3 vec3 = player.func_70676_i(1.0F);
/* 72 */     entity.field_70165_t = (player.field_70165_t + vec3.field_72450_a * 1.5D);
/* 73 */     entity.field_70163_u = (player.field_70163_u + player.eyeHeight - 0.10000000149011612D + vec3.field_72448_b * 1.5D);
/* 74 */     entity.field_70161_v = (player.field_70161_v + vec3.field_72449_c * 1.5D);
/* 75 */     world.func_72838_d(entity);
/*    */   }
/*    */   
/*    */   public abstract void onCollision(World paramWorld, EntityLivingBase paramEntityLivingBase, MovingObjectPosition paramMovingObjectPosition, EntitySpellEffect paramEntitySpellEffect);
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/infusion/infusions/symbols/SymbolEffectProjectile.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */