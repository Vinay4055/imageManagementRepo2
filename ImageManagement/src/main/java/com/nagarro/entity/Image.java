package com.nagarro.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Base64;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Entity
@Table(name = "Images")
@NamedQueries({

		@NamedQuery(name = "Image.findImage", query = "from Image image where image.user.userId = ?1"),
		@NamedQuery(name = "Image.updateImageName", query = "update Image image set image.name = ?1 where image.id = ?2"),
		@NamedQuery(name = "Image.updateImageSource", query = "update Image image set image.image = ?1 where image.id=?2"),
		@NamedQuery(name = "Image.findTotalSizeOfImage", query = "Select  COALESCE( sum(image.size),0.0 ) from Image image where image.user.userId=?1 ") })
public class Image {
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "native")
	private int imageId;
	@Column(name = "imageName", unique = false, nullable = false)
	@Getter
	@Setter
	String name;

	@Setter
	double size;

	@Getter
	@Setter
	 @Basic(fetch = FetchType.LAZY)
	@Lob
	@Column(name = "IMAGE", nullable = false, columnDefinition = "mediumblob")
	private byte[] image;
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "userID")
	private User user;
	@Transient
	private String base64Image;

	public String getBase64Image() {
		base64Image = Base64.getEncoder().encodeToString(this.image);

		return base64Image;
	}

	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
	}

	public double getSize() {
		
		return Math.round((size / (1024 * 1024)) * 100.0) / 100.0;

	}

}
