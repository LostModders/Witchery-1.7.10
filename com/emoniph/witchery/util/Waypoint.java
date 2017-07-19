/*    */ package com.emoniph.witchery.util;
/*    */ 
/*    */ import com.emoniph.witchery.Witchery;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.WorldProvider;
/*    */ 
/*    */ public class Waypoint
/*    */ {
/*    */   public final boolean valid;
/*    */   public final double X;
/*    */   public final double Y;
/*    */   public final double Z;
/*    */   public final double D;
/*    */   
/*    */   public Waypoint(World world, double homeX, double homeY, double homeZ)
/*    */   {
/* 19 */     this.X = homeX;
/* 20 */     this.Y = homeY;
/* 21 */     this.Z = homeZ;
/* 22 */     this.D = world.field_73011_w.field_76574_g;
/* 23 */     this.valid = true;
/*    */   }
/*    */   
/*    */   public Waypoint(World world, net.minecraft.world.ChunkPosition pos) {
/* 27 */     this.X = pos.field_151329_a;
/* 28 */     this.Y = pos.field_151327_b;
/* 29 */     this.Z = pos.field_151328_c;
/* 30 */     this.D = world.field_73011_w.field_76574_g;
/* 31 */     this.valid = true;
/*    */   }
/*    */   
/*    */   public Waypoint(World world, net.minecraft.item.ItemStack stack, double homeX, double homeY, double homeZ) {
/* 35 */     if (Witchery.Items.GENERIC.itemWaystoneBound.isMatch(stack)) {
/* 36 */       NBTTagCompound nbtWaystone = stack.func_77978_p();
/* 37 */       int x = nbtWaystone.func_74762_e("PosX");
/* 38 */       int z = nbtWaystone.func_74762_e("PosZ");
/*    */       
/* 40 */       if (world.func_72938_d(x, z).field_76636_d) {
/* 41 */         this.X = (x + 0.5D);
/* 42 */         this.Y = (nbtWaystone.func_74762_e("PosY") + 1.5D);
/* 43 */         this.Z = (z + 0.5D);
/* 44 */         this.D = nbtWaystone.func_74762_e("PosD");
/* 45 */         this.valid = true;
/*    */       } else {
/* 47 */         this.X = homeX;
/* 48 */         this.Y = homeY;
/* 49 */         this.Z = homeZ;
/* 50 */         this.D = world.field_73011_w.field_76574_g;
/* 51 */         this.valid = false;
/*    */       }
/*    */     }
/* 54 */     else if (Witchery.Items.GENERIC.itemWaystonePlayerBound.isMatch(stack)) {
/* 55 */       EntityLivingBase entity = Witchery.Items.TAGLOCK_KIT.getBoundEntity(world, null, stack, Integer.valueOf(1));
/* 56 */       if (entity != null) {
/* 57 */         this.X = entity.field_70165_t;
/* 58 */         this.Y = (entity.field_70163_u + 1.0D);
/* 59 */         this.Z = entity.field_70161_v;
/* 60 */         this.D = entity.field_71093_bK;
/* 61 */         this.valid = true;
/*    */       } else {
/* 63 */         this.X = homeX;
/* 64 */         this.Y = homeY;
/* 65 */         this.Z = homeZ;
/* 66 */         this.D = world.field_73011_w.field_76574_g;
/* 67 */         this.valid = false;
/*    */       }
/* 69 */     } else if ((stack != null) && (stack.func_77973_b() == Witchery.Items.TAGLOCK_KIT)) {
/* 70 */       EntityLivingBase entity = Witchery.Items.TAGLOCK_KIT.getBoundEntity(world, null, stack, Integer.valueOf(1));
/* 71 */       if (entity != null) {
/* 72 */         this.X = entity.field_70165_t;
/* 73 */         this.Y = (entity.field_70163_u + 1.0D);
/* 74 */         this.Z = entity.field_70161_v;
/* 75 */         this.D = entity.field_71093_bK;
/* 76 */         this.valid = true;
/*    */       } else {
/* 78 */         this.X = homeX;
/* 79 */         this.Y = homeY;
/* 80 */         this.Z = homeZ;
/* 81 */         this.D = world.field_73011_w.field_76574_g;
/* 82 */         this.valid = false;
/*    */       }
/*    */     } else {
/* 85 */       this.X = homeX;
/* 86 */       this.Y = homeY;
/* 87 */       this.Z = homeZ;
/* 88 */       this.D = world.field_73011_w.field_76574_g;
/* 89 */       this.valid = false;
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/util/Waypoint.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */