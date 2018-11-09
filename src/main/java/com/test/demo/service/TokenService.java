/**
 * 
 */
package com.test.demo.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.demo.model.Token;

/**
 * @author kishoredabi
 *
 */
public interface TokenService extends JpaRepository<Token, Long>{

	Token findByUserid(Long id);
	
}
