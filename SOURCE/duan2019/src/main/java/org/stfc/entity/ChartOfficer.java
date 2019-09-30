/**
 * 
 */
package org.stfc.entity;

import java.util.List;

/**
 * @author viettx
 *
 */
public class ChartOfficer {
	private List<DataChart> data;
	private List<String> legend;

	/**
	 * @return the legend
	 */
	public List<String> getLegend() {
		return legend;
	}

	/**
	 * @param legend the legend to set
	 */
	public void setLegend(List<String> legend) {
		this.legend = legend;
	}

	/**
	 * @return the data
	 */
	public List<DataChart> getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(List<DataChart> data) {
		this.data = data;
	}

}
