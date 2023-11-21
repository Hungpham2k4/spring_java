package com.project.shopapp.controllers;

import com.project.shopapp.dtos.ProductDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("${api.prefix}/product")
public class ProductController {
    //Lấy tất cả các sản phẩm
    @GetMapping("")
    public ResponseEntity<String> getAllProduct(
            @RequestParam("page") int page,
            @RequestParam("limit") int limit
    ){
        return ResponseEntity.ok("getAllProduct");
    }

//    Lấy sản phẩm theo id
    @GetMapping("/{productId}")
    public ResponseEntity<String> getProductById(@PathVariable int productId){
        return ResponseEntity.ok("Product with id: " + productId);
    }


    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createCategory(
            @Validated @ModelAttribute ProductDTO productDTO,
//            @RequestPart("file") MultipartFile file,
            BindingResult result
    ){
        try{
            if (result.hasErrors()){
                List<String> ErrorMessage = result.getFieldErrors().stream().map(FieldError::getDefaultMessage).toList();
                return ResponseEntity.badRequest().body(ErrorMessage);
            }
            List<MultipartFile> files = productDTO.getFiles();
            files = files == null ? new ArrayList<MultipartFile>() : files;
            for (MultipartFile file : files){
                    if (file.getSize() == 0){
                        continue;
                    }
//                Kiểm tra kích thước và định dạng file
                    if (file.getSize() > 10 * 1024 * 1024){
//                    Kích thước > 10MB
                        return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body("File is to large! Maximum is 10MB");
                    }
//                Kiểm tra định dạng file
                    String contentType = file.getContentType();
                    if (contentType == null || !contentType.startsWith("image/")){
                        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body("File must be an image");
                    }
//                Lưu file và cập nhật thumbnail trong DTO
                    String filename = storeFile(file); //Thay thế hàm này với code để lưu file
                    //Lưu vào đối tợng product => sẽ làm sau
//                Lưu vào bảng product_images
            }
            return ResponseEntity.ok("Product create successfully ");
        }catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    private String storeFile(MultipartFile file) throws IOException{
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
//        Thêm UUID vào trước tên file để đảm bảo tên file là duy nhất
        String uniqueFilename = UUID.randomUUID().toString() + "_" + filename;
//        Đường dẫn đến thư mục mà bạn muốn lưu file
        java.nio.file.Path uploadDir = Paths.get("uploads");
//        Kiểm tra và tạo thư mục nếu nó không ồn tại
        if (!Files.exists(uploadDir)){
            Files.createDirectories(uploadDir);
        }
//        Đường dẫn đầy đủ đến file
        java.nio.file.Path destination = Paths.get(uploadDir.toString(), uniqueFilename);
//        Sao chép file vào thư mục đích
        Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
        return uniqueFilename;
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable Long id){
        return ResponseEntity.ok("This is updateCategory" + id );
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> DeleteCategory(@PathVariable Long productID){
        return ResponseEntity.ok("Delete product Successfully" + productID);
    }
}


