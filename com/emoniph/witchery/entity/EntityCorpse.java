/*     */ package com.emoniph.witchery.entity;
/*     */ 
/*     */ import com.emoniph.witchery.dimension.WorldProviderDreamWorld;
/*     */ import net.minecraft.client.entity.AbstractClientPlayer;
/*     */ import net.minecraft.entity.DataWatcher;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldServer;
/*     */ 
/*     */ public class EntityCorpse extends EntityLiving
/*     */ {
/*     */   private net.minecraft.client.renderer.ThreadDownloadImageData downloadImageSkin;
/*     */   private net.minecraft.util.ResourceLocation locationSkin;
/*     */   
/*     */   public EntityCorpse(World world)
/*     */   {
/*  22 */     super(world);
/*  23 */     func_70105_a(1.2F, 0.5F);
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70104_M()
/*     */   {
/*  29 */     return false;
/*     */   }
/*     */   
/*     */   public boolean func_70067_L()
/*     */   {
/*  34 */     return super.func_70067_L();
/*     */   }
/*     */   
/*     */   protected void func_110147_ax()
/*     */   {
/*  39 */     super.func_110147_ax();
/*  40 */     func_110148_a(net.minecraft.entity.SharedMonsterAttributes.field_111267_a).func_111128_a(20.0D);
/*  41 */     func_110148_a(net.minecraft.entity.SharedMonsterAttributes.field_111266_c).func_111128_a(1.0D);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_70091_d(double par1, double par3, double par5) {}
/*     */   
/*     */ 
/*     */ 
/*     */   protected void func_70088_a()
/*     */   {
/*  52 */     super.func_70088_a();
/*  53 */     this.field_70180_af.func_75682_a(17, "");
/*     */   }
/*     */   
/*     */   protected boolean func_70085_c(EntityPlayer par1EntityPlayer)
/*     */   {
/*  58 */     return true;
/*     */   }
/*     */   
/*     */   public boolean func_70097_a(DamageSource par1DamageSource, float par2)
/*     */   {
/*  63 */     if (!this.field_70170_p.field_72995_K) {
/*  64 */       if ((par1DamageSource.func_76364_f() != null) && ((par1DamageSource.func_76364_f() instanceof EntityPlayer)) && (((EntityPlayer)par1DamageSource.func_76364_f()).field_71075_bZ.field_75098_d)) {
/*  65 */         return super.func_70097_a(par1DamageSource, par2);
/*     */       }
/*     */       
/*  68 */       String username = getOwnerName();
/*  69 */       for (WorldServer world : MinecraftServer.func_71276_C().field_71305_c) {
/*  70 */         EntityPlayer player = world.func_72924_a(username);
/*  71 */         if (player != null) {
/*  72 */           return super.func_70097_a(par1DamageSource, par2);
/*     */         }
/*     */       }
/*  75 */       return false;
/*     */     }
/*  77 */     return super.func_70097_a(par1DamageSource, par2);
/*     */   }
/*     */   
/*     */ 
/*     */   public String func_70005_c_()
/*     */   {
/*  83 */     if (func_94056_bM()) {
/*  84 */       return func_94057_bL();
/*     */     }
/*  86 */     return net.minecraft.util.StatCollector.func_74838_a("entity.witchery.body.name");
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_70650_aV()
/*     */   {
/*  92 */     return true;
/*     */   }
/*     */   
/*     */   public void func_70014_b(NBTTagCompound nbtRoot)
/*     */   {
/*  97 */     super.func_70014_b(nbtRoot);
/*     */     
/*  99 */     if (getOwnerName() == null) {
/* 100 */       nbtRoot.func_74778_a("Owner", "");
/*     */     } else {
/* 102 */       nbtRoot.func_74778_a("Owner", getOwnerName());
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_70037_a(NBTTagCompound nbtRoot)
/*     */   {
/* 108 */     super.func_70037_a(nbtRoot);
/*     */     
/* 110 */     String s = nbtRoot.func_74779_i("Owner");
/*     */     
/* 112 */     if (s.length() > 0) {
/* 113 */       setOwner(s);
/*     */     }
/*     */   }
/*     */   
/*     */   public String getOwnerName() {
/* 118 */     return this.field_70180_af.func_75681_e(17);
/*     */   }
/*     */   
/*     */   public void setOwner(String username) {
/* 122 */     func_110163_bv();
/* 123 */     this.field_70180_af.func_75692_b(17, username);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void setupCustomSkin()
/*     */   {
/* 130 */     String username = getOwnerName();
/* 131 */     this.locationSkin = AbstractClientPlayer.func_110311_f(username);
/* 132 */     this.downloadImageSkin = AbstractClientPlayer.func_110304_a(this.locationSkin, username);
/*     */   }
/*     */   
/*     */   public EntityPlayer getOwnerEntity() {
/* 136 */     return this.field_70170_p.func_72924_a(getOwnerName());
/*     */   }
/*     */   
/*     */   public void func_70645_a(DamageSource par1DamageSource)
/*     */   {
/* 141 */     super.func_70645_a(par1DamageSource);
/* 142 */     if (!this.field_70170_p.field_72995_K) {
/* 143 */       String username = getOwnerName();
/* 144 */       for (WorldServer world : MinecraftServer.func_71276_C().field_71305_c) {
/* 145 */         EntityPlayer player = world.func_72924_a(username);
/* 146 */         if (player != null) {
/* 147 */           if (player.field_71093_bK == com.emoniph.witchery.util.Config.instance().dimensionDreamID) {
/* 148 */             WorldProviderDreamWorld.returnPlayerToOverworld(player); break; }
/* 149 */           if (!WorldProviderDreamWorld.getPlayerIsGhost(player)) break;
/* 150 */           WorldProviderDreamWorld.returnGhostPlayerToSpiritWorld(player);
/* 151 */           WorldProviderDreamWorld.returnPlayerToOverworld(player); break;
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_70636_d()
/*     */   {
/* 161 */     super.func_70636_d();
/*     */   }
/*     */   
/*     */   public net.minecraft.util.ResourceLocation getLocationSkin() {
/* 165 */     if (this.locationSkin == null) {
/* 166 */       setupCustomSkin();
/*     */     }
/* 168 */     if (this.locationSkin != null) {
/* 169 */       return this.locationSkin;
/*     */     }
/* 171 */     return AbstractClientPlayer.field_110314_b;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/entity/EntityCorpse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */