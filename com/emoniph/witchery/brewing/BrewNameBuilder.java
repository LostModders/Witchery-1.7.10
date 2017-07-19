/*     */ package com.emoniph.witchery.brewing;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.util.StatCollector;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BrewNameBuilder
/*     */ {
/*     */   int dispersalExtent;
/*     */   int dispersalDuration;
/*     */   int strength;
/*     */   int durationModifier;
/*     */   int totalStrength;
/*     */   int totalDuration;
/*     */   boolean inverted;
/*     */   private boolean terse;
/*     */   
/*     */   public BrewNameBuilder(boolean terse)
/*     */   {
/*  23 */     this.terse = terse;
/*     */   }
/*     */   
/*     */   private static class Part {
/*     */     String base;
/*     */     long duration;
/*     */     
/*     */     private Part(String base, long duration) {
/*  31 */       this.base = base;
/*  32 */       this.duration = duration;
/*     */     }
/*     */     
/*     */     public String toString(boolean splash, boolean terse) {
/*  36 */       long modDuration = (splash) && (this.duration > 0L) ? this.duration / 2L : this.duration;
/*  37 */       if ((!terse) && (modDuration > 0L)) {
/*  38 */         return this.base + " [" + BrewNameBuilder.ticksToElapsedTime((int)modDuration) + "]";
/*     */       }
/*  40 */       return this.base;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   private static String ticksToElapsedTime(int p_76337_0_)
/*     */   {
/*  47 */     int j = p_76337_0_ / 20;
/*  48 */     int k = j / 60;
/*  49 */     j %= 60;
/*  50 */     return k + ":" + j;
/*     */   }
/*     */   
/*  53 */   ArrayList<Part> parts = new ArrayList();
/*  54 */   ArrayList<String> prefixes = new ArrayList();
/*  55 */   ArrayList<String> postfixes = new ArrayList();
/*     */   boolean removePowerCeiling;
/*     */   
/*     */   public void append(String text, String invertedText, long duration, long invertedDuration) {
/*  59 */     StringBuilder builder = new StringBuilder();
/*  60 */     if (this.inverted) {
/*  61 */       builder.append(invertedText);
/*     */     } else {
/*  63 */       builder.append(text);
/*     */     }
/*  65 */     this.inverted = false;
/*     */     
/*  67 */     if ((!this.terse) && (this.strength > 0)) {
/*  68 */       builder.append(" ");
/*  69 */       builder.append(StatCollector.func_74838_a("potion.potency." + this.strength));
/*     */     }
/*  71 */     this.strength = 0;
/*     */     
/*  73 */     this.parts.add(new Part(builder.toString(), duration * (this.durationModifier + 1), null));
/*  74 */     this.durationModifier = 0;
/*     */   }
/*     */   
/*     */   public void appendPrefix(String text) {
/*  78 */     this.prefixes.add(text);
/*     */     
/*  80 */     if ((!this.terse) && (this.dispersalExtent > 0)) {
/*  81 */       this.prefixes.add(StatCollector.func_74838_a("potion.potency." + this.dispersalExtent));
/*     */     }
/*  83 */     this.dispersalExtent = 0;
/*     */     
/*  85 */     if ((!this.terse) && (this.dispersalDuration > 0)) {
/*  86 */       this.prefixes.add(String.format("[%s %s]", new Object[] { Witchery.resource("witchery:brew.lifetime"), StatCollector.func_74838_a("potion.potency." + this.dispersalDuration) }));
/*     */     }
/*  88 */     this.dispersalDuration = 0;
/*     */   }
/*     */   
/*     */   public void appendPostfix(String text) {
/*  92 */     this.postfixes.add(text);
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/*  97 */     StringBuilder builder = new StringBuilder();
/*  98 */     for (String text : this.prefixes) {
/*  99 */       builder.append(text);
/* 100 */       builder.append(" ");
/*     */     }
/*     */     
/* 103 */     if (this.terse) {
/* 104 */       builder.append(Witchery.resource("witchery:brew.potion"));
/* 105 */       builder.append(" ");
/*     */     } else {
/* 107 */       builder.append("\n");
/*     */     }
/*     */     
/* 110 */     if (this.parts.size() > 0) {
/* 111 */       for (int i = 0; i < this.parts.size(); i++) {
/* 112 */         builder.append(((Part)this.parts.get(i)).toString(this.prefixes.size() > 0, this.terse));
/* 113 */         builder.append(this.terse ? " " : i < this.parts.size() - 1 ? " & " : i < this.parts.size() - 2 ? ", " : "\n");
/*     */       }
/*     */     } else {
/* 116 */       builder.append(Witchery.resource("witchery:brew.potionwater"));
/* 117 */       if (!this.terse) {
/* 118 */         builder.append("\n");
/*     */       }
/*     */     }
/*     */     
/* 122 */     for (String text : this.postfixes) {
/* 123 */       builder.append(text);
/* 124 */       builder.append(" ");
/*     */     }
/*     */     
/* 127 */     builder.trimToSize();
/*     */     
/* 129 */     return builder.toString();
/*     */   }
/*     */   
/*     */   public void addStrength(int strength2) {
/* 133 */     if ((this.totalStrength < 7) || (this.removePowerCeiling)) {
/* 134 */       this.strength += strength2;
/* 135 */       this.totalStrength += strength2;
/*     */     }
/*     */   }
/*     */   
/*     */   public void addDuration(int duration) {
/* 140 */     if ((this.totalDuration < 7) || (this.removePowerCeiling)) {
/* 141 */       this.durationModifier += duration;
/* 142 */       this.totalDuration += duration;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/brewing/BrewNameBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */