/*     */ package com.emoniph.witchery.integration;
/*     */ 
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.HashMap;
/*     */ 
/*     */ public class ReflectManager
/*     */ {
/*  11 */   public static HashMap<Class<?>, Class<?>> primitiveWrappers = new HashMap();
/*     */   
/*     */   static
/*     */   {
/*  15 */     primitiveWrappers.put(Integer.TYPE, Integer.class);
/*  16 */     primitiveWrappers.put(Short.TYPE, Short.class);
/*  17 */     primitiveWrappers.put(Byte.TYPE, Byte.class);
/*  18 */     primitiveWrappers.put(Long.TYPE, Long.class);
/*  19 */     primitiveWrappers.put(Double.TYPE, Double.class);
/*  20 */     primitiveWrappers.put(Float.TYPE, Float.class);
/*  21 */     primitiveWrappers.put(Boolean.TYPE, Boolean.class);
/*  22 */     primitiveWrappers.put(Character.TYPE, Character.class);
/*     */   }
/*     */   
/*     */   public static boolean isInstance(Class<?> class1, Object obj)
/*     */   {
/*  27 */     Class<?> primitive = (Class)primitiveWrappers.get(class1);
/*  28 */     if (primitive != null)
/*     */     {
/*  30 */       if ((primitive == Long.class) && (Long.class.isInstance(obj)))
/*  31 */         return true;
/*  32 */       if (((primitive == Long.class) || (primitive == Integer.class)) && (Integer.class.isInstance(obj)))
/*  33 */         return true;
/*  34 */       if (((primitive == Long.class) || (primitive == Integer.class) || (primitive == Short.class)) && (Short.class.isInstance(obj)))
/*  35 */         return true;
/*  36 */       if (((primitive == Long.class) || (primitive == Integer.class) || (primitive == Short.class) || (primitive == Byte.class)) && (Integer.class.isInstance(obj))) {
/*  37 */         return true;
/*     */       }
/*  39 */       if ((primitive == Double.class) && (Double.class.isInstance(obj)))
/*  40 */         return true;
/*  41 */       if (((primitive == Double.class) || (primitive == Float.class)) && (Float.class.isInstance(obj))) {
/*  42 */         return true;
/*     */       }
/*  44 */       return primitive.isInstance(obj);
/*     */     }
/*  46 */     return class1.isInstance(obj);
/*     */   }
/*     */   
/*     */   public static Class<?> findClass(String name)
/*     */   {
/*  51 */     return findClass(name, true);
/*     */   }
/*     */   
/*     */   public static boolean classExists(String name)
/*     */   {
/*  56 */     return findClass(name, false) != null;
/*     */   }
/*     */   
/*     */   public static Class<?> findClass(String name, boolean init)
/*     */   {
/*     */     try
/*     */     {
/*  63 */       return Class.forName(name, init, ReflectManager.class.getClassLoader());
/*     */     }
/*     */     catch (ClassNotFoundException cnfe)
/*     */     {
/*     */       try
/*     */       {
/*  69 */         return Class.forName("net.minecraft.src." + name, init, ReflectManager.class.getClassLoader());
/*     */       }
/*     */       catch (ClassNotFoundException cnfe2) {}
/*     */     }
/*  73 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   public static void setField(Class<?> class1, Object instance, String name, Object value)
/*     */     throws IllegalArgumentException, IllegalAccessException
/*     */   {
/*  80 */     setField(class1, instance, new String[] { name }, value);
/*     */   }
/*     */   
/*     */   public static void setField(Class<?> class1, Object instance, String[] names, Object value) throws IllegalArgumentException, IllegalAccessException
/*     */   {
/*  85 */     for (Field field : class1.getDeclaredFields())
/*     */     {
/*  87 */       boolean match = false;
/*  88 */       for (String name : names)
/*     */       {
/*  90 */         if (field.getName().equals(name))
/*     */         {
/*  92 */           match = true;
/*  93 */           break;
/*     */         }
/*     */       }
/*  96 */       if (match)
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/* 101 */         field.setAccessible(true);
/* 102 */         field.set(instance, value);
/* 103 */         return;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static void setField(Class<?> class1, Object instance, int fieldindex, Object value) throws IllegalArgumentException, IllegalAccessException {
/* 109 */     Field field = class1.getDeclaredFields()[fieldindex];
/* 110 */     field.setAccessible(true);
/* 111 */     field.set(instance, value);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void callMethod(Class<?> class1, String name, Object... params)
/*     */     throws IllegalArgumentException, IllegalAccessException, InvocationTargetException
/*     */   {
/* 121 */     callMethod(class1, null, new String[] { name }, params);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void callMethod(Class<?> class1, String[] names, Object... params)
/*     */     throws IllegalArgumentException, IllegalAccessException, InvocationTargetException
/*     */   {
/* 131 */     callMethod(class1, null, names, params);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void callMethod(Class<?> class1, Object instance, String name, Object... params)
/*     */     throws IllegalArgumentException, IllegalAccessException, InvocationTargetException
/*     */   {
/* 140 */     callMethod(class1, null, instance, new String[] { name }, params);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static void callMethod(Class<?> class1, Object instance, String[] names, Object... params)
/*     */     throws IllegalArgumentException, IllegalAccessException, InvocationTargetException
/*     */   {
/* 148 */     callMethod(class1, null, instance, names, params);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <R> R callMethod(Class<?> class1, Class<R> returntype, String name, Object... params)
/*     */     throws IllegalArgumentException, IllegalAccessException, InvocationTargetException
/*     */   {
/* 157 */     return (R)callMethod(class1, returntype, null, new String[] { name }, params);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static <R> R callMethod(Class<?> class1, Class<R> returntype, String[] names, Object... params)
/*     */     throws IllegalArgumentException, IllegalAccessException, InvocationTargetException
/*     */   {
/* 165 */     return (R)callMethod(class1, returntype, null, names, params);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static <R> R callMethod(Class<?> class1, Class<R> returntype, Object instance, String name, Object... params)
/*     */     throws IllegalArgumentException, IllegalAccessException, InvocationTargetException
/*     */   {
/* 173 */     return (R)callMethod(class1, returntype, instance, new String[] { name }, params);
/*     */   }
/*     */   
/*     */   public static <R> R callMethod(Class<?> class1, Class<R> returntype, Object instance, String[] names, Object... params) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
/*     */     label159:
/* 178 */     for (Method method : class1.getDeclaredMethods())
/*     */     {
/* 180 */       boolean match = false;
/* 181 */       for (String name : names)
/*     */       {
/* 183 */         if (method.getName().equals(name))
/*     */         {
/* 185 */           match = true;
/* 186 */           break;
/*     */         }
/*     */       }
/* 189 */       if (match)
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/* 194 */         Class<?>[] paramtypes = method.getParameterTypes();
/* 195 */         if (paramtypes.length == params.length)
/*     */         {
/*     */ 
/* 198 */           for (int i = 0; i < params.length; i++)
/*     */           {
/* 200 */             if (!isInstance(paramtypes[i], params[i])) {
/*     */               break label159;
/*     */             }
/*     */           }
/* 204 */           method.setAccessible(true);
/* 205 */           return (R)method.invoke(instance, params);
/*     */         } } }
/* 207 */     return null;
/*     */   }
/*     */   
/*     */   public static <T> T getField(Class<?> class1, Class<T> fieldType, Object instance, int fieldIndex) throws IllegalArgumentException, IllegalAccessException
/*     */   {
/* 212 */     Field field = class1.getDeclaredFields()[fieldIndex];
/* 213 */     field.setAccessible(true);
/* 214 */     return (T)field.get(instance);
/*     */   }
/*     */   
/*     */   public static <T> T getField(Class<?> class1, Class<T> fieldType, Object instance, String fieldName)
/*     */   {
/*     */     try
/*     */     {
/* 221 */       Field field = class1.getDeclaredField(fieldName);
/* 222 */       field.setAccessible(true);
/* 223 */       return (T)field.get(instance);
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 227 */       throw new RuntimeException(e);
/*     */     }
/*     */   }
/*     */   
/*     */   public static <T> T newInstance(Class<T> class1, Object... params) throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException {
/*     */     label88:
/* 233 */     for (Constructor<?> constructor : class1.getDeclaredConstructors())
/*     */     {
/* 235 */       Class<?>[] paramtypes = constructor.getParameterTypes();
/* 236 */       if (paramtypes.length == params.length)
/*     */       {
/*     */ 
/* 239 */         for (int i = 0; i < params.length; i++)
/*     */         {
/* 241 */           if (!isInstance(paramtypes[i], params[i])) {
/*     */             break label88;
/*     */           }
/*     */         }
/* 245 */         constructor.setAccessible(true);
/* 246 */         return (T)constructor.newInstance(params);
/*     */       } }
/* 248 */     return null;
/*     */   }
/*     */   
/*     */   public static boolean hasField(Class<?> class1, String fieldName)
/*     */   {
/*     */     try
/*     */     {
/* 255 */       class1.getDeclaredField(fieldName);
/* 256 */       return true;
/*     */     }
/*     */     catch (NoSuchFieldException nfe) {}
/*     */     
/* 260 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public static <T> T get(Field field, Class<T> class1)
/*     */   {
/* 266 */     return (T)get(field, class1, null);
/*     */   }
/*     */   
/*     */   public static <T> T get(Field field, Class<T> class1, Object instance)
/*     */   {
/*     */     try
/*     */     {
/* 273 */       return (T)field.get(instance);
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 277 */       throw new RuntimeException(e);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void set(Field field, Object value)
/*     */   {
/* 283 */     set(field, null, value);
/*     */   }
/*     */   
/*     */   public static void set(Field field, Object instance, Object value)
/*     */   {
/*     */     try
/*     */     {
/* 290 */       field.set(instance, value);
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 294 */       throw new RuntimeException(e);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/shannon/Desktop/witchery-1.7.10-0.24.1.jar!/com/emoniph/witchery/integration/ReflectManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */