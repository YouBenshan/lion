package com.cj.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Prize 
{
    private boolean success;
    private int     prizetype;
    private String  state;
    private String  sn;
    private String  prize;
    private String  error;
    private String  msg;

}
