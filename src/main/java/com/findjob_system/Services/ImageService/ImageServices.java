package com.findjob_system.Services.ImageService;

import com.findjob_system.models.Dto.UserDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ImageServices {
    // Save image in a local directory
    public String saveImageToStorage(String uPloadDirectory, MultipartFile imageFile, UserDTO jobSeekerDTO) throws IOException {
        String extension = getFileExtension(imageFile);
//        String path = "/uploads/profile_images/";
        String uniqueImageName = generateFileNameDate() +"_profileImage_"+ jobSeekerDTO.getUserId()+"."+extension;
        Path uploadPath = Path.of(uPloadDirectory);
        Path filePath = uploadPath.resolve(uniqueImageName);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return  uniqueImageName;
    }


    // To view an image
    public byte[] getImage(String imageDirectory, String imageName) throws IOException {
        Path imagePath = Path.of(imageDirectory, imageName);

        if (Files.exists(imagePath)) {
            byte[] imageBytes = Files.readAllBytes(imagePath);
            return imageBytes;
        } else {
            return null; // Handle missing images
        }
    }

    // Delete an image
    public String deleteImage(String imageDirectory, String imageName) throws IOException {
        Path imagePath = Path.of(imageDirectory, imageName);

        if (Files.exists(imagePath)) {
            Files.delete(imagePath);
            return "Success";
        } else {
            return "Failed"; // Handle missing images
        }
    }

    private static String getFileExtension(MultipartFile multipartFile) {
        String originalFilename = multipartFile.getOriginalFilename();
        if (originalFilename != null && originalFilename.contains(".")) {
            return originalFilename.substring(originalFilename.lastIndexOf('.') + 1);
        }
        return "";
    }

    public static String generateFileNameDate() {
        Date currentDate = new Date();

        // Create a SimpleDateFormat object with the desired date format
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy_HH:mm:ss_Z");

        // Format the date without spaces
        String formattedDate = dateFormat.format(currentDate);

        // Combine the formatted date with other information (e.g., user ID)
        String fileName = formattedDate;

        return fileName;
    }

}
