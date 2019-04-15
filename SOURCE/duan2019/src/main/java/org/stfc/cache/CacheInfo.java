/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.stfc.cache;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.stfc.dto.Departments;
import org.stfc.dto.Positions;
import org.stfc.repository.DepartmentsRepository;
import org.stfc.repository.PositionsRepository;

/**
 *
 * @author viettx
 * @since 01/02/2019
 */
@Component
public class CacheInfo {
	private static final Logger logger = LoggerFactory.getLogger(CacheInfo.class);
	private static CacheInfo instance = null;
	@Autowired
	PositionsRepository positionsRepository;
	@Autowired
	DepartmentsRepository departmentsRepository;
	
	public static Map<Long, Positions> positions;
	
	public static Map<Long, Departments> departments;

	/**
	 * @return the instance
	 */
	public static CacheInfo getInstance() {
		if (instance == null) {
			instance = new CacheInfo();
		}
		return instance;
	}

	/**
	 * Khoi tao cache tai day
	 */
	@PostConstruct
	public synchronized void init() {
		long start = System.currentTimeMillis();
		logger.debug("Init cache: {}", start);
		List<Positions> listPosi = positionsRepository.findAllPositionsActive();
		/**
		 * Convert data from list to map
		 */
		positions = listPosi.stream()
                .collect(Collectors.toMap(Positions::getId, vlstPosition -> vlstPosition));
		
		List<Departments> listDept = departmentsRepository.findAllDepartmentsActive();
		departments = listDept.stream()
                .collect(Collectors.toMap(Departments::getId, vlstDepartments -> vlstDepartments));
		
		logger.debug("Finish cache: {}", (System.currentTimeMillis() - start));
	}

	/**
	 * @return the positions
	 */
	public Map<Long, Positions> getPositions() {
		return positions;
	}

	/**
	 * @return the departments
	 */
	public Map<Long, Departments> getDepartments() {
		return departments;
	}

}
