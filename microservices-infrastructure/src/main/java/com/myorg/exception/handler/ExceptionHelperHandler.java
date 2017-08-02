package com.myorg.exception.handler;

import static com.myorg.infra.util.StringConstants.GENERIC_LOCALE_ERROR;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.myorg.infra.api.elements.MessageModel;
import com.myorg.infra.api.elements.ResponseModel;
import com.myorg.infra.exception.ApplicationException;
import com.myorg.infra.exception.DataNotFoundException;
import com.myorg.infra.exception.DuplicateDataException;
import com.myorg.infra.exception.InvalidDataException;
import com.myorg.infra.exception.ValidationError;
import com.myorg.infra.messageservice.MessageLocalService;

@Transactional(rollbackFor = Exception.class)
@ControllerAdvice
public class ExceptionHelperHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageLocalService messageLocalService;

	@ExceptionHandler({ RuntimeException.class })
	public ResponseEntity<Object> handleError(RuntimeException ex) {
		String message = ex.getMessage();
		if (message == null) {
			if (ex instanceof NullPointerException) {
				message = "Null Pointer Exception occurred.";
			} else {
				message = ex.getCause().toString();
			}
		}
		ResponseModel localizedErrorMessage = resolveLocalizedErrorMessage(ex.getMessage());
		return new ResponseEntity<Object>(localizedErrorMessage, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler({ DataNotFoundException.class })
	public ResponseEntity<Object> handleDataException(DataNotFoundException ex) {

		ResponseModel localizedErrorMessage = resolveLocalizedErrorMessage(ex.getMessage());
		return new ResponseEntity<Object>(localizedErrorMessage, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler({ InvalidDataException.class })
	public ResponseEntity<Object> handleValidationException(InvalidDataException ex) {

		ResponseModel localizedErrorMessage = resolveLocalizedErrorMessage(ex.getMessage());
		return new ResponseEntity<Object>(localizedErrorMessage, HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@ExceptionHandler({ DuplicateDataException.class })
	public ResponseEntity<Object> handleDataException(DuplicateDataException ex) {
		ResponseModel localizedErrorMessage = resolveLocalizedErrorMessage(ex.getMessage());
		return new ResponseEntity<Object>(localizedErrorMessage, HttpStatus.CONFLICT);
	}

	@ExceptionHandler({ ApplicationException.class })
	public ResponseEntity<Object> handleException(ApplicationException ex) {
		ResponseModel localizedErrorMessage = resolveLocalizedErrorMessage(ex.getMessage());
		return new ResponseEntity<Object>(localizedErrorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		BindingResult result = ex.getBindingResult();
		List<FieldError> fieldErrors = result.getFieldErrors();
		ValidationError validationerror = new ValidationError();

		for (FieldError fieldError : fieldErrors) {
			String localizedErrorMessage = localiseErrorMessage(fieldError.getCode());
			validationerror.addFieldError(fieldError.getField(), localizedErrorMessage);
		}

		return new ResponseEntity<Object>(validationerror, headers, status);
	}

	private ResponseModel resolveLocalizedErrorMessage(String fieldErrorCode) {
		String errorMessage = localiseErrorMessage(fieldErrorCode);
		String severity = "Error";
		ResponseModel messages = new ResponseModel();
		List<MessageModel> MessageModelList = new ArrayList<MessageModel>();
		MessageModel messageModel = new MessageModel();
		messageModel.setMessage(errorMessage);
		messageModel.setSeverity(severity);
		MessageModelList.add(messageModel);
		messages.setMessages(MessageModelList);
		messages.setOutcome("Failure");

		return messages;
	}

	private String localiseErrorMessage(String fieldErrorCode) {
		String errorMessage = messageLocalService.getMessage(fieldErrorCode);
		if (errorMessage == null) {
			errorMessage = GENERIC_LOCALE_ERROR;
		}
		return errorMessage;
	}

}
