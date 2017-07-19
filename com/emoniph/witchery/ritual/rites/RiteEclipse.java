/*    */ package com.emoniph.witchery.ritual.rites;
/*    */ 
/*    */ import com.emoniph.witchery.blocks.BlockCircle.TileEntityCircle.ActivatedRitual;
/*    */ import com.emoniph.witchery.ritual.Rite;
/*    */ import com.emoniph.witchery.ritual.RitualStep;
/*    */ import com.emoniph.witchery.ritual.RitualStep.Result;
/*    */ import com.emoniph.witchery.util.ChatUtil;
/*    */ import com.emoniph.witchery.util.Config;
/*    */ import com.emoniph.witchery.util.TimeUtil;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Hashtable;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.util.EnumChatFormatting;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.WorldProvider;
/*    */ import net.minecraft.world.storage.WorldInfo;
/*    */ 
/*    */ public class RiteEclipse
/*    */   extends Rite
/*    */ {
/*    */   public void addSteps(ArrayList<RitualStep> steps, int initialStage)
/*    */   {
/* 24 */     steps.add(new StepEclipse(this, initialStage));
/*    */   }
/*    */   
/* 27 */   private static Hashtable<Integer, Long> lastEclipseTimes = new Hashtable();
/*    */   
/*    */   private static class StepEclipse extends RitualStep
/*    */   {
/*    */     private final RiteEclipse rite;
/*    */     private int stage;
/*    */     
/*    */     public StepEclipse(RiteEclipse rite, int initialStage) {
/* 35 */       super();
/* 36 */       this.rite = rite;
/* 37 */       this.stage = initialStage;
/*    */     }
/*    */     
/*    */     public int getCurrentStage()
/*    */     {
/* 42 */       return this.stage;
/*    */     }
/*    */     
/*    */     public RitualStep.Result process(World world, int posX, int posY, int posZ, long ticks, BlockCircle.TileEntityCircle.ActivatedRitual ritual)
/*    */     {
/* 47 */       if (ticks % 30L != 0L) {
/* 48 */         return RitualStep.Result.STARTING;
/*    */       }
/*    */       
/* 51 */       if (!world.field_72995_K) {
/* 52 */         long riteOfEclipseCooldown = TimeUtil.secsToTicks(Config.instance().riteOfEclipseCooldownInSecs);
/* 53 */         EntityPlayer player = ritual.getInitiatingPlayer(world);
/* 54 */         if ((riteOfEclipseCooldown > 0L) && (world.field_73010_i.size() > 1) && 
/* 55 */           (RiteEclipse.lastEclipseTimes.containsKey(Integer.valueOf(world.field_73011_w.field_76574_g)))) {
/* 56 */           long lastActivation = ((Long)RiteEclipse.lastEclipseTimes.get(Integer.valueOf(world.field_73011_w.field_76574_g))).longValue();
/* 57 */           if (world.func_82737_E() < lastActivation + riteOfEclipseCooldown) {
/* 58 */             if (player != null) {
/* 59 */               ChatUtil.sendTranslated(EnumChatFormatting.RED, player, "witchery.rite.eclipse.cooldown", new Object[0]);
/*    */             }
/* 61 */             return RitualStep.Result.ABORTED_REFUND;
/*    */           }
/*    */         }
/*    */         
/*    */ 
/* 66 */         long i = world.func_72912_H().func_76073_f();
/* 67 */         world.func_72912_H().func_76068_b(i - i % 24000L + 18000L);
/* 68 */         RiteEclipse.lastEclipseTimes.put(Integer.valueOf(world.field_73011_w.field_76574_g), Long.valueOf(world.func_82737_E()));
/*    */       }
/* 70 */       return RitualStep.Result.COMPLETED;
/*    */     }
/*    */   }
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/ritual/rites/RiteEclipse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */