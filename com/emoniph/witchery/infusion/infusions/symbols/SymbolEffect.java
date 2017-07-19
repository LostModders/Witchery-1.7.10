/*     */ package com.emoniph.witchery.infusion.infusions.symbols;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.infusion.Infusion;
/*     */ import com.emoniph.witchery.util.Const;
/*     */ import com.emoniph.witchery.util.TimeUtil;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.PlayerCapabilities;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public abstract class SymbolEffect
/*     */ {
/*     */   private final int effectID;
/*     */   private final String unlocalisedName;
/*     */   private final int chargeCost;
/*     */   private final boolean curse;
/*     */   private final boolean fallsToEarth;
/*     */   private final String knowledgeKey;
/*     */   private final boolean isVisible;
/*     */   private byte[] defaultStrokes;
/*     */   private final int cooldownTicks;
/*     */   
/*     */   public SymbolEffect(int effectID, String unlocalisedName)
/*     */   {
/*  26 */     this(effectID, unlocalisedName, 1, false, false, null, 0, true);
/*     */   }
/*     */   
/*     */   public SymbolEffect(int effectID, String unlocalisedName, int spellCost, boolean curse, boolean fallsToEarth, String knowledgeKey, int cooldown) {
/*  30 */     this(effectID, unlocalisedName, spellCost, curse, fallsToEarth, knowledgeKey, cooldown, true);
/*     */   }
/*     */   
/*     */   public SymbolEffect(int effectID, String unlocalisedName, int spellCost, boolean curse, boolean fallsToEarth, String knowledgeKey, int cooldown, boolean isVisible) {
/*  34 */     this.effectID = effectID;
/*  35 */     this.unlocalisedName = unlocalisedName;
/*  36 */     this.chargeCost = spellCost;
/*  37 */     this.curse = curse;
/*  38 */     this.fallsToEarth = fallsToEarth;
/*  39 */     this.knowledgeKey = knowledgeKey;
/*  40 */     this.cooldownTicks = cooldown;
/*  41 */     this.isVisible = isVisible;
/*     */   }
/*     */   
/*     */   public int getEffectID()
/*     */   {
/*  46 */     return this.effectID;
/*     */   }
/*     */   
/*     */   public boolean isCurse() {
/*  50 */     return this.curse;
/*     */   }
/*     */   
/*     */   public boolean isUnforgivable() {
/*  54 */     return (this.curse) && (this.knowledgeKey == null);
/*     */   }
/*     */   
/*     */   public String getLocalizedName() {
/*  58 */     return Witchery.resource(this.unlocalisedName);
/*     */   }
/*     */   
/*     */   public abstract void perform(World paramWorld, EntityPlayer paramEntityPlayer, int paramInt);
/*     */   
/*     */   public int getChargeCost(World world, EntityPlayer player, int level) {
/*  64 */     return net.minecraft.util.MathHelper.func_76128_c(Math.pow(2.0D, level - 1) * this.chargeCost);
/*     */   }
/*     */   
/*     */   public boolean fallsToEarth() {
/*  68 */     return this.fallsToEarth;
/*     */   }
/*     */   
/*     */   public boolean hasValidInfusion(EntityPlayer player, int infusionID) {
/*  72 */     if (player.field_71075_bZ.field_75098_d) {
/*  73 */       return true;
/*     */     }
/*     */     
/*  76 */     if (infusionID <= 0) {
/*  77 */       return false;
/*     */     }
/*     */     
/*  80 */     if ((isUnforgivable()) && (infusionID != Witchery.Recipes.infusionBeast.infusionID)) {
/*  81 */       return false;
/*     */     }
/*     */     
/*  84 */     return true;
/*     */   }
/*     */   
/*     */   public boolean isVisible(EntityPlayer player) {
/*  88 */     return this.isVisible;
/*     */   }
/*     */   
/*     */   public String getDescription() {
/*  92 */     StringBuffer sb = new StringBuffer();
/*     */     
/*  94 */     sb.append("§n");
/*  95 */     sb.append(Witchery.resource(this.unlocalisedName));
/*  96 */     sb.append("§r");
/*  97 */     sb.append(Const.BOOK_NEWLINE);
/*  98 */     sb.append(Const.BOOK_NEWLINE);
/*     */     
/* 100 */     String descKey = this.unlocalisedName + ".info";
/* 101 */     String description = Witchery.resource(descKey);
/* 102 */     if ((description != null) && (!description.isEmpty()) && (!description.equals(descKey))) {
/* 103 */       sb.append(description);
/* 104 */       sb.append(Const.BOOK_NEWLINE);
/* 105 */       sb.append(Const.BOOK_NEWLINE);
/*     */     }
/*     */     
/* 108 */     sb.append("§8");
/* 109 */     sb.append(Witchery.resource("witchery.book.wands.strokes"));
/* 110 */     sb.append("§0");
/* 111 */     sb.append(Const.BOOK_NEWLINE);
/* 112 */     int i = 1;
/* 113 */     for (byte stroke : this.defaultStrokes) {
/* 114 */       sb.append(i++);
/* 115 */       sb.append(": ");
/* 116 */       sb.append(Witchery.resource("witchery.book.wands.stroke." + stroke));
/* 117 */       sb.append(Const.BOOK_NEWLINE);
/*     */     }
/*     */     
/* 120 */     return sb.toString();
/*     */   }
/*     */   
/*     */   public void setDefaultStrokes(byte[] strokes) {
/* 124 */     this.defaultStrokes = strokes;
/*     */   }
/*     */   
/*     */   public boolean hasValidKnowledge(EntityPlayer player, NBTTagCompound nbtPlayer) {
/* 128 */     if (player.field_71075_bZ.field_75098_d) {
/* 129 */       return true;
/*     */     }
/* 131 */     if (this.knowledgeKey != null) {
/* 132 */       if (nbtPlayer.func_74764_b("WITCSpellBook")) {
/* 133 */         NBTTagCompound nbtSpells = nbtPlayer.func_74775_l("WITCSpellBook");
/* 134 */         return nbtSpells.func_74767_n(this.knowledgeKey);
/*     */       }
/* 136 */       return false;
/*     */     }
/*     */     
/* 139 */     return true;
/*     */   }
/*     */   
/*     */   public void acquireKnowledge(EntityPlayer player)
/*     */   {
/* 144 */     if (this.knowledgeKey != null) {
/* 145 */       NBTTagCompound nbtPlayer = Infusion.getNBT(player);
/* 146 */       if (nbtPlayer != null) {
/* 147 */         if (!nbtPlayer.func_74764_b("WITCSpellBook")) {
/* 148 */           nbtPlayer.func_74782_a("WITCSpellBook", new NBTTagCompound());
/*     */         }
/* 150 */         NBTTagCompound nbtSpells = nbtPlayer.func_74775_l("WITCSpellBook");
/* 151 */         nbtSpells.func_74757_a(this.knowledgeKey, true);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static String getKnowledge(EntityPlayer player) {
/* 157 */     StringBuilder sb = new StringBuilder();
/* 158 */     NBTTagCompound nbtPlayer = Infusion.getNBT(player);
/* 159 */     if ((nbtPlayer != null) && 
/* 160 */       (nbtPlayer.func_74764_b("WITCSpellBook"))) {
/* 161 */       for (SymbolEffect effect : EffectRegistry.instance().getEffects()) {
/* 162 */         if ((effect.knowledgeKey != null) && (effect.hasValidKnowledge(player, nbtPlayer))) {
/* 163 */           if (sb.length() > 0) {
/* 164 */             sb.append(", ");
/*     */           }
/* 166 */           sb.append(effect.getLocalizedName());
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 171 */     return sb.toString();
/*     */   }
/*     */   
/*     */   public long cooldownRemaining(EntityPlayer player, NBTTagCompound nbtPlayer) {
/* 175 */     if ((this.cooldownTicks > 0) && (this.knowledgeKey != null) && 
/* 176 */       (nbtPlayer.func_74764_b("WITCSpellBook"))) {
/* 177 */       NBTTagCompound nbtSpells = nbtPlayer.func_74775_l("WITCSpellBook");
/* 178 */       long lastUseTime = nbtSpells.func_74763_f(this.knowledgeKey + "LastUse");
/* 179 */       long timeNow = TimeUtil.getServerTimeInTicks();
/* 180 */       if (timeNow < lastUseTime + this.cooldownTicks) {
/* 181 */         return lastUseTime + this.cooldownTicks - timeNow;
/*     */       }
/*     */     }
/*     */     
/* 185 */     return 0L;
/*     */   }
/*     */   
/*     */   public void setOnCooldown(EntityPlayer player) {
/* 189 */     if ((this.cooldownTicks > 0) && (this.knowledgeKey != null) && (!player.field_71075_bZ.field_75098_d)) {
/* 190 */       NBTTagCompound nbtPlayer = Infusion.getNBT(player);
/* 191 */       if ((nbtPlayer != null) && 
/* 192 */         (nbtPlayer.func_74764_b("WITCSpellBook"))) {
/* 193 */         NBTTagCompound nbtSpells = nbtPlayer.func_74775_l("WITCSpellBook");
/* 194 */         nbtSpells.func_74772_a(this.knowledgeKey + "LastUse", TimeUtil.getServerTimeInTicks());
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 202 */     if ((obj == null) || (getClass() != obj.getClass())) {
/* 203 */       return false;
/*     */     }
/*     */     
/* 206 */     if (obj == this) {
/* 207 */       return true;
/*     */     }
/*     */     
/* 210 */     SymbolEffect other = (SymbolEffect)obj;
/* 211 */     return other.effectID == this.effectID;
/*     */   }
/*     */   
/*     */   public int hashCode()
/*     */   {
/* 216 */     int result = 17;
/* 217 */     result = 37 * result + this.effectID;
/* 218 */     return result;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/infusion/infusions/symbols/SymbolEffect.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */