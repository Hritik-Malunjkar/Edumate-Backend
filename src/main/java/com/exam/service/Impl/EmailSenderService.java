package com.exam.service.Impl;

import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.exam.model.User;
import com.exam.repo.UserRepository;

@Service
public class EmailSenderService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
    private BCryptPasswordEncoder passwordEncoder;

	public boolean isEmailExists(String email) {
		// Perform a database query to check if the email exists
		return userRepository.existsByEmail(email);
	}


	public String sendOtpEmail(String email) throws MessagingException {
		UUID uuid = UUID.randomUUID();
		// Extract and format the UUID as a 6-digit OTP.
		String otp = String.format("%06d", Math.abs(uuid.getMostSignificantBits() % 1000000));
		
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		String htmlContent = "OTP: " + otp;

		System.out.println("OTP: " + otp);
		
		 User user = userRepository.findByEmail(email);
         user.setOtp(otp);
         userRepository.save(user);

		helper.setFrom("hrithik.malunjkar.02@gmail.com");
		helper.setTo(email);
		helper.setSubject("OTP From Edumate");
		helper.setText(htmlContent, true);	

		mailSender.send(message);
		System.out.println("Mail Sent..");

		return otp;
	}
	
	
	  public void updatePassword(String email, String confirmPassword) throws Exception {
	        Optional<User> userOptional = Optional.of(userRepository.findByEmail(email));
	        if (userOptional.isPresent()) {
	            User user = userOptional.get();
	            // Update the password in the user object
	            String hashedPassword = passwordEncoder.encode(confirmPassword);
	            user.setPassword(hashedPassword);
	            // Clear the OTP after password update
	            user.setOtp(null);
	            userRepository.save(user);
	        } else {
	            throw new Exception("User not found");
	        }
	    }

	    public boolean verifyOTP(String email, String otp) {
	        Optional<User> userOptional = Optional.of(userRepository.findByEmail(email));
	        if (userOptional.isPresent()) {
	            User user = userOptional.get();
	            String storedOTP = user.getOtp();
	            return storedOTP != null && storedOTP.equals(otp);
	        }
	        return false;
	    }
	
}
