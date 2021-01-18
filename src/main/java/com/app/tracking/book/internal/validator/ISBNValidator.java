package com.app.tracking.book.internal.validator;

import java.util.Arrays;
import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ISBNValidator implements ConstraintValidator<ISBNConstraint, String> {

  private static final String ISBN_PATTERN = "^[0-9]{9}-[0-9]{1}$";
  private static final Pattern pattern = Pattern.compile(ISBN_PATTERN);

  private RequestType[] requestTypes;

  @Override
  public void initialize(ISBNConstraint constraintAnnotation) {
    requestTypes = constraintAnnotation.requestType();
  }

  @Override
  public boolean isValid(String isbn, ConstraintValidatorContext constraintValidatorContext) {
    if(checkIfUpdateAndNullValue(isbn)) {
      return true;
    }
    return isIsbnValid(isbn);
  }

  /**
   * Check if request is type of update and isbn value is null.
   * If yes that means we don't pass this value.
   * If no then process further validation.
   * @param isbn
   * @return true if request is update and isbn null, otherwise false.
   */
  private boolean checkIfUpdateAndNullValue(String isbn) {
    return Arrays.stream(requestTypes).anyMatch(requestType -> requestType == RequestType.UPDATE) && isbn == null;
  }

  boolean isIsbnValid(String isbn) {
    if(isbn == null || isbn.length() != 11) {
      return false;
    }
    return isbn.matches(pattern.pattern());
  }

}
