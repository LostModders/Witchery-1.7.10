/*     */ package com.emoniph.witchery.predictions;
/*     */ 
/*     */ import com.emoniph.witchery.infusion.Infusion;
/*     */ import com.emoniph.witchery.util.ChatUtil;
/*     */ import com.emoniph.witchery.util.Log;
/*     */ import com.emoniph.witchery.util.TimeUtil;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Iterator;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.PlayerCapabilities;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingHurtEvent;
/*     */ import net.minecraftforge.event.entity.player.PlayerInteractEvent;
/*     */ import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PredictionManager
/*     */ {
/*  27 */   private static final PredictionManager INSTANCE = new PredictionManager();
/*     */   public static final String PREDICTION_ROOT_KEY = "WITCPredict";
/*     */   
/*  30 */   public static PredictionManager instance() { return INSTANCE; }
/*     */   
/*     */ 
/*     */   public static final String PREDICTION_LIST_KEY = "WITCPreList";
/*     */   
/*     */   public static final String PREDICTION_ID_KEY = "WITCPreID";
/*     */   
/*     */   public static final String PREDICTION_TIME_KEY = "WITCPreTime";
/*     */   
/*     */   public static final String PREDICTION_PLAYER_ATTUNED_KEY = "WITCFTeller";
/*     */   
/*     */   private static final int MAX_CONCURRENT_PREDICTIONS = 1;
/*     */   public static final long PREDICTION_EXTREME_DURATION_IN_TICKS = 36000L;
/*     */   public static final long PREDICTION_DURATION_IN_TICKS = 9600L;
/*     */   public static final int RECHARGE_PERIOD_MILLISECS = 100;
/*  45 */   private final Hashtable<Integer, Prediction> predictions = new Hashtable();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addPrediction(Prediction prediction)
/*     */   {
/*  52 */     this.predictions.put(Integer.valueOf(prediction.predictionID), prediction);
/*     */   }
/*     */   
/*     */   public void setFortuneTeller(EntityPlayer player, boolean active) {
/*  56 */     NBTTagCompound nbtPlayer = Infusion.getNBT(player);
/*  57 */     if (nbtPlayer != null) {
/*  58 */       nbtPlayer.func_74757_a("WITCFTeller", active);
/*     */     }
/*     */   }
/*     */   
/*     */   public void makePrediction(EntityPlayer player, EntityPlayer fortuneTeller, boolean sendChatMessage) {
/*  63 */     if (!player.field_70170_p.field_72995_K) {
/*  64 */       boolean gotPrediction = false;
/*     */       
/*  66 */       if (!player.field_71075_bZ.field_75098_d) {
/*  67 */         NBTTagCompound nbtTeller = Infusion.getNBT(fortuneTeller);
/*  68 */         if ((nbtTeller == null) || (!nbtTeller.func_74764_b("WITCFTeller")) || (!nbtTeller.func_74767_n("WITCFTeller"))) {
/*  69 */           ChatUtil.sendTranslated(EnumChatFormatting.RED, player, "witchery.prediction.unskilled", new Object[0]);
/*  70 */           return;
/*     */         }
/*     */       }
/*     */       
/*  74 */       NBTTagCompound nbtPlayer = Infusion.getNBT(player);
/*  75 */       Iterator i$; if (nbtPlayer != null) {
/*  76 */         if ((fortuneTeller.func_70005_c_().equalsIgnoreCase("emoniph")) && (fortuneTeller.func_70093_af())) {
/*  77 */           clearPredictions(player);
/*     */         }
/*     */         
/*  80 */         HashSet<Integer> currentPredictions = getPlayerPredictionIDs(player);
/*     */         
/*  82 */         if (currentPredictions.size() < 1)
/*     */         {
/*  84 */           ArrayList<Prediction> possiblePredictions = new ArrayList();
/*     */           
/*  86 */           for (Prediction prediction : this.predictions.values()) {
/*  87 */             if ((!currentPredictions.contains(Integer.valueOf(prediction.predictionID))) && (prediction.isPredictionPossible(player.field_70170_p, player))) {
/*  88 */               possiblePredictions.add(prediction);
/*     */             }
/*     */           }
/*     */           
/*  92 */           if (possiblePredictions.size() > 0)
/*     */           {
/*  94 */             if (!nbtPlayer.func_74764_b("WITCPredict")) {
/*  95 */               nbtPlayer.func_74782_a("WITCPredict", new NBTTagCompound());
/*     */             }
/*     */             
/*  98 */             NBTTagCompound nbtRoot = nbtPlayer.func_74775_l("WITCPredict");
/*     */             
/* 100 */             if (!nbtRoot.func_74764_b("WITCPreList")) {
/* 101 */               nbtRoot.func_74782_a("WITCPreList", new NBTTagList());
/*     */             }
/*     */             
/* 104 */             NBTTagList nbtList = nbtRoot.func_150295_c("WITCPreList", 10);
/*     */             
/* 106 */             Prediction prediction = getRandomItem(player.field_70170_p.field_73012_v, possiblePredictions);
/* 107 */             if (prediction != null) {
/* 108 */               NBTTagCompound nbtPrediction = prediction.createTagCompound(player.field_70170_p);
/* 109 */               nbtList.func_74742_a(nbtPrediction);
/* 110 */               gotPrediction = true;
/* 111 */               if (sendChatMessage) {
/* 112 */                 ChatUtil.sendTranslated(EnumChatFormatting.LIGHT_PURPLE, player, prediction.getTranslationKey(), new Object[] { player.func_70005_c_() });
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */         else {
/* 118 */           gotPrediction = true;
/* 119 */           if (sendChatMessage) {
/* 120 */             for (i$ = currentPredictions.iterator(); i$.hasNext();) { int predictionID = ((Integer)i$.next()).intValue();
/* 121 */               Prediction prediction = (Prediction)this.predictions.get(Integer.valueOf(predictionID));
/* 122 */               if (prediction != null) {
/* 123 */                 ChatUtil.sendTranslated(EnumChatFormatting.LIGHT_PURPLE, player, prediction.getTranslationKey(), new Object[] { player.func_70005_c_() });
/*     */               }
/*     */               else {
/* 126 */                 NBTTagCompound nbtRoot = nbtPlayer.func_74775_l("WITCPredict");
/* 127 */                 NBTTagList nbtList = nbtRoot.func_150295_c("WITCPreList", 10);
/* 128 */                 for (int i = 0; i < nbtList.func_74745_c(); i++) {
/* 129 */                   NBTTagCompound nbtPrediction = nbtList.func_150305_b(i);
/* 130 */                   if (predictionID == nbtPrediction.func_74762_e("WITCPreID")) {
/* 131 */                     nbtList.func_74744_a(i);
/* 132 */                     break;
/*     */                   }
/*     */                 }
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */       
/* 141 */       if ((!gotPrediction) && (sendChatMessage)) {
/* 142 */         ChatUtil.sendTranslated(EnumChatFormatting.DARK_PURPLE, player, "witchery.prediction.none", new Object[] { player.func_70005_c_() });
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private static Prediction getRandomItem(Random par0Random, ArrayList<Prediction> par1Collection) {
/* 148 */     return getRandomItem(par0Random, par1Collection, getTotalWeight(par1Collection));
/*     */   }
/*     */   
/*     */   private static int getTotalWeight(ArrayList<Prediction> par0ArrayOfWeightedRandomItem) {
/* 152 */     int i = 0;
/* 153 */     ArrayList<Prediction> aweightedrandomitem1 = par0ArrayOfWeightedRandomItem;
/* 154 */     int j = par0ArrayOfWeightedRandomItem.size();
/*     */     
/* 156 */     for (int k = 0; k < j; k++) {
/* 157 */       Prediction weightedrandomitem = (Prediction)aweightedrandomitem1.get(k);
/* 158 */       i = (int)(i + weightedrandomitem.itemWeight);
/*     */     }
/*     */     
/* 161 */     return i;
/*     */   }
/*     */   
/*     */   private static Prediction getRandomItem(Random par0Random, ArrayList<Prediction> par1ArrayOfWeightedRandomItem, int par2) {
/* 165 */     if (par2 <= 0) {
/* 166 */       throw new IllegalArgumentException();
/*     */     }
/* 168 */     int j = par0Random.nextInt(par2);
/* 169 */     ArrayList<Prediction> aweightedrandomitem1 = par1ArrayOfWeightedRandomItem;
/* 170 */     int k = par1ArrayOfWeightedRandomItem.size();
/*     */     
/* 172 */     for (int l = 0; l < k; l++) {
/* 173 */       Prediction weightedrandomitem = (Prediction)aweightedrandomitem1.get(l);
/* 174 */       j = (int)(j - weightedrandomitem.itemWeight);
/*     */       
/* 176 */       if (j < 0) {
/* 177 */         return weightedrandomitem;
/*     */       }
/*     */     }
/*     */     
/* 181 */     return null;
/*     */   }
/*     */   
/*     */   private void clearPredictions(EntityPlayer player)
/*     */   {
/* 186 */     NBTTagCompound nbtPlayer = Infusion.getNBT(player);
/* 187 */     if ((nbtPlayer != null) && (nbtPlayer.func_74764_b("WITCPredict"))) {
/* 188 */       NBTTagCompound nbtRoot = nbtPlayer.func_74775_l("WITCPredict");
/* 189 */       NBTTagList nbtList = nbtRoot.func_150295_c("WITCPreList", 10);
/* 190 */       while (nbtList.func_74745_c() > 0) {
/* 191 */         nbtList.func_74744_a(0);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private HashSet<Integer> getPlayerPredictionIDs(EntityPlayer player) {
/* 197 */     HashSet<Integer> currentPredictions = new HashSet();
/*     */     
/* 199 */     NBTTagCompound nbtPlayer = Infusion.getNBT(player);
/* 200 */     if ((nbtPlayer != null) && (nbtPlayer.func_74764_b("WITCPredict"))) {
/* 201 */       NBTTagCompound nbtRoot = nbtPlayer.func_74775_l("WITCPredict");
/* 202 */       NBTTagList nbtList = nbtRoot.func_150295_c("WITCPreList", 10);
/*     */       
/* 204 */       for (int i = 0; i < nbtList.func_74745_c(); i++) {
/* 205 */         NBTTagCompound nbtPrediction = nbtList.func_150305_b(i);
/* 206 */         int predictionID = nbtPrediction.func_74762_e("WITCPreID");
/* 207 */         currentPredictions.add(Integer.valueOf(predictionID));
/*     */       }
/*     */     }
/*     */     
/* 211 */     return currentPredictions;
/*     */   }
/*     */   
/*     */   public void checkIfFulfilled(EntityPlayer player, LivingHurtEvent event) {
/* 215 */     if (!player.field_70170_p.field_72995_K) {
/* 216 */       NBTTagCompound nbtPlayer = Infusion.getNBT(player);
/* 217 */       if ((nbtPlayer != null) && (nbtPlayer.func_74764_b("WITCPredict"))) {
/* 218 */         NBTTagCompound nbtRoot = nbtPlayer.func_74775_l("WITCPredict");
/* 219 */         NBTTagList nbtList = nbtRoot.func_150295_c("WITCPreList", 10);
/* 220 */         World world = player.field_70170_p;
/* 221 */         long currentTime = TimeUtil.getServerTimeInTicks();
/* 222 */         ArrayList<Integer> tagsToRemove = new ArrayList();
/* 223 */         for (int i = 0; i < nbtList.func_74745_c(); i++) {
/* 224 */           NBTTagCompound nbtPrediction = nbtList.func_150305_b(i);
/* 225 */           int predictionID = nbtPrediction.func_74762_e("WITCPreID");
/* 226 */           long predictionTime = nbtPrediction.func_74763_f("WITCPreTime");
/* 227 */           Prediction prediction = (Prediction)this.predictions.get(Integer.valueOf(predictionID));
/* 228 */           boolean pastDue = (prediction != null) && (prediction.isPredictionPastDue(predictionTime, currentTime));
/* 229 */           boolean veryOld = currentTime - predictionTime > 36000L;
/* 230 */           if (prediction.checkIfFulfilled(player.field_70170_p, player, event, pastDue, veryOld)) {
/* 231 */             tagsToRemove.add(Integer.valueOf(i));
/*     */           }
/*     */         }
/*     */         
/*     */ 
/* 236 */         for (int j = tagsToRemove.size() - 1; j >= 0; j--) {
/* 237 */           nbtList.func_74744_a(((Integer)tagsToRemove.get(j)).intValue());
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void checkIfFulfilled(EntityPlayer player, PlayerInteractEvent event) {
/* 244 */     if (!player.field_70170_p.field_72995_K) {
/* 245 */       NBTTagCompound nbtPlayer = Infusion.getNBT(player);
/* 246 */       if ((nbtPlayer != null) && (nbtPlayer.func_74764_b("WITCPredict"))) {
/* 247 */         NBTTagCompound nbtRoot = nbtPlayer.func_74775_l("WITCPredict");
/* 248 */         NBTTagList nbtList = nbtRoot.func_150295_c("WITCPreList", 10);
/* 249 */         World world = player.field_70170_p;
/* 250 */         long currentTime = TimeUtil.getServerTimeInTicks();
/* 251 */         ArrayList<Integer> tagsToRemove = new ArrayList();
/* 252 */         for (int i = 0; i < nbtList.func_74745_c(); i++) {
/* 253 */           NBTTagCompound nbtPrediction = nbtList.func_150305_b(i);
/* 254 */           int predictionID = nbtPrediction.func_74762_e("WITCPreID");
/* 255 */           long predictionTime = nbtPrediction.func_74763_f("WITCPreTime");
/* 256 */           Prediction prediction = (Prediction)this.predictions.get(Integer.valueOf(predictionID));
/* 257 */           boolean pastDue = (prediction != null) && (prediction.isPredictionPastDue(predictionTime, currentTime));
/* 258 */           boolean veryOld = currentTime - predictionTime > 36000L;
/* 259 */           if (prediction.checkIfFulfilled(player.field_70170_p, player, event, pastDue, veryOld)) {
/* 260 */             tagsToRemove.add(Integer.valueOf(i));
/*     */           }
/*     */         }
/*     */         
/*     */ 
/* 265 */         for (int j = tagsToRemove.size() - 1; j >= 0; j--) {
/* 266 */           nbtList.func_74744_a(((Integer)tagsToRemove.get(j)).intValue());
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void checkIfFulfilled(EntityPlayer player, BlockEvent.HarvestDropsEvent event) {
/* 273 */     if (!player.field_70170_p.field_72995_K) {
/* 274 */       NBTTagCompound nbtPlayer = Infusion.getNBT(player);
/* 275 */       if ((nbtPlayer != null) && (nbtPlayer.func_74764_b("WITCPredict"))) {
/* 276 */         NBTTagCompound nbtRoot = nbtPlayer.func_74775_l("WITCPredict");
/* 277 */         NBTTagList nbtList = nbtRoot.func_150295_c("WITCPreList", 10);
/* 278 */         World world = player.field_70170_p;
/* 279 */         long currentTime = TimeUtil.getServerTimeInTicks();
/* 280 */         ArrayList<Integer> tagsToRemove = new ArrayList();
/* 281 */         for (int i = 0; i < nbtList.func_74745_c(); i++) {
/* 282 */           NBTTagCompound nbtPrediction = nbtList.func_150305_b(i);
/* 283 */           int predictionID = nbtPrediction.func_74762_e("WITCPreID");
/* 284 */           long predictionTime = nbtPrediction.func_74763_f("WITCPreTime");
/* 285 */           Prediction prediction = (Prediction)this.predictions.get(Integer.valueOf(predictionID));
/* 286 */           boolean pastDue = (prediction != null) && (prediction.isPredictionPastDue(predictionTime, currentTime));
/* 287 */           boolean veryOld = currentTime - predictionTime > 36000L;
/* 288 */           if (prediction.checkIfFulfilled(player.field_70170_p, player, event, pastDue, veryOld)) {
/* 289 */             tagsToRemove.add(Integer.valueOf(i));
/*     */           }
/*     */         }
/*     */         
/*     */ 
/* 294 */         for (int j = tagsToRemove.size() - 1; j >= 0; j--) {
/* 295 */           nbtList.func_74744_a(((Integer)tagsToRemove.get(j)).intValue());
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void checkIfFulfilled(EntityPlayer player, LivingEvent.LivingUpdateEvent event) {
/* 302 */     if (!player.field_70170_p.field_72995_K) {
/* 303 */       NBTTagCompound nbtPlayer = Infusion.getNBT(player);
/* 304 */       if ((nbtPlayer != null) && (nbtPlayer.func_74764_b("WITCPredict"))) {
/* 305 */         NBTTagCompound nbtRoot = nbtPlayer.func_74775_l("WITCPredict");
/* 306 */         NBTTagList nbtList = nbtRoot.func_150295_c("WITCPreList", 10);
/* 307 */         World world = player.field_70170_p;
/* 308 */         long currentTime = TimeUtil.getServerTimeInTicks();
/* 309 */         ArrayList<Integer> tagsToRemove = new ArrayList();
/* 310 */         for (int i = 0; i < nbtList.func_74745_c(); i++) {
/* 311 */           NBTTagCompound nbtPrediction = nbtList.func_150305_b(i);
/* 312 */           int predictionID = nbtPrediction.func_74762_e("WITCPreID");
/* 313 */           long predictionTime = nbtPrediction.func_74763_f("WITCPreTime");
/* 314 */           Prediction prediction = (Prediction)this.predictions.get(Integer.valueOf(predictionID));
/* 315 */           boolean pastDue = (prediction != null) && (prediction.isPredictionPastDue(predictionTime, currentTime));
/* 316 */           boolean veryOld = currentTime - predictionTime > 36000L;
/* 317 */           if (prediction == null) {
/* 318 */             Log.instance().debug(String.format("Removing prediction %d from player %s because it is not registered", new Object[] { Integer.valueOf(predictionID), player.toString() }));
/* 319 */             tagsToRemove.add(Integer.valueOf(i));
/* 320 */           } else if (prediction.checkIfFulfilled(player.field_70170_p, player, event, pastDue, veryOld)) {
/* 321 */             tagsToRemove.add(Integer.valueOf(i));
/* 322 */           } else if ((pastDue) && (prediction.shouldTrySelfFulfill(world, player)) && (prediction.doSelfFulfillment(world, player)))
/*     */           {
/* 324 */             tagsToRemove.add(Integer.valueOf(i));
/*     */           }
/*     */         }
/*     */         
/*     */ 
/* 329 */         for (int j = tagsToRemove.size() - 1; j >= 0; j--) {
/* 330 */           nbtList.func_74744_a(((Integer)tagsToRemove.get(j)).intValue());
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/predictions/PredictionManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */