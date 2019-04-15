/**
 * 
 */
package org.stfc.repository.impl;

import java.util.List;

import org.stfc.dto.Lecturers;

/**
 * @author viett
 *
 */

public interface LecturersCustomerRepository {
	List<Lecturers> findByFullNameAndGender(String fullName, String gender);

	List<Lecturers> onSearch(String fullName, String gender, String phone, String email, Long deprtId, Long postionId,
			Integer status);

}
