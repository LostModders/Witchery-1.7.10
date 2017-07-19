/*     */ package com.emoniph.witchery.item;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.brewing.WitcheryBrewRegistry;
/*     */ import com.emoniph.witchery.util.Dye;
/*     */ import com.emoniph.witchery.util.SoundEffect;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.inventory.InventoryBasic;
/*     */ import net.minecraft.inventory.Slot;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class ItemBrewBag extends ItemBase
/*     */ {
/*     */   private static final float THRESHOLD_ORTHOGONAL = 7.0F;
/*     */   private static final float THRESHOLD_DIAGONAL = 3.5F;
/*     */   
/*     */   public ItemBrewBag()
/*     */   {
/*  27 */     func_77625_d(1);
/*  28 */     func_77664_n();
/*     */   }
/*     */   
/*     */   public int func_82790_a(ItemStack stack, int parse)
/*     */   {
/*  33 */     return getColor(stack);
/*     */   }
/*     */   
/*     */   public static void setColor(ItemStack stack, Dye color) {
/*  37 */     setColor(stack, color.rgb);
/*     */   }
/*     */   
/*     */   public static void setColor(ItemStack stack, int rgb) {
/*  41 */     if (!stack.func_77942_o()) {
/*  42 */       stack.func_77982_d(new NBTTagCompound());
/*     */     }
/*  44 */     NBTTagCompound nbtRoot = stack.func_77978_p();
/*  45 */     nbtRoot.func_74768_a("WITCColor", rgb);
/*     */   }
/*     */   
/*     */   public static int getColor(ItemStack stack) {
/*  49 */     if (stack.func_77942_o()) {
/*  50 */       NBTTagCompound nbtRoot = stack.func_77978_p();
/*  51 */       if (nbtRoot.func_74764_b("WITCColor")) {
/*  52 */         return nbtRoot.func_74762_e("WITCColor");
/*     */       }
/*     */     }
/*  55 */     return Dye.COCOA_BEANS.rgb;
/*     */   }
/*     */   
/*     */   public int func_77626_a(ItemStack stack)
/*     */   {
/*  60 */     return 36000;
/*     */   }
/*     */   
/*     */   public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player)
/*     */   {
/*  65 */     if (!player.func_70093_af()) {
/*  66 */       NBTTagCompound nbtTag = player.getEntityData();
/*     */       
/*  68 */       nbtTag.func_74773_a("Strokes", new byte[0]);
/*  69 */       nbtTag.func_74776_a("startPitch", player.field_70125_A);
/*  70 */       nbtTag.func_74776_a("startYaw", player.field_70759_as);
/*  71 */       nbtTag.func_82580_o("WITCLastBrewIndex");
/*     */       
/*  73 */       player.func_71008_a(stack, func_77626_a(stack));
/*     */     }
/*  75 */     else if ((!world.field_72995_K) && 
/*  76 */       (player.func_70093_af())) {
/*  77 */       player.openGui(Witchery.instance, 5, world, 0, 0, 0);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*  82 */     return stack;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void onUsingTick(ItemStack stack, EntityPlayer player, int countdown)
/*     */   {
/*  90 */     if (player.field_70170_p.field_72995_K) {
/*  91 */       NBTTagCompound nbtTag = player.getEntityData();
/*  92 */       if (nbtTag == null) {
/*  93 */         return;
/*     */       }
/*     */       
/*  96 */       if (player.func_70093_af()) {
/*  97 */         nbtTag.func_82580_o("Strokes");
/*  98 */         nbtTag.func_74776_a("startPitch", player.field_70125_A);
/*  99 */         nbtTag.func_74776_a("startYaw", player.field_70759_as);
/* 100 */         return;
/*     */       }
/*     */       
/* 103 */       float yawDiff = nbtTag.func_74760_g("startYaw") - player.field_70759_as;
/* 104 */       float pitchDiff = nbtTag.func_74760_g("startPitch") - player.field_70125_A;
/*     */       
/* 106 */       byte[] oldStrokes = nbtTag.func_74770_j("Strokes");
/* 107 */       byte[] strokes = oldStrokes;
/*     */       
/* 109 */       int strokesStart = strokes.length;
/*     */       
/* 111 */       if (strokesStart == 0) {
/* 112 */         if ((pitchDiff >= 3.5F) && (yawDiff <= -3.5F)) {
/* 113 */           strokes = addNewStroke(nbtTag, strokes, (byte)4);
/* 114 */         } else if ((pitchDiff >= 3.5F) && (yawDiff >= 3.5F)) {
/* 115 */           strokes = addNewStroke(nbtTag, strokes, (byte)6);
/* 116 */         } else if ((pitchDiff <= -3.5F) && (yawDiff <= -3.5F)) {
/* 117 */           strokes = addNewStroke(nbtTag, strokes, (byte)7);
/* 118 */         } else if ((pitchDiff <= -3.5F) && (yawDiff >= 3.5F)) {
/* 119 */           strokes = addNewStroke(nbtTag, strokes, (byte)5);
/* 120 */         } else if (pitchDiff >= 7.0F) {
/* 121 */           strokes = addNewStroke(nbtTag, strokes, (byte)0);
/* 122 */         } else if (pitchDiff <= -7.0F) {
/* 123 */           strokes = addNewStroke(nbtTag, strokes, (byte)1);
/* 124 */         } else if (yawDiff <= -7.0F) {
/* 125 */           strokes = addNewStroke(nbtTag, strokes, (byte)2);
/* 126 */         } else if (yawDiff >= 7.0F) {
/* 127 */           strokes = addNewStroke(nbtTag, strokes, (byte)3);
/*     */         }
/*     */         
/* 130 */         if (strokes.length > strokesStart) {
/* 131 */           nbtTag.func_74776_a("startPitch", player.field_70125_A);
/* 132 */           nbtTag.func_74776_a("startYaw", player.field_70759_as);
/*     */         }
/*     */         
/* 135 */         if ((oldStrokes != strokes) && (strokes.length > 0)) {
/* 136 */           int brewIndex = com.emoniph.witchery.infusion.infusions.symbols.StrokeSet.Stroke.STROKE_TO_INDEX[strokes[0]];
/* 137 */           InventoryBrewBag inv = new InventoryBrewBag(player);
/* 138 */           ItemStack brew = inv.func_70301_a(brewIndex);
/* 139 */           if (brew != null) {
/* 140 */             Witchery.packetPipeline.sendToServer(new com.emoniph.witchery.network.PacketBrewPrepared(brewIndex));
/*     */           } else {
/* 142 */             nbtTag.func_82580_o("Strokes");
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 147 */     else if (player.func_70093_af()) {
/* 148 */       NBTTagCompound nbtTag = player.getEntityData();
/* 149 */       if (nbtTag.func_74764_b("WITCLastBrewIndex")) {
/* 150 */         nbtTag.func_82580_o("WITCLastBrewIndex");
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public byte[] addNewStroke(NBTTagCompound nbtTag, byte[] strokes, byte stroke)
/*     */   {
/* 157 */     byte[] newStrokes = new byte[1];
/* 158 */     newStrokes[(newStrokes.length - 1)] = stroke;
/* 159 */     nbtTag.func_74773_a("Strokes", newStrokes);
/* 160 */     return newStrokes;
/*     */   }
/*     */   
/*     */   public void func_77615_a(ItemStack stack, World world, EntityPlayer player, int countdown)
/*     */   {
/* 165 */     NBTTagCompound nbtTag = player.getEntityData();
/* 166 */     if (nbtTag != null) {
/* 167 */       if ((!world.field_72995_K) && (nbtTag.func_74764_b("WITCLastBrewIndex"))) {
/* 168 */         int brewIndex = nbtTag.func_74762_e("WITCLastBrewIndex");
/* 169 */         nbtTag.func_82580_o("WITCLastBrewIndex");
/*     */         
/* 171 */         if (!player.func_70093_af()) {
/* 172 */           if (brewIndex > -1) {
/* 173 */             InventoryBrewBag inv = new InventoryBrewBag(player);
/* 174 */             ItemStack brew = inv.func_70301_a(brewIndex);
/* 175 */             if (brew != null)
/*     */             {
/* 177 */               if (brew.func_77973_b() == Witchery.Items.BREW) {
/* 178 */                 if (!player.field_71075_bZ.field_75098_d) {
/* 179 */                   brew.field_77994_a -= 1;
/*     */                 }
/*     */                 
/* 182 */                 world.func_72956_a(player, "random.bow", 0.5F, 0.4F / (field_77697_d.nextFloat() * 0.4F + 0.8F));
/*     */                 
/* 184 */                 if (!world.field_72995_K) {
/* 185 */                   world.func_72838_d(new com.emoniph.witchery.brewing.EntityBrew(world, player, brew, false));
/*     */                 }
/*     */               } else {
/* 188 */                 Witchery.Items.GENERIC.throwBrew(brew, world, player);
/*     */               }
/* 190 */               if (brew.field_77994_a == 0) {
/* 191 */                 inv.func_70299_a(brewIndex, null);
/*     */               }
/*     */               
/* 194 */               inv.writeToNBT();
/*     */             }
/*     */           }
/*     */           else {
/* 198 */             com.emoniph.witchery.util.ChatUtil.sendTranslated(net.minecraft.util.EnumChatFormatting.RED, player, "witchery.infuse.branch.unknownsymbol", new Object[0]);
/* 199 */             SoundEffect.NOTE_SNARE.playAtPlayer(world, player);
/*     */           }
/*     */         }
/*     */       } else {
/* 203 */         nbtTag.func_82580_o("Strokes");
/* 204 */         nbtTag.func_82580_o("startYaw");
/* 205 */         nbtTag.func_82580_o("startPitch");
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public static class InventoryBrewBag
/*     */     extends InventoryBasic
/*     */   {
/*     */     protected String title;
/*     */     
/*     */     protected EntityPlayer player;
/*     */     
/* 218 */     protected boolean locked = false;
/*     */     
/*     */     public InventoryBrewBag(EntityPlayer player) {
/* 221 */       super(false, 8);
/*     */       
/* 223 */       this.player = player;
/*     */       
/* 225 */       if (!hasInventory()) {
/* 226 */         createInventory();
/*     */       }
/*     */       
/* 229 */       readFromNBT();
/*     */     }
/*     */     
/*     */     public void func_70296_d()
/*     */     {
/* 234 */       super.func_70296_d();
/* 235 */       if (!this.locked) {
/* 236 */         writeToNBT();
/*     */       }
/*     */     }
/*     */     
/*     */     public void func_70295_k_()
/*     */     {
/* 242 */       readFromNBT();
/*     */     }
/*     */     
/*     */     public void func_70305_f()
/*     */     {
/* 247 */       writeToNBT();
/*     */     }
/*     */     
/*     */     public String func_145825_b()
/*     */     {
/* 252 */       return this.title;
/*     */     }
/*     */     
/*     */     protected boolean hasInventory() {
/* 256 */       ItemStack bag = this.player.func_70694_bm();
/* 257 */       return (bag != null) && (bag.func_77942_o()) && (bag.func_77978_p().func_74764_b("Inventory"));
/*     */     }
/*     */     
/*     */     protected void createInventory() {
/* 261 */       ItemStack bag = this.player.func_70694_bm();
/* 262 */       this.title = new String(bag.func_82833_r());
/* 263 */       writeToNBT();
/*     */     }
/*     */     
/*     */     protected void writeToNBT() {
/* 267 */       ItemStack bag = this.player.func_70694_bm();
/* 268 */       if ((bag == null) || (bag.func_77973_b() != Witchery.Items.BREW_BAG)) {
/* 269 */         return;
/*     */       }
/*     */       
/* 272 */       if (!bag.func_77942_o()) {
/* 273 */         bag.func_77982_d(new NBTTagCompound());
/*     */       }
/*     */       
/* 276 */       NBTTagCompound nbtRoot = bag.func_77978_p();
/*     */       
/* 278 */       NBTTagCompound name = new NBTTagCompound();
/* 279 */       name.func_74778_a("Name", func_145825_b());
/* 280 */       nbtRoot.func_74782_a("display", name);
/*     */       
/* 282 */       NBTTagList itemList = new NBTTagList();
/* 283 */       for (int i = 0; i < func_70302_i_(); i++) {
/* 284 */         if (func_70301_a(i) != null) {
/* 285 */           NBTTagCompound slotEntry = new NBTTagCompound();
/* 286 */           slotEntry.func_74774_a("Slot", (byte)i);
/* 287 */           func_70301_a(i).func_77955_b(slotEntry);
/* 288 */           itemList.func_74742_a(slotEntry);
/*     */         }
/*     */       }
/*     */       
/* 292 */       NBTTagCompound inventory = new NBTTagCompound();
/* 293 */       inventory.func_74782_a("Items", itemList);
/* 294 */       nbtRoot.func_74782_a("Inventory", inventory);
/*     */     }
/*     */     
/*     */     protected void readFromNBT() {
/* 298 */       this.locked = true;
/*     */       
/* 300 */       ItemStack bag = this.player.func_70694_bm();
/*     */       
/* 302 */       if ((bag != null) && (bag.func_77973_b() == Witchery.Items.BREW_BAG) && (bag.func_77942_o())) {
/* 303 */         NBTTagCompound nbtRoot = bag.func_77978_p();
/*     */         
/* 305 */         this.title = nbtRoot.func_74775_l("display").func_74779_i("Name");
/*     */         
/* 307 */         NBTTagList itemList = nbtRoot.func_74775_l("Inventory").func_150295_c("Items", 10);
/* 308 */         for (int i = 0; i < itemList.func_74745_c(); i++) {
/* 309 */           NBTTagCompound slotEntry = itemList.func_150305_b(i);
/* 310 */           int j = slotEntry.func_74771_c("Slot") & 0xFF;
/*     */           
/* 312 */           if ((j >= 0) && (j < func_70302_i_())) {
/* 313 */             func_70299_a(j, ItemStack.func_77949_a(slotEntry));
/*     */           }
/*     */         }
/*     */       }
/* 317 */       this.locked = false;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public static class ContainerBrewBag
/*     */     extends Container
/*     */   {
/*     */     private int numRows;
/*     */     private ItemStack bag;
/*     */     
/*     */     public ContainerBrewBag(IInventory playerInventory, IInventory bagInventory, ItemStack bag)
/*     */     {
/* 330 */       this.numRows = (bagInventory.func_70302_i_() / 8);
/* 331 */       bagInventory.func_70295_k_();
/* 332 */       int offset = (this.numRows - 4) * 18;
/*     */       
/*     */ 
/* 335 */       for (int row = 0; row < this.numRows; row++) {
/* 336 */         for (int col = 0; col < 8; col++) {
/* 337 */           func_75146_a(new ItemBrewBag.SlotBrewBag(bagInventory, col + row * 8, 16 + col * 18, 18 + row * 18));
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 342 */       for (int row = 0; row < 3; row++) {
/* 343 */         for (int col = 0; col < 9; col++) {
/* 344 */           func_75146_a(new Slot(playerInventory, col + row * 9 + 9, 8 + col * 18, 103 + row * 18 + offset));
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 349 */       for (int col = 0; col < 9; col++) {
/* 350 */         func_75146_a(new Slot(playerInventory, col, 8 + col * 18, 161 + offset));
/*     */       }
/*     */       
/* 353 */       this.bag = bag;
/*     */     }
/*     */     
/*     */     public boolean func_75145_c(EntityPlayer player)
/*     */     {
/* 358 */       ItemStack itemStack = null;
/* 359 */       if (player.func_71045_bC() != null) {
/* 360 */         itemStack = player.func_71045_bC();
/*     */       }
/* 362 */       return (itemStack != null) && (itemStack.func_77973_b() == Witchery.Items.BREW_BAG);
/*     */     }
/*     */     
/*     */     public ItemStack func_82846_b(EntityPlayer player, int index)
/*     */     {
/* 367 */       ItemStack returnStack = null;
/* 368 */       Slot slot = (Slot)this.field_75151_b.get(index);
/*     */       
/* 370 */       if ((slot != null) && (slot.func_75216_d())) {
/* 371 */         ItemStack itemStack = slot.func_75211_c();
/* 372 */         if (!ItemBrewBag.SlotBrewBag.isBrew(itemStack)) {
/* 373 */           return returnStack;
/*     */         }
/* 375 */         returnStack = itemStack.func_77946_l();
/*     */         
/* 377 */         if (index < this.numRows * 9) {
/* 378 */           if (!func_75135_a(itemStack, this.numRows * 9, this.field_75151_b.size(), true)) {
/* 379 */             return null;
/*     */           }
/* 381 */         } else if (!func_75135_a(itemStack, 0, this.numRows * 9, false)) {
/* 382 */           return null;
/*     */         }
/*     */         
/* 385 */         if (itemStack.field_77994_a == 0) {
/* 386 */           slot.func_75215_d((ItemStack)null);
/*     */         } else {
/* 388 */           slot.func_75218_e();
/*     */         }
/*     */       }
/*     */       
/* 392 */       return returnStack;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   private static class SlotBrewBag
/*     */     extends Slot
/*     */   {
/*     */     public SlotBrewBag(IInventory inventory, int slot, int x, int y)
/*     */     {
/* 402 */       super(slot, x, y);
/*     */     }
/*     */     
/*     */     public boolean func_75214_a(ItemStack stack)
/*     */     {
/* 407 */       return isBrew(stack);
/*     */     }
/*     */     
/*     */     public static boolean isBrew(ItemStack stack) {
/* 411 */       return (stack != null) && ((Witchery.Items.GENERIC.isBrew(stack)) || ((Witchery.Items.BREW == stack.func_77973_b()) && (WitcheryBrewRegistry.INSTANCE.isSplash(stack.func_77978_p()))));
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/item/ItemBrewBag.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */