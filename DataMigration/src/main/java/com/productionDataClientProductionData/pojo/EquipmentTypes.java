package com.productionDataClientProductionData.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.productionDataClientProductionData.impl.ApiSuperClass;
@Entity
@Table(name="EquipmentTypes")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id", "value", "machineTypeCode" })
@JsonIgnoreProperties(ignoreUnknown = true)
public class EquipmentTypes implements  ApiSuperClass,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EquipmentTypes() {
		
	}
	

	public EquipmentTypes(String id, String value, String machineTypeCode, Object properties,
			Map<String, Object> additionalProperties) {
		super();
		this.id = id;
		this.value = value;
		this.machineTypeCode = machineTypeCode;
		this.additionalProperties = additionalProperties;
	}


	@Override
	public String toString() {
		return "EquipmentTypes [id=" + id + ", value=" + value + ", machineTypeCode=" + machineTypeCode
				+ ", additionalProperties=" + additionalProperties + "]";
	}

	
//	@SequenceGenerator(name = "generator", sequenceName = "MY_SEQ")@GeneratedValue(generator = "generator", strategy = GenerationType.SEQUENCE)
//	@Column(name = "id", updatable=true, nullable = false)
//	private Long TI;
	
	@Id
	@JsonProperty("id")
	private String id;
	@Column(name="Value")
	@JsonProperty("value")
	private String value;
	@Column(name="MachineTypeCode")
	@JsonProperty("machineTypeCode")
	private String machineTypeCode;
	@Transient
	public final List<String> l1 = new ArrayList<String>();
	
	@Transient 
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("id")
	public String getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(String id) {
		this.id = id;
	}

	

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getMachineTypeCode() {
		return machineTypeCode;
	}

	public void setMachineTypeCode(String machineTypeCode) {
		this.machineTypeCode = machineTypeCode;
	}

	public void setAdditionalProperties(Map<String, Object> additionalProperties) {
		this.additionalProperties = additionalProperties;
	}

	
	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}


	@Override
	public List<String> l1(String nothing) {
		return l1;
	}
	

}