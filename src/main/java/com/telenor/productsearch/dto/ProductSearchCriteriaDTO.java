package com.telenor.productsearch.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductSearchCriteriaDTO {

	private String type;

	@Min(0)
	private BigDecimal minPrice;

	@Min(0)
	private BigDecimal maxPrice;

	private String city;

	private String property;

	private String color;

	private Integer minGBLimit;

	private Integer maxGBLimit;

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("ProductSearchCriteriaDTO{");
		sb.append("type='").append(type);
		sb.append(", minPrice=").append(minPrice);
		sb.append(", maxPrice=").append(maxPrice);
		sb.append(", city='").append(city);
		sb.append(", property='").append(property);
		sb.append(", color='").append(color);
		sb.append(", minGBLimit=").append(minGBLimit);
		sb.append(", maxGBLimit=").append(maxGBLimit);
		sb.append('}');
		return sb.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof ProductSearchCriteriaDTO))
			return false;
		ProductSearchCriteriaDTO other = (ProductSearchCriteriaDTO) o;
		boolean typeEquals = (this.type == null && other.type == null)
				|| (this.type != null && this.type.equals(other.type));

		boolean cityEquals = (this.city == null && other.city == null)
				|| (this.city != null && this.city.equals(other.city));

		boolean propertyEquals = (this.property == null && other.property == null)
				|| (this.property != null && this.property.equals(other.property));

		boolean colorEquals = (this.color == null && other.color == null)
				|| (this.color != null && this.color.equals(other.color));

		return this.minPrice.equals(other.minPrice) && typeEquals && cityEquals && propertyEquals && colorEquals
				&& this.maxPrice.equals(other.maxPrice) && this.maxGBLimit.equals(other.maxGBLimit) && this.minGBLimit.equals(other.minGBLimit);
	}

}
