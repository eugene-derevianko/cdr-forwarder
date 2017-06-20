package com.symulakr.telegram.bot.model;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity(name = "cdr")
public class Cdr {

   @Id private int id;
   private Date calldate;
   private String clid;
   private String src;
   private String dst;
   private String dcontext;
   private String lastapp;
   private Float duration;
   private Float billsec;
   private Float route_rate;
   private Float charge;
   @Convert(converter = DispositionConverter.class)
   private Disposition disposition;
   private String channel;
   private String dstchannel;
   private String amaflags;
   private String accountcode;
   private String uniqueid;
   private Float userfield;

   public int getId(){
      return id;
   }

   public void setId(int id){
      this.id = id;
   }

   public Date getCalldate(){
      return calldate;
   }

   public void setCalldate(Date calldate){
      this.calldate = calldate;
   }

   public String getClid(){
      return clid;
   }

   public void setClid(String clid){
      this.clid = clid;
   }

   public String getSrc(){
      return src;
   }

   public void setSrc(String src){
      this.src = src;
   }

   public String getDst(){
      return dst;
   }

   public void setDst(String dst){
      this.dst = dst;
   }

   public String getDcontext(){
      return dcontext;
   }

   public void setDcontext(String dcontext){
      this.dcontext = dcontext;
   }

   public String getLastapp(){
      return lastapp;
   }

   public void setLastapp(String lastapp){
      this.lastapp = lastapp;
   }

   public float getDuration(){
      return duration;
   }

   public void setDuration(float duration){
      this.duration = duration;
   }

   public float getBillsec(){
      return billsec;
   }

   public void setBillsec(float billsec){
      this.billsec = billsec;
   }

   public float getRoute_rate(){
      return route_rate;
   }

   public void setRoute_rate(float route_rate){
      this.route_rate = route_rate;
   }

   public float getCharge(){
      return charge;
   }

   public void setCharge(float charge){
      this.charge = charge;
   }

   public Disposition getDisposition(){
      return disposition;
   }

   public void setDisposition(Disposition disposition){
      this.disposition = disposition;
   }

   public String getChannel(){
      return channel;
   }

   public void setChannel(String channel){
      this.channel = channel;
   }

   public String getDstchannel(){
      return dstchannel;
   }

   public void setDstchannel(String dstchannel){
      this.dstchannel = dstchannel;
   }

   public String getAmaflags(){
      return amaflags;
   }

   public void setAmaflags(String amaflags){
      this.amaflags = amaflags;
   }

   public String getAccountcode(){
      return accountcode;
   }

   public void setAccountcode(String accountcode){
      this.accountcode = accountcode;
   }

   public String getUniqueid(){
      return uniqueid;
   }

   public void setUniqueid(String uniqueid){
      this.uniqueid = uniqueid;
   }

   public float getUserfield(){
      return userfield;
   }

   public void setUserfield(float userfield){
      this.userfield = userfield;
   }
}
