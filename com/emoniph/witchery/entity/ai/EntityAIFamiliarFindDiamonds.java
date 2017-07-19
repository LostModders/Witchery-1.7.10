/*     */ package com.emoniph.witchery.entity.ai;
/*     */ 
/*     */ import com.emoniph.witchery.entity.EntityFamiliar;
/*     */ import com.emoniph.witchery.util.ChatUtil;
/*     */ import com.emoniph.witchery.util.SoundEffect;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.ai.EntityAISit;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntityAIFamiliarFindDiamonds extends net.minecraft.entity.ai.EntityAIBase
/*     */ {
/*     */   private final EntityFamiliar theFamiliar;
/*     */   private final double field_75404_b;
/*     */   private int currentTick;
/*     */   private int field_75402_d;
/*     */   private int maxSittingTicks;
/*     */   private int sitableBlockX;
/*     */   private int sitableBlockY;
/*     */   private int sitableBlockZ;
/*     */   
/*     */   public EntityAIFamiliarFindDiamonds(EntityFamiliar familiarEntity, double par2)
/*     */   {
/*  30 */     this.theFamiliar = familiarEntity;
/*  31 */     this.field_75404_b = par2;
/*  32 */     func_75248_a(5);
/*     */   }
/*     */   
/*     */   public boolean func_75250_a()
/*     */   {
/*  37 */     EntityLivingBase entitylivingbase = this.theFamiliar.func_70902_q();
/*  38 */     return (this.theFamiliar.func_70909_n()) && (!this.theFamiliar.func_70906_o()) && (this.theFamiliar.getBlockIDToFind() != null) && (entitylivingbase != null) && (this.theFamiliar.func_70068_e(entitylivingbase) < 100.0D) && (this.theFamiliar.func_70681_au().nextDouble() <= 0.1D) && (getNearbySitableBlockDistance());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_75253_b()
/*     */   {
/*  45 */     EntityLivingBase entitylivingbase = this.theFamiliar.func_70902_q();
/*  46 */     return (this.currentTick <= this.maxSittingTicks) && (this.field_75402_d <= 60) && (entitylivingbase != null) && (this.theFamiliar.func_70068_e(entitylivingbase) < 100.0D) && (isSittableBlock(this.theFamiliar.field_70170_p, this.sitableBlockX, this.sitableBlockY, this.sitableBlockZ));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_75249_e()
/*     */   {
/*  53 */     if (!this.theFamiliar.func_70661_as().func_75492_a(this.sitableBlockX + 0.5D, this.sitableBlockY + 1, this.sitableBlockZ + 0.5D, this.field_75404_b))
/*     */     {
/*  55 */       this.theFamiliar.func_70661_as().func_75492_a(this.sitableBlockX + 0.5D, this.theFamiliar.field_70163_u + 1.0D, this.sitableBlockZ + 0.5D, this.field_75404_b);
/*     */     }
/*     */     
/*  58 */     this.currentTick = 0;
/*  59 */     this.field_75402_d = 0;
/*  60 */     this.maxSittingTicks = (this.theFamiliar.func_70681_au().nextInt(this.theFamiliar.func_70681_au().nextInt(1200) + 1200) + 1200);
/*  61 */     this.theFamiliar.func_70907_r().func_75270_a(false);
/*     */   }
/*     */   
/*     */   public void func_75251_c()
/*     */   {
/*  66 */     this.theFamiliar.func_70904_g(false);
/*     */   }
/*     */   
/*     */   public void func_75246_d()
/*     */   {
/*  71 */     this.currentTick += 1;
/*  72 */     this.theFamiliar.func_70907_r().func_75270_a(false);
/*     */     
/*  74 */     if (this.theFamiliar.func_70092_e(this.sitableBlockX, this.theFamiliar.field_70163_u, this.sitableBlockZ) > 2.0D) {
/*  75 */       this.theFamiliar.func_70904_g(false);
/*     */       
/*  77 */       if (!this.theFamiliar.func_70661_as().func_75492_a(this.sitableBlockX + 0.5D, this.sitableBlockY + 1, this.sitableBlockZ + 0.5D, this.field_75404_b))
/*     */       {
/*  79 */         this.theFamiliar.func_70661_as().func_75492_a(this.sitableBlockX + 0.5D, this.theFamiliar.field_70163_u, this.sitableBlockZ + 0.5D, this.field_75404_b);
/*     */       }
/*     */       
/*     */ 
/*  83 */       this.field_75402_d += 1;
/*  84 */     } else if (!this.theFamiliar.func_70906_o()) {
/*  85 */       EntityLivingBase living = this.theFamiliar.func_70902_q();
/*  86 */       if ((living != null) && 
/*  87 */         ((living instanceof EntityPlayer))) {
/*  88 */         EntityPlayer player = (EntityPlayer)living;
/*  89 */         SoundEffect.RANDOM_ORB.playAtPlayer(this.theFamiliar.field_70170_p, player);
/*  90 */         ChatUtil.sendTranslated(EnumChatFormatting.LIGHT_PURPLE, player, "witchery.familiar.foundsomething", new Object[] { Integer.valueOf(MathHelper.func_76128_c(this.theFamiliar.field_70165_t)).toString(), Integer.valueOf(MathHelper.func_76128_c(this.theFamiliar.field_70163_u)).toString(), Integer.valueOf(MathHelper.func_76128_c(this.theFamiliar.field_70161_v)).toString() });
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*  96 */       this.theFamiliar.clearItemToFind();
/*  97 */       this.theFamiliar.setAISitting(true);
/*     */     } else {
/*  99 */       this.field_75402_d -= 1;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   protected boolean getNearbySitableBlockDistance()
/*     */   {
/* 106 */     int MAX_WIDTH = 4;
/* 107 */     int DEPTH = this.theFamiliar.getDepthToFind();
/*     */     
/* 109 */     for (int i = 1; i < DEPTH; i++) {
/* 110 */       for (int j = (int)this.theFamiliar.field_70165_t - 4; j < this.theFamiliar.field_70165_t + 4.0D; j++) {
/* 111 */         for (int k = (int)this.theFamiliar.field_70161_v - 4; k < this.theFamiliar.field_70161_v + 4.0D; k++) {
/* 112 */           if (isSittableBlock(this.theFamiliar.field_70170_p, j, i, k))
/*     */           {
/*     */ 
/*     */ 
/*     */ 
/* 117 */             this.sitableBlockX = j;
/* 118 */             this.sitableBlockY = i;
/* 119 */             this.sitableBlockZ = k;
/*     */             
/*     */ 
/* 122 */             return true;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 128 */     return false;
/*     */   }
/*     */   
/*     */   protected boolean isSittableBlock(World par1World, int par2, int par3, int par4) {
/* 132 */     Block blockID = this.theFamiliar.getBlockIDToFind();
/* 133 */     Block foundBlockID = par1World.func_147439_a(par2, par3, par4);
/* 134 */     if (blockID == Blocks.field_150482_ag)
/* 135 */       return (foundBlockID == blockID) || (foundBlockID == Blocks.field_150412_bA);
/* 136 */     if (blockID == Blocks.field_150412_bA) {
/* 137 */       return (foundBlockID == blockID) || (foundBlockID == Blocks.field_150482_ag);
/*     */     }
/* 139 */     return foundBlockID == blockID;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/entity/ai/EntityAIFamiliarFindDiamonds.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */