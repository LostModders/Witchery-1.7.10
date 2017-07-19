/*     */ package com.emoniph.witchery.client;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.brewing.potions.WitcheryPotions;
/*     */ import com.emoniph.witchery.common.ExtendedPlayer;
/*     */ import com.emoniph.witchery.common.ExtendedPlayer.VampirePower;
/*     */ import cpw.mods.fml.common.eventhandler.SubscribeEvent;
/*     */ import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
/*     */ import cpw.mods.fml.common.gameevent.TickEvent.Phase;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.GuiIngame;
/*     */ import net.minecraft.client.settings.GameSettings;
/*     */ import net.minecraft.client.settings.KeyBinding;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.entity.player.PlayerCapabilities;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import org.lwjgl.input.Keyboard;
/*     */ 
/*     */ public class KeyboardHandler
/*     */ {
/*  25 */   private final List<KeyInfo> bindings = new ArrayList();
/*     */   
/*  27 */   private final KeyInfo JUMP = new KeyInfo(Minecraft.func_71410_x().field_71474_y.field_74314_A, this.bindings)
/*     */   {
/*     */     private boolean isJumping;
/*     */     private int remainingJumps;
/*     */     private boolean clearFall;
/*     */     
/*     */     protected void onKeyDown(EntityPlayer player, boolean repeated, boolean end) {
/*  34 */       if ((!player.field_71075_bZ.field_75098_d) && (!end)) {
/*  35 */         if (this.isJumping) {
/*  36 */           if (this.remainingJumps > 0) {
/*  37 */             int jumpsLeft = this.remainingJumps;
/*  38 */             this.remainingJumps -= 1;
/*  39 */             player.field_70181_x = 0.42D;
/*  40 */             if (player.func_70644_a(Potion.field_76430_j)) {
/*  41 */               player.field_70181_x += 0.1D * (1 + player.func_70660_b(Potion.field_76430_j).func_76458_c());
/*     */             }
/*     */           }
/*     */         } else {
/*  45 */           this.isJumping = player.field_70160_al;
/*  46 */           if (player.func_70644_a(Witchery.Potions.DOUBLE_JUMP)) {
/*  47 */             this.remainingJumps += 1 + player.func_70660_b(Witchery.Potions.DOUBLE_JUMP).func_76458_c();
/*     */           }
/*     */         }
/*     */       }
/*     */       
/*  52 */       if (this.clearFall) {
/*  53 */         this.clearFall = false;
/*  54 */         player.field_70143_R = 0.0F;
/*  55 */         Witchery.packetPipeline.sendToServer(new com.emoniph.witchery.network.PacketClearFallDamage());
/*     */       }
/*     */     }
/*     */     
/*     */     protected void onTick(EntityPlayer player, boolean end)
/*     */     {
/*  61 */       if (player.field_70122_E) {
/*  62 */         this.isJumping = false;
/*  63 */         this.remainingJumps = 0;
/*     */       }
/*     */     }
/*     */   };
/*     */   
/*  68 */   private final KeyInfo HOTBAR1 = new KeyInfo(Minecraft.func_71410_x().field_71474_y.field_151456_ac[0], this.bindings)
/*     */   {
/*     */     protected void onKeyDown(EntityPlayer player, boolean repeated, boolean end) {
/*  71 */       if (!end) {
/*  72 */         ExtendedPlayer playerEx = ExtendedPlayer.get(player);
/*  73 */         if ((playerEx.isVampire()) && (!Minecraft.func_71410_x().field_71456_v.func_146158_b().func_146241_e())) {
/*  74 */           int MAXPOWER = playerEx.getMaxAvailablePowerOrdinal();
/*  75 */           if (player.field_71071_by.field_70461_c == 0) {
/*  76 */             int power = playerEx.getSelectedVampirePower().ordinal();
/*     */             
/*  78 */             if (power == MAXPOWER) {
/*  79 */               playerEx.setSelectedVampirePower(ExtendedPlayer.VampirePower.NONE, true);
/*     */             } else {
/*  81 */               playerEx.setSelectedVampirePower(ExtendedPlayer.VampirePower.values()[(power + 1)], true);
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */     protected void onKeyUp(EntityPlayer player, boolean end) {}
/*     */     
/*     */ 
/*     */ 
/*     */     protected void onTick(EntityPlayer player, boolean end) {}
/*     */   };
/*     */   
/*     */ 
/*     */   public KeyboardHandler()
/*     */   {
/* 100 */     for (int i = 1; i < Minecraft.func_71410_x().field_71474_y.field_151456_ac.length; i++) {
/* 101 */       KeyBinding binding = Minecraft.func_71410_x().field_71474_y.field_151456_ac[i];
/* 102 */       new KeyInfo(binding, this.bindings)
/*     */       {
/*     */         protected void onKeyDown(EntityPlayer player, boolean repeated, boolean end) {
/* 105 */           if (!end) {
/* 106 */             ExtendedPlayer playerEx = ExtendedPlayer.get(player);
/* 107 */             if ((playerEx.isVampire()) && (playerEx.getSelectedVampirePower() != ExtendedPlayer.VampirePower.NONE)) {
/* 108 */               playerEx.setSelectedVampirePower(ExtendedPlayer.VampirePower.NONE, true);
/*     */             }
/*     */           }
/*     */         }
/*     */       };
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onTick(TickEvent.ClientTickEvent event) {
/*     */     EntityPlayer player;
/* 119 */     if (event.side == cpw.mods.fml.relauncher.Side.CLIENT) {
/* 120 */       Minecraft mc = Minecraft.func_71410_x();
/* 121 */       player = mc.field_71439_g;
/* 122 */       if (player != null) {
/* 123 */         for (KeyInfo keyInfo : this.bindings) {
/* 124 */           keyInfo.doTick(player, event.phase == TickEvent.Phase.END);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private static abstract class KeyInfo {
/*     */     private final KeyBinding bind;
/*     */     private boolean repeat;
/*     */     private boolean down;
/*     */     
/*     */     public KeyInfo(KeyBinding bind, List<KeyInfo> bindings) {
/* 136 */       this.bind = bind;
/* 137 */       bindings.add(this);
/*     */     }
/*     */     
/*     */     public void doTick(EntityPlayer player, boolean end) {
/* 141 */       int keyCode = this.bind.func_151463_i();
/* 142 */       boolean newlyDown = keyCode < 0 ? org.lwjgl.input.Mouse.isButtonDown(keyCode + 100) : Keyboard.isKeyDown(keyCode);
/*     */       
/* 144 */       if ((newlyDown != this.down) || ((newlyDown) && (this.repeat))) {
/* 145 */         if (newlyDown) {
/* 146 */           onKeyDown(player, newlyDown != this.down, end);
/*     */         } else {
/* 148 */           onKeyUp(player, end);
/*     */         }
/* 150 */         if (end) {
/* 151 */           this.down = newlyDown;
/*     */         }
/*     */       }
/* 154 */       if (end) {
/* 155 */         onTick(player, end);
/*     */       }
/*     */     }
/*     */     
/*     */     protected void onKeyDown(EntityPlayer player, boolean repeated, boolean end) {}
/*     */     
/*     */     protected void onKeyUp(EntityPlayer player, boolean end) {}
/*     */     
/*     */     protected void onTick(EntityPlayer player, boolean end) {}
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/client/KeyboardHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */