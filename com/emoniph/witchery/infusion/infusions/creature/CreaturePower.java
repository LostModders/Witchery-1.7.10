/*     */ package com.emoniph.witchery.infusion.infusions.creature;
/*     */ 
/*     */ import com.emoniph.witchery.infusion.Infusion;
/*     */ import com.emoniph.witchery.util.Log;
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.event.entity.living.LivingFallEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingHurtEvent;
/*     */ 
/*     */ public class CreaturePower
/*     */ {
/*     */   private final int creaturePowerID;
/*     */   private final Class<? extends EntityLiving> creatureType;
/*     */   private static final String BEAST_POWER_KEY = "WITCBeastPower";
/*     */   private static final String BEAST_POWER_CHARGES_KEY = "WITCBeastPowerCharges";
/*     */   protected static final int DEFAULT_CHARGES_PER_SACRIFICE = 10;
/*     */   
/*     */   public CreaturePower(int creaturePowerID, Class<? extends EntityLiving> creatureType)
/*     */   {
/*  24 */     this.creaturePowerID = creaturePowerID;
/*  25 */     this.creatureType = creatureType;
/*     */   }
/*     */   
/*     */   public int getCreaturePowerID() {
/*  29 */     return this.creaturePowerID;
/*     */   }
/*     */   
/*     */   public int activateCost(World world, EntityPlayer player, int elapsedTicks, MovingObjectPosition mop) {
/*  33 */     return 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void onActivate(World world, EntityPlayer player, int elapsedTicks, MovingObjectPosition mop) {}
/*     */   
/*     */ 
/*     */ 
/*     */   public void onUpdate(World world, EntityPlayer player) {}
/*     */   
/*     */ 
/*     */ 
/*     */   public void onDamage(World world, EntityPlayer player, LivingHurtEvent event) {}
/*     */   
/*     */ 
/*     */ 
/*     */   public void onFalling(World worldObj, EntityPlayer player, LivingFallEvent event) {}
/*     */   
/*     */ 
/*     */ 
/*     */   public static int getCreaturePowerID(EntityPlayer player)
/*     */   {
/*  56 */     NBTTagCompound nbt = Infusion.getNBT(player);
/*  57 */     return nbt.func_74762_e("WITCBeastPower");
/*     */   }
/*     */   
/*     */   public static void setCreaturePowerID(EntityPlayer playerEntity, int beastPower, int beastCharges) {
/*  61 */     NBTTagCompound nbt = Infusion.getNBT(playerEntity);
/*  62 */     if (beastPower > 0) {
/*  63 */       nbt.func_74768_a("WITCBeastPower", beastPower);
/*  64 */       nbt.func_74768_a("WITCBeastPowerCharges", beastCharges);
/*     */     } else {
/*  66 */       if (nbt.func_74764_b("WITCBeastPower")) {
/*  67 */         nbt.func_82580_o("WITCBeastPower");
/*     */       }
/*     */       
/*  70 */       if (nbt.func_74764_b("WITCBeastPowerCharges")) {
/*  71 */         nbt.func_82580_o("WITCBeastPowerCharges");
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static int getCreaturePowerCharges(EntityPlayer player) {
/*  77 */     NBTTagCompound nbt = Infusion.getNBT(player);
/*  78 */     if ((nbt.func_74764_b("WITCBeastPower")) && (nbt.func_74764_b("WITCBeastPowerCharges"))) {
/*  79 */       return nbt.func_74762_e("WITCBeastPowerCharges");
/*     */     }
/*  81 */     return 0;
/*     */   }
/*     */   
/*     */   public static void setCreaturePowerCharges(EntityPlayer player, int charges) {
/*  85 */     NBTTagCompound nbt = Infusion.getNBT(player);
/*  86 */     nbt.func_74768_a("WITCBeastPowerCharges", charges);
/*     */   }
/*     */   
/*     */   public net.minecraft.util.IIcon getPowerBarIcon(World worldObj, EntityPlayer player) {
/*  90 */     return net.minecraft.init.Blocks.field_150435_aG.func_149691_a(0, 0);
/*     */   }
/*     */   
/*     */   public static class Registry
/*     */   {
/*  95 */     private static final Registry INSTANCE = new Registry();
/*     */     
/*     */     public static Registry instance() {
/*  98 */       return INSTANCE;
/*     */     }
/*     */     
/* 101 */     private ArrayList<CreaturePower> registry = new ArrayList();
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     public void add(CreaturePower power)
/*     */     {
/* 108 */       if (power.creaturePowerID == this.registry.size() + 1) {
/* 109 */         this.registry.add(power);
/* 110 */       } else if (power.creaturePowerID > this.registry.size() + 1) {
/* 111 */         for (int i = this.registry.size(); i < power.creaturePowerID; i++) {
/* 112 */           this.registry.add(null);
/*     */         }
/* 114 */         this.registry.add(power);
/*     */       } else {
/* 116 */         CreaturePower existingPower = (CreaturePower)this.registry.get(power.creaturePowerID);
/* 117 */         if (existingPower != null) {
/* 118 */           Log.instance().warning(String.format("Creature power %s at id %d is being overwritten by another creature power %s.", new Object[] { existingPower, Integer.valueOf(power.creaturePowerID), power }));
/*     */         }
/*     */         
/*     */ 
/* 122 */         this.registry.set(power.creaturePowerID, power);
/*     */       }
/*     */     }
/*     */     
/*     */     public CreaturePower get(EntityLiving creature) {
/* 127 */       for (CreaturePower power : this.registry) {
/* 128 */         if ((power != null) && (power.creatureType == creature.getClass())) {
/* 129 */           return power;
/*     */         }
/*     */       }
/* 132 */       return null;
/*     */     }
/*     */     
/*     */     public CreaturePower get(int creaturePowerID) {
/* 136 */       return (CreaturePower)this.registry.get(creaturePowerID - 1);
/*     */     }
/*     */   }
/*     */   
/*     */   public int getChargesPerSacrifice()
/*     */   {
/* 142 */     return 10;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/infusion/infusions/creature/CreaturePower.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */