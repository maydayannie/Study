package online.store.controller;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import model.Customer;

@Component
public class OnlineStoreValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return Customer.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Customer dataForm = (Customer)target;
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "hitName", "constrains.notEmpty", new String[] {"Hit Name"});
		if (dataForm.getCusId() == null) {
			errors.reject("action.error.unformal");
			return;
		}
		boolean hasFiles = false;
//		if (!dataForm.getImage().isEmpty()) {
//			hasFiles = true;
//			if (!dataForm.getImage().getContentType().matches("^image.*")) {
//				errors.rejectValue("image", null, "上傳失敗, 廣告圖檔必須是影像檔案!");
//			}
//		}
		
		if (!hasFiles) {
			errors.reject(null, "上傳失敗, 沒有任何上載檔案!");
		}
	}

}
