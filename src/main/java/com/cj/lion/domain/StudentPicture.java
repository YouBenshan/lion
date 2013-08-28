package com.cj.lion.domain;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

import com.cj.domain.IdEntity;

@Setter
@Getter
@Entity
public class StudentPicture extends IdEntity{

	@Size(min=1)
	private String other;
	@Size(min=2)
	private String url;
	
	@Lob
    @Basic(fetch=FetchType.LAZY)
    public byte[] picture;
}
