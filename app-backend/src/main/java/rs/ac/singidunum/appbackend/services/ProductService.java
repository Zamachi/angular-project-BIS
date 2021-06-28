package rs.ac.singidunum.appbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import rs.ac.singidunum.appbackend.entities.ProductEntity;
import rs.ac.singidunum.appbackend.models.ProductModel;
import rs.ac.singidunum.appbackend.repositories.iProductRepository;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

@Service
public class ProductService implements iProductService {
    @Autowired
    private iProductRepository productRepository;
    @Autowired
    private AutoMapperService autoMapperService;

    // allowed poster extensions
    private final Set<String> allowedPosterExtensions = Set.of(
            "png", "jpg", "jpeg", "bmp"
    );

    @Override
    public ProductEntity createProduct(ProductModel productModel, MultipartFile[] file) {

        MultipartFile model, poster;

//        String extension = (fileName.substring(fileName.lastIndexOf(".") + 1)).toLowerCase(Locale.ROOT);

        if (this.allowedPosterExtensions.contains(file[0].getOriginalFilename().substring((file[0].getOriginalFilename().lastIndexOf('.') + 1)).toLowerCase(Locale.ROOT))) {
            poster = file[0];
            model = file[1];
        } else {
            poster = file[1];
            model = file[0];
        }

        // get file extensions
        String posterExtension = poster.getOriginalFilename().substring((poster.getOriginalFilename().lastIndexOf('.') + 1)).toLowerCase(Locale.ROOT);
        String modelExtension = model.getOriginalFilename().substring((model.getOriginalFilename().lastIndexOf('.') + 1)).toLowerCase(Locale.ROOT);

        // slug from model (used for a file name when downloading)
        String slug = productModel.getName().trim().replaceAll("\\s+", "-").toLowerCase();

        // new file names generated via slug and poster/model prefix
        String posterFileName = "poster-" + slug + "." + posterExtension;
        String modelFileName = "model-" + slug + "." + modelExtension;

        // location for a folder
        final Path storageLocation = Paths.get("src/main/resources/assets/" + slug);

        try {
            Files.createDirectories(storageLocation);
        } catch (Exception e) {
            e.printStackTrace();
        }

        productModel.setModelPath(
                ServletUriComponentsBuilder
                        .fromCurrentContextPath()
                        .path("/products/download")
                        .queryParam("fileName", modelFileName)
                        .toUriString());

        productModel.setImagePath(
                ServletUriComponentsBuilder
                        .fromCurrentContextPath()
                        .path("/products/download")
                        .queryParam("fileName", posterFileName)
                        .toUriString());

        productModel.setSlug(slug);

        try {
            this.storeFile(model, storageLocation, modelFileName);
            this.storeFile(poster, storageLocation, posterFileName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return productRepository.insert(autoMapperService.map(productModel, ProductEntity.class));
    }

    @Override
    public List<ProductEntity> findAll() {
        return productRepository.findAll();
    }

    // download file (used for image and model src paths on the frontend)
    @Override
    public ResponseEntity<Resource> download(String fileName, HttpServletRequest request) {
        String slug = fileName.substring(fileName.indexOf("-") + 1, fileName.lastIndexOf("."));

        final Path storageLocation = Paths.get("src/main/resources/assets/" + slug);

        Resource product = this.loadFileAsResource(fileName, storageLocation);

        // if product is null then return http 404
        if (product == null) {
            return ResponseEntity.notFound().build();
        }

        String contentType = null;

        try {
            contentType = request.getServletContext().getMimeType(product.getFile().getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(product);
    }

    // method to store file to a local directory on the server (move file to a server)
    private String storeFile(MultipartFile file, Path storageLocation, String fileName) throws Exception {

        try {
            Path targetLocation = storageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return "File copied";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // method to load a file as a resource (we send this as a http response body later)
    private Resource loadFileAsResource(String fileName, Path storageLocation) {
        try {
            Path filePath = storageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new Exception("Product " + fileName + " not found!");
            }
        } catch (Exception e) {
            return null;
        }
    }
}
