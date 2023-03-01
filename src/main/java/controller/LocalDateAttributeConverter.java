/**
* @author Elizabeth McCurdy - emccurdy
* CIS 175 - Spring 2023
* Feb 20, 2023
*/
package controller;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Local Date Attributes converter class
 */
@Converter(autoApply = true)
public class LocalDateAttributeConverter implements AttributeConverter<LocalDate, Date> {

	@Override
	public Date convertToDatabaseColumn(LocalDate attribute) {
		// TODO Auto-generated method stub
		return (attribute == null ? null: Date.valueOf(attribute));
	}

	@Override
	public LocalDate convertToEntityAttribute(Date dbData) {
		// TODO Auto-generated method stub
		return (dbData == null ? null: dbData.toLocalDate());
	}

}
