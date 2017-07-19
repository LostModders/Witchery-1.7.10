/*     */ package com.emoniph.witchery.entity.ai;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityAIFlyerLand
/*     */   extends EntityAIBase
/*     */ {
/*     */   private double speed;
/*     */   int[] target;
/*     */   World worldObj;
/*     */   public int courseChangeCooldown;
/*     */   public double waypointX;
/*     */   public double waypointY;
/*     */   public double waypointZ;
/*     */   public boolean findTrees;
/*     */   EntityLiving living;
/*     */   
/*     */   public EntityAIFlyerLand(EntityLiving par1EntityCreature, double par2, boolean findTrees)
/*     */   {
/*  30 */     this.living = par1EntityCreature;
/*  31 */     this.worldObj = this.living.field_70170_p;
/*  32 */     this.speed = par2;
/*  33 */     this.findTrees = findTrees;
/*  34 */     func_75248_a(1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean func_75250_a()
/*     */   {
/*  42 */     return (!isLanded()) && (!liquidBelow((int)this.living.field_70163_u - 1)) && (!liquidBelow((int)this.living.field_70163_u)) && (this.worldObj.field_73012_v.nextInt(20) == 0);
/*     */   }
/*     */   
/*     */   private boolean liquidBelow(int y) {
/*  46 */     return this.worldObj.func_147439_a(MathHelper.func_76128_c(this.living.field_70165_t), y, MathHelper.func_76128_c(this.living.field_70161_v)).func_149688_o().func_76224_d();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean func_75253_b()
/*     */   {
/*  53 */     boolean cont = (!isLanded()) && (!liquidBelow((int)this.living.field_70163_u - 1)) && (!liquidBelow((int)this.living.field_70163_u));
/*  54 */     return cont;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_75249_e()
/*     */   {
/*  61 */     this.courseChangeCooldown = 100;
/*  62 */     int x0 = MathHelper.func_76128_c(this.living.field_70165_t);
/*  63 */     int y0 = MathHelper.func_76128_c(this.living.field_70163_u);
/*  64 */     int z0 = MathHelper.func_76128_c(this.living.field_70161_v);
/*     */     
/*  66 */     this.target = (this.findTrees ? findTreeTop(x0, y0, z0) : null);
/*  67 */     if (this.target == null) {
/*  68 */       this.target = findGround(x0, y0, z0);
/*     */     }
/*     */     
/*  71 */     if (this.target != null) {}
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_75251_c()
/*     */   {
/*  78 */     this.target = null;
/*  79 */     super.func_75251_c();
/*     */   }
/*     */   
/*     */   private int[] findTreeTop(int x0, int y0, int z0) {
/*  83 */     int RADIUS = 16;
/*  84 */     int Y_RADIUS = 3;
/*  85 */     for (int y = Math.max(y0 - 3, 1); y <= y0 + 3; y++) {
/*  86 */       for (int x = x0 - 16; x <= x0 + 16; x++) {
/*  87 */         for (int z = z0 - 16; z <= z0 + 16; z++) {
/*  88 */           Block blockID = this.worldObj.func_147439_a(x, y, z);
/*  89 */           if (blockID.func_149688_o() == Material.field_151584_j) {
/*  90 */             for (int y2 = y; y2 < y0 + 10; y2++) {
/*  91 */               if (this.worldObj.func_147437_c(x, y2, z)) {
/*  92 */                 double d0 = x - this.living.field_70165_t;
/*  93 */                 double d1 = y2 - this.living.field_70163_u;
/*  94 */                 double d2 = z - this.living.field_70161_v;
/*  95 */                 double d3 = d0 * d0 + d1 * d1 + d2 * d2;
/*  96 */                 d3 = MathHelper.func_76133_a(d3);
/*  97 */                 if (isCourseTraversable(x, y2, z, d3)) {
/*  98 */                   return new int[] { x, y2 + 2, z };
/*     */                 }
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 107 */     return null;
/*     */   }
/*     */   
/*     */   private int[] findGround(int x0, int y0, int z0) {
/* 111 */     for (int y = y0; y > 1; y--) {
/* 112 */       Material material = this.worldObj.func_147439_a(x0, y, z0).func_149688_o();
/* 113 */       if (material != Material.field_151579_a) {
/* 114 */         if (!material.func_76224_d()) {
/* 115 */           return new int[] { x0, y + 1, z0 };
/*     */         }
/* 117 */         for (int i = 0; i < 10; i++) {
/* 118 */           int j = MathHelper.func_76128_c(this.living.field_70165_t + this.worldObj.field_73012_v.nextInt(20) - 10.0D);
/* 119 */           int k = MathHelper.func_76128_c(this.living.field_70121_D.field_72338_b + this.worldObj.field_73012_v.nextInt(6) - 3.0D);
/* 120 */           int l = MathHelper.func_76128_c(this.living.field_70161_v + this.worldObj.field_73012_v.nextInt(20) - 10.0D);
/* 121 */           Block blockID = this.worldObj.func_147439_a(j, k, l);
/* 122 */           double d0 = j - this.living.field_70165_t;
/* 123 */           double d1 = k - this.living.field_70163_u;
/* 124 */           double d2 = l - this.living.field_70161_v;
/* 125 */           double d3 = d0 * d0 + d1 * d1 + d2 * d2;
/* 126 */           d3 = MathHelper.func_76133_a(d3);
/* 127 */           if (((blockID.func_149688_o() == Material.field_151584_j) || (blockID.func_149688_o().func_76220_a())) && (this.worldObj.func_147437_c(j, k + 1, l)) && (isCourseTraversable(j, k, l, d3)))
/*     */           {
/* 129 */             return new int[] { j, k + 1, l };
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 135 */     return null;
/*     */   }
/*     */   
/*     */   public void func_75246_d()
/*     */   {
/* 140 */     if (!isLanded())
/*     */     {
/* 142 */       if ((this.target != null) && (this.living.func_70092_e(this.target[0], this.living.field_70163_u, this.target[2]) > 1.0D) && (this.courseChangeCooldown-- > 0)) {
/* 143 */         double d0 = this.target[0] - this.living.field_70165_t;
/* 144 */         double d1 = this.target[1] - this.living.field_70163_u;
/* 145 */         double d2 = this.target[2] - this.living.field_70161_v;
/* 146 */         double d3 = d0 * d0 + d1 * d1 + d2 * d2;
/*     */         
/* 148 */         d3 = MathHelper.func_76133_a(d3);
/*     */         
/* 150 */         if (isCourseTraversable(this.target[0], this.target[1], this.target[2], d3)) {
/* 151 */           this.living.field_70159_w += d0 / d3 * 0.05D;
/* 152 */           this.living.field_70181_x += d1 / d3 * 0.05D;
/* 153 */           this.living.field_70179_y += d2 / d3 * 0.05D;
/*     */         }
/*     */         
/*     */ 
/*     */       }
/* 158 */       else if (!liquidBelow((int)(this.living.field_70163_u - 1.0D))) {
/* 159 */         this.living.field_70181_x = -0.1D;
/*     */       }
/*     */       
/* 162 */       this.living.field_70761_aq = (this.living.field_70177_z = -(float)Math.atan2(this.living.field_70159_w, this.living.field_70179_y) * 180.0F / 3.1415927F);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 215 */     this.living.field_70761_aq = (this.living.field_70177_z = -(float)Math.atan2(this.living.field_70159_w, this.living.field_70179_y) * 180.0F / 3.1415927F);
/*     */   }
/*     */   
/*     */   private boolean isLanded() {
/* 219 */     Block blockID = this.worldObj.func_147439_a(MathHelper.func_76128_c(this.living.field_70165_t), (int)(this.living.field_70163_u - 0.01D), MathHelper.func_76128_c(this.living.field_70161_v));
/*     */     
/* 221 */     Material material = blockID.func_149688_o();
/* 222 */     if ((material == Material.field_151584_j) || (material.func_76220_a())) {
/* 223 */       return true;
/*     */     }
/* 225 */     return false;
/*     */   }
/*     */   
/*     */   private boolean isCourseTraversable(double par1, double par3, double par5, double par7) {
/* 229 */     double d4 = (par1 - this.living.field_70165_t) / par7;
/* 230 */     double d5 = (par3 - this.living.field_70163_u) / par7;
/* 231 */     double d6 = (par5 - this.living.field_70161_v) / par7;
/*     */     
/* 233 */     AxisAlignedBB axisalignedbb = this.living.field_70121_D.func_72329_c();
/*     */     
/* 235 */     for (int i = 1; i < par7; i++) {
/* 236 */       axisalignedbb.func_72317_d(d4, d5, d6);
/*     */       
/* 238 */       if (!this.worldObj.func_72945_a(this.living, axisalignedbb).isEmpty()) {
/* 239 */         return false;
/*     */       }
/*     */     }
/*     */     
/* 243 */     return true;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/entity/ai/EntityAIFlyerLand.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */