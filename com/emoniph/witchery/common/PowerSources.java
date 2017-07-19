/*     */ package com.emoniph.witchery.common;
/*     */ 
/*     */ import com.emoniph.witchery.util.Coord;
/*     */ import com.emoniph.witchery.util.Log;
/*     */ import cpw.mods.fml.common.FMLCommonHandler;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ 
/*     */ public class PowerSources
/*     */ {
/*     */   private static PowerSources INSTANCE_CLIENT;
/*     */   private static PowerSources INSTANCE_SERVER;
/*     */   
/*     */   public static PowerSources instance()
/*     */   {
/*  20 */     if (FMLCommonHandler.instance().getEffectiveSide() == cpw.mods.fml.relauncher.Side.SERVER) {
/*  21 */       return INSTANCE_SERVER;
/*     */     }
/*     */     
/*  24 */     return INSTANCE_CLIENT;
/*     */   }
/*     */   
/*     */   public static void initiate() {
/*  28 */     INSTANCE_CLIENT = new PowerSources();
/*  29 */     INSTANCE_SERVER = new PowerSources();
/*     */   }
/*     */   
/*     */ 
/*     */   public static class RelativePowerSource
/*     */   {
/*     */     private final IPowerSource powerSource;
/*     */     private final double distanceSq;
/*     */     private final double rangeSq;
/*     */     
/*     */     public RelativePowerSource(IPowerSource powerSource, Coord relativeLocation)
/*     */     {
/*  41 */       this.powerSource = powerSource;
/*  42 */       this.distanceSq = relativeLocation.distanceSqTo(this.powerSource.getLocation());
/*  43 */       double range = powerSource.getRange();
/*  44 */       this.rangeSq = (range * range);
/*     */     }
/*     */     
/*     */     public boolean equals(Object obj)
/*     */     {
/*  49 */       if (obj == this)
/*  50 */         return true;
/*  51 */       if ((obj == null) || (obj.getClass() != getClass())) {
/*  52 */         return false;
/*     */       }
/*  54 */       return ((RelativePowerSource)obj).powerSource == this.powerSource;
/*     */     }
/*     */     
/*     */     public boolean isInWorld(World world)
/*     */     {
/*  59 */       return this.powerSource.getWorld() == world;
/*     */     }
/*     */     
/*     */     public IPowerSource source() {
/*  63 */       return this.powerSource;
/*     */     }
/*     */     
/*     */     public boolean isInRange() {
/*  67 */       return this.distanceSq <= this.rangeSq;
/*     */     }
/*     */   }
/*     */   
/*  71 */   private final ArrayList<IPowerSource> powerSources = new ArrayList();
/*  72 */   private final ArrayList<INullSource> nullSources = new ArrayList();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getDebugData()
/*     */   {
/*  79 */     StringBuilder sb = new StringBuilder();
/*  80 */     for (IPowerSource source : this.powerSources) {
/*  81 */       if (sb.length() > 0) {
/*  82 */         sb.append('\n');
/*     */       }
/*  84 */       sb.append(String.format("Altar (%s) [dim=%d] power=%f", new Object[] { source.getLocation(), Integer.valueOf(source.getWorld().field_73011_w.field_76574_g), Float.valueOf(source.getCurrentPower()) }));
/*     */     }
/*     */     
/*  87 */     return sb.length() > 0 ? sb.insert(0, "** ALTARS **\n").toString() : "No power sources";
/*     */   }
/*     */   
/*     */   public void registerPowerSource(IPowerSource powerSource) {
/*  91 */     if (!this.powerSources.contains(powerSource)) {
/*     */       try {
/*  93 */         Iterator<IPowerSource> it = this.powerSources.iterator();
/*  94 */         while (it.hasNext()) {
/*  95 */           IPowerSource source = (IPowerSource)it.next();
/*     */           
/*  97 */           if ((source == null) || (source.isPowerInvalid()) || (source.getLocation().equals(powerSource.getLocation())))
/*     */           {
/*  99 */             it.remove();
/*     */           }
/*     */         }
/*     */       } catch (Throwable e) {
/* 103 */         Log.instance().warning(e, "Exception occured validating existing power source entries");
/*     */       }
/* 105 */       this.powerSources.add(powerSource);
/*     */     }
/*     */   }
/*     */   
/*     */   public void removePowerSource(IPowerSource powerSource) {
/* 110 */     if (this.powerSources.contains(powerSource)) {
/* 111 */       this.powerSources.remove(powerSource);
/*     */     }
/*     */     try {
/* 114 */       Iterator<IPowerSource> it = this.powerSources.iterator();
/* 115 */       while (it.hasNext()) {
/* 116 */         IPowerSource source = (IPowerSource)it.next();
/*     */         
/* 118 */         if ((source == null) || (source.isPowerInvalid())) {
/* 119 */           it.remove();
/* 120 */         } else if (source.getLocation().getBlockTileEntity(source.getWorld()) != source) {
/* 121 */           it.remove();
/*     */         }
/*     */       }
/*     */     } catch (Throwable e) {
/* 125 */       Log.instance().warning(e, "Exception occured removing existing power source entries");
/*     */     }
/*     */   }
/*     */   
/*     */   public ArrayList<RelativePowerSource> get(World world, Coord location, int radius) {
/* 130 */     ArrayList<RelativePowerSource> nearbyPowerSources = new ArrayList();
/* 131 */     double radiusSq = radius * radius;
/*     */     
/* 133 */     for (IPowerSource registeredSource : this.powerSources) {
/* 134 */       RelativePowerSource powerSource = new RelativePowerSource(registeredSource, location);
/* 135 */       if ((powerSource.isInWorld(world)) && (powerSource.isInRange())) {
/* 136 */         nearbyPowerSources.add(powerSource);
/*     */       }
/*     */     }
/*     */     
/* 140 */     java.util.Collections.sort(nearbyPowerSources, new java.util.Comparator()
/*     */     {
/*     */       public int compare(PowerSources.RelativePowerSource a, PowerSources.RelativePowerSource b) {
/* 143 */         return Double.compare(a.distanceSq, b.distanceSq);
/*     */       }
/*     */       
/* 146 */     });
/* 147 */     return nearbyPowerSources;
/*     */   }
/*     */   
/*     */   public void registerNullSource(INullSource nullSource) {
/* 151 */     if (!this.nullSources.contains(nullSource)) {
/* 152 */       Coord newLocation = new Coord(nullSource);
/*     */       try {
/* 154 */         Iterator<INullSource> it = this.nullSources.iterator();
/* 155 */         while (it.hasNext()) {
/* 156 */           INullSource source = (INullSource)it.next();
/*     */           
/* 158 */           if ((source == null) || (source.isPowerInvalid()) || (new Coord(source).equals(newLocation))) {
/* 159 */             it.remove();
/*     */           }
/*     */         }
/*     */       } catch (Throwable e) {
/* 163 */         Log.instance().warning(e, "Exception occured validating existing null source entries");
/*     */       }
/* 165 */       this.nullSources.add(nullSource);
/*     */     }
/*     */   }
/*     */   
/*     */   public void removeNullSource(INullSource nullSource) {
/* 170 */     if (this.nullSources.contains(nullSource)) {
/* 171 */       this.nullSources.remove(nullSource);
/*     */     }
/*     */     try
/*     */     {
/* 175 */       Iterator<INullSource> it = this.nullSources.iterator();
/* 176 */       while (it.hasNext()) {
/* 177 */         INullSource source = (INullSource)it.next();
/*     */         
/* 179 */         if ((source == null) || (source.isPowerInvalid())) {
/* 180 */           it.remove();
/* 181 */         } else if (new Coord(source).getBlockTileEntity(source.getWorld()) != source) {
/* 182 */           it.remove();
/*     */         }
/*     */       }
/*     */     } catch (Throwable e) {
/* 186 */       Log.instance().warning(e, "Exception occured removing existing null source entries");
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean isAreaNulled(World world, int posX, int posY, int posZ) {
/* 191 */     for (INullSource source : this.nullSources) {
/* 192 */       double rangeSq = source.getRange() * source.getRange();
/* 193 */       if (Coord.distanceSq(posX, posY, posZ, source.getPosX(), source.getPosY(), source.getPosZ()) < rangeSq) {
/* 194 */         return true;
/*     */       }
/*     */     }
/*     */     
/* 198 */     return false;
/*     */   }
/*     */   
/*     */   public static IPowerSource findClosestPowerSource(World world, int posX, int posY, int posZ) {
/* 202 */     List<RelativePowerSource> sources = instance() != null ? instance().get(world, new Coord(posX, posY, posZ), 16) : null;
/*     */     
/* 204 */     return (sources != null) && (sources.size() > 0) ? ((RelativePowerSource)sources.get(0)).source() : null;
/*     */   }
/*     */   
/*     */   public static IPowerSource findClosestPowerSource(World world, Coord coord) {
/* 208 */     return findClosestPowerSource(world, coord.x, coord.y, coord.z);
/*     */   }
/*     */   
/*     */   public static IPowerSource findClosestPowerSource(TileEntity tile) {
/* 212 */     return findClosestPowerSource(tile.func_145831_w(), tile.field_145851_c, tile.field_145848_d, tile.field_145849_e);
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/common/PowerSources.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */