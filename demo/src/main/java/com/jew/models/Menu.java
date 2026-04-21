package com.jew.models;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Menu {
	
	private int MENU_NO;

	private String MENU_NM;

	private String MENU_SRC;

	private int UP_MENU_NO;

	private String REG_ID;

	private Date REG_DT;

	private String UPD_ID;

	private Date UPD_DT;

	private String DEL_YN;

	private int DISP_ORD;

}