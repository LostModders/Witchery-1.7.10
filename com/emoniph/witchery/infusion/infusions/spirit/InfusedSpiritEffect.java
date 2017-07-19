/*     */ package com.emoniph.witchery.infusion.infusions.spirit;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.entity.EntityBanshee;
/*     */ import com.emoniph.witchery.entity.EntityPoltergeist;
/*     */ import com.emoniph.witchery.entity.EntitySpectre;
/*     */ import com.emoniph.witchery.entity.EntitySpirit;
/*     */ import com.emoniph.witchery.util.Const;
/*     */ import com.emoniph.witchery.util.ParticleEffect;
/*     */ import com.emoniph.witchery.util.SoundEffect;
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ public abstract class InfusedSpiritEffect
/*     */ {
/*  23 */   public static final ArrayList<InfusedSpiritEffect> effectList = new ArrayList();
/*     */   
/*  25 */   public static final InfusedSpiritEffect POPPET_ENHANCEMENT = new InfusedSpiritEnhancedPoppetEffect(1, 3, 1, 1, 1);
/*  26 */   public static final InfusedSpiritEffect SENTINAL = new InfusedSpiritSentinalEffect(2, 3, 3, 0, 0);
/*  27 */   public static final InfusedSpiritEffect SCREAMER = new InfusedSpiritScreamerEffect(3, 3, 0, 2, 0);
/*  28 */   public static final InfusedSpiritEffect TWISTER = new InfusedSpiritTwisterEffect(4, 3, 0, 0, 2);
/*  29 */   public static final InfusedSpiritEffect GHOST_WALKER = new InfusedSpiritGhostWalkerEffect(5, 3, 1, 1, 0);
/*  30 */   public static final InfusedSpiritEffect DEATH = new InfusedSpiritEffect(6, "death", 0, 5, 5, 5, false)
/*     */   {
/*     */     public boolean doUpdateEffect(TileEntity tile, boolean triggered, ArrayList<EntityLivingBase> foundEntities) {
/*  33 */       return true;
/*     */     }
/*     */   };
/*     */   
/*     */ 
/*     */   public static final double RANGE = 16.0D;
/*     */   
/*     */ 
/*     */   public static final double RANGE_SQ = 256.0D;
/*     */   public final int id;
/*     */   public final int spirits;
/*     */   public final int spectres;
/*     */   public final int banshees;
/*     */   public final int poltergeists;
/*     */   public final String unlocalizedName;
/*     */   private boolean inBook;
/*     */   
/*  50 */   protected InfusedSpiritEffect(int id, String unlocalizedName, int spirits, int spectres, int banshees, int poltergeists) { this(id, unlocalizedName, spirits, spectres, banshees, poltergeists, true); }
/*     */   
/*     */   protected InfusedSpiritEffect(int id, String unlocalizedName, int spirits, int spectres, int banshees, int poltergeists, boolean inBook) {
/*  53 */     this.id = id;
/*  54 */     this.spirits = spirits;
/*  55 */     this.spectres = spectres;
/*  56 */     this.banshees = banshees;
/*  57 */     this.poltergeists = poltergeists;
/*  58 */     this.unlocalizedName = unlocalizedName;
/*  59 */     this.inBook = inBook;
/*     */     
/*  61 */     while (effectList.size() <= id) {
/*  62 */       effectList.add(null);
/*     */     }
/*  64 */     effectList.set(id, this);
/*     */   }
/*     */   
/*     */   public String getDescription() {
/*  68 */     StringBuffer sb = new StringBuffer();
/*     */     
/*  70 */     sb.append("§n");
/*  71 */     sb.append(getDisplayName());
/*  72 */     sb.append("§r");
/*  73 */     sb.append(Const.BOOK_NEWLINE);
/*  74 */     sb.append(Const.BOOK_NEWLINE);
/*     */     
/*  76 */     String description = Witchery.resource("witchery.fetish." + this.unlocalizedName + ".desc");
/*  77 */     if (!description.equals("witchery.fetish." + this.unlocalizedName + ".desc")) {
/*  78 */       sb.append(description);
/*  79 */       sb.append(Const.BOOK_NEWLINE);
/*  80 */       sb.append(Const.BOOK_NEWLINE);
/*     */     }
/*     */     
/*  83 */     sb.append(Witchery.resource("witchery.book.burning3"));
/*  84 */     sb.append(Const.BOOK_NEWLINE);
/*  85 */     sb.append(Const.BOOK_NEWLINE);
/*  86 */     if (this.spirits > 0) {
/*  87 */       sb.append(String.format("§8>§0  %s: %d", new Object[] { Witchery.resource("entity.witchery.spirit.name"), Integer.valueOf(this.spirits) }));
/*  88 */       sb.append(Const.BOOK_NEWLINE);
/*     */     }
/*     */     
/*  91 */     if (this.spectres > 0) {
/*  92 */       sb.append(String.format("§8>§0  %s: %d", new Object[] { Witchery.resource("entity.witchery.spectre.name"), Integer.valueOf(this.spectres) }));
/*  93 */       sb.append(Const.BOOK_NEWLINE);
/*     */     }
/*     */     
/*  96 */     if (this.banshees > 0) {
/*  97 */       sb.append(String.format("§8>§0  %s: %d", new Object[] { Witchery.resource("entity.witchery.banshee.name"), Integer.valueOf(this.banshees) }));
/*  98 */       sb.append(Const.BOOK_NEWLINE);
/*     */     }
/*     */     
/* 101 */     if (this.poltergeists > 0) {
/* 102 */       sb.append(String.format("§8>§0  %s: %d", new Object[] { Witchery.resource("entity.witchery.poltergeist.name"), Integer.valueOf(this.poltergeists) }));
/* 103 */       sb.append(Const.BOOK_NEWLINE);
/*     */     }
/*     */     
/* 106 */     return sb.toString();
/*     */   }
/*     */   
/*     */   private void setInBook(boolean inBook) {
/* 110 */     this.inBook = inBook;
/*     */   }
/*     */   
/*     */   public boolean isInBook() {
/* 114 */     return this.inBook;
/*     */   }
/*     */   
/* 117 */   private String localizedName = null;
/*     */   
/* 119 */   private String getDisplayName() { if (this.localizedName == null) {
/* 120 */       this.localizedName = Witchery.resource("witchery.fetish." + this.unlocalizedName + ".name");
/*     */     }
/* 122 */     return this.localizedName;
/*     */   }
/*     */   
/*     */   public boolean isBound(IFetishTile tile) {
/* 126 */     return tile.getEffectType() == this.id;
/*     */   }
/*     */   
/*     */   public int getCooldownTicks() {
/* 130 */     return -1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static int tryBindFetish(World world, ItemStack stack, ArrayList<EntitySpirit> spiritList, ArrayList<EntitySpectre> spectreList, ArrayList<EntityBanshee> bansheeList, ArrayList<EntityPoltergeist> poltergeistList)
/*     */   {
/* 139 */     for (InfusedSpiritEffect effect : effectList) {
/* 140 */       if ((effect != null) && (effect.spirits <= spiritList.size()) && (effect.spectres <= spectreList.size()) && (effect.banshees <= bansheeList.size()) && (effect.poltergeists <= poltergeistList.size()))
/*     */       {
/*     */ 
/*     */ 
/* 144 */         if (!stack.func_77942_o()) {
/* 145 */           stack.func_77982_d(new NBTTagCompound());
/*     */         }
/* 147 */         NBTTagCompound nbtRoot = stack.func_77978_p();
/* 148 */         nbtRoot.func_74768_a("WITCSpiritEffect", effect.id);
/*     */         
/* 150 */         consumeSpirits(world, effect.spirits, spiritList);
/* 151 */         consumeSpirits(world, effect.spectres, spectreList);
/* 152 */         consumeSpirits(world, effect.banshees, bansheeList);
/* 153 */         consumeSpirits(world, effect.poltergeists, poltergeistList);
/* 154 */         return effect == DEATH ? 2 : 1;
/*     */       }
/*     */     }
/*     */     
/* 158 */     return 0;
/*     */   }
/*     */   
/*     */   private static <T extends EntityLiving> void consumeSpirits(World world, int count, ArrayList<T> list) {
/* 162 */     for (int i = 0; i < count; i++) {
/* 163 */       EntityLiving entity = (EntityLiving)list.get(i);
/* 164 */       if (!world.field_72995_K) {
/* 165 */         entity.func_70106_y();
/* 166 */         ParticleEffect.PORTAL.send(SoundEffect.RANDOM_POP, entity, 1.0D, 2.0D, 16);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static String getEffectDisplayName(ItemStack stack) {
/* 172 */     if (stack.func_77942_o()) {
/* 173 */       NBTTagCompound nbtRoot = stack.func_77978_p();
/* 174 */       if (nbtRoot.func_74764_b("WITCSpiritEffect")) {
/* 175 */         int effect = nbtRoot.func_74762_e("WITCSpiritEffect");
/* 176 */         if (effect > 0) {
/* 177 */           return ((InfusedSpiritEffect)effectList.get(effect)).getDisplayName();
/*     */         }
/*     */       }
/*     */     }
/* 181 */     return null;
/*     */   }
/*     */   
/*     */   public static int getEffectID(ItemStack stack) {
/* 185 */     if (stack.func_77942_o()) {
/* 186 */       NBTTagCompound nbtRoot = stack.func_77978_p();
/* 187 */       if (nbtRoot.func_74764_b("WITCSpiritEffect")) {
/* 188 */         return nbtRoot.func_74762_e("WITCSpiritEffect");
/*     */       }
/*     */     }
/* 191 */     return 0;
/*     */   }
/*     */   
/*     */   public static ItemStack setEffectID(ItemStack stack, int id) {
/* 195 */     if (!stack.func_77942_o()) {
/* 196 */       stack.func_77982_d(new NBTTagCompound());
/*     */     }
/* 198 */     NBTTagCompound nbtRoot = stack.func_77978_p();
/*     */     
/* 200 */     nbtRoot.func_74768_a("WITCSpiritEffect", id);
/* 201 */     return stack;
/*     */   }
/*     */   
/*     */   public static ItemStack setEffect(ItemStack stack, InfusedSpiritEffect effect) {
/* 205 */     return setEffectID(stack, effect.id);
/*     */   }
/*     */   
/*     */   public boolean isNearTo(EntityPlayer player) {
/* 209 */     for (Object obj : player.field_70170_p.field_147482_g) {
/* 210 */       if ((obj instanceof IFetishTile)) {
/* 211 */         IFetishTile tile = (IFetishTile)obj;
/* 212 */         if ((player.func_70092_e(0.5D + tile.getX(), 0.5D + tile.getY(), 0.5D + tile.getZ()) <= 256.0D) && 
/* 213 */           (isBound(tile))) {
/* 214 */           return true;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 219 */     return false;
/*     */   }
/*     */   
/*     */   public static InfusedSpiritEffect getEffect(IFetishTile tile) {
/* 223 */     return tile.getEffectType() > 0 ? (InfusedSpiritEffect)effectList.get(tile.getEffectType()) : null;
/*     */   }
/*     */   
/*     */   public abstract boolean doUpdateEffect(TileEntity paramTileEntity, boolean paramBoolean, ArrayList<EntityLivingBase> paramArrayList);
/*     */   
/*     */   public boolean isRedstoneSignaller() {
/* 229 */     return false;
/*     */   }
/*     */   
/*     */   public double getRadius() {
/* 233 */     return 0.0D;
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/infusion/infusions/spirit/InfusedSpiritEffect.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */