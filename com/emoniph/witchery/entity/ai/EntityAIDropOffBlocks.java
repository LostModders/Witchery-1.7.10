/*     */ package com.emoniph.witchery.entity.ai;
/*     */ 
/*     */ import com.emoniph.witchery.entity.EntityGoblin;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import java.util.concurrent.Callable;
/*     */ import net.minecraft.crash.CrashReport;
/*     */ import net.minecraft.crash.CrashReportCategory;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.ItemTool;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntityAIDropOffBlocks extends net.minecraft.entity.ai.EntityAIBase
/*     */ {
/*     */   protected final EntityGoblin entity;
/*     */   protected final double range;
/*  23 */   private TileEntity targetTile = null;
/*     */   
/*     */   public EntityAIDropOffBlocks(EntityGoblin entity, double range) {
/*  26 */     this.entity = entity;
/*  27 */     this.range = range;
/*  28 */     func_75248_a(7);
/*     */   }
/*     */   
/*     */   public boolean func_75250_a() {
/*  32 */     if (((this.entity != null) && (!this.entity.isWorshipping()) && (this.entity.func_70694_bm() == null)) || (!this.entity.func_110167_bD()) || ((this.entity.func_70694_bm().func_77973_b() instanceof ItemTool))) {
/*  33 */       return false;
/*     */     }
/*     */     
/*  36 */     if ((this.targetTile != null) && (!this.targetTile.func_145837_r()) && (this.entity.func_70661_as().func_75488_a(this.targetTile.field_145851_c, this.targetTile.field_145848_d, this.targetTile.field_145849_e) != null)) {
/*  37 */       return true;
/*     */     }
/*  39 */     this.targetTile = null;
/*     */     
/*     */ 
/*  42 */     if (this.entity.field_70170_p.field_73012_v.nextInt(60) != 0) {
/*  43 */       return false;
/*     */     }
/*     */     
/*  46 */     setTargetTile();
/*     */     
/*  48 */     return this.targetTile != null;
/*     */   }
/*     */   
/*     */   public void func_75249_e() {}
/*     */   
/*     */   private void setTargetTile()
/*     */   {
/*  55 */     this.targetTile = null;
/*  56 */     ArrayList<IInventory> chests = new ArrayList();
/*  57 */     double bestDist = Double.MAX_VALUE;
/*  58 */     double RANGE_SQ = this.range * this.range;
/*  59 */     for (int i = 0; i < this.entity.field_70170_p.field_147482_g.size(); i++) {
/*     */       try {
/*  61 */         Object te = this.entity.field_70170_p.field_147482_g.get(i);
/*  62 */         if ((te != null) && ((te instanceof IInventory))) {
/*  63 */           TileEntity tile = (TileEntity)te;
/*  64 */           if ((!tile.func_145837_r()) && (((IInventory)tile).func_70302_i_() >= 27)) {
/*  65 */             double distSq = this.entity.func_70092_e(tile.field_145851_c, tile.field_145848_d, tile.field_145849_e);
/*  66 */             if ((distSq <= RANGE_SQ) && (this.entity.func_70661_as().func_75488_a(tile.field_145851_c, tile.field_145848_d, tile.field_145849_e) != null) && 
/*  67 */               (distSq < bestDist)) {
/*  68 */               bestDist = distSq;
/*  69 */               this.targetTile = tile;
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */       catch (Throwable e) {}
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean func_75253_b()
/*     */   {
/*  81 */     return (this.entity != null) && (!this.entity.isWorshipping()) && (this.entity.func_70694_bm() != null) && (this.entity.func_110167_bD()) && (this.targetTile != null);
/*     */   }
/*     */   
/*     */   public void func_75246_d() {
/*  85 */     double SPEED = 0.6D;
/*  86 */     if (this.entity.func_70661_as().func_75500_f()) {
/*  87 */       setTargetTile();
/*  88 */       if (this.targetTile != null) {
/*  89 */         this.entity.func_70661_as().func_75492_a(this.targetTile.field_145851_c, this.targetTile.field_145848_d, this.targetTile.field_145849_e, 0.6D);
/*     */       }
/*     */     }
/*  92 */     double DROP_RANGE = 2.5D;
/*  93 */     double DROP_RANGE_SQ = 6.25D;
/*  94 */     if ((this.targetTile != null) && 
/*  95 */       (this.entity.func_70092_e(this.targetTile.field_145851_c + 0.5D, this.targetTile.field_145848_d + 0.5D, this.targetTile.field_145849_e + 0.5D) <= 6.25D)) {
/*  96 */       IInventory inventory = (IInventory)this.targetTile;
/*  97 */       inventory.func_70295_k_();
/*  98 */       if ((addItemStackToInventory(this.entity.func_70694_bm(), inventory)) && 
/*  99 */         (this.entity.func_70694_bm().field_77994_a == 0)) {
/* 100 */         this.entity.func_70062_b(0, null);
/*     */       }
/*     */       
/*     */ 
/* 104 */       inventory.func_70305_f();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean addItemStackToInventory(final ItemStack par1ItemStack, IInventory inventory)
/*     */   {
/* 111 */     if ((par1ItemStack != null) && (par1ItemStack.field_77994_a != 0) && (par1ItemStack.func_77973_b() != null))
/*     */     {
/*     */       try
/*     */       {
/* 115 */         if (par1ItemStack.func_77951_h()) {
/* 116 */           int i = getFirstEmptyStack(inventory);
/*     */           
/* 118 */           if (i >= 0) {
/* 119 */             inventory.func_70299_a(i, ItemStack.func_77944_b(par1ItemStack));
/* 120 */             par1ItemStack.field_77994_a = 0;
/* 121 */             par1ItemStack.field_77992_b = 5;
/* 122 */             return true;
/*     */           }
/* 124 */           return false;
/*     */         }
/*     */         int i;
/*     */         do {
/* 128 */           i = par1ItemStack.field_77994_a;
/* 129 */           par1ItemStack.field_77994_a = storePartialItemStack(par1ItemStack, inventory);
/* 130 */         } while ((par1ItemStack.field_77994_a > 0) && (par1ItemStack.field_77994_a < i));
/*     */         
/* 132 */         return par1ItemStack.field_77994_a < i;
/*     */       }
/*     */       catch (Throwable throwable) {
/* 135 */         CrashReport crashreport = CrashReport.func_85055_a(throwable, "Adding item to inventory");
/* 136 */         CrashReportCategory crashreportcategory = crashreport.func_85058_a("Item being added");
/* 137 */         crashreportcategory.func_71507_a("Item ID", Integer.valueOf(Item.func_150891_b(par1ItemStack.func_77973_b())));
/* 138 */         crashreportcategory.func_71507_a("Item data", Integer.valueOf(par1ItemStack.func_77960_j()));
/* 139 */         crashreportcategory.func_71500_a("Item name", new Callable() {
/*     */           private static final String __OBFID = "CL_00001710";
/*     */           
/*     */           public String call() {
/* 143 */             return par1ItemStack.func_82833_r();
/*     */           }
/* 145 */         });
/* 146 */         throw new net.minecraft.util.ReportedException(crashreport);
/*     */       }
/*     */     }
/* 149 */     return false;
/*     */   }
/*     */   
/*     */   public int getFirstEmptyStack(IInventory inventory)
/*     */   {
/* 154 */     for (int i = 0; i < inventory.func_70302_i_(); i++) {
/* 155 */       if (inventory.func_70301_a(i) == null) {
/* 156 */         return i;
/*     */       }
/*     */     }
/*     */     
/* 160 */     return -1;
/*     */   }
/*     */   
/*     */   private int storePartialItemStack(ItemStack par1ItemStack, IInventory inventory) {
/* 164 */     Item item = par1ItemStack.func_77973_b();
/* 165 */     int i = par1ItemStack.field_77994_a;
/*     */     
/*     */ 
/* 168 */     if (par1ItemStack.func_77976_d() == 1) {
/* 169 */       int j = getFirstEmptyStack(inventory);
/*     */       
/* 171 */       if (j < 0) {
/* 172 */         return i;
/*     */       }
/* 174 */       if (inventory.func_70301_a(j) == null) {
/* 175 */         inventory.func_70299_a(j, ItemStack.func_77944_b(par1ItemStack));
/*     */       }
/*     */       
/* 178 */       return 0;
/*     */     }
/*     */     
/* 181 */     int j = storeItemStack(par1ItemStack, inventory);
/*     */     
/* 183 */     if (j < 0) {
/* 184 */       j = getFirstEmptyStack(inventory);
/*     */     }
/*     */     
/* 187 */     if (j < 0) {
/* 188 */       return i;
/*     */     }
/* 190 */     if (inventory.func_70301_a(j) == null) {
/* 191 */       inventory.func_70299_a(j, new ItemStack(item, 0, par1ItemStack.func_77960_j()));
/*     */       
/* 193 */       if (par1ItemStack.func_77942_o()) {
/* 194 */         inventory.func_70301_a(j).func_77982_d((NBTTagCompound)par1ItemStack.func_77978_p().func_74737_b());
/*     */       }
/*     */     }
/*     */     
/* 198 */     int k = i;
/*     */     
/* 200 */     if (i > inventory.func_70301_a(j).func_77976_d() - inventory.func_70301_a(j).field_77994_a) {
/* 201 */       k = inventory.func_70301_a(j).func_77976_d() - inventory.func_70301_a(j).field_77994_a;
/*     */     }
/*     */     
/* 204 */     if (k > 64 - inventory.func_70301_a(j).field_77994_a) {
/* 205 */       k = 64 - inventory.func_70301_a(j).field_77994_a;
/*     */     }
/*     */     
/* 208 */     if (k == 0) {
/* 209 */       return i;
/*     */     }
/* 211 */     i -= k;
/* 212 */     inventory.func_70301_a(j).field_77994_a += k;
/* 213 */     inventory.func_70301_a(j).field_77992_b = 5;
/* 214 */     return i;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private int storeItemStack(ItemStack par1ItemStack, IInventory inventory)
/*     */   {
/* 222 */     for (int i = 0; i < inventory.func_70302_i_(); i++)
/*     */     {
/* 224 */       if ((inventory.func_70301_a(i) != null) && (inventory.func_70301_a(i).func_77973_b() == par1ItemStack.func_77973_b()) && (inventory.func_70301_a(i).func_77985_e()) && (inventory.func_70301_a(i).field_77994_a < inventory.func_70301_a(i).func_77976_d()) && (inventory.func_70301_a(i).field_77994_a < 64) && ((!inventory.func_70301_a(i).func_77981_g()) || (inventory.func_70301_a(i).func_77960_j() == par1ItemStack.func_77960_j())) && (ItemStack.func_77970_a(inventory.func_70301_a(i), par1ItemStack)))
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 232 */         return i;
/*     */       }
/*     */     }
/*     */     
/* 236 */     return -1;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/entity/ai/EntityAIDropOffBlocks.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */