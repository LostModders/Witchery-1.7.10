/*     */ package com.emoniph.witchery.entity;
/*     */ 
/*     */ import com.emoniph.witchery.Witchery;
/*     */ import com.emoniph.witchery.WitcheryItems;
/*     */ import com.emoniph.witchery.item.ItemGeneral;
/*     */ import com.emoniph.witchery.item.ItemGeneral.SubItem;
/*     */ import com.emoniph.witchery.util.ChatUtil;
/*     */ import com.emoniph.witchery.util.Coord;
/*     */ import com.emoniph.witchery.util.ParticleEffect;
/*     */ import com.emoniph.witchery.util.SoundEffect;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.client.entity.AbstractClientPlayer;
/*     */ import net.minecraft.client.renderer.ThreadDownloadImageData;
/*     */ import net.minecraft.entity.DataWatcher;
/*     */ import net.minecraft.entity.EntityAgeable;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIFollowOwner;
/*     */ import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
/*     */ import net.minecraft.entity.ai.EntityAISwimming;
/*     */ import net.minecraft.entity.ai.EntityAITasks;
/*     */ import net.minecraft.entity.ai.EntityAIWander;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.monster.EntityMob;
/*     */ import net.minecraft.entity.passive.EntityTameable;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.pathfinding.PathEntity;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.ChunkCoordinates;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntityFollower extends EntityTameable
/*     */ {
/*  46 */   private EntityAIWander aiWander = new EntityAIWander(this, 0.8D);
/*     */   
/*  48 */   int ticksToLive = -1;
/*     */   int transformCount;
/*     */   
/*  51 */   public EntityFollower(World world) { super(world);
/*  52 */     func_70105_a(0.6F, 1.8F);
/*     */     
/*  54 */     func_70661_as().func_75495_e(true);
/*  55 */     func_70661_as().func_75491_a(false);
/*     */     
/*  57 */     this.field_70714_bg.func_75776_a(1, new EntityAISwimming(this));
/*  58 */     this.field_70714_bg.func_75776_a(2, new EntityAIMoveTowardsRestriction(this, 2.0D));
/*  59 */     this.field_70714_bg.func_75776_a(3, new EntityAIFollowOwner(this, 1.0D, 2.0F, 4.0F));
/*  60 */     this.field_70714_bg.func_75776_a(4, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
/*     */     
/*  62 */     this.field_70728_aV = 0;
/*     */   }
/*     */   
/*     */   protected int func_70693_a(EntityPlayer p_70693_1_) {
/*  66 */     return 0;
/*     */   }
/*     */   
/*     */   protected void func_110147_ax() {
/*  70 */     super.func_110147_ax();
/*  71 */     func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.3D);
/*     */   }
/*     */   
/*     */   public void setTTL(int ticks) {
/*  75 */     this.ticksToLive = ticks;
/*     */   }
/*     */   
/*     */   public EntityAgeable func_90011_a(EntityAgeable lover)
/*     */   {
/*  80 */     return null;
/*     */   }
/*     */   
/*     */   public String func_70005_c_()
/*     */   {
/*  85 */     switch (getFollowerType()) {
/*     */     case 0: 
/*  87 */       return Witchery.resource("entity.witchery.follower.elle.name");
/*     */     }
/*  89 */     return super.func_70005_c_();
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_70088_a()
/*     */   {
/*  95 */     super.func_70088_a();
/*  96 */     this.field_70180_af.func_75682_a(18, Integer.valueOf(0));
/*  97 */     this.field_70180_af.func_75682_a(19, String.valueOf(""));
/*     */   }
/*     */   
/*     */   public String getSkin() {
/* 101 */     return this.field_70180_af.func_75681_e(19);
/*     */   }
/*     */   
/*     */   public void setSkin(String mode) {
/* 105 */     this.field_70180_af.func_75692_b(19, mode);
/*     */   }
/*     */   
/*     */   public int getFollowerType() {
/* 109 */     return this.field_70180_af.func_75679_c(18);
/*     */   }
/*     */   
/*     */   public void func_70030_z()
/*     */   {
/* 114 */     super.func_70030_z();
/* 115 */     if ((!this.field_70170_p.field_72995_K) && (this.field_70173_aa == 1) && (getFollowerType() == 5)) {
/* 116 */       String skin = getSkin();
/* 117 */       if ((skin != null) && (!skin.isEmpty())) {
/* 118 */         EntityPlayer player = this.field_70170_p.func_72924_a(getSkin());
/* 119 */         if (player != null) {
/* 120 */           for (int i = 0; i <= 4; i++) {
/* 121 */             ItemStack stack = player.func_71124_b(i);
/* 122 */             if (stack != null) {
/* 123 */               func_70062_b(i, stack.func_77946_l());
/*     */             } else {
/* 125 */               func_70062_b(i, null);
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 132 */     if ((!this.field_70170_p.field_72995_K) && (this.field_70173_aa % 40 == 5) && (getFollowerType() == 5)) {
/* 133 */       attractAttention();
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_70636_d()
/*     */   {
/* 139 */     super.func_70636_d();
/* 140 */     if ((!this.field_70170_p.field_72995_K) && 
/* 141 */       (func_70089_S()) && (this.ticksToLive >= 0) && (--this.ticksToLive == 0)) {
/* 142 */       func_70106_y();
/*     */     }
/*     */   }
/*     */   
/*     */   public void setFollowerType(int followerType)
/*     */   {
/* 148 */     this.field_70180_af.func_75692_b(18, Integer.valueOf(followerType));
/* 149 */     if (followerType == 0) {
/* 150 */       this.field_70178_ae = true;
/* 151 */     } else if (followerType <= 5) {
/* 152 */       this.field_70714_bg.func_75776_a(5, this.aiWander);
/*     */     }
/*     */   }
/*     */   
/*     */   protected int func_70682_h(int par1)
/*     */   {
/* 158 */     if (getFollowerType() == 0) {
/* 159 */       return par1;
/*     */     }
/* 161 */     return super.func_70682_h(par1);
/*     */   }
/*     */   
/*     */   protected boolean func_70650_aV()
/*     */   {
/* 166 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   protected void func_82160_b(boolean p_82160_1_, int p_82160_2_) {}
/*     */   
/*     */ 
/*     */   protected void func_70628_a(boolean par1, int par2)
/*     */   {
/* 175 */     if ((getFollowerType() >= 1) && (getFollowerType() <= 4)) {
/* 176 */       func_70099_a(Witchery.Items.GENERIC.itemHeartOfGold.createStack(), 0.1F);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   private boolean isCourseTraversable(double waypointX, double waypointY, double waypointZ, double p_70790_7_)
/*     */   {
/* 183 */     double d4 = (waypointX - this.field_70165_t) / p_70790_7_;
/* 184 */     double d5 = (waypointY - this.field_70163_u) / p_70790_7_;
/* 185 */     double d6 = (waypointZ - this.field_70161_v) / p_70790_7_;
/* 186 */     AxisAlignedBB axisalignedbb = this.field_70121_D.func_72329_c();
/*     */     
/* 188 */     for (int i = 1; i < p_70790_7_; i++) {
/* 189 */       axisalignedbb.func_72317_d(d4, d5, d6);
/*     */       
/* 191 */       if (!this.field_70170_p.func_72945_a(this, axisalignedbb).isEmpty()) {
/* 192 */         return false;
/*     */       }
/*     */     }
/*     */     
/* 196 */     return true;
/*     */   }
/*     */   
/*     */   protected void func_70619_bc()
/*     */   {
/* 201 */     super.func_70619_bc();
/* 202 */     if ((!this.field_70170_p.field_72995_K) && (this.field_70173_aa % 10 == 1) && 
/* 203 */       (getFollowerType() == 0)) {
/* 204 */       doElleAI();
/*     */     }
/*     */   }
/*     */   
/*     */   public void doElleAI()
/*     */   {
/* 210 */     if (func_110175_bO()) {
/* 211 */       if (func_110173_bK()) {
/* 212 */         this.transformCount += 1;
/* 213 */         if (this.transformCount == 20) {
/* 214 */           EntityLivingBase owner = func_70902_q();
/* 215 */           if ((owner != null) && ((owner instanceof EntityPlayer))) {
/* 216 */             ChatUtil.sendTranslated(EnumChatFormatting.DARK_PURPLE, (EntityPlayer)owner, "item.witchery:glassgoblet.lilithquestsummon", new Object[0]);
/*     */             
/* 218 */             SoundEffect.WITCHERY_MOB_LILITH_TALK.playAt(this);
/*     */           }
/* 220 */         } else if (this.transformCount == 40) {
/* 221 */           this.transformCount = 0;
/* 222 */           ParticleEffect.INSTANT_SPELL.send(SoundEffect.FIREWORKS_BLAST1, this, 1.0D, 1.0D, 16);
/* 223 */           EntityLilith vampire = new EntityLilith(this.field_70170_p);
/* 224 */           vampire.func_110163_bv();
/* 225 */           vampire.func_82149_j(this);
/* 226 */           vampire.func_110161_a(null);
/* 227 */           this.field_70170_p.func_72900_e(this);
/* 228 */           this.field_70170_p.func_72838_d(vampire);
/* 229 */           vampire.field_70170_p.func_72889_a(null, 1017, (int)vampire.field_70165_t, (int)vampire.field_70163_u, (int)vampire.field_70161_v, 0);
/*     */           
/* 231 */           EntityLivingBase owner = func_70902_q();
/* 232 */           if ((owner != null) && ((owner instanceof EntityPlayer))) {
/* 233 */             ChatUtil.sendTranslated(EnumChatFormatting.DARK_PURPLE, (EntityPlayer)owner, "item.witchery:glassgoblet.lilithquestsummon2", new Object[0]);
/*     */             
/* 235 */             SoundEffect.WITCHERY_MOB_LILITH_TALK.playAt(vampire);
/*     */           }
/* 237 */           this.field_70170_p.func_72876_a(vampire, vampire.field_70165_t, vampire.field_70163_u, vampire.field_70161_v, 6.0F, true);
/*     */         }
/*     */       } else {
/* 240 */         double d0 = func_110172_bL().field_71574_a - this.field_70165_t;
/* 241 */         double d1 = func_110172_bL().field_71572_b - this.field_70163_u;
/* 242 */         double d2 = func_110172_bL().field_71573_c - this.field_70161_v;
/* 243 */         double d3 = d0 * d0 + d1 * d1 + d2 * d2;
/*     */         
/* 245 */         if (d3 > 0.0D) {
/* 246 */           d3 = MathHelper.func_76133_a(d3);
/* 247 */           double waypointX = this.field_70165_t + (this.field_70146_Z.nextFloat() * 2.0F - 1.0F) * 16.0F;
/* 248 */           double waypointY = this.field_70163_u + (this.field_70146_Z.nextFloat() * 2.0F - 1.0F) * 16.0F;
/* 249 */           double waypointZ = this.field_70161_v + (this.field_70146_Z.nextFloat() * 2.0F - 1.0F) * 16.0F;
/* 250 */           if (isCourseTraversable(waypointX, waypointY, waypointZ, d3)) {
/* 251 */             this.field_70159_w += d0 / d3 * 0.2D;
/* 252 */             this.field_70181_x += d1 / d3 * 0.2D;
/* 253 */             this.field_70179_y += d2 / d3 * 0.2D;
/*     */           }
/*     */         }
/*     */       }
/*     */     } else {
/* 258 */       for (int i = 0; i < 10; i++) {
/* 259 */         int j = MathHelper.func_76128_c(this.field_70165_t + this.field_70146_Z.nextInt(30) - 15.0D);
/* 260 */         int k = MathHelper.func_76128_c(this.field_70121_D.field_72338_b + this.field_70146_Z.nextInt(6) - 3.0D);
/* 261 */         int l = MathHelper.func_76128_c(this.field_70161_v + this.field_70146_Z.nextInt(30) - 15.0D);
/*     */         
/* 263 */         if (isLavaPool(this.field_70170_p, j, k, l, 6)) {
/* 264 */           func_110171_b(j, k, l, 2);
/* 265 */           func_152115_b("");
/* 266 */           break;
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private static boolean isLavaPool(World world, int x, int y, int z, int max) {
/* 273 */     if ((isLavaPoolColumn(world, x, y, z)) && (isLavaPoolColumn(world, x + 1, y, z)) && (isLavaPoolColumn(world, x - 1, y, z)) && (isLavaPoolColumn(world, x, y, z + 1)) && (isLavaPoolColumn(world, x, y, z - 1)))
/*     */     {
/*     */ 
/* 276 */       int max2 = max * max;
/* 277 */       for (int dx = x - max; dx <= x + max; dx++) {
/* 278 */         for (int dz = z - max; dz <= z + max; dz++) {
/* 279 */           double dist = Coord.distanceSq(x, y, z, dx, y, dz);
/* 280 */           if ((dist <= max2) && 
/* 281 */             (world.func_147439_a(dx, y, dz) != Blocks.field_150353_l)) {
/* 282 */             return false;
/*     */           }
/*     */         }
/*     */       }
/*     */       
/* 287 */       return true;
/*     */     }
/*     */     
/* 290 */     return false;
/*     */   }
/*     */   
/*     */   private static boolean isLavaPoolColumn(World world, int x, int y, int z) {
/* 294 */     if ((world.func_147439_a(x, y, z) == Blocks.field_150353_l) && (world.func_147437_c(x, y + 1, z)) && (world.func_147437_c(x, y + 2, z)))
/*     */     {
/* 296 */       int depth = 4;
/* 297 */       for (int dy = y - depth; dy < dy; dy++) {
/* 298 */         if (world.func_147439_a(x, y, z) != Blocks.field_150353_l) {
/* 299 */           return false;
/*     */         }
/*     */       }
/*     */       
/* 303 */       return true;
/*     */     }
/* 305 */     return false;
/*     */   }
/*     */   
/*     */   public void setOwner(EntityPlayer player) {
/* 309 */     func_70903_f(true);
/* 310 */     func_70778_a((PathEntity)null);
/* 311 */     func_70624_b((EntityLivingBase)null);
/* 312 */     func_70606_j(20.0F);
/* 313 */     func_152115_b(player.func_110124_au().toString());
/* 314 */     this.field_70170_p.func_72960_a(this, (byte)7);
/*     */   }
/*     */   
/*     */   public void func_70037_a(NBTTagCompound nbtRoot)
/*     */   {
/* 319 */     super.func_70037_a(nbtRoot);
/* 320 */     setFollowerType(nbtRoot.func_74762_e("FollowerType"));
/* 321 */     if (nbtRoot.func_74764_b("TicksToLive")) {
/* 322 */       this.ticksToLive = nbtRoot.func_74762_e("TicksToLive");
/*     */     } else {
/* 324 */       this.ticksToLive = -1;
/*     */     }
/*     */     
/* 327 */     if (nbtRoot.func_74764_b("Skin")) {
/* 328 */       setSkin(nbtRoot.func_74779_i("Skin"));
/*     */     }
/*     */   }
/*     */   
/*     */   public void func_70109_d(NBTTagCompound nbtRoot)
/*     */   {
/* 334 */     super.func_70109_d(nbtRoot);
/* 335 */     nbtRoot.func_74768_a("FollowerType", getFollowerType());
/* 336 */     nbtRoot.func_74768_a("TicksToLive", this.ticksToLive);
/* 337 */     nbtRoot.func_74778_a("Skin", getSkin());
/*     */   }
/*     */   
/*     */   public static String generateFollowerName(int followerType) {
/* 341 */     Random ra = new Random();
/* 342 */     if (followerType != 4) {
/* 343 */       return String.format("%s %s", new Object[] { FIRST_NAMES_F[ra.nextInt(FIRST_NAMES_F.length)], SURNAMES[ra.nextInt(SURNAMES.length)] });
/*     */     }
/*     */     
/* 346 */     return String.format("%s %s", new Object[] { FIRST_NAMES_M[ra.nextInt(FIRST_NAMES_M.length)], SURNAMES[ra.nextInt(SURNAMES.length)] });
/*     */   }
/*     */   
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   private ThreadDownloadImageData downloadImageSkin;
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   private ResourceLocation locationSkin;
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public ResourceLocation getLocationSkin()
/*     */   {
/* 359 */     if (this.locationSkin == null) {
/* 360 */       setupCustomSkin();
/*     */     }
/* 362 */     if (this.locationSkin != null) {
/* 363 */       return this.locationSkin;
/*     */     }
/* 365 */     return AbstractClientPlayer.field_110314_b;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   private void setupCustomSkin()
/*     */   {
/* 371 */     String owner = getSkin();
/* 372 */     if ((owner != null) && (!owner.isEmpty())) {
/* 373 */       this.locationSkin = AbstractClientPlayer.func_110311_f(owner);
/* 374 */       this.downloadImageSkin = AbstractClientPlayer.func_110304_a(this.locationSkin, owner);
/*     */     }
/*     */   }
/*     */   
/* 378 */   private static final String[] FIRST_NAMES_M = { "Abraham", "Adam", "Adrian", "Alex", "Alexander", "Allen", "Ambrose", "Andrew", "Anthony", "Arthur", "Avery", "Barnaby", "Bartholomew", "Benedict", "Benjamin", "Bernard", "Billy", "Bobby", "Charles", "Charley", "Christopher", "Colin", "Conrad", "Cuthbert", "Daniel", "Danny", "Davey", "David", "Edmund", "Edward", "Francis", "Fred", "Freddy", "Geoffrey", "George", "Georgie", "Gerard", "Gilbert", "Giles", "Gregory", "Hans", "Hansel", "Heinrich", "Henry", "Hugh", "Humphrey", "Isaac", "Jack", "Jacques", "James", "Jamie", "Jerome", "Jim", "Jimmy", "John", "Johnny", "Joseph", "Julian", "Lancelot", "Lawrence", "Leonard", "Lou", "Luke", "Mark", "Martin", "Mathias", "Matthew", "Merlin", "Michael", "Miles", "Nat", "Nathan", "Nathaniel", "Ned", "Nicholas", "Oliver", "Oswyn", "Patrick", "Paul", "Peter", "Philip", "Piers", "Ralph", "Reynold", "Richard", "Ricky", "Robert", "Robin", "Roger", "Rowland", "Samuel", "Simon", "Solomon", "Stephen", "Terence", "Thomas", "Tim", "Tobias", "Tom", "Tommy", "Valentine", "Walter", "Wendell", "Will", "William", "Willie" };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 391 */   private static final String[] FIRST_NAMES_F = { "Agnes", "Alice", "Amy", "Anna", "Annabella", "Anne", "Arabella", "Audrey", "Avis", "Barbara", "Beatrice", "Becky", "Bella", "Belle", "Bertha", "Bessy", "Betty", "Blanche", "Bo", "Bonny", "Bridget", "Catalina", "Catherine", "Cecily", "Charity", "Charlotte", "Christina", "Christine", "Cinderella", "Cindy", "Clara", "Clarissa", "Clemence", "Clementine", "Constance", "Daisy", "Denise", "Dorothy", "Edith", "Elinor", "Elizabeth", "Ella", "Ellen", "Elsa", "Elsie", "Emma", "Eve", "Evelyn", "Fawn", "Flora", "Florence", "Floretta", "Fortune", "Frances", "Frideswide", "Gertrude", "Gillian", "Ginger", "Goat", "Goatley", "Goldie", "Grace", "Gretel", "Helen", "Hilda", "Hazel", "Isabel", "Jane", "Janet", "Jemima", "Jill", "Joan", "Joyce", "Judith", "Julia", "Juliet", "Katherine", "Katie", "Kitty", "Lena", "Lily", "Liza", "Lizzie", "Lucy", "Mabel", "Maggie", "Margaret", "Margery", "Maria", "Marion", "Marlene", "Martha", "Mary", "Maud", "Mildred", "Millicent", "Molly", "Odette", "Pansy", "Pearl", "Petunia", "Philippa", "Polly", "Rachel", "Rapunzel", "Rebecca", "Rose", "Rosie", "Ruth", "Sarah", "Shiela", "Snow", "Susanna", "Susie", "Sybil", "Talia", "Thomasina", "Trudy", "Ursula", "Winifred" };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 405 */   private static final String[] SURNAMES = { "Apple", "Applegreen", "Applerose", "Appleseed", "Appleton", "Applewhite", "Baker", "Barnes", "Bean", "Beanblossom", "Beanstock", "Beaste", "Beasten", "Bell", "Berry", "Bird", "Blackbird", "Blackwood", "Boar", "Botter", "Bowers", "Bremen", "Brockett", "Buckle", "Butcher", "Candle", "Castle", "Castleton", "Cherry", "Cherrytree", "Cherrywood", "Cherrywell", "Cottage", "Daw", "Deer", "Dilly", "Dove", "Duck", "Duckfield", "Duckling", "Faery", "Fairy", "Fiddle", "Fiddler", "Fisher", "Fitcher", "Flinders", "Friday", "Frogg", "Frogley", "Frost", "Gold", "Goldencrown", "Goldhair", "Goodfellow", "Goose", "Gooseberry", "Gosling", "Gray", "Green", "Greengrass", "Griggs", "Grimm", "Grundy", "Hare", "Hay", "Hazeltree", "Hickory", "Hood", "Horner", "Hubbard", "Hunter", "Korbes", "Lamb", "Lampkin", "Lark", "Locket", "Locks", "MacDonald", "Mack", "Malone", "Marsh", "McDiddler", "Meadow", "Meadows", "Merrypips", "Miller", "Mills", "Mockingbird", "Monday", "Mouse", "Mouseley", "Muffet", "Mulberry", "Nimble", "Nutt", "O'Hare", "O'Lairy", "Pea", "Peartree", "Pease", "Peep", "Pie", "Pigeon", "Pinchme", "Piper", "Porgy", "Porridge", "Pott", "Pumpkin", "Pumpkinseed", "Reynard", "River", "Rivers", "Roley", "Rooster", "Roseberry", "Rosebottom", "Roseleaf", "Shoe", "Shoemaker", "Shorter", "Silver", "Slipper", "Sprat", "Saturday", "Sparrow", "Spindle", "Spindler", "Spinner", "Star", "Stone", "Stonebridge", "Sunday", "Swan", "Tailor", "Thatcher", "Thumb", "Thursday", "Toad", "Tower", "Towers", "Trot", "Tucker", "Tuesday", "Twist", "Wednesday", "White", "Whittington", "Winkie", "Wolf", "Wolfram", "Wolfson", "Wolfwood", "Woodcroft", "Woods" };
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void attractAttention()
/*     */   {
/*     */     EntityPlayer player;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 425 */     if (!this.field_70170_p.field_72995_K) {
/* 426 */       String owner = getSkin();
/* 427 */       if ((owner != null) && (!owner.isEmpty())) {
/* 428 */         player = this.field_70170_p.func_72924_a(owner);
/* 429 */         if (player != null) {
/* 430 */           List<EntityMob> list = this.field_70170_p.func_72872_a(EntityMob.class, this.field_70121_D.func_72314_b(16.0D, 8.0D, 16.0D));
/*     */           
/*     */ 
/* 433 */           for (EntityMob mob : list) {
/* 434 */             if (mob.func_70638_az() == player) {
/* 435 */               mob.func_70604_c(this);
/* 436 */               mob.func_70624_b(this);
/* 437 */               mob.func_70784_b(this);
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/entity/EntityFollower.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */