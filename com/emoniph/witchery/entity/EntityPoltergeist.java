/*     */ package com.emoniph.witchery.entity;
/*     */ 
/*     */ import com.emoniph.witchery.blocks.BlockBrazier.TileEntityBrazier;
/*     */ import com.emoniph.witchery.util.TimeUtil;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityHanging;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIAttackOnCollide;
/*     */ import net.minecraft.entity.ai.EntityAIHurtByTarget;
/*     */ import net.minecraft.entity.ai.EntityAILookIdle;
/*     */ import net.minecraft.entity.ai.EntityAIOpenDoor;
/*     */ import net.minecraft.entity.ai.EntityAITasks;
/*     */ import net.minecraft.entity.ai.EntityAIWander;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntityPoltergeist extends EntitySummonedUndead
/*     */ {
/*     */   private int attackTimer;
/*     */   
/*     */   public EntityPoltergeist(World par1World)
/*     */   {
/*  41 */     super(par1World);
/*  42 */     func_70661_as().func_75491_a(true);
/*  43 */     func_70661_as().func_75498_b(true);
/*  44 */     this.field_70714_bg.func_75776_a(1, new net.minecraft.entity.ai.EntityAISwimming(this));
/*  45 */     this.field_70714_bg.func_75776_a(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
/*  46 */     this.field_70714_bg.func_75776_a(3, new EntityAIOpenDoor(this, true));
/*  47 */     this.field_70714_bg.func_75776_a(4, new EntityAIWander(this, 1.0D));
/*  48 */     this.field_70714_bg.func_75776_a(5, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
/*  49 */     this.field_70714_bg.func_75776_a(6, new EntityAILookIdle(this));
/*  50 */     this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, true));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void func_110147_ax()
/*     */   {
/*  57 */     super.func_110147_ax();
/*  58 */     func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(20.0D);
/*  59 */     func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.3D);
/*  60 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(3.0D);
/*     */   }
/*     */   
/*     */   protected void func_70088_a()
/*     */   {
/*  65 */     super.func_70088_a();
/*     */   }
/*     */   
/*     */   protected boolean func_70650_aV()
/*     */   {
/*  70 */     return true;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int getAttackTimer() {
/*  75 */     return this.attackTimer;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_70103_a(byte par1)
/*     */   {
/*  81 */     if (par1 == 4) {
/*  82 */       this.attackTimer = 15;
/*     */     } else {
/*  84 */       super.func_70103_a(par1);
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_70636_d()
/*     */   {
/*  90 */     super.func_70636_d();
/*     */     
/*  92 */     if (this.attackTimer > 0) {
/*  93 */       this.attackTimer -= 1;
/*     */     }
/*     */     
/*  96 */     if (TimeUtil.secondsElapsed(5, this.field_70173_aa)) {
/*  97 */       double RADIUS = 16.0D;
/*  98 */       double RADIUS_SQ = 256.0D;
/*  99 */       double THROW_RANGE = 3.0D;
/* 100 */       double THROW_RANGE_SQ = 9.0D;
/* 101 */       double EVIL_RANGE = 8.0D;
/* 102 */       double EVIL_RANGE_SQ = 64.0D;
/* 103 */       double MAX_SPEED = 0.6D;
/*     */       
/*     */ 
/* 106 */       AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(this.field_70165_t - 16.0D, this.field_70163_u - 16.0D, this.field_70161_v - 16.0D, this.field_70165_t + 16.0D, this.field_70163_u + 16.0D, this.field_70161_v + 16.0D);
/*     */       
/* 108 */       List hangingItems = this.field_70170_p.func_72872_a(EntityHanging.class, bounds);
/* 109 */       for (Object obj : hangingItems) {
/* 110 */         EntityHanging hanging = (EntityHanging)obj;
/* 111 */         if (func_70068_e(hanging) <= 256.0D) {
/* 112 */           if (func_70068_e(hanging) <= 9.0D) {
/* 113 */             if (!this.field_70170_p.field_72995_K) {
/* 114 */               hanging.func_70097_a(DamageSource.func_76358_a(this), 3.0F);
/*     */             }
/* 116 */             this.attackTimer = 15;
/* 117 */             this.field_70170_p.func_72960_a(this, (byte)4);
/*     */           } else {
/* 119 */             func_70661_as().func_75492_a(hanging.field_70165_t, hanging.field_70163_u, hanging.field_70161_v, 1.0D);
/*     */           }
/* 121 */           return;
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 127 */       EntityPlayer summoner = getSummoner();
/* 128 */       if ((summoner != null) && (func_70068_e(summoner) <= 64.0D)) {
/* 129 */         TileEntity closest = null;
/* 130 */         double closestDist = -1.0D;
/* 131 */         for (Object obj : this.field_70170_p.field_147482_g) {
/* 132 */           if (((obj instanceof IInventory)) && (!(obj instanceof com.emoniph.witchery.blocks.BlockKettle.TileEntityKettle)) && (!(obj instanceof BlockBrazier.TileEntityBrazier))) {
/* 133 */             TileEntity tile = (TileEntity)obj;
/* 134 */             double distSq = func_70092_e(0.5D + tile.field_145851_c, 0.5D + tile.field_145848_d, 0.5D + tile.field_145849_e);
/* 135 */             if (distSq <= 256.0D) {
/* 136 */               IInventory inventory = (IInventory)tile;
/* 137 */               ArrayList<Integer> indices = new ArrayList();
/* 138 */               for (int i = 0; i < inventory.func_70302_i_(); i++) {
/* 139 */                 if (inventory.func_70301_a(i) != null) {
/* 140 */                   indices.add(Integer.valueOf(i));
/*     */                 }
/*     */               }
/* 143 */               if ((indices.size() > 0) && ((closest == null) || (distSq < closestDist))) {
/* 144 */                 closest = tile;
/* 145 */                 closestDist = distSq;
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */         
/* 151 */         if (closest != null) {
/* 152 */           IInventory inventory = (IInventory)closest;
/* 153 */           ArrayList<Integer> indices = new ArrayList();
/* 154 */           for (int i = 0; i < inventory.func_70302_i_(); i++) {
/* 155 */             if (inventory.func_70301_a(i) != null) {
/* 156 */               indices.add(Integer.valueOf(i));
/*     */             }
/*     */           }
/*     */           
/* 160 */           if (indices.size() > 0) {
/* 161 */             if (func_70092_e(0.5D + closest.field_145851_c, 0.5D + closest.field_145848_d, 0.5D + closest.field_145849_e) <= 9.0D) {
/* 162 */               if (!this.field_70170_p.field_72995_K) {
/* 163 */                 int slot = ((Integer)indices.get(this.field_70170_p.field_73012_v.nextInt(indices.size()))).intValue();
/* 164 */                 ItemStack stack = inventory.func_70301_a(slot);
/* 165 */                 if (stack.field_77994_a > 1) {
/* 166 */                   stack.field_77994_a -= 1;
/* 167 */                   stack = stack.func_77946_l();
/* 168 */                   stack.field_77994_a = 1;
/*     */                 } else {
/* 170 */                   inventory.func_70299_a(slot, null);
/*     */                 }
/* 172 */                 EntityItem itemEntity = new EntityItem(this.field_70170_p, 0.5D + closest.field_145851_c, 0.5D + closest.field_145848_d, 0.5D + closest.field_145849_e, stack);
/*     */                 
/* 174 */                 this.field_70170_p.func_72838_d(itemEntity);
/* 175 */                 itemEntity.lifespan = TimeUtil.minsToTicks(15);
/* 176 */                 itemEntity.field_70159_w = (-0.3D + this.field_70170_p.field_73012_v.nextDouble() * 0.6D);
/* 177 */                 itemEntity.field_70181_x = (0.1D + this.field_70170_p.field_73012_v.nextDouble() * 0.2D);
/* 178 */                 itemEntity.field_70179_y = (-0.3D + this.field_70170_p.field_73012_v.nextDouble() * 0.6D);
/*     */               }
/* 180 */               this.attackTimer = 15;
/* 181 */               this.field_70170_p.func_72960_a(this, (byte)4);
/*     */             } else {
/* 183 */               func_70661_as().func_75492_a(closest.field_145851_c, closest.field_145848_d, closest.field_145849_e, 1.0D);
/*     */             }
/* 185 */             return;
/*     */           }
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 191 */       List droppedItems = this.field_70170_p.func_72872_a(EntityItem.class, bounds);
/* 192 */       for (Object obj : droppedItems) {
/* 193 */         EntityItem dropped = (EntityItem)obj;
/* 194 */         if (func_70068_e(dropped) <= 256.0D) {
/* 195 */           if (func_70068_e(dropped) <= 9.0D) {
/* 196 */             if (!this.field_70170_p.field_72995_K) {
/* 197 */               dropped.field_70159_w = (-0.3D + this.field_70170_p.field_73012_v.nextDouble() * 0.6D);
/* 198 */               dropped.field_70181_x = (0.1D + this.field_70170_p.field_73012_v.nextDouble() * 0.2D);
/* 199 */               dropped.field_70179_y = (-0.3D + this.field_70170_p.field_73012_v.nextDouble() * 0.6D);
/*     */             }
/* 201 */             this.attackTimer = 15;
/* 202 */             this.field_70170_p.func_72960_a(this, (byte)4);
/*     */           } else {
/* 204 */             func_70661_as().func_75492_a(dropped.field_70165_t, dropped.field_70163_u, dropped.field_70161_v, 1.0D);
/*     */           }
/* 206 */           return;
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void func_70071_h_()
/*     */   {
/* 216 */     super.func_70071_h_();
/*     */   }
/*     */   
/*     */   public boolean func_70652_k(Entity par1Entity)
/*     */   {
/* 221 */     boolean flag = super.func_70652_k(par1Entity);
/*     */     
/* 223 */     return flag;
/*     */   }
/*     */   
/*     */   protected String func_70639_aQ()
/*     */   {
/* 228 */     return null;
/*     */   }
/*     */   
/*     */   protected String func_70621_aR()
/*     */   {
/* 233 */     return "witchery:mob.spectre.spectre_die";
/*     */   }
/*     */   
/*     */   protected String func_70673_aS()
/*     */   {
/* 238 */     return "witchery:mob.spectre.spectre_die";
/*     */   }
/*     */   
/*     */   public String func_70005_c_()
/*     */   {
/* 243 */     if (func_94056_bM()) {
/* 244 */       return func_94057_bL();
/*     */     }
/* 246 */     return net.minecraft.util.StatCollector.func_74838_a("entity.witchery.poltergeist.name");
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70014_b(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 252 */     super.func_70014_b(par1NBTTagCompound);
/*     */   }
/*     */   
/*     */ 
/*     */   public void func_70037_a(NBTTagCompound par1NBTTagCompound)
/*     */   {
/* 258 */     super.func_70037_a(par1NBTTagCompound);
/*     */   }
/*     */   
/*     */   public IEntityLivingData func_110161_a(IEntityLivingData par1EntityLivingData)
/*     */   {
/* 263 */     Object par1EntityLivingData1 = super.func_110161_a(par1EntityLivingData);
/*     */     
/* 265 */     func_70690_d(new PotionEffect(Potion.field_76441_p.field_76415_H, Integer.MAX_VALUE));
/*     */     
/* 267 */     return (IEntityLivingData)par1EntityLivingData1;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/entity/EntityPoltergeist.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */