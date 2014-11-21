package com.scu.Config;

public class SignalSourceConfig extends ConfigFather{
	private static SignalSourceConfig instance = null;
	private boolean  lamp_near_reversal=false;//signal_lamphighbeam signal_lampnear
	private boolean  lamp_far_reversal=false;//signal_lamphighbeam 
	private boolean  handBreak_reversal=false;//signal_handbrake
	private boolean  lamp_fog_reversal=false;//signal_lampfog
	private boolean  footBreak_reversal=false;//signal_lampbrake
	private SignalSourceConfig()
	{
		
	}
	public static SignalSourceConfig getInstance(){
		if(instance == null){
			instance = new SignalSourceConfig();
		}
		return instance;
	}
	public boolean isLamp_near_reversal() {
		 String res=super.dbHelper.QureyConfig("signal_lampnear");
			if(Integer.parseInt(res)==0)
			{
				return false;
			}
			else
			 return true;
	}
	public void setLamp_near_reversal(boolean lamp_near_reversal) {
		String pValue="";
		if(lamp_near_reversal)
		{
			pValue="1";
		}
		else
		{
			pValue="0";
		}
		dbHelper.updateConfig("signal_lampnear", pValue);
		this.lamp_near_reversal = lamp_near_reversal;
	}
	public boolean isLamp_far_reversal() {
		String res=super.dbHelper.QureyConfig("signal_lamphighbeam");
		if(Integer.parseInt(res)==0)
		{
			return false;
		}
		else
		 return true;
	}
	public void setLamp_far_reversal(boolean lamp_far_reversal) {
		String pValue="";
		if(lamp_far_reversal)
		{
			pValue="1";
		}
		else
		{
			pValue="0";
		}
		dbHelper.updateConfig("signal_lamphighbeam", pValue);
		this.lamp_far_reversal = lamp_far_reversal;
	}
	public boolean isHandBreak_reversal() {
		String res=super.dbHelper.QureyConfig("signal_handbrake");
		if(Integer.parseInt(res)==0)
		{
			return false;
		}
		else
		 return true;
	}
	public void setHandBreak_reversal(boolean handBreak_reversal) {
		String pValue="";
		if(handBreak_reversal)
		{
			pValue="1";
		}
		else
		{
			pValue="0";
		}
		dbHelper.updateConfig("signal_handbrake", pValue);
		this.handBreak_reversal = handBreak_reversal;
	}
	public boolean isLamp_fog_reversal() {//signal_lampfog
		String res=super.dbHelper.QureyConfig("signal_lampfog");
		if(Integer.parseInt(res)==0)
		{
			return false;
		}
		else
		 return true;
	}
	public void setLamp_fog_reversal(boolean lamp_fog_reversal) {
		String pValue="";
		if(lamp_fog_reversal)
		{
			pValue="1";
		}
		else
		{
			pValue="0";
		}
		dbHelper.updateConfig("signal_lampfog", pValue);
		this.lamp_fog_reversal = lamp_fog_reversal;
	}
	public boolean isFootBreak_reversal() {
		String res=super.dbHelper.QureyConfig("signal_lampbrake");
		if(Integer.parseInt(res)==0)
		{
			return false;
		}
		else
		 return true;
	}
	public void setFootBreak_reversal(boolean footBreak_reversal) {
		String pValue="";
		if(footBreak_reversal)
		{
			pValue="1";
		}
		else
		{
			pValue="0";
		}
		dbHelper.updateConfig("signal_lampbrake", pValue);
		this.footBreak_reversal = footBreak_reversal;
	}
}
