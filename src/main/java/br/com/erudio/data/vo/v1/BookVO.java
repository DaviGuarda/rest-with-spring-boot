package br.com.erudio.data.vo.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@JsonPropertyOrder({"id","author","launchDate","price","title"})
public class BookVO extends RepresentationModel<BookVO> implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	@JsonProperty("id")
	@Mapping("id")
	private Long key;
	private String author;
	private Date launchDate;
	private BigDecimal price;
	private String title;


	public BookVO() {}

	public Long getKey() {
		return key;
	}

	public void setKey(Long key) {
		this.key = key;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getLaunchDate() {
		return launchDate;
	}

	public void setLaunchDate(Date launchDate) {
		this.launchDate = launchDate;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getGender() {
		return title;
	}

	public void setGender(String gender) {
		this.title = gender;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		BookVO personVO = (BookVO) o;
		return Objects.equals(key, personVO.key) && Objects.equals(author, personVO.author) && Objects.equals(launchDate, personVO.launchDate) && Objects.equals(price, personVO.price) && Objects.equals(title, personVO.title);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), key, author, launchDate, price, title);
	}
}