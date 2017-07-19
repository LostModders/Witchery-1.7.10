/*    */ package com.emoniph.witchery.integration;
/*    */ 
/*    */ import com.emoniph.witchery.Witchery;
/*    */ import com.emoniph.witchery.WitcheryBlocks;
/*    */ import cpw.mods.fml.common.registry.FMLControlledNamespacedRegistry;
/*    */ import cpw.mods.fml.common.registry.GameData;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.nbt.NBTTagList;
/*    */ 
/*    */ public class ModHookTreecapitator extends ModHook
/*    */ {
/*    */   public String getModID()
/*    */   {
/* 15 */     return "TreeCapitator";
/*    */   }
/*    */   
/*    */   protected void doInit()
/*    */   {
/* 20 */     NBTTagCompound tpModCfg = new NBTTagCompound();
/* 21 */     tpModCfg.func_74778_a("modID", "witchery");
/*    */     
/* 23 */     NBTTagList treeList = new NBTTagList();
/*    */     
/* 25 */     NBTTagCompound treeDef = new NBTTagCompound();
/* 26 */     treeDef.func_74778_a("treeName", "rowan");
/* 27 */     String logName = GameData.getBlockRegistry().func_148750_c(Witchery.Blocks.LOG);
/* 28 */     String leafName = GameData.getBlockRegistry().func_148750_c(Witchery.Blocks.LEAVES);
/* 29 */     treeDef.func_74778_a("logs", String.format("%s,0; %s,4; %s,8", new Object[] { logName, logName, logName }));
/* 30 */     treeDef.func_74778_a("leaves", String.format("%s,0", new Object[] { leafName }));
/* 31 */     treeList.func_74742_a(treeDef);
/*    */     
/* 33 */     treeDef = new NBTTagCompound();
/* 34 */     treeDef.func_74778_a("treeName", "alder");
/* 35 */     treeDef.func_74778_a("logs", String.format("%s,1; %s,5; %s,9", new Object[] { logName, logName, logName }));
/* 36 */     treeDef.func_74778_a("leaves", String.format("%s,1", new Object[] { leafName }));
/* 37 */     treeList.func_74742_a(treeDef);
/*    */     
/* 39 */     treeDef = new NBTTagCompound();
/* 40 */     treeDef.func_74778_a("treeName", "hawthorn");
/* 41 */     treeDef.func_74778_a("logs", String.format("%s,2; %s,6; %s,10", new Object[] { logName, logName, logName }));
/* 42 */     treeDef.func_74778_a("leaves", String.format("%s,2", new Object[] { leafName }));
/* 43 */     treeList.func_74742_a(treeDef);
/*    */     
/* 45 */     tpModCfg.func_74782_a("trees", treeList);
/*    */     
/* 47 */     cpw.mods.fml.common.event.FMLInterModComms.sendMessage(getModID(), "ThirdPartyModConfig", tpModCfg);
/*    */   }
/*    */   
/*    */   protected void doPostInit() {}
/*    */   
/*    */   protected void doReduceMagicPower(EntityLivingBase entity, float factor) {}
/*    */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/integration/ModHookTreecapitator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */