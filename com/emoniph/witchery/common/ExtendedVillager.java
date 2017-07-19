/*     */ package com.emoniph.witchery.common;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.brewing.potions.PotionResizing;
/*     */ import com.emoniph.witchery.network.PacketPipeline;
/*     */ import com.emoniph.witchery.util.TimeUtil;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.passive.EntityVillager;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EntityDamageSource;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class ExtendedVillager implements net.minecraftforge.common.IExtendedEntityProperties
/*     */ {
/*     */   private static final String EXT_PROP_NAME = "WitcheryExtendedVillager";
/*     */   private final EntityVillager villager;
/*     */   
/*     */   public static final void register(EntityVillager villager)
/*     */   {
/*  23 */     villager.registerExtendedProperties("WitcheryExtendedVillager", new ExtendedVillager(villager));
/*     */   }
/*     */   
/*     */   public static final ExtendedVillager get(EntityVillager villager) {
/*  27 */     return (ExtendedVillager)villager.getExtendedProperties("WitcheryExtendedVillager");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public ExtendedVillager(EntityVillager villager)
/*     */   {
/*  34 */     this.villager = villager;
/*     */   }
/*     */   
/*     */   public EntityVillager getVillager() {
/*  38 */     return this.villager;
/*     */   }
/*     */   
/*     */   public void saveNBTData(NBTTagCompound compound)
/*     */   {
/*  43 */     NBTTagCompound props = new NBTTagCompound();
/*  44 */     props.func_74768_a("Blood", this.blood);
/*     */     
/*     */ 
/*     */ 
/*  48 */     compound.func_74782_a("WitcheryExtendedVillager", props);
/*     */   }
/*     */   
/*     */   public void loadNBTData(NBTTagCompound compound)
/*     */   {
/*  53 */     if (compound.func_74764_b("WitcheryExtendedVillager")) {
/*  54 */       NBTTagCompound props = (NBTTagCompound)compound.func_74781_a("WitcheryExtendedVillager");
/*  55 */       this.blood = net.minecraft.util.MathHelper.func_76125_a(props.func_74762_e("Blood"), 0, 500);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void init(Entity entity, World world) {}
/*     */   
/*     */ 
/*     */ 
/*  66 */   private int blood = 500;
/*     */   
/*     */   public void setBlood(int blood) {
/*  69 */     if (this.blood != blood) {
/*  70 */       this.blood = Math.max(Math.min(blood, 500), 0);
/*  71 */       sync();
/*     */     } }
/*     */   
/*     */   private boolean sleeping;
/*     */   private int sleepingTicks;
/*  76 */   public int takeBlood(int quantity, EntityLivingBase player) { PotionEffect potionEffect = this.villager.func_70660_b(Witchery.Potions.PARALYSED);
/*  77 */     boolean isKnockedOut = (isSleeping()) || ((potionEffect != null) && (potionEffect.func_76458_c() >= 4));
/*  78 */     if (!isKnockedOut) {
/*  79 */       quantity = (int)Math.ceil(0.66F * quantity);
/*     */     }
/*  81 */     int remainder = Math.max(this.blood - quantity, 0);
/*  82 */     int taken = this.blood - remainder;
/*  83 */     setBlood(remainder);
/*  84 */     if ((player instanceof net.minecraft.entity.player.EntityPlayer)) {
/*  85 */       if (this.blood < (int)Math.ceil(250.0D)) {
/*  86 */         this.villager.func_70097_a(new EntityDamageSource(DamageSource.field_76376_m.func_76355_l(), player), 1.3F);
/*  87 */       } else if (!isKnockedOut) {
/*  88 */         this.villager.func_70097_a(new EntityDamageSource(DamageSource.field_76376_m.func_76355_l(), player), 0.1F);
/*     */       }
/*     */     }
/*  91 */     return taken;
/*     */   }
/*     */   
/*     */   public void giveBlood(int quantity) {
/*  95 */     if (this.blood < 500) {
/*  96 */       setBlood(this.blood + quantity);
/*     */     }
/*     */   }
/*     */   
/*     */   public int getBlood() {
/* 101 */     return this.blood;
/*     */   }
/*     */   
/*     */   public boolean synced;
/*     */   private boolean trySync;
/*     */   public void setSleeping(boolean sleeping)
/*     */   {
/* 108 */     if (this.sleeping != sleeping) {
/* 109 */       this.sleeping = sleeping;
/* 110 */       if (this.sleeping) {
/* 111 */         PotionResizing.setEntitySize(this.villager, 0.8F, 1.1F);
/*     */       } else {
/* 113 */         PotionResizing.setEntitySize(this.villager, 0.6F, 1.8F);
/* 114 */         if (this.sleepingTicks >= TimeUtil.minsToTicks(2)) {
/* 115 */           this.villager.func_70606_j(this.villager.func_110138_aP());
/*     */         }
/*     */         
/* 118 */         if (this.sleepingTicks > TimeUtil.minsToTicks(1)) {
/* 119 */           int blops = this.sleepingTicks / TimeUtil.minsToTicks(1);
/* 120 */           giveBlood(50 * blops);
/*     */         }
/*     */       }
/* 123 */       this.sleepingTicks = 0;
/* 124 */       sync();
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean isSleeping() {
/* 129 */     return this.sleeping;
/*     */   }
/*     */   
/*     */   public void incrementSleepingTicks() {
/* 133 */     this.sleepingTicks += 1;
/*     */   }
/*     */   
/*     */   public void sync() {
/* 137 */     if ((!this.villager.field_70170_p.field_72995_K) && (this.villager.func_110143_aJ() > 0.0F) && (!this.villager.field_70128_L)) {
/* 138 */       Witchery.packetPipeline.sendToAll(new com.emoniph.witchery.network.PacketExtendedVillagerSync(this));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean isClientSynced()
/*     */   {
/* 145 */     if (this.villager.field_70170_p.field_72995_K) {
/* 146 */       if (this.synced)
/* 147 */         return true;
/* 148 */       if (this.trySync)
/*     */       {
/* 150 */         return false;
/*     */       }
/* 152 */       this.trySync = true;
/* 153 */       Witchery.packetPipeline.sendToServer(new com.emoniph.witchery.network.PacketExtendedEntityRequestSyncToClient(this.villager));
/*     */     }
/*     */     
/* 156 */     return false;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/common/ExtendedVillager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */