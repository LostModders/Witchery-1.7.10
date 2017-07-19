/*     */ package com.emoniph.witchery.worldgen;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryBlocks;
/*     */ import com.emoniph.witchery.entity.EntityCovenWitch;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.gen.structure.StructureBoundingBox;
/*     */ import net.minecraft.world.gen.structure.StructureComponent;
/*     */ import net.minecraft.world.gen.structure.StructureVillagePieces.House1;
/*     */ import net.minecraft.world.gen.structure.StructureVillagePieces.Start;
/*     */ 
/*     */ public class ComponentVillageWitchHut
/*     */   extends StructureVillagePieces.House1
/*     */ {
/*     */   private boolean isTallHouse;
/*     */   private int tablePosition;
/*     */   
/*     */   public ComponentVillageWitchHut() {}
/*     */   
/*     */   public ComponentVillageWitchHut(StructureVillagePieces.Start par1ComponentVillageStartPiece, int par2, Random par3Random, StructureBoundingBox par4StructureBoundingBox, int par5)
/*     */   {
/*  26 */     super(par1ComponentVillageStartPiece, par2, par3Random, par4StructureBoundingBox, par5);
/*  27 */     this.field_74885_f = par5;
/*  28 */     this.field_74887_e = par4StructureBoundingBox;
/*  29 */     this.isTallHouse = true;
/*  30 */     this.tablePosition = (par3Random.nextInt(2) + 1);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_143012_a(NBTTagCompound par1NBTTagCompound)
/*     */   {
/*  36 */     super.func_143012_a(par1NBTTagCompound);
/*  37 */     par1NBTTagCompound.func_74768_a("T", this.tablePosition);
/*  38 */     par1NBTTagCompound.func_74757_a("C", this.isTallHouse);
/*  39 */     par1NBTTagCompound.func_74768_a("WITCWCount", this.witchesSpawned);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_143011_b(NBTTagCompound par1NBTTagCompound)
/*     */   {
/*  45 */     super.func_143011_b(par1NBTTagCompound);
/*  46 */     this.tablePosition = par1NBTTagCompound.func_74762_e("T");
/*  47 */     this.isTallHouse = par1NBTTagCompound.func_74767_n("C");
/*  48 */     this.witchesSpawned = par1NBTTagCompound.func_74762_e("WITCWCount");
/*     */   }
/*     */   
/*     */   public static ComponentVillageWitchHut buildComponent(StructureVillagePieces.Start par0ComponentVillageStartPiece, List par1List, Random par2Random, int par3, int par4, int par5, int par6, int par7)
/*     */   {
/*  53 */     StructureBoundingBox structureboundingbox = StructureBoundingBox.func_78889_a(par3, par4, par5, 0, 0, 0, 4, 6, 5, par6);
/*  54 */     return (func_74895_a(structureboundingbox)) && (StructureComponent.func_74883_a(par1List, structureboundingbox) == null) ? new ComponentVillageWitchHut(par0ComponentVillageStartPiece, par7, par2Random, structureboundingbox, par6) : null;
/*     */   }
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
/*     */   public boolean func_74875_a(World par1World, Random par2Random, StructureBoundingBox par3StructureBoundingBox)
/*     */   {
/*  73 */     if (this.field_143015_k < 0)
/*     */     {
/*  75 */       this.field_143015_k = func_74889_b(par1World, par3StructureBoundingBox);
/*     */       
/*  77 */       if (this.field_143015_k < 0)
/*     */       {
/*  79 */         return true;
/*     */       }
/*     */       
/*  82 */       this.field_74887_e.func_78886_a(0, this.field_143015_k - this.field_74887_e.field_78894_e + 6 - 1, 0);
/*     */     }
/*     */     
/*  85 */     func_151549_a(par1World, par3StructureBoundingBox, 1, 1, 1, 3, 5, 4, Blocks.field_150350_a, Blocks.field_150350_a, false);
/*  86 */     func_151549_a(par1World, par3StructureBoundingBox, 0, 0, 0, 3, 0, 4, Blocks.field_150347_e, Blocks.field_150347_e, false);
/*  87 */     func_151549_a(par1World, par3StructureBoundingBox, 1, 0, 1, 2, 0, 3, Blocks.field_150347_e, Blocks.field_150347_e, false);
/*     */     
/*  89 */     if (!this.isTallHouse)
/*     */     {
/*  91 */       func_151556_a(par1World, par3StructureBoundingBox, 1, 4, 1, 2, 4, 3, Blocks.field_150344_f, 1, Blocks.field_150344_f, 1, false);
/*     */     }
/*     */     else
/*     */     {
/*  95 */       func_151556_a(par1World, par3StructureBoundingBox, 1, 5, 1, 2, 5, 3, Blocks.field_150344_f, 1, Blocks.field_150344_f, 1, false);
/*     */     }
/*     */     
/*  98 */     func_151550_a(par1World, Blocks.field_150344_f, 1, 1, 4, 0, par3StructureBoundingBox);
/*  99 */     func_151550_a(par1World, Blocks.field_150344_f, 1, 2, 4, 0, par3StructureBoundingBox);
/* 100 */     func_151550_a(par1World, Blocks.field_150344_f, 1, 1, 4, 4, par3StructureBoundingBox);
/* 101 */     func_151550_a(par1World, Blocks.field_150344_f, 1, 2, 4, 4, par3StructureBoundingBox);
/* 102 */     func_151550_a(par1World, Blocks.field_150344_f, 1, 0, 4, 1, par3StructureBoundingBox);
/* 103 */     func_151550_a(par1World, Blocks.field_150344_f, 1, 0, 4, 2, par3StructureBoundingBox);
/* 104 */     func_151550_a(par1World, Blocks.field_150344_f, 1, 0, 4, 3, par3StructureBoundingBox);
/* 105 */     func_151550_a(par1World, Blocks.field_150344_f, 1, 3, 4, 1, par3StructureBoundingBox);
/* 106 */     func_151550_a(par1World, Blocks.field_150344_f, 1, 3, 4, 2, par3StructureBoundingBox);
/* 107 */     func_151550_a(par1World, Blocks.field_150344_f, 1, 3, 4, 3, par3StructureBoundingBox);
/* 108 */     func_151556_a(par1World, par3StructureBoundingBox, 0, 1, 0, 0, 3, 0, Blocks.field_150344_f, 2, Blocks.field_150344_f, 2, false);
/* 109 */     func_151556_a(par1World, par3StructureBoundingBox, 3, 1, 0, 3, 3, 0, Blocks.field_150344_f, 2, Blocks.field_150344_f, 2, false);
/* 110 */     func_151556_a(par1World, par3StructureBoundingBox, 0, 1, 4, 0, 3, 4, Blocks.field_150344_f, 2, Blocks.field_150344_f, 2, false);
/* 111 */     func_151556_a(par1World, par3StructureBoundingBox, 3, 1, 4, 3, 3, 4, Blocks.field_150344_f, 2, Blocks.field_150344_f, 2, false);
/* 112 */     func_151550_a(par1World, Blocks.field_150344_f, 1, 0, 3, 0, par3StructureBoundingBox);
/* 113 */     func_151550_a(par1World, Blocks.field_150344_f, 1, 3, 3, 0, par3StructureBoundingBox);
/* 114 */     func_151550_a(par1World, Blocks.field_150344_f, 1, 0, 3, 4, par3StructureBoundingBox);
/* 115 */     func_151550_a(par1World, Blocks.field_150344_f, 1, 3, 3, 4, par3StructureBoundingBox);
/* 116 */     func_151549_a(par1World, par3StructureBoundingBox, 0, 1, 1, 0, 3, 3, Blocks.field_150344_f, Blocks.field_150344_f, false);
/* 117 */     func_151549_a(par1World, par3StructureBoundingBox, 3, 1, 1, 3, 3, 3, Blocks.field_150344_f, Blocks.field_150344_f, false);
/* 118 */     func_151549_a(par1World, par3StructureBoundingBox, 1, 1, 0, 2, 3, 0, Blocks.field_150344_f, Blocks.field_150344_f, false);
/* 119 */     func_151549_a(par1World, par3StructureBoundingBox, 1, 1, 4, 2, 3, 4, Blocks.field_150344_f, Blocks.field_150344_f, false);
/* 120 */     func_151550_a(par1World, Blocks.field_150410_aZ, 0, 0, 2, 2, par3StructureBoundingBox);
/* 121 */     func_151550_a(par1World, Blocks.field_150410_aZ, 0, 3, 2, 2, par3StructureBoundingBox);
/*     */     
/* 123 */     if (this.tablePosition > 0)
/*     */     {
/*     */ 
/*     */ 
/* 127 */       func_151550_a(par1World, Blocks.field_150383_bp, 3, 1, 1, 3, par3StructureBoundingBox);
/* 128 */       func_151550_a(par1World, Witchery.Blocks.LOG, 0, 2, 1, 3, par3StructureBoundingBox);
/* 129 */       func_151550_a(par1World, Blocks.field_150457_bL, 4, 2, 2, 3, par3StructureBoundingBox);
/*     */     }
/*     */     
/* 132 */     func_151550_a(par1World, Blocks.field_150350_a, 0, 1, 1, 0, par3StructureBoundingBox);
/* 133 */     func_151550_a(par1World, Blocks.field_150350_a, 0, 1, 2, 0, par3StructureBoundingBox);
/* 134 */     func_74881_a(par1World, par3StructureBoundingBox, par2Random, 1, 1, 0, func_151555_a(Blocks.field_150466_ao, 1));
/*     */     
/* 136 */     if ((func_151548_a(par1World, 1, 0, -1, par3StructureBoundingBox) == Blocks.field_150350_a) && (func_151548_a(par1World, 1, -1, -1, par3StructureBoundingBox) != Blocks.field_150350_a))
/*     */     {
/* 138 */       func_151550_a(par1World, Blocks.field_150446_ar, func_151555_a(Blocks.field_150446_ar, 3), 1, 0, -1, par3StructureBoundingBox);
/*     */     }
/*     */     
/* 141 */     for (int i = 0; i < 5; i++)
/*     */     {
/* 143 */       for (int j = 0; j < 4; j++)
/*     */       {
/* 145 */         func_74871_b(par1World, j, 6, i, par3StructureBoundingBox);
/* 146 */         func_151554_b(par1World, Blocks.field_150347_e, 0, j, -1, i, par3StructureBoundingBox);
/*     */       }
/*     */     }
/*     */     
/* 150 */     spawnWitches(par1World, par3StructureBoundingBox, 1, 1, 2, 1);
/* 151 */     return true;
/*     */   }
/*     */   
/* 154 */   private int witchesSpawned = 0;
/*     */   
/*     */   private void spawnWitches(World par1World, StructureBoundingBox par2StructureBoundingBox, int par3, int par4, int par5, int par6) {
/* 157 */     if (this.witchesSpawned < par6)
/*     */     {
/* 159 */       for (int i1 = this.witchesSpawned; i1 < par6; i1++)
/*     */       {
/* 161 */         int j1 = func_74865_a(par3 + i1, par5);
/* 162 */         int k1 = func_74862_a(par4);
/* 163 */         int l1 = func_74873_b(par3 + i1, par5);
/*     */         
/* 165 */         if (!par2StructureBoundingBox.func_78890_b(j1, k1, l1)) {
/*     */           break;
/*     */         }
/*     */         
/*     */ 
/* 170 */         this.witchesSpawned += 1;
/* 171 */         EntityCovenWitch entityvillager = new EntityCovenWitch(par1World);
/* 172 */         entityvillager.func_70012_b(j1 + 0.5D, k1, l1 + 0.5D, 0.0F, 0.0F);
/* 173 */         entityvillager.func_110163_bv();
/* 174 */         par1World.func_72838_d(entityvillager);
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/worldgen/ComponentVillageWitchHut.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */