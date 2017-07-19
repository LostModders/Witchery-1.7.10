/*     */ package com.emoniph.witchery.infusion;
/*     */ 
/*     */ import com.emoniph.witchery.util.TimeUtil;
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class InfusedBrewEffect
/*     */ {
/*  16 */   public static final ArrayList<InfusedBrewEffect> brewList = new ArrayList();
/*     */   
/*  18 */   public static final InfusedBrewEffect Soaring = new InfusedBrewSoaringEffect(1, 144000L);
/*  19 */   public static final InfusedBrewEffect Grave = new InfusedBrewGraveEffect(2, 144000L);
/*     */   public final int id;
/*     */   public final long durationTicks;
/*     */   public final int imageMapX;
/*     */   public final int imageMapY;
/*     */   
/*     */   protected InfusedBrewEffect(int id, long durationMS, int imageX, int imageY)
/*     */   {
/*  27 */     this.id = id;
/*  28 */     this.durationTicks = durationMS;
/*  29 */     this.imageMapX = imageX;
/*  30 */     this.imageMapY = imageY;
/*  31 */     while (brewList.size() <= id) {
/*  32 */       brewList.add(null);
/*     */     }
/*  34 */     brewList.set(id, this);
/*     */   }
/*     */   
/*     */   public void drunk(World world, EntityPlayer player, ItemStack itemstack) {
/*  38 */     setActiveBrew(this, player, true);
/*  39 */     immediateEffect(world, player, itemstack);
/*     */   }
/*     */   
/*     */   public abstract void immediateEffect(World paramWorld, EntityPlayer paramEntityPlayer, ItemStack paramItemStack);
/*     */   
/*     */   public abstract void regularEffect(World paramWorld, EntityPlayer paramEntityPlayer);
/*     */   
/*     */   public boolean tryUseEffect(EntityPlayer player, MovingObjectPosition mop) {
/*  47 */     return isActive(player);
/*     */   }
/*     */   
/*     */   public boolean isActive(EntityPlayer player) {
/*  51 */     return getActiveBrew(player) == this;
/*     */   }
/*     */   
/*  54 */   private static String BREW_TYPE_KEY = "WITCInfusedBrewType";
/*  55 */   private static String BREW_START_KEY = "WITCInfusedBrewStart";
/*  56 */   private static String BREW_MINS_LEFT_KEY = "WITCInfusedBrewMinesLeft";
/*     */   
/*     */   public static InfusedBrewEffect getActiveBrew(EntityPlayer player) {
/*  59 */     if (player != null) {
/*  60 */       NBTTagCompound nbtPlayer = Infusion.getNBT(player);
/*  61 */       return getActiveBrew(nbtPlayer);
/*     */     }
/*  63 */     return null;
/*     */   }
/*     */   
/*     */   public static InfusedBrewEffect getActiveBrew(NBTTagCompound nbtPlayer) {
/*  67 */     if (nbtPlayer != null) {
/*  68 */       int brewID = nbtPlayer.func_74762_e(BREW_TYPE_KEY);
/*  69 */       if (brewID > 0) {
/*  70 */         return (InfusedBrewEffect)brewList.get(brewID);
/*     */       }
/*     */     }
/*  73 */     return null;
/*     */   }
/*     */   
/*     */   public static long getActiveBrewStartTime(NBTTagCompound nbtPlayer) {
/*  77 */     if (nbtPlayer != null) {
/*  78 */       long startTime = nbtPlayer.func_74763_f(BREW_START_KEY);
/*  79 */       return startTime;
/*     */     }
/*  81 */     return 0L;
/*     */   }
/*     */   
/*     */   public static String getMinutesRemaining(World world, NBTTagCompound nbtPlayer, InfusedBrewEffect effect) {
/*  85 */     if (nbtPlayer != null) {
/*  86 */       long minsLeft = nbtPlayer.func_74763_f(BREW_MINS_LEFT_KEY);
/*  87 */       return String.format("%d", new Object[] { Long.valueOf(minsLeft) });
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  95 */     return "";
/*     */   }
/*     */   
/*     */   public static void setActiveBrew(InfusedBrewEffect brew, EntityPlayer player, boolean sync) {
/*  99 */     if (player != null) {
/* 100 */       NBTTagCompound nbtPlayer = Infusion.getNBT(player);
/* 101 */       setActiveBrew(player.field_70170_p, player, nbtPlayer, brew, sync);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void setActiveBrew(World world, EntityPlayer player, NBTTagCompound nbtPlayer, InfusedBrewEffect brew, boolean sync) {
/* 106 */     if ((nbtPlayer != null) && (!world.field_72995_K))
/*     */     {
/* 108 */       nbtPlayer.func_74768_a(BREW_TYPE_KEY, brew.id);
/* 109 */       nbtPlayer.func_74772_a(BREW_START_KEY, TimeUtil.getServerTimeInTicks());
/*     */       
/* 111 */       if (sync) {
/* 112 */         Infusion.syncPlayer(world, player);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static void setActiveBrewInfo(NBTTagCompound nbtPlayer, int brewID, long startTime) {
/* 118 */     nbtPlayer.func_74768_a(BREW_TYPE_KEY, brewID);
/* 119 */     nbtPlayer.func_74772_a(BREW_MINS_LEFT_KEY, startTime);
/*     */   }
/*     */   
/*     */   public static void checkActiveEffects(World world, EntityPlayer player, NBTTagCompound nbtPlayer, boolean sync, long currentTime)
/*     */   {
/* 124 */     if ((nbtPlayer != null) && (!world.field_72995_K)) {
/* 125 */       InfusedBrewEffect activeEffect = getActiveBrew(nbtPlayer);
/* 126 */       if (activeEffect != null) {
/* 127 */         long startTime = nbtPlayer.func_74763_f(BREW_START_KEY);
/* 128 */         if (currentTime > startTime + activeEffect.durationTicks) {
/* 129 */           nbtPlayer.func_82580_o(BREW_START_KEY);
/* 130 */           nbtPlayer.func_82580_o(BREW_TYPE_KEY);
/*     */           
/* 132 */           Infusion.syncPlayer(world, player);
/* 133 */           return;
/*     */         }
/* 135 */         activeEffect.regularEffect(world, player);
/*     */         
/*     */ 
/* 138 */         if (sync) {
/* 139 */           Infusion.syncPlayer(world, player);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/infusion/InfusedBrewEffect.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */