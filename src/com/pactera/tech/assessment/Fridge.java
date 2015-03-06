package com.pactera.tech.assessment;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Java bean class to hold Fridge Items
 * 
 * @author Ibrahim
 * @version 1.0
 * @since 05-Mar-2015
 * */
public class Fridge {

	private int amount;
	private String item;
	private Date use_by_date;
	private String unit;

	/**
	 * @return the unit
	 */
	public String getUnit() {
		return unit;
	}

	/**
	 * @param unit
	 *            the unit to set
	 */
	public void setUnit(String _unit) {
		this.unit = _unit;
	}

	/**
	 * @return the amount
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(int _amount) {
		this.amount = _amount;
	}

	/**
	 * @return the item
	 */
	public String getItem() {
		return item;
	}

	/**
	 * @param item
	 *            the item to set
	 */
	public void setItem(String _item) {
		this.item = _item;
	}

	/**
	 * @return the use_by_date
	 */
	public Date getUse_by_date() {
		return use_by_date;
	}

	/**
	 * @param use_by_date
	 *            the use_by_date to set
	 * 
	 */
	public void setUse_by_date(String _use_by_date) {

		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

		// Date date;
		try {
			Date formattedDate = (Date) formatter.parse(_use_by_date);
			this.use_by_date = formattedDate;
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}
}
