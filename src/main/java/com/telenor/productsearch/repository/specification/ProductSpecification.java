package com.telenor.productsearch.repository.specification;

import com.telenor.productsearch.entity.Product;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

/**
 * Product specification to build custom query dynamically
 */
public class ProductSpecification {

	/**
	 * Get products with given type
	 * @param type
	 * @return
	 */
    public static Specification<Product> withType(String type){
        if (type == null || type.length() == 0) {
            return null;
        } else {
            return (root, query, cb) -> cb.equal(root.get("type"), type);
        }
    }

	/**
	 * Get products greater than or equal to given price
	 * @param minPrice
	 * @return
	 */
    public static Specification<Product> withMinPrice(BigDecimal minPrice ){
        if (minPrice == null ) {
            return null;
        } else {
            return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get("price"), minPrice);
        }
    }

	/**
	 * Get products less than or equal to given price
	 * @param maxPrice
	 * @return
	 */
	public static Specification<Product> withMaxPrice(BigDecimal maxPrice ){
        if (maxPrice == null) {
            return null;
        } else {
            return (root, query, cb) -> cb.lessThanOrEqualTo(root.get("price"), maxPrice);
        }
    }

	/**
	 * Get products in given city
	 * @param cityName
	 * @return
	 */
	public static Specification<Product> withCityName(String cityName){
        if (cityName == null || cityName.length() == 0) {
            return null;
        } else {
            return (root, query, cb) -> cb.equal(root.get("cityName"), cityName);
        }
    }

	/**
	 * Get products with given property type
	 * @param property
	 * @return
	 */
	public static Specification<Product> withProperty(String property){
        if (property == null || property.length() == 0) {
            return null;
        } else {
            return (root, query, cb) -> cb.equal(root.get("property"), property);
        }
    }

	/**
	 * Get products with given color
	 * @param color
	 * @return
	 */
	public static Specification<Product> withColor(String color){
        if (color == null || color.length() == 0) {
            return null;
        } else {
            return (root, query, cb) -> cb.equal(root.get("color"), color);
        }
    }

	/**
	 * Get products has  GB limit greater than or equal to given GB limit
	 * @param minGB
	 * @return
	 */
	public static Specification<Product> withMinGB(Integer minGB){
        if (minGB == null) {
            return null;
        } else {
            return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get("gbLimit"), minGB);
        }
    }

	/**
	 * Get products has GB limit less than or equal to given GB limit
	 * @param maxGB
	 * @return
	 */
	public static Specification<Product> withMaxGB(Integer maxGB){
        if (maxGB == null) {
            return null;
        } else {
            return (root, query, cb) -> cb.lessThanOrEqualTo(root.get("gbLimit"), maxGB);
        }
    }

}
