package boot.file.upload.exception.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class FileUploadExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ Exception.class, MultipartException.class })
	public ResponseEntity<Object> handleError1(Exception exception, RedirectAttributes redirectAttributes,
			WebRequest webRequest) {
		Map<String, Object> exceptionMap = new HashMap<>();
		exceptionMap.put("statusCode", 500);
		exceptionMap.put("message", exception.getMessage());
		return handleExceptionInternal(exception, exceptionMap, new HttpHeaders(), HttpStatus.BAD_REQUEST,
				webRequest);
	}

	/**
	 * <parent>
	 *	<groupId>org.springframework.boot</groupId>
	 *	<artifactId>spring-boot-starter-parent</artifactId>
	 *	<version>1.3.1.RELEASE</version>
	 * </parent>
	 * Above dependency will add Spring 4.2.3 into classpath
	 * In this case we need to use below exception handler
	 */
	/* Spring < 4.3.5
	@ExceptionHandler(MultipartException.class)
	   public String handleError2(MultipartException e) {
	       return "redirect:/errorPage";
	   }*/
}