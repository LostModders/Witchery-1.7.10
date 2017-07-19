/*     */ package com.emoniph.witchery.ritual;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.util.ChatUtil;
/*     */ import com.emoniph.witchery.util.Const;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.EnumSet;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ 
/*     */ public class RiteRegistry
/*     */ {
/*  22 */   private static final RiteRegistry INSTANCE = new RiteRegistry();
/*     */   
/*     */ 
/*  25 */   public static RiteRegistry instance() { return INSTANCE; }
/*     */   
/*     */   public static class Ritual {
/*     */     private String unlocalizedName;
/*     */     public final Rite rite;
/*     */     final Sacrifice initialSacrifice;
/*     */     final EnumSet<RitualTraits> traits;
/*     */     
/*  33 */     public String getUnlocalizedName() { return this.unlocalizedName; }
/*     */     
/*     */ 
/*     */     final Circle[] circles;
/*     */     
/*     */     final byte ritualID;
/*     */     
/*     */     final int bookIndex;
/*     */     
/*     */     boolean visibleInBook;
/*     */     public Ritual setUnlocalizedName(String unlocalizedName)
/*     */     {
/*  45 */       this.unlocalizedName = unlocalizedName;
/*  46 */       return this;
/*     */     }
/*     */     
/*     */     public String getLocalizedName() {
/*  50 */       if (this.unlocalizedName != null) {
/*  51 */         return Witchery.resource(getUnlocalizedName());
/*     */       }
/*     */       
/*  54 */       return toString();
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
/*     */     Ritual(byte ritualID, int bookIndex, Rite rite, Sacrifice initialSacrifice, EnumSet<RitualTraits> traits, Circle[] circles)
/*     */     {
/*  67 */       this.ritualID = ritualID;
/*  68 */       this.bookIndex = bookIndex;
/*  69 */       this.rite = rite;
/*  70 */       this.initialSacrifice = initialSacrifice;
/*  71 */       this.traits = traits;
/*  72 */       this.circles = circles;
/*  73 */       this.visibleInBook = true;
/*     */     }
/*     */     
/*     */     public String getDescription() {
/*  77 */       StringBuffer sb = new StringBuffer();
/*  78 */       sb.append("§n");
/*  79 */       sb.append(getLocalizedName());
/*  80 */       sb.append("§r");
/*  81 */       sb.append(Const.BOOK_NEWLINE);
/*  82 */       sb.append(Const.BOOK_NEWLINE);
/*  83 */       this.initialSacrifice.addDescription(sb);
/*  84 */       return sb.toString();
/*     */     }
/*     */     
/*     */     public boolean isMatch(World world, int posX, int posY, int posZ, Circle[] nearbyCircles, ArrayList<Entity> entities, ArrayList<ItemStack> grassperStacks, boolean isDaytime, boolean isRaining, boolean isThundering)
/*     */     {
/*  89 */       if (((this.traits.contains(RitualTraits.ONLY_AT_NIGHT)) && (isDaytime)) || ((this.traits.contains(RitualTraits.ONLY_AT_DAY)) && (!isDaytime)) || ((this.traits.contains(RitualTraits.ONLY_IN_RAIN)) && (!isRaining)) || ((this.traits.contains(RitualTraits.ONLY_IN_STROM)) && (!isThundering)) || ((this.traits.contains(RitualTraits.ONLY_OVERWORLD)) && (world.field_73011_w.field_76574_g != 0)))
/*     */       {
/*     */ 
/*  92 */         return false;
/*     */       }
/*     */       
/*  95 */       if (this.circles.length > 0) {
/*  96 */         ArrayList<Circle> circlesToFind = new ArrayList(Arrays.asList(this.circles));
/*  97 */         for (Circle circle : nearbyCircles) {
/*  98 */           circle.removeIfRequired(circlesToFind);
/*  99 */           if (circlesToFind.size() == 0) {
/*     */             break;
/*     */           }
/*     */         }
/*     */         
/* 104 */         if (circlesToFind.size() > 0) {
/* 105 */           return false;
/*     */         }
/*     */       }
/*     */       
/* 109 */       return this.initialSacrifice.isMatch(world, posX, posY, posZ, getMaxDistance(), entities, grassperStacks);
/*     */     }
/*     */     
/*     */     public void addSteps(ArrayList<RitualStep> steps, AxisAlignedBB bounds) {
/* 113 */       int maxDistance = getMaxDistance();
/* 114 */       this.initialSacrifice.addSteps(steps, bounds, maxDistance);
/* 115 */       addRiteSteps(steps, 0);
/*     */     }
/*     */     
/*     */     private int getMaxDistance() {
/* 119 */       int maxDistance = this.circles.length > 0 ? 0 : 4;
/* 120 */       for (Circle circle : this.circles) {
/* 121 */         maxDistance = Math.max(circle.getRadius(), maxDistance);
/*     */       }
/* 123 */       return maxDistance;
/*     */     }
/*     */     
/*     */     public byte getRitualID() {
/* 127 */       return this.ritualID;
/*     */     }
/*     */     
/*     */     public void addRiteSteps(ArrayList<RitualStep> ritualSteps, int stage) {
/* 131 */       this.rite.addSteps(ritualSteps, stage);
/*     */     }
/*     */     
/*     */     public byte[] getCircles() {
/* 135 */       byte[] result = new byte[this.circles.length];
/* 136 */       int index = 0;
/* 137 */       for (Circle circle : this.circles) {
/* 138 */         result[(index++)] = ((byte)circle.getTextureIndex());
/*     */       }
/* 140 */       return result;
/*     */     }
/*     */     
/*     */     public Ritual setShowInBook(boolean show) {
/* 144 */       this.visibleInBook = show;
/* 145 */       return this;
/*     */     }
/*     */     
/*     */     public boolean showInBook() {
/* 149 */       return this.visibleInBook;
/*     */     }
/*     */     
/* 152 */     private boolean consumeAttunedStoneCharged = false;
/*     */     
/* 154 */     public boolean isConsumeAttunedStoneCharged() { return this.consumeAttunedStoneCharged; }
/*     */     
/*     */     public void setConsumeAttunedStoneCharged()
/*     */     {
/* 158 */       this.consumeAttunedStoneCharged = true;
/*     */     }
/*     */     
/* 161 */     private boolean consumeNecroStone = false;
/*     */     
/* 163 */     public boolean isConsumeNecroStone() { return this.consumeNecroStone; }
/*     */     
/*     */     public void setConsumeNecroStone()
/*     */     {
/* 167 */       this.consumeNecroStone = true;
/*     */     }
/*     */   }
/*     */   
/* 171 */   final ArrayList<Ritual> rituals = new ArrayList();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public final List<Ritual> getRituals()
/*     */   {
/* 178 */     return this.rituals;
/*     */   }
/*     */   
/*     */   public static Ritual addRecipe(int ritualID, int bookIndex, Rite rite, Sacrifice initialSacrifice, EnumSet<RitualTraits> traits, Circle... circles) {
/* 182 */     Ritual ritual = new Ritual((byte)ritualID, bookIndex, rite, initialSacrifice, traits, circles);
/* 183 */     instance().rituals.add(ritual);
/* 184 */     return ritual;
/*     */   }
/*     */   
/*     */   public Ritual getRitual(byte ritualID) {
/* 188 */     return (Ritual)this.rituals.get(ritualID - 1);
/*     */   }
/*     */   
/*     */   public static class IndexComparitor implements Comparator<RiteRegistry.Ritual>
/*     */   {
/*     */     public int compare(RiteRegistry.Ritual o1, RiteRegistry.Ritual o2) {
/* 194 */       return Integer.valueOf(o1.bookIndex).compareTo(Integer.valueOf(o2.bookIndex));
/*     */     }
/*     */   }
/*     */   
/*     */   public List<Ritual> getSortedRituals() {
/* 199 */     ArrayList<Ritual> sortedRituals = new ArrayList();
/* 200 */     sortedRituals.addAll(this.rituals);
/* 201 */     Collections.sort(sortedRituals, new IndexComparitor());
/* 202 */     return sortedRituals;
/*     */   }
/*     */   
/*     */   public static void RiteError(String translationID, String username, World world) {
/* 206 */     if ((world != null) && (!world.field_72995_K) && (username != null)) {
/* 207 */       for (Object obj : world.field_73010_i) {
/* 208 */         if ((obj instanceof EntityPlayer)) {
/* 209 */           EntityPlayer worldPlayer = (EntityPlayer)obj;
/* 210 */           if (worldPlayer.func_70005_c_().equals(username)) {
/* 211 */             RiteError(translationID, worldPlayer, world);
/* 212 */             return;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static void RiteError(String translationID, EntityPlayer player, World world) {
/* 220 */     if ((world != null) && (!world.field_72995_K) && (player != null)) {
/* 221 */       ChatUtil.sendTranslated(EnumChatFormatting.RED, player, translationID, new Object[0]);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/ritual/RiteRegistry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */