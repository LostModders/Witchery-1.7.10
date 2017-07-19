/*     */ package com.emoniph.witchery.entity.ai;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.entity.ai.EntityAISit;
/*     */ import net.minecraft.entity.passive.EntityTameable;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityAIToadSit
/*     */   extends EntityAIBase
/*     */ {
/*     */   private final EntityTameable theOcelot;
/*     */   private final double field_75404_b;
/*     */   private int currentTick;
/*     */   private int field_75402_d;
/*     */   private int maxSittingTicks;
/*     */   private int sitableBlockX;
/*     */   private int sitableBlockY;
/*     */   private int sitableBlockZ;
/*     */   
/*     */   public EntityAIToadSit(EntityTameable par1EntityOcelot, double par2)
/*     */   {
/*  32 */     this.theOcelot = par1EntityOcelot;
/*  33 */     this.field_75404_b = par2;
/*  34 */     func_75248_a(5);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_75250_a()
/*     */   {
/*  42 */     return (!this.theOcelot.func_70906_o()) && (this.theOcelot.func_70681_au().nextDouble() <= 0.006500000134110451D) && (getNearbySitableBlockDistance());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_75253_b()
/*     */   {
/*  50 */     return (this.currentTick <= this.maxSittingTicks) && (this.field_75402_d <= 60) && (isSittableBlock(this.theOcelot.field_70170_p, this.sitableBlockX, this.sitableBlockY, this.sitableBlockZ));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_75249_e()
/*     */   {
/*  58 */     this.theOcelot.func_70661_as().func_75492_a(this.sitableBlockX + 0.5D, this.sitableBlockY + 1, this.sitableBlockZ + 0.5D, this.field_75404_b);
/*  59 */     this.currentTick = 0;
/*  60 */     this.field_75402_d = 0;
/*  61 */     this.maxSittingTicks = (this.theOcelot.func_70681_au().nextInt(this.theOcelot.func_70681_au().nextInt(1200) + 1200) + 1200);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_75251_c()
/*     */   {
/*  70 */     this.theOcelot.func_70904_g(false);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void func_75246_d()
/*     */   {
/*  78 */     this.currentTick += 1;
/*  79 */     this.theOcelot.func_70907_r().func_75270_a(false);
/*     */     
/*  81 */     if (this.theOcelot.func_70092_e(this.sitableBlockX, this.sitableBlockY + 1, this.sitableBlockZ) > 1.0D)
/*     */     {
/*  83 */       this.theOcelot.func_70904_g(false);
/*  84 */       this.theOcelot.func_70661_as().func_75492_a(this.sitableBlockX + 0.5D, this.sitableBlockY + 1, this.sitableBlockZ + 0.5D, this.field_75404_b);
/*  85 */       this.field_75402_d += 1;
/*     */     }
/*  87 */     else if (!this.theOcelot.func_70906_o())
/*     */     {
/*  89 */       this.theOcelot.func_70904_g(true);
/*     */     }
/*     */     else
/*     */     {
/*  93 */       this.field_75402_d -= 1;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean getNearbySitableBlockDistance()
/*     */   {
/* 102 */     int i = (int)this.theOcelot.field_70163_u;
/* 103 */     double d0 = 4.147483647E9D;
/*     */     
/* 105 */     for (int j = (int)this.theOcelot.field_70165_t - 8; j < this.theOcelot.field_70165_t + 8.0D; j++)
/*     */     {
/* 107 */       for (int k = (int)this.theOcelot.field_70161_v - 8; k < this.theOcelot.field_70161_v + 8.0D; k++)
/*     */       {
/* 109 */         for (int y = (int)this.theOcelot.field_70163_u - 2; y < this.theOcelot.field_70163_u + 3.0D; y++)
/*     */         {
/* 111 */           if ((isSittableBlock(this.theOcelot.field_70170_p, j, y, k)) && (this.theOcelot.field_70170_p.func_147437_c(j, y + 1, k)))
/*     */           {
/* 113 */             double d1 = this.theOcelot.func_70092_e(j, y, k);
/*     */             
/* 115 */             if (d1 < d0)
/*     */             {
/* 117 */               this.sitableBlockX = j;
/* 118 */               this.sitableBlockY = y;
/* 119 */               this.sitableBlockZ = k;
/* 120 */               d0 = d1;
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 127 */     return d0 < 2.147483647E9D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean isSittableBlock(World par1World, int par2, int par3, int par4)
/*     */   {
/* 135 */     Block l = par1World.func_147439_a(par2, par3, par4);
/* 136 */     int i1 = par1World.func_72805_g(par2, par3, par4);
/*     */     
/* 138 */     if (l == Blocks.field_150392_bi)
/*     */     {
/*     */ 
/* 141 */       return true;
/*     */     }
/*     */     
/* 144 */     return false;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/entity/ai/EntityAIToadSit.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */