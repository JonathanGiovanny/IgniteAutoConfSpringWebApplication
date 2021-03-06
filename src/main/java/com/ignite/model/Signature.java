package com.ignite.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ignite.utilities.annotations.IgniteTable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@IgniteTable(cacheName = "SignatureCache")
public class Signature implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2067279529146860257L;

	@Id
	@GeneratedValue
	@Column(name = "SIGNATURE_ID")
	private Long id;

	@Column(name = "SIGNATURE_NAME")
	private String name;

	@Column(name = "SIGNATURE_POINTS")
	private Integer points;
}