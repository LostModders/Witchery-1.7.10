/*    */ package com.emoniph.witchery.entity;
/*    */ 
/*    */ import com.emoniph.witchery.Witchery;
/*    */ import com.emoniph.witchery.brewing.potions.WitcheryPotions;
/*    */ import com.emoniph.witchery.util.CreatureUtil;
/*    */ import com.emoniph.witchery.util.SoundEffect;
/*    */ import net.minecraft.entity.passive.EntityVillager;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class EntityVillagerWere extends EntityVillager
/*    */ {
/*    */   private boolean infectious;
/*    */   
/*    */   public EntityVillagerWere(World world)
/*    */   {
/* 17 */     this(world, 0, false);
/*    */   }
/*    */   
/*    */   public EntityVillagerWere(World world, int profession, boolean infectious) {
/* 21 */     super(world, profession);
/* 22 */     this.infectious = infectious;
/*    */   }
/*    */   
/*    */   public void func_70014_b(NBTTagCompound nbtRoot)
/*    */   {
/* 27 */     super.func_70014_b(nbtRoot);
/* 28 */     nbtRoot.func_74757_a("Infectious", this.infectious);
/*    */   }
/*    */   
/*    */   public void func_70037_a(NBTTagCompound nbtRoot)
/*    */   {
/* 33 */     super.func_70037_a(nbtRoot);
/* 34 */     this.infectious = nbtRoot.func_74767_n("Infectious");
/*    */   }
/*    */   
/*    */   protected void func_70619_bc()
/*    */   {
/* 39 */     super.func_70619_bc();
/* 40 */     if ((!this.field_70170_p.field_72995_K) && 
/* 41 */       (this.field_70173_aa % 100 == 3) && (!func_70631_g_()) && (CreatureUtil.isFullMoon(this.field_70170_p)) && (!func_70644_a(Witchery.Potions.WOLFSBANE)))
/*    */     {
/* 43 */       convertToWolfman();
/*    */     }
/*    */   }
/*    */   
/*    */   protected void convertToWolfman()
/*    */   {
/* 49 */     EntityWolfman entity = new EntityWolfman(this.field_70170_p);
/* 50 */     if (this.infectious) {
/* 51 */       entity.setInfectious();
/*    */     }
/*    */     
/* 54 */     entity.setFormerProfession(func_70946_n(), this.field_70956_bz, this.field_70963_i);
/*    */     
/* 56 */     entity.func_110163_bv();
/* 57 */     entity.func_82149_j(this);
/* 58 */     entity.func_110161_a(null);
/* 59 */     this.field_70170_p.func_72900_e(this);
/* 60 */     this.field_70170_p.func_72838_d(entity);
/* 61 */     this.field_70170_p.func_72889_a(null, 1017, (int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v, 0);
/* 62 */     SoundEffect.WITCHERY_MOB_WOLFMAN_HOWL.playAt(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v);
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/entity/EntityVillagerWere.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */