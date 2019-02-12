package com.symulakr.telegram.bot.model;

import lombok.Data;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity(name = "cdr")
@Data
public class Cdr {

    @Id
    private int id;
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

}
