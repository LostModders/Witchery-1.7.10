/*     */ package com.emoniph.witchery.brewing;
/*     */ 
/*     */ import com.emoniph.witchery.blocks.TileEntityBase;
/*     */ import com.emoniph.witchery.util.EntityPosition;
/*     */ import com.emoniph.witchery.util.EntityUtil;
/*     */ import com.emoniph.witchery.util.ParticleEffect;
/*     */ import com.emoniph.witchery.util.SoundEffect;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.network.NetworkManager;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class TileEntityCursedBlock
/*     */   extends TileEntityBase
/*     */ {
/*     */   NBTTagCompound nbtEffect;
/*     */   int color;
/*     */   int duration;
/*     */   int expansion;
/*     */   int count;
/*     */   String thrower;
/*     */   
/*     */   public boolean canUpdate()
/*     */   {
/*  30 */     return false;
/*     */   }
/*     */   
/*     */   public void initalise(ModifiersImpact impactModifiers, NBTTagCompound nbtBrew) {
/*  34 */     if (nbtBrew != null) {
/*  35 */       this.nbtEffect = ((NBTTagCompound)nbtBrew.func_74737_b());
/*     */     }
/*  37 */     this.color = WitcheryBrewRegistry.INSTANCE.getBrewColor(this.nbtEffect);
/*  38 */     this.duration = (impactModifiers.lifetime >= 0 ? 5 + impactModifiers.lifetime * impactModifiers.lifetime * 5 : 100);
/*     */     
/*  40 */     this.expansion = Math.min(4 + impactModifiers.extent, 10);
/*  41 */     if (impactModifiers.thrower != null) {
/*  42 */       this.thrower = impactModifiers.thrower.func_70005_c_();
/*     */     }
/*  44 */     this.count = 1;
/*  45 */     this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */   }
/*     */   
/*     */   public void updateCurse(ModifiersImpact impactModifiers, NBTTagCompound nbtBrew) {
/*  49 */     if (nbtBrew != null) {
/*  50 */       if ((this.nbtEffect != null) && (this.nbtEffect.func_150295_c("Items", 10).equals(nbtBrew.func_150295_c("Items", 10))))
/*     */       {
/*     */ 
/*  53 */         this.count += 1;
/*     */       } else {
/*  55 */         this.nbtEffect = nbtBrew;
/*  56 */         this.count = 1;
/*  57 */         this.color = WitcheryBrewRegistry.INSTANCE.getBrewColor(this.nbtEffect);
/*  58 */         this.duration = (impactModifiers.lifetime >= 0 ? 5 + impactModifiers.lifetime * impactModifiers.lifetime * 5 : 100);
/*     */         
/*  60 */         this.expansion = Math.min(4 + impactModifiers.extent, 10);
/*  61 */         if (impactModifiers.thrower != null) {
/*  62 */           this.thrower = impactModifiers.thrower.func_70005_c_();
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public Packet func_145844_m()
/*     */   {
/*  70 */     NBTTagCompound nbtTag = new NBTTagCompound();
/*  71 */     func_145841_b(nbtTag);
/*  72 */     return new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, nbtTag);
/*     */   }
/*     */   
/*     */   public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet)
/*     */   {
/*  77 */     super.onDataPacket(net, packet);
/*  78 */     func_145839_a(packet.func_148857_g());
/*  79 */     this.field_145850_b.func_147479_m(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */   }
/*     */   
/*     */   public void func_145841_b(NBTTagCompound nbtRoot)
/*     */   {
/*  84 */     super.func_145841_b(nbtRoot);
/*  85 */     if (this.nbtEffect != null) {
/*  86 */       nbtRoot.func_74782_a("Effect", this.nbtEffect);
/*     */     }
/*  88 */     nbtRoot.func_74768_a("Color", this.color);
/*  89 */     nbtRoot.func_74768_a("Duration", this.duration);
/*  90 */     nbtRoot.func_74768_a("Expansion", this.expansion);
/*  91 */     nbtRoot.func_74768_a("Count", this.count);
/*  92 */     if (this.thrower != null) {
/*  93 */       nbtRoot.func_74778_a("Thrower", this.thrower);
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_145839_a(NBTTagCompound nbtRoot)
/*     */   {
/*  99 */     super.func_145839_a(nbtRoot);
/* 100 */     if (nbtRoot.func_74764_b("Effect")) {
/* 101 */       this.nbtEffect = nbtRoot.func_74775_l("Effect");
/*     */     }
/* 103 */     this.color = nbtRoot.func_74762_e("Color");
/* 104 */     this.duration = nbtRoot.func_74762_e("Duration");
/* 105 */     this.expansion = nbtRoot.func_74762_e("Expansion");
/* 106 */     this.thrower = nbtRoot.func_74779_i("Thrower");
/* 107 */     this.count = nbtRoot.func_74762_e("Count");
/*     */   }
/*     */   
/*     */   public boolean applyToEntityAndDestroy(Entity entity) {
/* 111 */     if ((this.nbtEffect != null) && (entity != null) && ((entity instanceof EntityLivingBase))) {
/* 112 */       EntityLivingBase living = (EntityLivingBase)entity;
/* 113 */       WitcheryBrewRegistry.INSTANCE.applyToEntity(entity.field_70170_p, living, this.nbtEffect, new ModifiersEffect(1.0D, 1.0D, false, new EntityPosition(living), false, 0, EntityUtil.playerOrFake(entity.field_70170_p, this.thrower)));
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 119 */       ParticleEffect.SPELL_COLORED.send(SoundEffect.RANDOM_POP, living, 1.0D, 1.0D, 16);
/*     */     }
/* 121 */     return --this.count > 0;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/TileEntityCursedBlock.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */