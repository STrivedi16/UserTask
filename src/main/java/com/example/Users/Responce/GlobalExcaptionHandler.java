package com.example.Users.Responce;

import java.util.NoSuchElementException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExcaptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponce> resourceNotFound(ResourceNotFoundException exception) {
		String message = exception.getMessage();
		ApiResponce apiResponce = new ApiResponce(message, false);
		return new ResponseEntity<ApiResponce>(apiResponce, HttpStatus.NOT_FOUND);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		return new ResponseEntity<Object>(
				new ErrorMessage(ErrorMessageConstant.REQUEST_ERROR, ErrorMessageKey.REQUEST_E0301),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<?> handleNoSuchElements(NoSuchElementException elementException) {
		return new ResponseEntity<>(new ErrorMessage("Something wrong no value is get ", "Error"),
				HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<?> handleNullPointer(NullPointerException exception) {
		return new ResponseEntity<>(new ErrorMessage("Null pointer ", "Error"), HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public ResponseEntity<?> fileSizeException(MaxUploadSizeExceededException exception)
	{
		ApiResponce apiResponce=new ApiResponce();
		apiResponce.setMessage("please derease your file size");
		
		return new ResponseEntity<>(new ErrorMessage(apiResponce.getMessage(), ErrorMessageConstant.FILE_SIZE),HttpStatus.BAD_REQUEST);
		
	}

}
