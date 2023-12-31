package com.exam.controller;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.service.UserService;
import com.exam.service.Impl.EmailSenderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

	@Autowired
	private EmailSenderService service;

	@Autowired
	private UserService userService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	 @Autowired
	    private HttpSession httpSession;

	// creating User
	@PostMapping("/")
	public User createuser(@RequestBody User user) throws Exception {

		user.setProfile("default.png");
		// encodein password with Bcryptpasswpord

		user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));

		Set<UserRole> roles = new HashSet<>();

		Role role = new Role();
		role.setRoleId(45L);
		role.setRoleName("NORMAL");

		UserRole userRole = new UserRole();
		userRole.setUser(user);
		userRole.setRole(role);

		roles.add(userRole);
		return this.userService.createUser(user, roles);
	}

	@GetMapping("/{username}")
	public User getUser(@PathVariable("username") String username) {
		return this.userService.getUser(username);
	}

	@DeleteMapping("/{userId}")
	public void deleteUser(@PathVariable("userId") Long userId) {
		this.userService.deleteUser(userId);
	}

	
	@PostMapping("/sendEmail")
	public ResponseEntity<Map<String, Object>> sendEmail(@RequestBody String email)
	        throws MessagingException {
	    boolean emailExists = service.isEmailExists(email.trim());
	    System.out.println("Value of Boolean " + emailExists);
	    ObjectMapper objectMapper = new ObjectMapper();

	    if (emailExists) {
	        // Get the OTP from the servicefz
	        String otp = service.sendOtpEmail(email);

	        String jsonString = "{\"email\":\"Email sent successfully.\"}";
	        try {
	            Map<String, Object> result = objectMapper.readValue(jsonString,
	                    new TypeReference<Map<String, Object>>() {
	                    });

	            return ResponseEntity.ok(result);
	        } catch (JsonMappingException e) {
	            e.printStackTrace();
	        } catch (JsonProcessingException e) {
	            e.printStackTrace();
	        }
	        return null;
	    } else {
	        String jsonString = "{\"email\":\"Email does not exist\"}";
	        try {
	            Map<String, Object> result = objectMapper.readValue(jsonString,
	                    new TypeReference<Map<String, Object>>() {
	                    });

	            System.out.println("Email does not exist");
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
	        } catch (JsonMappingException e) {
	            e.printStackTrace();
	        } catch (JsonProcessingException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
	}
	
	
	
	@PostMapping("/update-password-and-verify-otp")
    public ResponseEntity<Map<String, String>> updatePasswordAndVerifyOTP(@RequestBody Map<String, String> requestBody) {
        String email = requestBody.get("email");
        String confirmPassword = requestBody.get("confirmPassword");
        String otp = requestBody.get("otp");

        // First, verify the OTP
        boolean isValidOTP = service.verifyOTP(email, otp);

        if (isValidOTP) {
            try {
                service.updatePassword(email, confirmPassword);
                return ResponseEntity.ok(Map.of("message", "Password updated successfully"));
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(Map.of("message", "An error occurred while updating the password."));
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "Invalid OTP"));
        }
    }
	
}
