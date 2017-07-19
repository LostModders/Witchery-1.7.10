/*     */ package com.emoniph.witchery.item;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.brewing.WitcheryBrewRegistry;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.inventory.InventoryBasic;
/*     */ import net.minecraft.inventory.Slot;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class ItemLeonardsUrn extends ItemBase
/*     */ {
/*     */   @SideOnly(Side.CLIENT)
/*     */   private IIcon[] icons;
/*     */   
/*     */   public ItemLeonardsUrn()
/*     */   {
/*  31 */     func_77625_d(1);
/*  32 */     func_77656_e(0);
/*  33 */     func_77627_a(true);
/*     */   }
/*     */   
/*     */   public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean advancedTooltips)
/*     */   {
/*  38 */     if (stack != null) {
/*  39 */       String localText = Witchery.resource(func_77658_a() + ".tip");
/*  40 */       if (localText != null) {
/*  41 */         for (String s : localText.split("\n")) {
/*  42 */           if (!s.isEmpty()) {
/*  43 */             list.add(s);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_77617_a(int damageValue)
/*     */   {
/*  56 */     return this.icons[Math.min(damageValue, this.icons.length - 1)];
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_94581_a(IIconRegister iconRegister)
/*     */   {
/*  62 */     this.icons = new IIcon[4];
/*  63 */     for (int i = 0; i < this.icons.length; i++) {
/*  64 */       this.icons[i] = iconRegister.func_94245_a(func_111208_A() + i);
/*     */     }
/*  66 */     this.field_77791_bV = this.icons[0];
/*     */   }
/*     */   
/*     */   public void func_150895_a(Item item, CreativeTabs tab, List items)
/*     */   {
/*  71 */     for (int i = 0; i < 4; i++) {
/*  72 */       items.add(new ItemStack(this, 1, i));
/*     */     }
/*     */   }
/*     */   
/*     */   public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player)
/*     */   {
/*  78 */     if (!world.field_72995_K) {
/*  79 */       player.openGui(Witchery.instance, 8, world, 0, 0, 0);
/*     */     }
/*  81 */     return stack;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_77636_d(ItemStack stack)
/*     */   {
/*  87 */     return true;
/*     */   }
/*     */   
/*     */   public EnumRarity func_77613_e(ItemStack stack)
/*     */   {
/*  92 */     return EnumRarity.epic;
/*     */   }
/*     */   
/*     */ 
/*     */   public static class InventoryLeonardsUrn
/*     */     extends InventoryBasic
/*     */   {
/*     */     protected String title;
/*     */     
/*     */     protected EntityPlayer player;
/*     */     
/*     */     protected ItemStack urnStack;
/* 104 */     protected boolean locked = false;
/*     */     
/*     */     public InventoryLeonardsUrn(EntityPlayer player) {
/* 107 */       this(player, null);
/*     */     }
/*     */     
/*     */     public InventoryLeonardsUrn(EntityPlayer player, ItemStack stack) {
/* 111 */       super(false, player.func_70694_bm() != null ? player.func_70694_bm().func_77960_j() + 1 : stack != null ? stack.func_77960_j() + 1 : 1);
/*     */       
/* 113 */       this.urnStack = stack;
/* 114 */       this.player = player;
/*     */       
/* 116 */       if (!hasInventory()) {
/* 117 */         createInventory();
/*     */       }
/*     */       
/* 120 */       readFromNBT();
/*     */     }
/*     */     
/*     */     public int func_70297_j_()
/*     */     {
/* 125 */       return 1;
/*     */     }
/*     */     
/*     */     public void func_70296_d()
/*     */     {
/* 130 */       super.func_70296_d();
/* 131 */       if (!this.locked) {
/* 132 */         writeToNBT();
/*     */       }
/*     */     }
/*     */     
/*     */     public void func_70295_k_()
/*     */     {
/* 138 */       readFromNBT();
/*     */     }
/*     */     
/*     */     public void func_70305_f()
/*     */     {
/* 143 */       writeToNBT();
/*     */     }
/*     */     
/*     */     public String func_145825_b()
/*     */     {
/* 148 */       return this.title;
/*     */     }
/*     */     
/*     */     protected boolean hasInventory() {
/* 152 */       if (this.urnStack != null) {
/* 153 */         return (this.urnStack != null) && (this.urnStack.func_77942_o()) && (this.urnStack.func_77978_p().func_74764_b("Inventory"));
/*     */       }
/*     */       
/* 156 */       return (this.player.func_70694_bm() != null) && (this.player.func_70694_bm().func_77942_o()) && (this.player.func_70694_bm().func_77978_p().func_74764_b("Inventory"));
/*     */     }
/*     */     
/*     */ 
/*     */     protected void createInventory()
/*     */     {
/* 162 */       this.title = new String(this.urnStack != null ? this.urnStack.func_82833_r() : this.player.func_70694_bm().func_82833_r());
/*     */       
/* 164 */       writeToNBT();
/*     */     }
/*     */     
/*     */     protected void writeToNBT() {
/* 168 */       ItemStack urnStack = this.urnStack != null ? this.urnStack : this.player.func_70694_bm();
/*     */       
/* 170 */       if ((urnStack == null) || (urnStack.func_77973_b() != Witchery.Items.LEONARDS_URN)) {
/* 171 */         return;
/*     */       }
/*     */       
/* 174 */       if (!urnStack.func_77942_o()) {
/* 175 */         urnStack.func_77982_d(new NBTTagCompound());
/*     */       }
/*     */       
/* 178 */       NBTTagCompound nbtRoot = urnStack.func_77978_p();
/*     */       
/* 180 */       NBTTagCompound name = new NBTTagCompound();
/* 181 */       name.func_74778_a("Name", func_145825_b());
/* 182 */       nbtRoot.func_74782_a("display", name);
/*     */       
/* 184 */       NBTTagList itemList = new NBTTagList();
/* 185 */       for (int i = 0; i < func_70302_i_(); i++) {
/* 186 */         if (func_70301_a(i) != null) {
/* 187 */           NBTTagCompound slotEntry = new NBTTagCompound();
/* 188 */           slotEntry.func_74774_a("Slot", (byte)i);
/* 189 */           func_70301_a(i).func_77955_b(slotEntry);
/* 190 */           itemList.func_74742_a(slotEntry);
/*     */         }
/*     */       }
/*     */       
/* 194 */       NBTTagCompound inventory = new NBTTagCompound();
/* 195 */       inventory.func_74782_a("Items", itemList);
/* 196 */       nbtRoot.func_74782_a("Inventory", inventory);
/*     */     }
/*     */     
/*     */     protected void readFromNBT() {
/* 200 */       this.locked = true;
/*     */       
/* 202 */       ItemStack bag = this.urnStack != null ? this.urnStack : this.player.func_70694_bm();
/*     */       
/* 204 */       if ((bag != null) && (bag.func_77973_b() == Witchery.Items.LEONARDS_URN) && (bag.func_77942_o())) {
/* 205 */         NBTTagCompound nbtRoot = bag.func_77978_p();
/*     */         
/* 207 */         this.title = nbtRoot.func_74775_l("display").func_74779_i("Name");
/*     */         
/* 209 */         NBTTagList itemList = nbtRoot.func_74775_l("Inventory").func_150295_c("Items", 10);
/*     */         
/* 211 */         for (int i = 0; i < itemList.func_74745_c(); i++) {
/* 212 */           NBTTagCompound slotEntry = itemList.func_150305_b(i);
/* 213 */           int j = slotEntry.func_74771_c("Slot") & 0xFF;
/*     */           
/* 215 */           if ((j >= 0) && (j < func_70302_i_())) {
/* 216 */             func_70299_a(j, ItemStack.func_77949_a(slotEntry));
/*     */           }
/*     */         }
/*     */       }
/* 220 */       this.locked = false;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public static class ContainerLeonardsUrn
/*     */     extends Container
/*     */   {
/*     */     private int numRows;
/*     */     private ItemStack bag;
/*     */     
/*     */     public ContainerLeonardsUrn(IInventory playerInventory, IInventory bagInventory, ItemStack bag)
/*     */     {
/* 233 */       this.numRows = 1;
/* 234 */       bagInventory.func_70295_k_();
/* 235 */       int size = bagInventory.func_70302_i_();
/*     */       
/* 237 */       int slot = 0;
/*     */       
/* 239 */       func_75146_a(new ItemLeonardsUrn.SlotLeonardsUrn(bagInventory, slot++, 80, 22));
/*     */       
/* 241 */       if (size >= 2) {
/* 242 */         func_75146_a(new ItemLeonardsUrn.SlotLeonardsUrn(bagInventory, slot++, 80, 70));
/*     */       }
/*     */       
/* 245 */       if (size >= 3) {
/* 246 */         func_75146_a(new ItemLeonardsUrn.SlotLeonardsUrn(bagInventory, slot++, 103, 46));
/*     */       }
/*     */       
/* 249 */       if (size >= 4) {
/* 250 */         func_75146_a(new ItemLeonardsUrn.SlotLeonardsUrn(bagInventory, slot++, 56, 46));
/*     */       }
/*     */       
/*     */ 
/* 254 */       for (int row = 0; row < 3; row++) {
/* 255 */         for (int col = 0; col < 9; col++) {
/* 256 */           func_75146_a(new Slot(playerInventory, col + row * 9 + 9, 8 + col * 18, 103 + row * 18));
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 261 */       for (int col = 0; col < 9; col++) {
/* 262 */         func_75146_a(new Slot(playerInventory, col, 8 + col * 18, 161));
/*     */       }
/*     */       
/* 265 */       this.bag = bag;
/*     */     }
/*     */     
/*     */     public boolean func_75145_c(EntityPlayer player)
/*     */     {
/* 270 */       ItemStack itemStack = null;
/* 271 */       if (player.func_71045_bC() != null) {
/* 272 */         itemStack = player.func_71045_bC();
/*     */       }
/* 274 */       return (itemStack != null) && (itemStack.func_77973_b() == Witchery.Items.LEONARDS_URN);
/*     */     }
/*     */     
/*     */     public ItemStack func_82846_b(EntityPlayer player, int index)
/*     */     {
/* 279 */       ItemStack returnStack = null;
/* 280 */       Slot slot = (Slot)this.field_75151_b.get(index);
/*     */       
/* 282 */       if ((slot != null) && (slot.func_75216_d())) {
/* 283 */         ItemStack itemStack = slot.func_75211_c();
/* 284 */         if (!ItemLeonardsUrn.SlotLeonardsUrn.isBrew(itemStack)) {
/* 285 */           return returnStack;
/*     */         }
/* 287 */         returnStack = itemStack.func_77946_l();
/*     */         
/* 289 */         if (index < this.numRows * 9) {
/* 290 */           if (!func_75135_a(itemStack, this.numRows * 9, this.field_75151_b.size(), true)) {
/* 291 */             return null;
/*     */           }
/* 293 */         } else if (!func_75135_a(itemStack, 0, this.numRows * 9, false)) {
/* 294 */           return null;
/*     */         }
/*     */         
/* 297 */         if (itemStack.field_77994_a == 0) {
/* 298 */           slot.func_75215_d((ItemStack)null);
/*     */         } else {
/* 300 */           slot.func_75218_e();
/*     */         }
/*     */       }
/*     */       
/* 304 */       return returnStack;
/*     */     }
/*     */     
/*     */     protected boolean func_75135_a(ItemStack stack, int slotCount, int invSize, boolean upper)
/*     */     {
/* 309 */       boolean flag1 = false;
/* 310 */       int k = slotCount;
/*     */       
/* 312 */       if (upper) {
/* 313 */         k = invSize - 1;
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 319 */       int maxStackSize = upper ? stack.func_77976_d() : 1;
/*     */       
/* 321 */       if (stack.func_77985_e())
/*     */       {
/* 323 */         while ((stack.field_77994_a > 0) && (((!upper) && (k < invSize)) || ((upper) && (k >= slotCount)))) {
/* 324 */           Slot slot = (Slot)this.field_75151_b.get(k);
/* 325 */           ItemStack itemstack1 = slot.func_75211_c();
/*     */           
/* 327 */           if ((itemstack1 != null) && (itemstack1.func_77973_b() == stack.func_77973_b()) && ((!stack.func_77981_g()) || (stack.func_77960_j() == itemstack1.func_77960_j())) && (ItemStack.func_77970_a(stack, itemstack1)))
/*     */           {
/*     */ 
/*     */ 
/* 331 */             int l = itemstack1.field_77994_a + stack.field_77994_a;
/*     */             
/* 333 */             if (l <= maxStackSize) {
/* 334 */               stack.field_77994_a = 0;
/* 335 */               itemstack1.field_77994_a = l;
/* 336 */               slot.func_75218_e();
/* 337 */               flag1 = true;
/* 338 */             } else if (itemstack1.field_77994_a < maxStackSize) {
/* 339 */               stack.field_77994_a -= maxStackSize - itemstack1.field_77994_a;
/* 340 */               itemstack1.field_77994_a = maxStackSize;
/* 341 */               slot.func_75218_e();
/* 342 */               flag1 = true;
/*     */             }
/*     */           }
/*     */           
/* 346 */           if (upper) {
/* 347 */             k--;
/*     */           } else {
/* 349 */             k++;
/*     */           }
/*     */         }
/*     */       }
/*     */       
/* 354 */       if (stack.field_77994_a > 0) {
/* 355 */         if (upper) {
/* 356 */           k = invSize - 1;
/*     */         } else {
/* 358 */           k = slotCount;
/*     */         }
/*     */         
/* 361 */         while (((!upper) && (k < invSize)) || ((upper) && (k >= slotCount))) {
/* 362 */           Slot slot = (Slot)this.field_75151_b.get(k);
/* 363 */           ItemStack itemstack1 = slot.func_75211_c();
/*     */           
/* 365 */           if (itemstack1 == null) {
/* 366 */             slot.func_75215_d(upper ? stack.func_77946_l() : stack.func_77979_a(1));
/* 367 */             slot.func_75218_e();
/* 368 */             if (upper) {
/* 369 */               stack.field_77994_a = 0;
/*     */             }
/* 371 */             flag1 = true;
/* 372 */             break;
/*     */           }
/*     */           
/* 375 */           if (upper) {
/* 376 */             k--;
/*     */           } else {
/* 378 */             k++;
/*     */           }
/*     */         }
/*     */       }
/*     */       
/* 383 */       return flag1;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   private static class SlotLeonardsUrn
/*     */     extends Slot
/*     */   {
/*     */     public SlotLeonardsUrn(IInventory inventory, int slot, int x, int y)
/*     */     {
/* 393 */       super(slot, x, y);
/*     */     }
/*     */     
/*     */     public boolean func_75214_a(ItemStack stack)
/*     */     {
/* 398 */       return isBrew(stack);
/*     */     }
/*     */     
/*     */     public static boolean isBrew(ItemStack stack) {
/* 402 */       return (stack != null) && (Witchery.Items.BREW == stack.func_77973_b()) && (WitcheryBrewRegistry.INSTANCE.isSplash(stack.func_77978_p()));
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/item/ItemLeonardsUrn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */