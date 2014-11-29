/*    */ package com.scu.Media;
/*    */ 
/*    */ import java.applet.Applet;
/*    */ import java.applet.AudioClip;
/*    */ import java.io.File;
/*    */ 
/*    */ public class MediaPlay
/*    */ {
/*    */   private static MediaPlay instance;
/* 11 */   private AudioClip clip = null;
/* 12 */   private String mp3rootpath = "";
/*    */ 
/* 14 */   private MediaPlay() {
		this.mp3rootpath =  "./mp3/";
		}
/*    */ 
/*    */   public static MediaPlay getInstance() {
/* 17 */     if (instance == null)
/* 18 */       instance = new MediaPlay();
/* 19 */     return instance;
/*    */   }
/*    */ 
/*    */   public void play(String mp3file)
/*    */   {
			System.out.println("mpsfile-------------------"+mp3file);
/*    */     try {
/* 25 */       if (this.clip != null)
/* 26 */         this.clip.stop();
/* 27 */       File mp3File = new File(this.mp3rootpath + mp3file);
/* 28 */       if (!mp3File.exists())
/* 29 */         return;
/* 30 */       this.clip = Applet.newAudioClip(mp3File.toURL());
/* 31 */       this.clip.play();
/*    */     }
/*    */     catch (Exception localException)
/*    */     {
/*    */     }
/*    */   }
/*    */ }
