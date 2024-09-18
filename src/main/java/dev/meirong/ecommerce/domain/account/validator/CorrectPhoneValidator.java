package dev.meirong.ecommerce.domain.account.validator;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CorrectPhoneValidator implements ConstraintValidator<CorrectPhoneNumber, String> {
  private final Logger log = LoggerFactory.getLogger(CorrectPhoneValidator.class);

  @Override
  public boolean isValid(String phoneNumber, ConstraintValidatorContext context) {
    PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
    Phonenumber.PhoneNumber phone;

    if (StringUtils.isBlank(phoneNumber)) {
      return true;
    }

    try {
      phone =
          phoneNumberUtil.parse(
              phoneNumber, Phonenumber.PhoneNumber.CountryCodeSource.UNSPECIFIED.name());
      return phoneNumberUtil.isValidNumber(phone);
    } catch (NumberParseException e) {
      log.error("Error parsing phone number: {}", phoneNumber);
      return false;
    }
  }
}
