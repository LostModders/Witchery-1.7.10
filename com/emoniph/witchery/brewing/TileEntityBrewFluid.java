/*    */ package com.emoniph.witchery.brewing;
/*    */ 
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.network.NetworkManager;
/*    */ import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ 
/*    */ public class TileEntityBrewFluid extends TileEntity
/*    */ {
/*    */   NBTTagCompound nbtEffect;
/*    */   int color;
/*    */   int duration;
/*    */   int expansion;
/*    */   int updateCount;
/*    */   String thrower;
/*    */   
/*    */   public void initalise(ModifiersImpact impactModifiers, NBTTagCompound nbtBrew)
/*    */   {
/* 19 */     if (nbtBrew != null) {
/* 20 */       this.nbtEffect = ((NBTTagCompound)nbtBrew.func_74737_b());
/*    */     }
/* 22 */     this.color = WitcheryBrewRegistry.INSTANCE.getBrewColor(this.nbtEffect);
/* 23 */     this.duration = (impactModifiers.lifetime >= 0 ? 5 + impactModifiers.lifetime * impactModifiers.lifetime * 5 : 100);
/* 24 */     this.expansion = Math.min(4 + impactModifiers.extent, 10);
/* 25 */     if (impactModifiers.thrower != null) {
/* 26 */       this.thrower = impactModifiers.thrower.func_70005_c_();
/*    */     }
/* 28 */     this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*    */   }
/*    */   
/*    */   public net.minecraft.network.Packet func_145844_m()
/*    */   {
/* 33 */     NBTTagCompound nbtTag = new NBTTagCompound();
/* 34 */     func_145841_b(nbtTag);
/* 35 */     return new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, nbtTag);
/*    */   }
/*    */   
/*    */   public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet)
/*    */   {
/* 40 */     super.onDataPacket(net, packet);
/* 41 */     func_145839_a(packet.func_148857_g());
/* 42 */     this.field_145850_b.func_147479_m(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*    */   }
/*    */   
/*    */   public void func_145841_b(NBTTagCompound nbtRoot)
/*    */   {
/* 47 */     super.func_145841_b(nbtRoot);
/* 48 */     if (this.nbtEffect != null) {
/* 49 */       nbtRoot.func_74782_a("Effect", this.nbtEffect);
/*    */     }
/* 51 */     nbtRoot.func_74768_a("Color", this.color);
/* 52 */     nbtRoot.func_74768_a("Duration", this.duration);
/* 53 */     nbtRoot.func_74768_a("Expansion", this.expansion);
/* 54 */     if (this.thrower != null) {
/* 55 */       nbtRoot.func_74778_a("Thrower", this.thrower);
/*    */     }
/*    */   }
/*    */   
/*    */   public void func_145839_a(NBTTagCompound nbtRoot)
/*    */   {
/* 61 */     super.func_145839_a(nbtRoot);
/* 62 */     if (nbtRoot.func_74764_b("Effect")) {
/* 63 */       this.nbtEffect = nbtRoot.func_74775_l("Effect");
/*    */     }
/* 65 */     this.color = nbtRoot.func_74762_e("Color");
/* 66 */     this.duration = nbtRoot.func_74762_e("Duration");
/* 67 */     this.expansion = nbtRoot.func_74762_e("Expansion");
/* 68 */     this.thrower = nbtRoot.func_74779_i("Thrower");
/*    */   }
/*    */   
/*    */   public boolean canUpdate()
/*    */   {
/* 73 */     return false;
/*    */   }
/*    */   
/* 76 */   private int runTicks = 0;
/*    */   
/*    */   public int incRunTicks() {
/* 79 */     return ++this.runTicks;
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/TileEntityBrewFluid.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */